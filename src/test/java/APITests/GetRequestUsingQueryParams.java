package APITests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

public class GetRequestUsingQueryParams {

    @Test
    public void getRequestCall() throws IOException {
        Playwright playwright = Playwright.create();
        APIRequest apiRequest = playwright.request();
        APIRequestContext apiRequestContext = apiRequest.newContext();

        APIResponse apiResponse = apiRequestContext.get("https://reqres.in/api/users", RequestOptions.create()
                .setQueryParam("page", "2"));

        Map<String, String> map = apiResponse.headers();
        System.out.println("Header values :: "+  map.get("content-type"));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode body = objectMapper.readTree(apiResponse.body());
       // System.out.println("Body :: " + body.toPrettyString());
        System.out.println(apiResponse.status());
        System.out.println("*********************************************************");
       // System.out.println(apiResponse.text());

    }
}
