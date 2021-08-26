package com.daddyrusher.web.common.http;

/**
 * Ответ сервера
 *
 * @param id идентификатор запроса, UUID
 * @param data отправляемые данные
 */
public record Response(String id, String data) {
}
