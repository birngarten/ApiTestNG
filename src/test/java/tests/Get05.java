package tests;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.*;
import org.testng.annotations.Test;
import pojos.Data;
import pojos.EmployeeFirst;
import java.io.IOException;
import static io.restassured.RestAssured.*;

public class Get05 {
    String entpoint = "http://dummy.restapiexample.com/api/v1/employee/1";
    Response response;
    EmployeeFirst [] employeeFirst;
    Data[] data;
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

        json = response.jsonPath();

        objectMapper = new ObjectMapper();
        employeeFirst = objectMapper.readValue(response.toString(),EmployeeFirst[].class);
        for (int i=0; i<employeeFirst.length; i++){
            if(employeeFirst[i]!=null){
                System.out.println(employeeFirst[i].getMessage());
            }
        }

    }
}
