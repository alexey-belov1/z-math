package ru.zmath.rest.controller.alert;

import org.springframework.http.HttpHeaders;


public final class HeaderUtil {

    public static HttpHeaders createSuccessAlert(String entity) {
        HttpHeaders headers = createAlert("success");
        headers.set("x-app-entity", entity);
        return headers;
    }

    private static HttpHeaders createAlert(String status) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-app-alert", status);
        return headers;
    }
}
