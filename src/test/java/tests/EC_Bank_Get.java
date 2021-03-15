package tests;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.Money;
import pojos.Rates;

import java.io.IOException;
import java.util.Map;


import static io.restassured.RestAssured.given;

public class EC_Bank_Get {

    String endpoint = "https://api.exchangeratesapi.io/latest";
    Response response;
    JsonPath json;
    Money money;
    Rates rates;
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void TC0101() throws IOException {
        /*"(Pojo Ile Yapilacak)
            Kullanici aktuel döviz referans oranlarını alabilmali ve asagidakilerini sirasiyla yapabilmelidir.
             * Status kodunun 200 oldugunu,
             * ""rates"" in icinde  ==> - ""CAD"": 1.5394,
                                        -  ""USD"": 1.2127,
                                        -  ""TRY"": 8.5503, para birmlerinin bulundugugunu ve
                karsisinda sözkonusu yukardaki degerlerin oldugunu (verify)
             * ""base"" in ==>  ""EUR"" oldugunu,
             * ""date"" in ==>   Rate'lerin alindigi (testinin yapildigi) gündeki ""yil/ay/gun"" tarih zaman

                dilimi oldugunu.(verify)"			*/

        response = given().
                   when().
                        get(endpoint);
//        response.prettyPrint();
        response.
                then().
                assertThat().
                statusCode(200);

        money = objectMapper.readValue(response.asString(),Money.class);
        System.out.println(money.getRates());


        Assert.assertTrue(money.getRates().getCAD()==1.5418f);
        Assert.assertTrue(money.getRates().getUSD()==1.2108f);
        Assert.assertTrue(money.getRates().getTRY()==8.501f);
        Assert.assertEquals(money.getBase(),"EUR");
        Assert.assertEquals(money.getDate(),"2021-02-12");

    }

    @Test
    public void TC0102() throws IOException {
        /*" * ""EUR"" ya göre en yüksek rate'in (en degerli paranin) ==>""GBP""para birimi oldugu ve
                    rate'in ise  0.8765'oldugunu (verify)"        */
//        money = objectMapper.readValue(response.asString(),Money.class);
        response = given().
                when().
                get(endpoint);
        json = response.jsonPath();
        Map<String,Float> allRates = json.getMap("rates");

        for (String w: allRates.keySet());{

        }




    }
}
