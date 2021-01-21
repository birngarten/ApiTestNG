package tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;

public class GetJson {

    String entpoint = "http://dummy.restapiexample.com/api/v1/employee/2";
    SoftAssert softAssert = new SoftAssert();
    Response response;
    JsonPath json;

    @Test
    public void test01(){
        /*{
        entpoint = "http://dummy.restapiexample.com/api/v1/employee/2"
    1. Content type'i “ContentType.JSON” olsun
    2. statuscodunun 200 oldugu assert ediniz ve
    3. asagidaki TC'yi pojo class'i ile verilerini alip verify ediniz
    "status": "success",
    "data": {
        "id": 2,
        "employee_name": "Garrett Winters",
        "employee_salary": 170750,
        "employee_age": 63,
        "profile_image": ""
    },
    "message": "Successfully! Record has been fetched." }      */
        response = given().
                        contentType(ContentType.JSON).
                   when().
                        get(entpoint);
        response.prettyPrint();
        response.
                then().
                statusCode(200).
                assertThat();

        json = response.jsonPath();

        String statusValue = json.get("status");
        softAssert.assertEquals(statusValue,"success");

         int dataId = json.get("data.id");
        softAssert.assertTrue(dataId==2);

        String employName = json.getString("data.employee_name");
        softAssert.assertEquals(employName,"Garrett Winters");
        System.out.println(employName);

        int employeeSalary = json.get("data.employee_salary");
        softAssert.assertTrue(employeeSalary==170750);

        int employeeAge = json.getInt("data.employee_age");
        softAssert.assertTrue(employeeAge==63);

        String profileImage = json.getString("data.profile_image");
        softAssert.assertEquals(profileImage,"");

        String messageValue = json.getString("message");
        softAssert.assertEquals(messageValue,"Successfully! Record has been fetched.");
        softAssert.assertAll();

    }
}
