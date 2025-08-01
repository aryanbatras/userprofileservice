package com.sashel.user_profile_service.utility.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthBridgeService {

    @Autowired
    ObjectMapper objectMapper;

    @Value("${authbridge.api-key}")
    private String apiKey;

    @Value("${authbridge.base-url}")
    private String baseUrl;

    public boolean verifyPan(String panNumber) throws IOException, InterruptedException {
        String url = baseUrl + "/pan/verify?id_number=" + panNumber;
        var request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + apiKey)
                .build();
        var client = HttpClient.newHttpClient();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode jsonNode = objectMapper.readTree(response.body());
        return jsonNode.get("status").asInt() == 1;
    }


    public boolean verifyAadhaar(String aadharNumber) throws IOException, InterruptedException {
        String url = baseUrl + "/aadhaar/verify?id_number=" + aadharNumber;
        var request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + apiKey)
                .build();
        var client = HttpClient.newHttpClient();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode jsonNode = objectMapper.readTree(response.body());
        return jsonNode.get("status").asInt() == 1;
    }

    public boolean verifyGst(String value) throws IOException, InterruptedException {
        String url = baseUrl + "/gst/verify?id_number=" + value;
        var request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + apiKey)
                .build();
        var client = HttpClient.newHttpClient();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode jsonNode = objectMapper.readTree(response.body());
        return jsonNode.get("status").asInt() == 1;
    }

    public boolean verifyBank(String accountNumber, String ifscCode) throws IOException, InterruptedException {
        String url = baseUrl + "/bank/verify?account_number=" + accountNumber + "&ifsc_code=" + ifscCode;
        var request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + apiKey)
                .build();
        var client = HttpClient.newHttpClient();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode jsonNode = objectMapper.readTree(response.body());
        return jsonNode.get("status").asInt() == 1;
    }

    public boolean verifyAddress(String address) throws IOException, InterruptedException {
        String url = baseUrl + "/address/verify?address=" + address;
        var request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + apiKey)
                .build();
        var client = HttpClient.newHttpClient();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode jsonNode = objectMapper.readTree(response.body());
        return jsonNode.get("status").asInt() == 1;
    }
}
