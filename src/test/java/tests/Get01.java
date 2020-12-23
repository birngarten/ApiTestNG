package tests;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import org.junit.Assert;
import org.testng.annotations.Test;


public class Get01 {

    String entPoint ="https://restful-booker.herokuapp.com/booking";

    Response response;

    @Test
    public void get1(){
        /*
    Entpoint : "https://restful-booker.herokuapp.com/booking"
    bu entpoint'in 5 nolu elemanini alin ve console'da yazdirin
     */
                 response = given().
                            when().
                                get(entPoint.concat("/5")); // 1.yol
// 2.yol       get("https://restful-booker.herokuapp.com/booking/5");
// 3.yol       get(entPoint+"/5")

        response.prettyPrint();
    }

    @Test
    public void get2(){
        /*	Scenario:
	 Endpoint'e : https://restful-booker.herokuapp.com/booking
	 And() Accept Type'i "application/json" dir
	 then() status code 200'dur
	 */
        response = given().
                        accept("application/json").
                  when().get(entPoint);

//      then() status code 200'dur
        response.
                then().
                assertThat().
                statusCode(200);
    }

    @Test
    public void get3(){
        /*	 Negative Scenario:
	 when() Bir GET Request asagida verilen Endpoint’e yollanir
	    https://restful-booker.herokuapp.com/booking/67
	 then() status code 404'dur
	 and() Response body’de “Mark” yok
	 */
        response = given().
                    when().
                        get(entPoint+"/67");
        response.prettyPrint();

        response.
                then().
                assertThat().
                statusCode(404);
        Assert.assertFalse(response.asString().contains("Mark"));

    }
}
