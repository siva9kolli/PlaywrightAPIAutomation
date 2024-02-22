package cpm.pw.apiTests;

import DataManager.ReadJsonData;
import com.pw.automation.BasePlayWright;
import com.pw.utilities.CommonAPICalls;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ReadValueFromPropertiesFile extends BasePlayWright {
    public CommonAPICalls commonAPICalls;

    @BeforeClass
    public void initializeCalls(){
        commonAPICalls = new CommonAPICalls();
    }

    @Test
    public void verifyGetRequestCallAndReadCodeFromJsonFile(){
        String response = commonAPICalls.callGetRequest("https://reqres.in/api/users?page=2");
        int expectedCode = Integer.valueOf(ReadJsonData.readCapabilities("./testData/statusCodes.json","codes","validCode"));

        Assert.assertEquals(commonAPICalls.getStatusCode("https://reqres.in/api/users?page=2"), expectedCode);
    }
}
