package tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class Get03 {

    String entpoint ="http://dummy.restapiexample.com/api/v1/employees";

    Response response;

    @Test
    public void get01(){
        /* Sceneri0:
        1- entpoint ; "http://dummy.restapiexample.com/api/v1/employees"
        2- butun calisanlarin isimlerini aliniz.
        3- yasi 60 ile 70 arasinda olan calisanlarin ismleri
        4- 63 yasindan buyuk olan calisanlarin isimlerini bul
        5- Michael Silva'nin 63 yasindan buyuk oldugunu dogrula
        6- 7. siradaki calisanin ismini consala yazdirin ve assert ediniz
        7- 5. siradaki calisanin maasini al
        8- maasi "345000" den fazla olan iscilerin isimlerini bulunuz ve "Yuri Berry"nin bu gruptan old. dogrulayin

         */

        response = given().
                        accept(ContentType.JSON).
                   when().
                        get(entpoint);

        response.prettyPrint();
        JsonPath json = response.jsonPath();

        List<String> employeesNames = json.getList("data.employee_name");

       System.out.println("Calisanlarin isimleri : "+ employeesNames);

//      3- Yasi 60 ile 70 arasi olan
       List<String> employeesAge = json.getList("data.findAll{Integer.valueOf(it.employee_age)>=60 && Integer.valueOf(it.employee_age)<=70}.employee_age");
        System.out.println("Calisanlarin yaslari : "+ employeesAge);
//      4- 63 yasindan buyuk olan calisanlarin isimlerini bul
        List<String> employeesOver63 = json.getList("data.findAll{Integer.valueOf(it.employee_age)>63}.employee_name");
        System.out.println("calisanlarin yaslari : " +employeesOver63);
////     5- Michael Silva'nin 63 yasindan buyuk oldugunu dogrula
        Assert.assertTrue(employeesOver63.contains("Michael Silva"));
//       6- 7. siradaki calisanin ismini consala yazdirin ve assert ediniz
        String yedinciSiradakiCalisan= json.getString("data[6].employee_name");
        System.out.println("yedinciSiradakiCalisan  : "+ yedinciSiradakiCalisan);
        Assert.assertEquals(yedinciSiradakiCalisan,"Herrod Chandler");
//       7- 5. siradaki calisanin maasini al
        int besinciSiradakiCalisaninMaasi = json.getInt("data[4].employee_salary");
        System.out.println("besinciSiradakiCalisaninMaasi  : "+ besinciSiradakiCalisaninMaasi);
//        8- maasi "345000" den fazla olan iscilerin isimlerini bulunuz ve "Yuri Berry"nin bu gruptan old. dogrulayin
        List<String> maasi345000tenFazlaCalisanlar = json.getList("data.findAll{Integer.valueOf(it.employee_salary)>345000}.employee_name");
        System.out.println("maasi345000tenFazlaCalisanlar : " + maasi345000tenFazlaCalisanlar);
        Assert.assertTrue(maasi345000tenFazlaCalisanlar.contains("Yuri Berry"));

    }

}
