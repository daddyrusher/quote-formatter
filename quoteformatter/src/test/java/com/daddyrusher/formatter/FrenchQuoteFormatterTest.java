package com.daddyrusher.formatter;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class FrenchQuoteFormatterTest {
    @Inject
    private FrenchQuoteFormatter formatter;

    @BeforeEach
    void setUp() {
    }

    @ParameterizedTest
    @MethodSource("formatDataProvider")
    void format(String input, String expected) {
        //GIVEN

        //WHEN
        String actual = formatter.format(input);

        //THEN
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> formatDataProvider() {
        return Stream.of(
                Arguments.of("\"Some company \"some text\" some work\"", "«Some company «some text» some work»"),
                Arguments.of("\" company \"company name\"\"", "« company «company name»»"),
                Arguments.of("\"company\"", "«company»"),
                Arguments.of("\"company \"companyTwo\"\" blabla \"companyThree\"", "«company «companyTwo»» blabla «companyThree»"),
                Arguments.of("bla \" ooo \"", "bla « ooo »"),
                Arguments.of("\"\"", "«»")
        );
    }
}