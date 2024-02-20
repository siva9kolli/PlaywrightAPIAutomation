package APITests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.FormData;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.annotations.Test;

import java.io.IOException;

public class XWWWFormTest {

    @Test
    public void postRequest() throws IOException {
        Playwright playwright = Playwright.create();
        APIRequest apiRequest = playwright.request();
        APIRequestContext apiRequestContext = apiRequest.newContext();
        FormData form = FormData.create()
                .set("zone", "Computers")
                .set("cost", "50");

        APIResponse apiResponse = apiRequestContext.post("https://jsonplaceholder.typicode.com/posts",
                RequestOptions.create()
                        .setForm(form)
                        .setHeader("content-type","application/x-www-form-urlencoded;charset=utf-8"));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(apiResponse.body());
        System.out.println(jsonNode.toPrettyString());

    }

    @Test
    public void createTokenUsingXWWWForm() throws IOException {
        Playwright playwright = Playwright.create();
        APIRequest apiRequest = playwright.request();
        APIRequestContext apiRequestContext = apiRequest.newContext();
        FormData form = FormData.create()
                .set("client_id", "xyz")
                .set("client_secret", "abc")
                .set("scope", "url/.default")
                .set("grant_type", "client_credentials");

        APIResponse apiResponse = apiRequestContext.post("url/oauth2/v2.0",
                RequestOptions.create()
                        .setForm(form)
                        .setHeader("content-type","application/x-www-form-urlencoded"));

        System.out.println(apiResponse.text());
        System.out.println(apiResponse.statusText());
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(apiResponse.body());
        System.out.println(jsonNode.toPrettyString());

    }
}
