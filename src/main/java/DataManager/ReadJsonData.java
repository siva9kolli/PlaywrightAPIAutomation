package DataManager;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

public class ReadJsonData {

    public static String getJsonBody(String filePath) {
        try {
            ObjectMapper m = new ObjectMapper();
            JsonNode rootNode = m.readTree(new File(filePath));
            return rootNode.toPrettyString();
        }catch (Exception e){
            System.err.println("Something wrong with specified file: "+filePath);
            return null;
        }
    }

    public static String readCapabilities(String file, String key, String value) {
        String keyValue = null;
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(file)) {
            Object obj = jsonParser.parse(reader);
            JSONArray dataList = (JSONArray) obj;
            for (Object data : dataList) {
                keyValue = getData((JSONObject) data, key, value);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e1) {
            e1.printStackTrace();
        }
        return keyValue;
    }

    public static String getData(JSONObject dataJson, String key, String value) {

        JSONObject dataObject = (JSONObject) dataJson.get(key);
        String valuePair = (String) dataObject.get(value);
        System.out.println(valuePair);
        return valuePair;
    }
}
