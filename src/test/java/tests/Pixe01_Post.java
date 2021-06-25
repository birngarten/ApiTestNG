package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;

public class Pixe01_Post {

    String endpoint = "https://pixe.la/v1/users";
    Response response;
    JsonPath json;
    Map<String, Object> postMapObject = new HashMap<>();

    @Test
    public void postWithJson(){ //1.YOL
        String postBody ="{\n" +
                "      \"username\":\"123\", \n" +
                "      \"agreeTermsOfService\":\"yes\", \n" +
                "      \"notMinor\":\"yes\"\n" +
                "  }";
        response = given().
                        body(postBody).
                    when().
                    post(endpoint);
        response.prettyPrint();

        json = response.jsonPath();

        String expectedMessageData = "It is necessary to specify the token used for authentication.";
        String actualMessageData = json.getString("message");
        Assert.assertEquals(actualMessageData,expectedMessageData);

        boolean actualResult = json.getBoolean("isSuccess");
        Assert.assertTrue(actualResult==false);

    }

    @Test
    public void postWithMap(){ //2.YOL
        postMapObject.put("username","123");
        postMapObject.put("agreeTermsOfService","yes");
        postMapObject.put("notMinor","yes");

        response = given().
                        body(postMapObject).
                    when().
                        post(endpoint);
        response.prettyPrint();

        json = response.jsonPath();

        String expectedMessageData = "It is necessary to specify the token used for authentication.";
        String actualMessageData = json.getString("message");
        Assert.assertEquals(actualMessageData,expectedMessageData);

        boolean actualResult = json.getBoolean("isSuccess");
        Assert.assertTrue(actualResult==false);

    }



}

/* TC0703
Asagidaki payload ile POST yapildiginda
  {
      "username":"123",
      "agreeTermsOfService":"yes",
      "notMinor":"yes"
  }
Response un asagidaki gibi oldugunu dogrulayiniz
  {
    "message": "It is necessary to specify the token used for authentication.",
    "isSuccess": false
  }
 */