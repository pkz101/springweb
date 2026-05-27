package com.gedi.filter;

import com.gedi.utils.CurrentHolder;
import com.gedi.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TokenFilterTest {

    private final TokenFilter tokenFilter = new TokenFilter();

    @AfterEach
    void tearDown() {
        CurrentHolder.remove();
    }

    @Test
    void loginRequestBypassesTokenCheck() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest("POST", "/login");
        MockHttpServletResponse response = new MockHttpServletResponse();
        AtomicBoolean invoked = new AtomicBoolean(false);

        tokenFilter.doFilter(request, response, (req, resp) -> invoked.set(true));

        assertTrue(invoked.get());
        assertEquals(200, response.getStatus());
    }

    @Test
    void protectedRequestWithoutTokenReturnsUnauthorized() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/emps");
        MockHttpServletResponse response = new MockHttpServletResponse();
        AtomicBoolean invoked = new AtomicBoolean(false);

        tokenFilter.doFilter(request, response, (req, resp) -> invoked.set(true));

        assertEquals(401, response.getStatus());
        assertTrue(response.getContentAsString().contains("Missing token"));
        assertFalse(invoked.get());
    }

    @Test
    void bearerTokenSetsAndClearsCurrentUser() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/emps");
        MockHttpServletResponse response = new MockHttpServletResponse();
        request.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + jwtForUser(7));
        AtomicReference<Integer> currentIdInChain = new AtomicReference<>();
        FilterChain chain = (req, resp) -> currentIdInChain.set(CurrentHolder.getCurrentId());

        tokenFilter.doFilter(request, response, chain);

        assertEquals(200, response.getStatus());
        assertEquals(7, currentIdInChain.get());
        assertNull(CurrentHolder.getCurrentId());
    }

    private String jwtForUser(int userId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userId);
        claims.put("username", "tester");
        return JwtUtils.generateJwt(claims);
    }
}
