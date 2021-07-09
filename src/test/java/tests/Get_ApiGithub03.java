package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.ApiGithub;
import pojos.ApiGithubLicense;
import pojos.ApiGithubOwner;
import java.io.IOException;
import java.util.*;

import static io.restassured.RestAssured.given;

public class Get_ApiGithub03 {
    String endpoint = "https://api.github.com/users";
    String endpoint1 = "https://api.github.com/users/brynary/repos";
    Response response;
    JsonPath json;
    ObjectMapper objectMapper = new ObjectMapper();
    ApiGithub apiGithub;
    ApiGithubLicense license;
    ApiGithubOwner owner;



    @Test
    public void TC1001(){
//         Status codun 200 oldugunu assert ediniz.
//         "html_url" lerin ==> Uniq oldugunu assert ediniz.
        response = given().
                    when().
                        get(endpoint);
//        response.prettyPrint();
        response.
                then().
                assertThat().
                statusCode(200);

        json = response.jsonPath();
        List<String> all_html_url = json.getList("html_url");
//        System.out.println("allDataList = " + all_html_url);
        Set<String> allUniq_html_url = new HashSet<>(all_html_url);
        Assert.assertTrue(all_html_url.size()==allUniq_html_url.size());
            
        }

    @Test
    public void TC1002() throws IOException {
//        Status codun 200 oldugunu assert ediniz.
//      1. get request yapildiginda:
//     - cikan ilk Obje (Data) 'de ==> ""id"" 'nin :4588940 oldugunu,
//     - ""full_name"" in ==> ""brynary/active_admin"" oldugunu,
//     - ""size"" in ==> 3729 oldugunu,
//     - ""archived""==> ""false"" oldugunu assert ediniz."
//
        response = given().
                when().
                get(endpoint1);
//        response.prettyPrint();

        response.
                then().
                assertThat().
                statusCode(200);

        json = response.jsonPath();
        List<String> allIDs = json.getList("id");
        Assert.assertEquals(allIDs.get(0),4588940);

        List<String> full_names = json.getList("full_name");
        Assert.assertEquals(full_names.get(0),"brynary/active_admin");

//        List<String> allSizes = json.getList("size");
//        Assert.assertEquals(allSizes.get(0),3729);

        List<String> allArchived= json.getList("archived");
        Assert.assertEquals(allArchived.get(0),false);

//        apiGithub = objectMapper.readValue(response.asString(),ApiGithub.class);
//        System.out.println("apiGithub = " + apiGithub);

    }

    @Test
    public void TC1003() {
//       - cikan ilk Obje (Data) 'de ==> ""owner"" 'in bodysinde ""login"" 'in ==>""brynary"" oldugunu,
//     - ""node_id"" in ==>28 karakterden olustugunu ve icinde  en az bir büyük harf ve bir numara bulundugunu assert ediniz.
        response = given().
                when().
                get(endpoint1);
//        response.prettyPrint();

        response.
                then().
                assertThat().
                statusCode(200);
        json = response.jsonPath();
        List<String> ownerLogins = json.getList("owner.login");
        Assert.assertEquals(ownerLogins.get(0), "brynary");

        List<String> all_node_id = json.getList("node_id");

        String first_node_id = all_node_id.get(0);
        Assert.assertEquals(first_node_id.length(), 28);

        String[] ch_first_node_id = first_node_id.split("");

        String sr = "AB234";
        String [] str = sr.split("");
        int count = 0;
        for (String w : ch_first_node_id) {
             if (w.matches("[A-Z0-9]*")){
                 count++;
             }
        }
        Assert.assertTrue(count > 0);

    }
}


/*
"  Ilgili endpoint'e get request yapildiginda;

  Status codun 200 oldugunu assert ediniz.
   ""html_url"" lerin ==> Uniq oldugunu assert ediniz.
"
"
 Status codun 200 oldugunu assert ediniz.
 1. get request yapildiginda:
     - cikan ilk Obje (Data) 'de ==> ""id"" 'nin :4588940 oldugunu,
     - ""full_name"" in ==> ""brynary/active_admin"" oldugunu,
     - ""size"" in ==> 3729 oldugunu,
     - ""archived""==> ""false"" oldugunu assert ediniz."
"
 Status codun 200 oldugunu assert ediniz.
 1. get request yapildiginda:
     - cikan ilk Obje (Data) 'de ==> ""owner"" 'in bodysinde ""login"" 'in ==>""brynary"" oldugunu,
     - ""node_id"" in ==>28 karakterden olustugunu ve icinde  en az bir büyük harf ve bir numara bulundugunu assert ediniz.
"
 */