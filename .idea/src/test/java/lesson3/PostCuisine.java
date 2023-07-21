package lesson3;


import org.junit.jupiter.api.Test;
import java.util.concurrent.TimeUnit;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PostCuisine extends AbstractTest {
    @Test
    void CuisineGarlickyKale() {
        given()
         .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Garlicky Kale")
                .when()
                .post(getBaseUrl()+"/recipes/cuisine")
                .then()
                .statusCode(200);
    }
    @Test
    void CuisineRedLentilSoup () {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Red Lentil Soup")
                .when()
                .post(getBaseUrl()+"/recipes/cuisine")
                .then()
                .time(lessThan(650L), TimeUnit.MILLISECONDS);
    }
    @Test
    void CuisineChickenFajita () {
        String request = given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Chicken Fajita")
                .when()
                .post(getBaseUrl()+"/recipes/cuisine")
                .jsonPath().toString();

        assertThat(request, notNullValue());
    }
    @Test
    void СuisineDe () {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("language","de")
                .expect()
                .body("confidence", equalTo(0.0F))
                .when()
                .post(getBaseUrl() + "/recipes/cuisine");
    }
    @Test
    void CuisineCaulifloweBrownRiceAndVegetableFriedRice () {
        given()
                .queryParam("apiKey", getApiKey())
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Cauliflower, Brown Rice, and Vegetable Fried Rice")
                .expect()
                .body("cuisine", equalTo("Chinese"))
                .when()
                .post(getBaseUrl() + "/recipes/cuisine");
    }
}
