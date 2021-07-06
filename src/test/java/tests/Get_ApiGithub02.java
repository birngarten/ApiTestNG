package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Get_ApiGithub02 {
    String endpoint ="https://api.github.com/orgs/toml-lang";
    Response response;
    JsonPath json;

    public Response getResponse() {
        response = given().
                    when().
                        get(endpoint);
        return response;
    }

    @Test
    public void TC0901(){
//        ""login"" nin ==> ""toml-lang"",
//        ""id""    nin ==> 7966854, oldugunu verify ediniz.
        getResponse();
        json = response.jsonPath();

        Assert.assertEquals(json.get("login"), "toml-lang");
        int id = Integer.parseInt(json.getString("id"));
        Assert.assertEquals(id, 7966854);

    }

    @Test
    public void TC0902(){
        getResponse();
//    ""company"": null,
//    ""blog"": null,
//    ""location"": null,
//    ""email"": null,
//    ""twitter_username"": null,
//Bes(5) tane value'nin null oldugunu assert ediniz
        json = response.jsonPath();
       Map<String,Object> valueList = json.getMap("");
       int count = 0;
        for (Object w: valueList.values()) {
            if (w==null){
                count++;
            }            
        }
//        System.out.println("count = " + count);
        Assert.assertTrue(count==5);
    }

    @Test
    public void TC0903(){
//    olusturma tarihinin (created_at) 2014
//    güncelleme tarihinin(updated_at) 2020 oldugunu dogrulayiniz.
        getResponse();
        json = response.jsonPath();
        String createYear = json.getString("created_at").substring(0,4);
        Assert.assertEquals(createYear,"2014");

        String updatedYear = json.getString("updated_at").substring(0,4);
        Assert.assertEquals(updatedYear,"2020");
    }
}



/* TC0901(), TC0902(), TC0903()
"endpoint'e get request yapildiginda;
1. ""login"" nin ==> ""toml-lang"",
    ""id""       nin ==> 7966854, oldugunu verify ediniz.
2. ""company"": null,
    ""blog"": null,
    ""location"": null,
    ""email"": null,
    ""twitter_username"": null,
Bes(5) tane value'nin null oldugunu assert ediniz
3. olusturma tarihinin (created_at) 2014
    güncelleme tarihinin(updated_at) 2020 oldugunu dogrulayiniz."
 */
