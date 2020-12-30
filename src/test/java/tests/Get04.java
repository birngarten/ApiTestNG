package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.DatabaseConnector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class Get04 {
//    ==> Bu class'ta De-Serialization yapilacak

    SoftAssert softAssert = new SoftAssert();
    Response response;
    String entpoint = "http://dummy.restapiexample.com/api/v1/employees";
    JsonPath json;

    @Test
    public void get01(){
        /*{ 1.entpoint : "http://dummy.restapiexample.com/api/v1/employees"
            2. Asagida verilen datalari sirayla verify ediniz.
            "id": "17",
            "employee_name": "Paul Byrd",
            "employee_salary": "725000",
            "employee_age": "64",
            "profile_image": ""        },
            3.tum employee_name'leri aliniz ve "Sonya Frost" isminin yer aldigini verify ediniz
            4. employee_salary'si 300.000 den fazla kac kisi oldugunu bulup, verify ediniz
            5. employee_age'i 60'dan buyuk kac kisi oldugunu bulup, verify ediniz
        */
        response = given().
                    when().
                        get(entpoint);
//        response.prettyPrint();

        json = response.jsonPath();
//        System.out.println(json.getList("data"));
        List<Map<String,String>> employeeList = json.getList("data");  // ==> De-Serilization
//        System.out.println(employeeList);

//        Assert.assertTrue(employeeList.size()==24); kac kisinin oldugunu bulduk ve verify ettik

        softAssert.assertEquals(employeeList.get(16).get("id"),"17");
        softAssert.assertEquals(employeeList.get(16).get("employee_name"),"Paul Byrd");
        softAssert.assertTrue(employeeList.get(16).get("employee_salary").equals("725000"));
        softAssert.assertTrue(employeeList.get(16).get("employee_age").equals("64"));
        softAssert.assertTrue(employeeList.get(16).get("profile_image").equals(""));
        softAssert.assertAll();

//        3.tum employee_name'leri aliniz ve "Sonya Frost" isminin yer aldigini verify ediniz
        List<String> nameList = new ArrayList<>();
        List<String> salaryList = new ArrayList<>();
        List<String> ageList = new ArrayList<>();

        for(Map<String,String> w: employeeList){
            nameList.add(w.get("employee_name"));
            salaryList.add(w.get("employee_salary"));
            ageList.add(w.get("employee_age"));
        }
//        System.out.println(nameList);
        System.out.println(ageList);
        Assert.assertTrue(nameList.contains("Sonya Frost"));

//        4. employee_salary'si 300.000 den fazla kac kisi oldugunu bulup, verify ediniz
        int count =0;
        for (String w: salaryList){
            int a = Integer.parseInt(w); //1.yol Strint to Integer
            if (a>300000){
//                System.out.println(a);
                count++;
//            int b = Integer.valueOf(w); //2.yol Strint to Integer
            }
        }
//        System.out.println(count); // 11
        softAssert.assertEquals(count,11);
        softAssert.assertAll();

//        5. employee_age'i 60'dan buyuk kac kisi oldugunu bulup, verify ediniz
        int countAge = 0;
        for (String altmisYasUstu: ageList){
            int stringToInteger = Integer.valueOf(altmisYasUstu);
            if (stringToInteger>60){
                countAge++;
            }
        }
        System.out.println(countAge);
        softAssert.assertTrue(countAge==6);
        softAssert.assertAll();
        }
    }


