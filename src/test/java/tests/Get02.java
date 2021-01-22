package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

public class Get02 {

    String entpoint = "https://restful-booker.herokuapp.com/booking/7";
    Response response;

    @Test
    public void get01(){
        	        /*  Scenario:
            - entPoint:https://restful-booker.herokuapp.com/booking/7
            - content Type ==> ContentType.JSON olsun
            - status codu ; 200 olsun
            - ilk ismi "Mary"
            - soyadi "Brown"
            - totalprice 384
            - depositpaid false
            - "bookingdates": {
                    "checkin": "2019-03-05",
                    "checkout": "2020-02-21"
                                            },olsun         */
        response = given().
                    contentType(ContentType.JSON).
                   when().
                        get(entpoint);

        response.prettyPrint();

//        response.
//                then().
//                assertThat().
//                statusCode(200).
//                body("firstname", Matchers.equalTo("Susan"),
//                        "lastname", Matchers.equalTo("Ericsson"),
//                         "totalprice",Matchers.equalTo(885),
//                        "depositpaid",Matchers.equalTo(false),
//                        "bookingdates.checkin",Matchers.equalTo("2020-06-08"),
//                        "bookingdates.checkout",Matchers.equalTo("2020-06-12"));

        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname", equalTo("Sally"),
                        "lastname", equalTo("Smith"),
                        "totalprice",equalTo(885),
                        "depositpaid",equalTo(false),
                        "bookingdates.checkin",equalTo("2020-06-08"),
                        "bookingdates.checkout",equalTo("2020-06-12"));

    }
}
