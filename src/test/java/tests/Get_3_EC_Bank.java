package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.Money;
import pojos.Rates;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class Get_3_EC_Bank {

    Response response;
    JsonPath json;
    Money money;
    Rates rates;
    ObjectMapper objectMapper = new ObjectMapper();
    String endpoint = "https://api.exchangeratesapi.io/2021-02-25/?base=USD";
    /*" Kullanici 2021-02-25 tarihindeki döviz referans oranlarını alabilmali ve
        asagidakilerini sirasiyla yapabilmelidir.
         * Status kodunun 200 oldugunu,
         * ""rates"" in icinde  ==> - ""EUR"": 0.81799591,
                                    -  ""TRY"": 7.2265030675;
                                    -  ""RUB"": 73.9074846626, para birmlerinin bulundugunu ve
            karsisinda sözkonusu yukardaki degerlerin oldugunu (verify)
         * ""base"" in ==>  ""USD"" oldugunu,
         * ""date"" in ==>   2021-02-25 ""yil/ay/gun"" tarih zaman
    dilimi oldugunu.(verify)"	 */

    @Test
    public void tc01() throws IOException {
        response = given().
                        contentType(ContentType.JSON).
                    when().
                        get(endpoint);
        response.prettyPrint();

        response.
                then().
                assertThat().
                statusCode(200);
        money = objectMapper.readValue(response.asString(),Money.class);

        Assert.assertTrue(money.getRates().getEUR() == 0.81799591f);
        Assert.assertTrue(money.getRates().getTRY() == 7.2265030675f);
        Assert.assertTrue(money.getRates().getRUB() == 73.9074846626f);

        Assert.assertEquals(money.getBase(),"USD");
        Assert.assertEquals(money.getDate(),"2021-02-25");
    }
}
