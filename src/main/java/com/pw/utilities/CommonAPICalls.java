package com.pw.utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import com.pw.automation.BasePlayWright;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class CommonAPICalls extends BasePlayWright {
    public APIResponse apiResponse;

    public String callGetRequest(String endPoint) {
        apiResponse = apiRequestContext.get(endPoint);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(apiResponse.body());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Body :: " + jsonNode.toPrettyString());
        System.out.println(apiResponse.status());
        return jsonNode.toPrettyString();
    }

    public int getStatusCode(String endPoint){
        apiResponse = apiRequestContext.get(endPoint);
        System.out.println(apiResponse.status());
        return apiResponse.status();
    }

    public String callPostRequest(String endPoint, Map<String, String> payload){

        apiResponse = apiRequestContext.post(endPoint, RequestOptions.create()
                .setHeader("content-type", "application/json")
                .setData(payload));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(apiResponse.body());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(jsonNode.toPrettyString());
        return jsonNode.toPrettyString();
    }

    public APIResponse getPostRequestResponse(String endPoint, Map<String, String> payload){
            apiResponse = apiRequestContext.post(endPoint, RequestOptions.create()
                    .setHeader("content-type", "application/json")
                    .setData(payload));

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = null;
            try {
                jsonNode = objectMapper.readTree(apiResponse.body());
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(jsonNode.toPrettyString());
            return apiResponse;
    }

    public String callPostRequest(String endPoint, byte[] payload){

        apiResponse = apiRequestContext.post(endPoint, RequestOptions.create()
                .setHeader("content-type", "application/json")
                .setData(payload));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(apiResponse.body());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(jsonNode.toPrettyString());
        return jsonNode.toPrettyString();
    }

    public byte[] getFile(String filePath){
        byte[] fileBytes = null;
        File file = new File(filePath);
        try {
            fileBytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileBytes;
    }
}
