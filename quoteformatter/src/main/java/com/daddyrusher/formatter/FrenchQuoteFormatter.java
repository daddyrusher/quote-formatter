package com.daddyrusher.formatter;

import com.daddyrusher.web.common.annotation.French;
import jakarta.inject.Singleton;

import static io.micronaut.core.util.StringUtils.EMPTY_STRING;
import static io.micronaut.core.util.StringUtils.isEmpty;

/**
 * Преобразователь двойных кавычек в французские
 */
@French
@Singleton
public class FrenchQuoteFormatter implements QuoteFormatter {
    private static final String COMMON_QUOTE = "\"";
    private static final String OPEN_QUOTE = "«";
    private static final String CLOSE_QUOTE = "»";
    private static final String SPACE = " ";

    @Override
    public String format(String input) {
        if (isEmpty(input)) {
            return EMPTY_STRING;
        }

        String[] letters = input.split(EMPTY_STRING);
        var result = new StringBuilder();
        for (var i = 0; i < letters.length; i++) {
            if (COMMON_QUOTE.equals(letters[i])) {
                if (i == 0) {
                    result.append(OPEN_QUOTE);
                } else if (i == letters.length - 1) {
                    result.append(CLOSE_QUOTE);
                } else {
                    if (isAppendOpen(letters[i - 1], letters[i + 1], input, i)) {
                        result.append(OPEN_QUOTE);
                    } else if (isAppendClose(letters[i - 1], letters[i + 1], input, i)) {
                        result.append(CLOSE_QUOTE);
                    }
                }
            } else {
                result.append(letters[i]);
            }
        }

        return result.toString();
    }

    /**
     * Форматирование через регулярные выражение
     *
     * @param input входящие данные
     * @return отформатированная строка
     */
    private String formatByRegexp(String input) {
        return input.replaceAll("\"\\b", OPEN_QUOTE).replaceAll("\"\\B", CLOSE_QUOTE);
    }

    /**
     * Если предыдущий символ пробел или пустой символ
     * и следующий символ пустой или цифра или буква или пробел - заменяем на открывающую французскую
     *
     * @param next  следующий символ относительно целевого
     * @param prev  предыдущий символ относительно целевого
     * @param input входная строка
     * @param i     индекс целевого символа
     * @return результат выполнения условия
     */
    private boolean isAppendOpen(String prev, String next, String input, int i) {
        boolean hasNextLetterOrDigit = Character.isLetterOrDigit(input.charAt(i + 1));
        return (EMPTY_STRING.equals(prev) || SPACE.equals(prev)) &&
                (hasNextLetterOrDigit || EMPTY_STRING.equals(next) || SPACE.equals(next));
    }

    /**
     * Если символ обрамлён пробелом
     * или предыдущий символ цифра или буква
     * или следующий символ цифра или буква
     * или следующий символ пробел - заменяем на открывающую французскую
     *
     * @param next  следующий символ относительно целевого
     * @param prev  предыдущий символ относительно целевого
     * @param input входная строка
     * @param i     индекс целевого символа
     * @return результат выполнения условия
     **/
    private boolean isAppendClose(String prev, String next, String input, int i) {
        boolean hasNextLetterOrDigit = Character.isLetterOrDigit(input.charAt(i + 1));
        boolean hasPrevLetterOrDigit = Character.isLetterOrDigit(input.charAt(i - 1));

        return hasEmptySymbolAround(prev, next) ||
                hasPrevLetterOrDigit ||
                hasNextLetterOrDigit ||
                SPACE.equals(next);
    }

    /**
     * Обрамлён ли целевой символ пустыми символами
     *
     * @param prev предыдущий символ относительно целевого
     * @param next следующий символ относительно целевого
     * @return результат выполнения условия
     */
    private boolean hasEmptySymbolAround(String prev, String next) {
        return EMPTY_STRING.equals(prev) || EMPTY_STRING.equals(next);
    }
}
