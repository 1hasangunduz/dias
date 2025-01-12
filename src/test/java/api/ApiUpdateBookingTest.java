package api;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class ApiUpdateBookingTest {

    @Before
    public void setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }

    @Test
    public void testUpdateBooking() {
        String requestBody = "{\"firstname\": \"James\", \"lastname\": \"Brown\", \"totalprice\": 111, \"depositpaid\": true, \"bookingdates\": {\"checkin\": \"2018-01-01\", \"checkout\": \"2019-01-01\"}, \"additionalneeds\": \"Breakfast\"}";

        given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=") // Basic Auth kullanıldı
                .body(requestBody)
                .when()
                .put("/booking/1")
                .then()
                .statusCode(200)
                .body("firstname", equalTo("James"))
                .body("lastname", equalTo("Brown"))
                .body("totalprice", equalTo(111))
                .body("depositpaid", equalTo(true))
                .body("bookingdates.checkin", equalTo("2018-01-01"))
                .body("bookingdates.checkout", equalTo("2019-01-01"))
                .body("additionalneeds", equalTo("Breakfast"));
    }
}
