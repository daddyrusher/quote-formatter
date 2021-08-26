package com.daddyrusher.web.exception;

public class EmptyInputException extends ServerException {
    public EmptyInputException(String requestId) {
        super(requestId);
    }

    @Override
    public String getMessage() {
        return "Запрос содержит пустые входящие данные!";
    }
}
