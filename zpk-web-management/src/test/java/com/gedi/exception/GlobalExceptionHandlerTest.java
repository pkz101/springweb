package com.gedi.exception;

import com.gedi.pojo.Result;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DuplicateKeyException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void businessExceptionReturnsOriginalMessage() {
        Result result = handler.handleBusinessException(new BusinessException("invalid department"));

        assertEquals(0, result.getCode());
        assertEquals("invalid department", result.getMsg());
    }

    @Test
    void duplicateKeyExceptionExtractsDuplicateValueWhenPossible() {
        Result result = handler.handleDuplicateKeyException(
                new DuplicateKeyException("Duplicate entry 'admin' for key 'emp.username'")
        );

        assertEquals(0, result.getCode());
        assertEquals("admin already exists", result.getMsg());
    }
}
