package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.Money;
import pojos.Rates;

import java.io.IOException;
import java.util.*;

import static io.restassured.RestAssured.given;

public class EC_Bank_Get02 {
    Response response;
    JsonPath json;
    String endpoint = "https://api.exchangeratesapi.io/2010-01-12";
    Money money;
    Rates rates;
    ObjectMapper objectMapper = new ObjectMapper();

    public Response getResponse(){
        response = given().
                when().
                get(endpoint);
//        response.prettyPrint();
        return response;
    }

    @Test
    public void tc01() throws IOException {
        /*"(Pojo nun pekismesi icin Pojo ile yapilacak)
            Kullanici 2010-01-12 tarihindeki döviz referans oranlarını alabilmali ve
            asagidakilerini sirasiyla yapabilmelidir.
                     * Status kodunun 200 oldugunu,
                     * ""rates"" in icinde  ==> - ""TRY"": 2.1084,
                                                -  ""CZK"": 26.258,
                                                -  ""PLN"": 4.0838, para birmlerinin bulundugunu ve
            karsisinda sözkonusu yukardaki degerlerin oldugunu (verify)
                     * ""base"" in ==>  ""EUR"" oldugunu,
                     * ""date"" in ==>   2010-01-12 ""yil/ay/gun"" tarih zaman
            dilimi oldugunu.(verify)"		   */
        getResponse();
        response.
                then().
                assertThat().
                statusCode(200);

        money = objectMapper.readValue(response.asString(),Money.class);
        Assert.assertTrue(money.getRates().getTRY()==2.1084f);
        Assert.assertTrue(money.getRates().getCZK()==26.258f);
        Assert.assertTrue(money.getRates().getPLN()==4.0838f);
        Assert.assertEquals(money.getBase(),"EUR");
        Assert.assertEquals(money.getDate(),"2010-01-12");

    }

    @Test
    public void tc02() throws IOException {
        /*" * ""EUR"" ya göre en yüksek 3'üncü rate'in (en degerli paranin) ==>""USD""para birimi oldugu ve
        rate'inin ise  1.4481'oldugunu,
- ayrica söz konusu tarihteki  en düsük para biriminin ""HUF"" oldugunu ve degerinin ise 268.18 oldugunu(verify)"*/
        getResponse();

        json = getResponse().jsonPath();
        Map<String,Double> allRates = json.getMap("rates");
        Map<String,Double> treeMap = new HashMap<>(allRates);

        Map<String,Double> sortedMap = new LinkedHashMap<>();

        treeMap.entrySet().stream().
                sorted(Map.Entry.comparingByValue()).
                forEachOrdered(t->sortedMap.put(t.getKey(),t.getValue()));

        System.out.println("sortedMap = " + sortedMap);

        Object [] allCurrencies = sortedMap.keySet().toArray();
        System.out.println("Arrays.toString(allCurrencies) = " + Arrays.toString(allCurrencies));

        String ucuncuParaBirimi = allCurrencies[2].toString();
        System.out.println("ucuncuParaBirimi = " + ucuncuParaBirimi);

        System.out.println("ucuncuParaBirimi degeri = " + sortedMap.get(ucuncuParaBirimi));

        Assert.assertEquals(ucuncuParaBirimi,"USD");

        json = response.jsonPath();

        //buyukten kucuge
//        LinkedHashMap<String, Float> reverseSortedMap = new LinkedHashMap<>();
//        myOrderedRates.entrySet()
//                .stream()
//                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
//                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
//        System.out.println(reverseSortedMap);

    }

}
