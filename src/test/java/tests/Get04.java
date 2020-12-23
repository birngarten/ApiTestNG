package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
        */
        response = given().
                    when().
                        get(entpoint);
//        response.prettyPrint();

        json = response.jsonPath();
//        System.out.println(json.getList("data"));
        List<Map<String,String>> employeeList = json.getList("data");
//        System.out.println(employeeList.get(16));
        softAssert.assertEquals(employeeList.get(16).get("id"),"17");
        softAssert.assertEquals(employeeList.get(16).get("employee_name"),"Paul Byrd");
        softAssert.assertTrue(employeeList.get(16).get("employee_salary").equals("725000"));
        softAssert.assertTrue(employeeList.get(16).get("employee_age").equals("64"));
        softAssert.assertTrue(employeeList.get(16).get("profile_image").equals(""));
        softAssert.assertAll();

        List<String>  names = new ArrayList<>();
        for (Map<String,String> w: employeeList) {
            names.add(w.get("employee_name"));
        }
//        System.out.println(names);
        Assert.assertTrue(names.contains("Sonya Frost"));

    }

}
