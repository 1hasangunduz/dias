package api;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class ApiCreateBookingTest {

    @Before
    public void setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }

    @Test
    public void testCreateBooking() {
        String requestBody = "{\"firstname\": \"Jim\", \"lastname\": \"Brown\", \"totalprice\": 111, \"depositpaid\": true, \"bookingdates\": {\"checkin\": \"2018-01-01\", \"checkout\": \"2019-01-01\"}, \"additionalneeds\": \"Breakfast\"}";

        given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(requestBody)
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .body("booking.firstname", equalTo("Jim"))
                .body("booking.lastname", equalTo("Brown"))
                .body("booking.totalprice", equalTo(111))
                .body("booking.depositpaid", equalTo(true))
                .body("booking.bookingdates.checkin", equalTo("2018-01-01"))
                .body("booking.bookingdates.checkout", equalTo("2019-01-01"))
                .body("booking.additionalneeds", equalTo("Breakfast"))
                .body("bookingid", notNullValue());
    }
}
