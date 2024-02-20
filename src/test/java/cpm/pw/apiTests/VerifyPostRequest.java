package cpm.pw.apiTests;

import com.pw.automation.BasePlayWright;
import com.pw.utilities.CommonAPICalls;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class VerifyPostRequest extends BasePlayWright {

    public CommonAPICalls commonAPICalls;

    @BeforeClass
    public void initializeCalls(){
        commonAPICalls = new CommonAPICalls();
    }

    @Test
    public void verifyPostRequest(){
        Map<String, String> payload = new HashMap<>();
        payload.put("name","morpheus");
        payload.put("job", "leader");

        commonAPICalls.callPostRequest("https://reqres.in/api/users", payload);
    }
}
