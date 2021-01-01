package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pojos.Data;
import pojos.Employee;

import java.io.IOException;

import static io.restassured.RestAssured.*;

public class GetPojo {

    String entpoint = "http://dummy.restapiexample.com/api/v1/employee/1";
    Response response;
    SoftAssert softAssert = new SoftAssert();
    ObjectMapper objectMapper = new ObjectMapper();
    JsonPath json;

    Employee employee;
    Data data;



    @Test
    public void get01() throws IOException {
            /*{
    "status": "success",
    "data": {
        "id": 1,
        "employee_name": "Tiger Nixon",
        "employee_salary": 320800,
        "employee_age": 61,
        "profile_image": ""
    },
    "message": "Successfully! Record has been fetched."
    1. yukaridaki key'lerin karsisindaki datalari birer birer alip, verify ediniz.
}     */
         response = given().
                    when().
                        get(entpoint);
//         response.prettyPrint();

         json = response.jsonPath();
         employee= objectMapper.readValue(response.asString(),Employee.class);

         String statusValue = employee.getStatus();
         softAssert.assertEquals(statusValue,"success");
         int dataIdValue= employee.getData().getId();
         softAssert.assertEquals(dataIdValue,1);
         softAssert.assertEquals(employee.getData().getEmployeeName(),"Tiger Nixon");
         int employeeSalary = employee.getData().getEmployeeSalary();
         softAssert.assertEquals(employeeSalary,320800);
         int employeeAge = employee.getData().getEmployeeAge();
         softAssert.assertTrue(employeeAge==61);
         softAssert.assertEquals(employee.getData().getProfileImage(),"");
         softAssert.assertEquals(employee.getMessage(),"Successfully! Record has been fetched.");
         softAssert.assertAll();

    }
}
