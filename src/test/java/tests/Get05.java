package tests;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.Data;
import pojos.Employee;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;

public class Get05 {
    String entpoint = "http://dummy.restapiexample.com/api/v1/employees";
    Response response;
    Employee employee;
    Data data;
    ObjectMapper objectMapper;
    JsonPath json;

    /*"data": [
        {
            "id": "1",
            "employee_name": "Tiger Nixon",
            "employee_salary": "320800",
            "employee_age": "61",
            "profile_image": ""
        }
     */
    @Test
    public void get01() throws IOException {
        response = given().
                    when().
                        get(entpoint);
//        response.prettyPrint();




    }
}
