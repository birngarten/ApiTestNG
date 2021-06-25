package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Trivia02 {

    String endpoint = "https://opentdb.com/api.php?amount=10";
    Response response;
    JsonPath json;

    public Response getResponse(){
        response = given().
                   when().
                        get(endpoint);
        response.prettyPrint();

        return response;
    }

    @Test
    public void TC0201(){
        /* Base enpointe get request yapildiginda;
           status kodu 200 ve body nin bos oldugunu dogrulayiniz.
         */
        String baseEndpoint = "https://opentdb.com/api.php";
        response = given().
                when().
                get(baseEndpoint);

        response.
                then().
                assertThat().
                statusCode(200);
        json = response.jsonPath();

        String isBodyEmpty = response.getBody().asString();
        Assert.assertEquals(isBodyEmpty,"");
    }
    @Test
    public void TC0202(){
        /*Base endpointe amount key ve 10 value su ile query yapildiginda
              responsta toplam 10 adet sorunun oldugunu dogrulayiniz.
              category ve difficulty degerlerinin birbirinden farkli degrler geldigini dogrulayiniz.
              Tum sorularin coktan secmeli olarak geldigini dogrulayiniz.
         */
        getResponse();
        json = response.jsonPath();

        List<String> endpointList = json.getList("results");
        Assert.assertTrue(endpointList.size()==10);

        List<String> categoryList = json.getList("results.category");
        List<String> difficultyList = json.getList("results.difficulty");
        Assert.assertNotEquals(categoryList.get(0),difficultyList.get(0)); // ilk elemanlarinin ayni olmadigi yeterli

        List<String> typeSize = json.getList("results.type");
        Assert.assertTrue(typeSize.size()>1);
    }
    @Test
    public void TC0203(){
        /*Base endponte (amount key, value 10) ve (category key value 27) query paramlari ile get request yapildiginda
         * Gelen sorularin categorisinin Animals oldugunu dogrulayiniz
         * Gelen sorularin typenin birbirinden farkli oldugunu dogrulayniz.
         */

    }

    @Test
    public void TC0204(){
        /*Base endpointe (amount key, value 10) ve (type key , value boolean)
            query paramlari ile get request yapildiginda
         *  responsta toplam 10 adet sorunun oldugunu dogrulayiniz
         * gelen sorularin typenin boolean oldugunu dogrulayiniz. "
         */

    }

}
