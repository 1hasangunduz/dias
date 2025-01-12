package api;

import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiGetBookingTest {


    @Test
    public void testGetBooking() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        given()
                .when()
                .get("/booking/1")
                .then()
                .statusCode(200)
                .body("firstname", equalTo("Sally"));
    }

}
