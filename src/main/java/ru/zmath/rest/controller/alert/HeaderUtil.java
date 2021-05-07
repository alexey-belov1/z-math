package ru.zmath.rest.controller.alert;

import org.springframework.http.HttpHeaders;

import java.io.UnsupportedEncodingException;

public final class HeaderUtil {

    public static HttpHeaders createAlert(String message) {
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.set("x-app-alert", new String(message.getBytes(),"ISO-8859-5"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return headers;
    }
}
