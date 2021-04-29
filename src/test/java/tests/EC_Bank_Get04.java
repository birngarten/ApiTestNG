package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.Money;

import java.io.IOException;

import static io.restassured.RestAssured.*;

public class EC_Bank_Get04 {

    Response response;
    JsonPath json;
    ObjectMapper objectMapper = new ObjectMapper();
    Money money;
    String endpoint = "https://api.exchangeratesapi.io/latest?symbols=USD,GBP";


    /*Kullanici ilgili Endpoint ile asagidakilerini sirasiyla yapabilmelidir.
         * Status kodunun 200 oldugunu,
         * ""rates"" in icinde  ==> - ""USD"": 1.1969,
                                   -  ""GPD"": 0.8567
           para birmlerinin bulundugunu ve  karsisinda sözkonusu
           yukardaki degerlerin güncel halinin bulundugunu (verify)
         * ""base"" in ==>  ""USD"" oldugunu,(verify)"	     */
    @Test
    public void TC0401() throws IOException {
        response = given().
                    when().
                        get(endpoint);
        response.prettyPrint();

        response.
                then().
                assertThat().
                statusCode(200);
        money = objectMapper.readValue(response.asString(),Money.class);

        Assert.assertTrue(money.getRates().getUSD() >1.193);
        Assert.assertTrue(money.getRates().getGBP() >0.858);

        json = response.jsonPath();
        String expectedValue = json.getString("base");
        Assert.assertNotEquals(expectedValue,"USD");

    }

    @Test
    public void TC0402() throws IOException {
        /*"Kullanici ilgili Endpoint ile asagidakilerini sirasiyla yapabilmelidir.
         * Status kodunun 200 oldugunu,
         * ""start_at"" in ==>  ""2018-01-01"",
         * ""base"" in ==>  ""EUR"" oldugunu,
         *  ""end_at"" in ==> ""2018-09-01"" oldugunu (verify)"

         */
        String endpoint = "https://api.exchangeratesapi.io/history?start_at=2018-01-01&end_at=2018-09-01";

        response = given().when().get(endpoint);
//        response.prettyPrint();

        response.
                then().
                assertThat().
                statusCode(200);

        json = response.jsonPath();
       String star_at = json.getString("start_at");
       Assert.assertEquals(star_at,"2018-01-01");
       Assert.assertEquals(json.getString("base"),"EUR");
       Assert.assertEquals(json.getString("end_at"),"2018-09-01");

    }

}
