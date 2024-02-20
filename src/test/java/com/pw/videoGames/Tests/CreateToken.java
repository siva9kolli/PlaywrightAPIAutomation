package com.pw.videoGames.Tests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class CreateToken {
       public String token;
    @Test
    public void createTokenAndPassToNextRequest() throws IOException {
        Playwright playwright = Playwright.create();
        APIRequest apiRequest = playwright.request();
        APIRequestContext apiRequestContext = apiRequest.newContext();

        Map<String, String> payload = new HashMap<>();
        payload.put("username","admin");
        payload.put("password", "admin");

        APIResponse apiResponse = apiRequestContext.post("https://videogamedb.uk/api/authenticate", RequestOptions.create()
                .setHeader("content-type", "application/json")
                .setData(payload));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(apiResponse.body());
        System.out.println(jsonNode.get("token"));
        token = jsonNode.get("token").asText();
        //token = token1.substring(1, token1.length() - 1);
        System.out.println(jsonNode.toPrettyString());


    }

    @Test(dependsOnMethods = "createTokenAndPassToNextRequest")
    public void postAVideoGame() throws IOException {
        byte[] fileBytes = null;
        File file = new File("./testData/videoGameData.json");
        fileBytes = Files.readAllBytes(file.toPath());

        Playwright playwright1 = Playwright.create();
        APIRequest apiRequest1 = playwright1.request();
        APIRequestContext apiRequestContext1 = apiRequest1.newContext();
        APIResponse apiResponse1 = apiRequestContext1.post("https://videogamedb.uk/api/videogame", RequestOptions.create()
                .setHeader("Authorization", "Bearer "+ token)
                .setHeader("content-type", "application/json")
                .setData(fileBytes));


        ObjectMapper objectMapper1 = new ObjectMapper();
        JsonNode jsonNode1 = objectMapper1.readTree(apiResponse1.body());

        System.out.println(jsonNode1.toPrettyString());
        System.out.println("category :: " +jsonNode1.get("category"));

    }
}
