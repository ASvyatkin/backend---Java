package lesson3;


import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class GetComplexSearch extends AbstractTest {

    @Test
    void getSearchMaxCalories() {
        given()
                .when()
                .get(getBaseUrl() + "/recipes/complexSearch?" +
                        "maxCalories=500&apiKey=" + getApiKey())
                .then()
                .statusCode(200);
    }

    @Test
    void SearchCuisine() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("cuisine", "italian")
                .pathParam("id", 659109)
                .when()
                .get(getBaseUrl() + "/recipes/{id}/cuisine")
                .then()
                .equals(10);
    }

    @Test
    void SearchMinVitamin() {
        JsonPath response = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("minVitaminB1", "5")
                .queryParam("minVitaminB2", "lesson4/builder")
                .queryParam("minVitaminB5", "10")
                .when()
                .get(getBaseUrl() + "/recipes/complexSearch")
                .body()
                .jsonPath();
        assertThat(response.get("number"), is(10));

    }

    @Test
    void SearchExcludeIngredients() {
        JsonPath response = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("excludeIngredients", "tomato")
                .when()
                .get(getBaseUrl() + "/recipes/complexSearch")
                .body()
                .jsonPath();
        assertThat(response.get("number"), is(10));

    }
    @Test
    void SearchIncludeIngredients() {
        JsonPath response = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("includeIngredients", "tomato,cheese,potato")
                .queryParam("minCalories", "100")
                .when()
                .get(getBaseUrl() + "/recipes/complexSearch")
                .body()
                .jsonPath();
        assertThat(response.get("totalResults"), is(12));

    }
}
