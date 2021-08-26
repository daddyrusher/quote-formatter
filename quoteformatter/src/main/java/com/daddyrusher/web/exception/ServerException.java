package com.daddyrusher.web.exception;

/** Общее серверное исключение */
public class ServerException extends RuntimeException {
    private final String requestId;

    public ServerException(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestId() {
        return requestId;
    }
}
