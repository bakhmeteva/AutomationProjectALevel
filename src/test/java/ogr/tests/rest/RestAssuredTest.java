package ogr.tests.rest;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class RestAssuredTest {

    @Test
    public void testEmojisApiRestAssured() throws Exception {
        RestAssured
                .get("https://api.github.com/emojis")
                .then()
                .assertThat()
                .body("articulated_lorry", is(notNullValue()));

    }
}
