package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.*;

import static io.restassured.RestAssured.*;

public class EC_Bank_Get05 {

    String endpoint = "https://api.exchangeratesapi.io/history?start_at=2018-01-01&end_at=2018-09-01";
    Response response;
    JsonPath json;

    public Response getAllData(){
        response = given().
                when().
                get(endpoint);
        response.prettyPrint();
        return response;
    }

    @Test
    public void TC0503() throws IOException {
        /*"Kullanici ilgili Endpoint ile asagidakilerini sirasiyla yapabilmelidir.
         * Status kodunun 200 oldugunu,
         * ""start_at"" in ==>  ""2018-01-01"",
         * ""base"" in ==>  ""EUR"" oldugunu,
         *  ""end_at"" in ==> ""2018-09-01"" oldugunu ,
         * Belirtilen baslangic ve bitis tarihleri arasindaki dönemde GPD ' nin en yüksek degerine
            "2018-08-28"" döneminde ulastigini ve bunun ise ==> ""GBP"": 0.9068, oldugunu.    */

        getAllData();
        response.
                then().
                assertThat().
                statusCode(200);
        json = response.jsonPath();
        Assert.assertEquals(json.getString("start_at"),"2018-01-01");
        Assert.assertEquals(json.getString("base"),"EUR");
        Assert.assertEquals(json.getString("end_at"),"2018-09-01");

        // 1. Cozum
        Map<String,Float> allDatesRates = json.getMap("rates.2018-08-28");
        Map<String,Float> treeMap = new TreeMap<>(allDatesRates);

        Map<String, Float> sortedMap = new LinkedHashMap<>(); // bos bir Map'a sirasiyla dolmasi icin LinkedHashMap<>()

        treeMap.entrySet().stream().
                sorted(Map.Entry.comparingByValue()).
                forEachOrdered(t->sortedMap.put(t.getKey(),t.getValue()));

        Object [] keyset = sortedMap.keySet().toArray();
        Assert.assertEquals(keyset[0].toString(),"GBP");

        // 1. Cozum
        List<Double> allGPData = new ArrayList<>();
        Map<String, Double> myDataMap = json.getMap("rates");

        for (String w: myDataMap.keySet()){
            allGPData.add(json.getDouble("rates."+w+".GBP"));
        }
        Double data = json.getDouble("rates.2018-08-28.GBP");
        Assert.assertEquals(Collections.max(allGPData),data); // *** Collections.max()

        /*"
  "rates": {
        "2018-08": {
            "GBP": 0.87295,
       ...}
  }
}"*/
    }

    @Test
    public void TC0504() throws IOException {
        /*     "Kullanici ilgili Endpoint ile asagidakilerini sirasiyla yapabilmelidir.
         *      Status kodunun 200 oldugunu,
         *      "2018-01-01"" ile  ""2018-09-01"" tarihleri arasindaki para degerlerinin
                kac dönem halinde incelendigini bulunuz.  (Yani ""2018-01-15"" gibi Yil-Ay-Gun  olarak kac
                dönem halinde incelendigini bulacagiz)
                Ve  bu dönemlerden ""2018-01-15""  'de ==>""CAD"" in ==> 1.5263 civarinda oldugunu,
                ""MGA"" para biriminin ise ==> ""1,9825 civarinda oldugunu dogrulayiniz."	      */
        getAllData().prettyPrint();
        response.
                then().
                assertThat().
                statusCode(200);
        json = response.jsonPath();

        int allDateKeySet = json.getMap("rates").keySet().size();
//        System.out.println(allDateKeySet); // 171
        Assert.assertTrue(allDateKeySet == 171);

//        System.out.println(json.getMap("rates.2018-01-15"));

        float valueCAD = (float) json.getMap("rates.2018-01-15").get("CAD");
        Assert.assertTrue(valueCAD == 1.5263f);

        // MGA diye bir para birimi yok.
        Map<String, Double> allData = json.getMap("rates.2018-01-15");
        Assert.assertFalse(allData.containsKey("MGA"));

    }
}
