package api;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class ApiGetBookingIdsTest {

    @Before
    public void setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }

    @Test
    public void testGetAllBookingIds() {
        given()
                .header("Accept", "application/json")
                .when()
                .get("/booking")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

}
