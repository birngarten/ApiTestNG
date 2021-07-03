package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

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
        getResponse();
        json = response.jsonPath();
        List<String> totalDataNum = json.get();
//        System.out.println(totalDataNum.size()); 30
        Assert.assertTrue(totalDataNum.size()==30);

    }

    @Test
    public void getTC3(){
//        3. "login" : datalarinin unique oldugunu assert ediniz
        getResponse();
        json = response.jsonPath();
        List<String> loginList = json.getList("login");
        Set<String> loginUniqList = new HashSet<>(loginList);
        Assert.assertTrue(loginList.size()== loginUniqList.size());

    }

    @Test
    public void getTC04(){
//      4.  tum datalarin "type": "User" olmadigini, "site_admin": false oldugunu assert ediniz.
        getResponse();
        json = response.jsonPath();
        List<String> type = json.getList("type");
//        System.out.println(type);
        List<String> site_admin = json.getList("site_admin");

        for (int i=0; i<type.size(); i++){

//            Assert.assertNotEquals(type.get(i),"User");
            Assert.assertEquals(site_admin.get(i),false);

        }
    }

    @Test
    public void getTC05(){
//        5. "id": datalarinin natural order olarak siralandigini assert ediniz.
        getResponse();
        json = response.jsonPath();
        List<Integer> idData = json.getList("id");
        for (int i=0; i<idData.size()-1; i++){
            Assert.assertTrue(idData.get(i)<idData.get(i+1));
        }
    }

    @Test
    public void getTC06(){
//        6."node_id" datalarinin en az bir numerik character icerdigini assert ediniz.
        getResponse();
        json = response.jsonPath();
        List<String> node_idList = json.getList("node_id");

        // Lambda ile
        node_idList.forEach(t -> Assert.assertTrue(t.matches(".*\\d.*")));

        // foreach ile
        for(String w: node_idList){
            Assert.assertTrue(w.matches(".*\\d.*"));
        }
        
        // 3: Yol
        for(String a: node_idList){
            char [] ch = a.toCharArray();
//            System.out.println("Arrays.toString(ch) = " + Arrays.toString(ch));
            int count = 0;
            for (char c: ch){
                if (c>='0' && c<='9'){
                    count++;
                }
            }
            Assert.assertTrue(count>0);
        }
    }
    @Test
    public void getTC07(){
//        7.""gravatar_id":  datalarinin bos ("") oldugunu assert ediniz.
        getResponse();
        json= response.jsonPath();
        List<String> gravatar_idList = json.getList("gravatar_id");

        // foreach ile
        for (String w: gravatar_idList){
            Assert.assertEquals(w,"");
        }
        // lambda ile
        gravatar_idList.forEach(t-> Assert.assertEquals(t,""));

    }

    @Test
    public void tc02(){
        String epoint = "https://api.github.com/orgs/toml-lang";

        response = given().when().get(epoint);
        response.prettyPrint();
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