package com.daddyrusher.formatter;

/** Интерфейс сервиса форматирования строк */
public interface QuoteFormatter {
    /**
     * Форматирование символов входящей строки в конкретный тип
     *
     * @param input входящая строка
     * @return отформатированная строка
     */
    String format(String input);
}
