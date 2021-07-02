package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ApiGithub_Get01 {

    String entpoint = "https://api.github.com/users";
    Response response;
    JsonPath json;

    public Response getResponse(){
        response = given().
                   when().
                        get(entpoint);

        return response;
    }

    @Test
    public void getTC01(){
//        1. status codun 200 oldugunu assert ediniz.
    getResponse();
    response.
            then().
            assertThat().
            statusCode(200);
    }

    @Test
    public void getTC02(){
//        2. toplam veri [] sayisini assert ediniz.
        getResponse().prettyPrint();




    }

    @Test
    public void getTC3(){
//        3. "login" : datalarinin unique oldugunu assert ediniz



    }

    @Test
    public void getTC04(){
//      4.  tum datalarin "type": "User", "site_admin": false oldugunu assert ediniz.


    }

    @Test
    public void getTC05(){
//        5. "id": datalarinin natural order olarak siralandigini assert ediniz.



    }

    @Test
    public void getTC06(){
//        6."node_id" datalarinin en az bir numerik character icerdigini assert ediniz.



    }
    @Test
    public void getTC07(){
//        7.""gravatar_id":  datalarinin bos ("") oldugunu assert ediniz.


    }
}

/*
ilgili end pointe get request yapildiginda
1. status codun 200 oldugunu assert ediniz.
2. toplam veri [] sayisini assert ediniz.
3. "login" : datalarinin unique oldugunu assert ediniz
4.  tum datalarin "type": "User", "site_admin": false oldugunu assert ediniz.
5. "id": datalarinin natural order olarak siralandigini assert ediniz.
6."node_id" datalarinin en az bir numerik character icerdigini assert ediniz.
7.""gravatar_id":  datalarinin bos ("") oldugunu assert ediniz.
 */