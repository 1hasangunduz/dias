package api;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiDeleteBookingTest {

    @Before
    public void setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }

    @Test
    public void testDeleteBooking() {
        given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .when()
                .delete("/booking/1")
                .then()
                .statusCode(201);
    }
}
