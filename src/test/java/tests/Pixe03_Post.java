package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Pixe03_Post {

    String endpoint = "https://pixe.la/v1/users/takip01/graphs/test-graph";
    Response response;
    JsonPath json;

    @Test
    public void postHeaderPayload(){
        String headerBody = "{\n" +
                "\"date\":\"20210625\",\n" +
                "\"quantity\":\"1\"\n" +
                "}";
        response = given().
                        header("X-USER-TOKEN" , "cokgizli").
                        body(headerBody).
                    when().
                        post(endpoint);
        response.prettyPrint();

        json = response.jsonPath();
        Assert.assertEquals(json.getString("message"),"Success.");
        Assert.assertEquals(json.getString("isSuccess"), "true");

    }
}

/*
"""https://pixe.la/v1/users/takip01/graphs/test-graph"" endpointine Post request yapildiginda

Asagidaki header ve payload ile POST yapildiginda
header kismi:
{
   ""X-USER-TOKEN"" : ""cokgizli""
}
payload kismi :
{
""date"":""20210625"",
""quantity"":""1""
}
Response un asagidaki gibi oldugunu dogrulayiniz
{
    ""message"": ""Success."",
    ""isSuccess"": true
}"
 */
