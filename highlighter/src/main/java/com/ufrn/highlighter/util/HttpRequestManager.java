package com.ufrn.highlighter.util;

import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class HttpRequestManager {

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    @SneakyThrows
    public static HttpResponse<String> requestPostOperation (String data, String uri ) {
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(data))
                .setHeader("Content-Type", "application/json")
                .uri(URI.create(uri))
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @SneakyThrows
    public static HttpResponse<String> requestPostOperationWithAuth (String data, String uri, String token ) {
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(data))
                .setHeader("Authorization", token)
                .setHeader("Content-Type", "application/json")
                .uri(URI.create(uri))
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @SneakyThrows
    public static HttpResponse<String> requestGetOperationWithAuth (String uri, String token){
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .setHeader("Authorization", token)
                .setHeader("Content-Type", "application/json")
                .uri(URI.create(uri))
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

}
