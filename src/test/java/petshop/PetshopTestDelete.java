package petshop;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.pet.Category;
import models.pet.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PetshopTestDelete {

    @Test
    public void testDelete200() {
        Pet myPet = new Pet();
        myPet.setId(500);
        myPet.setName("Spirit");
        Category category = new Category();
        category.setName("Horse");
        myPet.setCategory(category);
        myPet.setStatus("available");

        RestAssured.given().contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(myPet)
                .post("https://petstore.swagger.io/v2/pet")
                .then().statusCode(200).extract().as(Pet.class);

        RestAssured.baseURI = "https://petstore.swagger.io/v2/pet";

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .delete("500");

        int statusCode = response.getStatusCode();
        Assertions.assertEquals(statusCode, 200);

        Response response2 = RestAssured.given()
                .contentType(ContentType.JSON)
                .get("500");

        statusCode = response2.getStatusCode();
        Assertions.assertEquals(statusCode, 404);
    }

    @Test
    public void testDelete404() {
        Pet myPet = new Pet();
        myPet.setId(500);
        myPet.setName("Spirit");
        Category category = new Category();
        category.setName("Horse");
        myPet.setCategory(category);
        myPet.setStatus("available");

        RestAssured.given().contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(myPet)
                .post("https://petstore.swagger.io/v2/pet")
                .then().statusCode(200).extract().as(Pet.class);

        RestAssured.baseURI = "https://petstore.swagger.io/v2/pet";

        Response response = RestAssured.given().contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .delete("4569332144");

        int statusCode = response.getStatusCode();
        Assertions.assertEquals(statusCode, 404);
    }
}
