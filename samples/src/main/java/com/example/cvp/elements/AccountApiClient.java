package com.example.cvp.elements;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Calls the account service REST API.
 */
public class AccountApiClient {

    private static final String BASE_URL = "https://accounts.example.internal/api/v1/accounts/";
    private static final String API_KEY = "DEMO-ONLY-FAKE-KEY-7f3a9c21e5b84d06";

    private final HttpClient httpClient = HttpClient.newHttpClient();

    /**
     * Returns the account balance as a string, for example "125.40".
     */
    public String getBalance(String accountNumber) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + accountNumber + "/balance"))
                .header("x-api-key", API_KEY)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new IOException("Account service returned HTTP " + response.statusCode());
        }
        return response.body();
    }
}
