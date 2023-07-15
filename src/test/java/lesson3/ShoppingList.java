package lesson3;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;



public class ShoppingList extends AbstractTest {

    @Test
    void addMealTest()
    {
        String responseID =
                given()
                        .queryParam("apiKey", getApiKey())
                        .queryParam("hash", getHash())
                        .pathParams("user_name", getUserName())
                        .body("{\n"
                                + " \"item\": \"1 package baking powder\",\n"
                                + " \"aisle\": \"Baking\",\n"
                                + " \"parse\": true\n"
                                + "}"
                        )
                        .log().all()
                        .when()
                        .post(getBaseUrl() + "/mealplanner/{user_name}/shopping-list/items")
                        .prettyPeek()
                        .jsonPath()
                        .get("id")
                        .toString();


        given()
                .queryParam("apiKey", getApiKey())
                .pathParams("user_name", getUserName())
                .queryParam("hash", getHash())
                .pathParams("id", responseID)
                .log().all()
                .when()
                .delete(getBaseUrl() + "/mealplanner/{user_name}/shopping-list/items/{id}")
                .prettyPeek();
    }

}
