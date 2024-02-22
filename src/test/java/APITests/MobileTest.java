package APITests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MobileTest {
    public String createDesiredCapabilitiesObject(){
        String message;
        JSONObject desiredCapabilities = new JSONObject();
        JSONObject mobileCapabilities = new JSONObject();
        mobileCapabilities.put("appium:deviceName", "Samsung");
        mobileCapabilities.put("appium:platformVersion", 12.0);
        mobileCapabilities.put("platformName", "Android");
        mobileCapabilities.put("appium:automationName", "UiAutomator2");
        mobileCapabilities.put("app", "/Users/shiva/Desktop/DesktopBackUP/AutomationFramework/PlaywrightAPIAutomation/src/main/resources/NoBroker.apk");
        mobileCapabilities.put("appWaitActivity", "com.wam.android.*");

        desiredCapabilities.put("desiredCapabilities", mobileCapabilities);

        message = desiredCapabilities.toString();
        System.out.println(message);
        return message;
    }
    @Test
    public void launchApplication() throws IOException, InterruptedException {
        String data = "{\n" +
                "\"desiredCapabilities\":{\n" +
                "  \"appium:deviceName\": \"Samsung\",\n" +
                "  \"appium:platformVersion\": \"12.0\",\n" +
                "  \"platformName\": \"Android\",\n" +
                "  \"appium:automationName\": \"UiAutomator2\",\n" +
                "  \"appium:newCommandTimeout\": \"6000\",\n" +
                "  \"app\": \"/Users/shiva/Desktop/DesktopBackUP/AutomationFramework/PlaywrightAPIAutomation/src/main/resources/NoBroker.apk\",\n" +
                "  \"appPackage\": \"com.nobroker.app\",\n" +
                "  \"appWaitActivity\":\"com.nobroker.app.activities.NBLauncherActivity\"\n" +
                "}\n" +
                "}";



        Playwright playwright = Playwright.create();
        APIRequest apiRequest = playwright.request();
        APIRequestContext apiRequestContext = apiRequest.newContext();

        APIResponse sessionResponse = apiRequestContext.post("http://127.0.0.1:4723/wd/hub/session", RequestOptions.create()
                .setHeader("content-type", "application/json")
                .setData(data));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(sessionResponse.body());
        System.out.println(jsonNode.toPrettyString());
        // Get sessionId of Main Session
        System.out.println("sessionId ::" + jsonNode.get("sessionId"));
        String sessionId = jsonNode.get("sessionId").asText();

        Thread.sleep(5000);

        String wuaElementData = "{\n" +
                "        \"using\": \"-android uiautomator\",\n" +
                "        \"value\" : \"new UiSelector().text(\\\"Continue\\\")\"\n" +
                "        }";

        String continueElementData = "{\n" +
                "        \"using\": \"id\",\n" +
                "        \"value\" : \"yesPhoneState\"\n" +
                "        }";

        APIResponse continueButtonResponse = apiRequestContext.post("http://127.0.0.1:4723/wd/hub/session/"+sessionId+"/element", RequestOptions.create()
                .setHeader("content-type", "application/json")
                .setData(wuaElementData));

        JsonNode jsonNode1 = objectMapper.readTree(continueButtonResponse.body());
        System.out.println(jsonNode1.toPrettyString());
        String continueButtonSessionId = jsonNode1.get("value").get("ELEMENT").asText();
        System.out.println("continueButtonSessionId :: " + continueButtonSessionId );


        APIResponse clickContinueButton = apiRequestContext.post("http://127.0.0.1:4723/wd/hub/session/"+sessionId+"/element/"+continueButtonSessionId+"/click", RequestOptions.create()
                .setHeader("content-type", "application/json")
                .setData("{}"));
        JsonNode jsonNode2 = objectMapper.readTree(clickContinueButton.body());
        System.out.println(jsonNode2.toPrettyString());



    }

}
