package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pojos.Data;
import pojos.Employee;

import java.io.IOException;


import static io.restassured.RestAssured.*;

public class GetPojo2 {

    /*{
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

    String entpoint = "http://dummy.restapiexample.com/api/v1/employee/2";
    SoftAssert softAssert = new SoftAssert();
    Employee employee;
    Data data;
    ObjectMapper objectMapper = new ObjectMapper();
    Response response;
    JsonPath json;

    @Test
    public void get01() throws IOException {

        response = given().
                        contentType(ContentType.JSON).
                   when().
                        get(entpoint);

//        response.prettyPrint();

//        2. statuscodunun 200 oldugu assert ediniz
        response.
                then().
                assertThat().
                statusCode(200);

//        json = response.jsonPath();
        employee = objectMapper.readValue(response.asString(),Employee.class);

//         3. asagidaki TC'yi pojo class'i ile verilerini alip verify ediniz
        String statusText = employee.getStatus();
        softAssert.assertEquals(statusText,"success");
        softAssert.assertTrue(employee.getData().getId()==2);
        softAssert.assertEquals(employee.getData().getEmployeeName(),"Garrett Winters");
        softAssert.assertTrue(employee.getData().getEmployeeSalary()==170750);
        int employee_Age = employee.getData().getEmployeeAge();
        softAssert.assertTrue(employee_Age==63);
        softAssert.assertEquals(employee.getData().getProfileImage(),"");
        softAssert.assertEquals(employee.getMessage(),"Successfully! Record has been fetched.");
        softAssert.assertAll();

    }
}
