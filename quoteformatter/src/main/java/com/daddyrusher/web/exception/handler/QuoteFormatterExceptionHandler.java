package com.daddyrusher.web.exception.handler;

import com.daddyrusher.web.common.http.ErrorResponse;
import com.daddyrusher.web.exception.EmptyInputException;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

@Singleton
@Requires(classes = EmptyInputException.class)
public class QuoteFormatterExceptionHandler implements ExceptionHandler<EmptyInputException, HttpResponse<ErrorResponse>> {
    @Override
    public HttpResponse<ErrorResponse> handle(HttpRequest request, EmptyInputException exception) {
        return HttpResponse.serverError(new ErrorResponse(exception.getRequestId(),"INVALID_DATA", exception.getMessage()));
    }
}
