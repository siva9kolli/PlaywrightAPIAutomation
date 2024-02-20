package cpm.pw.apiTests;

import com.microsoft.playwright.APIResponse;
import com.pw.automation.BasePlayWright;
import com.pw.utilities.CommonAPICalls;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class VerifyPostRequestAndItsAttributes extends BasePlayWright {
    public CommonAPICalls commonAPICalls;
    public APIResponse apiResponse;

    @BeforeClass
    public void initializeCalls() {
        commonAPICalls = new CommonAPICalls();
    }

    @Test
    public void verifyPostRequest() {
        Map<String, String> payload = new HashMap<>();
        payload.put("name","morpheus");
        payload.put("job", "leader");

        apiResponse = commonAPICalls.getPostRequestResponse("https://reqres.in/api/users", payload);

    }
}
