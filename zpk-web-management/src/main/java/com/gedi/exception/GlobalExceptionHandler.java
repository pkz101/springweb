package com.gedi.exception;

import com.gedi.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public Result handleBusinessException(BusinessException e) {
        log.warn("Business exception: {}", e.getMessage());
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        log.warn("Duplicate key exception: {}", e.getMessage());
        String message = e.getMessage();
        if (message != null && message.contains("Duplicate entry")) {
            String[] parts = message.split("'");
            if (parts.length >= 2) {
                return Result.error(parts[1] + " already exists");
            }
        }
        return Result.error("Duplicate data already exists");
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error("Unexpected server error", e);
        return Result.error("Server error, please contact the administrator");
    }
}
