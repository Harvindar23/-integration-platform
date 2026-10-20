package com.harvindar.integration.Common.Exception;

import java.time.Instant;

/**
 * Consistent error response body exposed to API clients.
 *
 * Stack traces and internal details are intentionally never included.
 */
public record ApiError(
        Instant timestamp,
        int status,
        String error,
        String message) {

    public static ApiError of(int status, String error, String message) {
        return new ApiError(Instant.now(), status, error, message);
    }
}