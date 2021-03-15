package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.GorestPojo;
import utilities.ConfigurationReader;

import static io.restassured.RestAssured.*;

public class PostWithPojo {

    String entpoint = "https://gorest.co.in/public-api/users/";

    Response response;
    GorestPojo gorestPojo;
    GorestPojo gorestPojo1  = new GorestPojo();

    public void postPojo(Object body){
        response = given().
                        contentType(ContentType.JSON).
                        auth().oauth2(ConfigurationReader.getProperty("token")).
                        body(body).
                   when().
                        post(entpoint);
    }
    @Test
    public void postWithPojosObject(){ // parametreli constructor'dan uretilen obje ile post
        gorestPojo = new GorestPojo("Hasan", "hares16tr5@has.com", "Male", "Active");
        postPojo(gorestPojo);
//        response.prettyPrint();
    }

    @Test
    public void postWithGorestPojo(){ // parametresiz constructor'dan uretilen obje ile post
        gorestPojo1.setName("Huseyin");
        gorestPojo1.setEmail("huseyin22ddduh5g233475@kal.com");
        gorestPojo1.setGender("Male");
        gorestPojo1.setStatus("Active");
        postPojo(gorestPojo1);
//        response.prettyPrint();

    }
}
