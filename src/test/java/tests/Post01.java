package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class Post01 {

    String entpoint ="https://gorest.co.in/public-api/users/";
    Map <String, Object> postMapObje = new HashMap<>();

    Response response;

    public void postMap(Map mapbody){
     response = given().
                    contentType(ContentType.JSON).
                    auth().oauth2(ConfigurationReader.getProperty("token")).
                    body(mapbody).
                when().
                    post(entpoint);
    }

    @Test
    public void get(){
        response = given().
                   when().
                        get(entpoint);
        response.prettyPrint();
    }

    @Test
    public void post01(){
        String body = "{\n" +
                "        \"name\": \"Iskenderiye\",\n" +
                "        \"email\": \"demir13@emmerich.info\",\n" +
                "        \"gender\": \"Female\",\n" +
                "        \"status\": \"Active\"\n" +
                "    }";
        response = given().
                        contentType(ContentType.JSON).
                        auth().oauth2(ConfigurationReader.getProperty("token")).
                        body(body).
                   when().
                        post(entpoint);
        response.prettyPrint();

    }

    @Test
    public void post02(){
        String body = "{\n" +
                "        \"name\": \"Iskender\",\n" +
                "        \"email\": \"demiray13@emmerich.info\",\n" +
                "        \"gender\": \"Male\",\n" +
                "        \"status\": \"Active\"\n" +
                "    }";
        response = given().
                contentType(ContentType.JSON).
                auth().oauth2(ConfigurationReader.getProperty("token")).
                body(body).
                when().
                post(entpoint);
        response.prettyPrint();

    }

    @Test
    public void postWithMap01(){          // Bu method postMap() methodunun kalibini kullanarak post islemi yapiyor.
        postMapObje.put("name","Hasan");                // post01() metodu ile ayni isleve sahip, ayni isi yapiyor
        postMapObje.put("email","demof3@emmerich.info");
        postMapObje.put("gender","Male");
        postMapObje.put("status","Active");
        postMap(postMapObje);
        response.prettyPrint();

    }

    @Test
    public void postWithMap02(){
        postMapObje.put("name","Huseyin");                // post01() metodu ile ayni isleve sahip, ayni isi yapiyor
        postMapObje.put("email","demoqal333@emmerich.info");
        postMapObje.put("gender","Male");
        postMapObje.put("status","Active");
        postMap(postMapObje);
        response.prettyPrint();

    }

    }
