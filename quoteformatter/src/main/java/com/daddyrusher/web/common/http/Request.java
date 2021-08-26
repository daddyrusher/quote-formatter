package com.daddyrusher.web.common.http;

/**
 * Обьект запроса
 *
 * @param id идентификатор запроса, UUID
 * @param input входная строка
 */
public record Request(String id, String input) { }
