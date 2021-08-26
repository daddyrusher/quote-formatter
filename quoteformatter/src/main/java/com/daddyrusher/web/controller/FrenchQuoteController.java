package com.daddyrusher.web.controller;

import com.daddyrusher.formatter.QuoteFormatter;
import com.daddyrusher.web.common.http.Request;
import com.daddyrusher.web.common.annotation.French;
import com.daddyrusher.web.common.http.Response;
import com.daddyrusher.web.exception.EmptyInputException;
import io.micronaut.core.util.StringUtils;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;

@Controller("/api/v1/frenchquote")
public class FrenchQuoteController {
    private final QuoteFormatter formatter;

    //TODO: when jdk 16 compatibility will be available then replace to @RequiredArgsConstructor
    public FrenchQuoteController(@French QuoteFormatter formatter) {
        this.formatter = formatter;
    }

    @Post
    public Response format(@Body Request request) {
        if (StringUtils.isEmpty(request.input())) {
            throw new EmptyInputException(request.id());
        }

        return new Response(request.id(), formatter.format(request.input()));
    }
}
