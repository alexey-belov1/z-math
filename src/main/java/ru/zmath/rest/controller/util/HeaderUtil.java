package ru.zmath.rest.controller.util;

import org.springframework.http.HttpHeaders;

public final class HeaderUtil {

    public static HttpHeaders createSuccessAlert(String entity, String param) {
        HttpHeaders headers = createAlert("success");
        headers.set("x-app-entity", entity);
        headers.set("X-app-param", param);
        return headers;
    }

    private static HttpHeaders createAlert(String status) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-app-alert", status);
        return headers;
    }
}
