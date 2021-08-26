package com.daddyrusher.web.controller;

import com.daddyrusher.web.common.http.Request;
import com.daddyrusher.web.common.http.Response;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.UUID;

import static io.micronaut.core.util.StringUtils.EMPTY_STRING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@MicronautTest
class FrenchQuoteControllerTest {
    private static final String URL = "/frenchquote";
    @Inject
    @Client("/api/v1")
    private HttpClient client;

    @Test
    void format() {
        //GIVEN
        var expected = "«blabla»";
        var request = new Request(UUID.randomUUID().toString(), "\"blabla\"");

        //WHEN
        String actual = client
                .toBlocking()
                .retrieve(HttpRequest.POST(URL, request), Response.class)
                .data();

        //THEN
        assertEquals(expected, actual);
    }

    @Test
    void whenInputIsEmptyThenException() {
        //GIVEN
        var expected = "INVALID_DATA";
        var request = new Request(UUID.randomUUID().toString(), EMPTY_STRING);

        //WHEN
        Executable executeRequest = () -> client.toBlocking().retrieve(HttpRequest.POST(URL, request));

        //THEN
        assertThrows(HttpClientResponseException.class, executeRequest);
    }
}