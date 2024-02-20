package cpm.pw.apiTests;

import com.pw.automation.BasePlayWright;
import com.pw.utilities.CommonAPICalls;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class VerifyPostRequestUsingJSONFile extends BasePlayWright {

    public CommonAPICalls commonAPICalls;

    @BeforeClass
    public void initializeCalls() {
        commonAPICalls = new CommonAPICalls();
    }

    @Test
    public void verifyPostRequest() {
        commonAPICalls.callPostRequest("https://reqres.in/api/users", commonAPICalls.getFile("./testData/user.json"));

    }
}
