package petshop;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.pet.Category;
import models.pet.Pet;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PetshopTestGet {

    @Test
    public void testGet200() {
        Pet myPet = new Pet();
        myPet.setId(400);
        myPet.setName("Drujok");
        Category category = new Category();
        category.setName("Dog");
        myPet.setCategory(category);
        myPet.setStatus("available");

        RestAssured.given().contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(myPet)
                .post("https://petstore.swagger.io/v2/pet")
                .then().statusCode(200).extract().as(Pet.class);

        RestAssured.given().contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(myPet)
                .get("https://petstore.swagger.io/v2/pet/400")
                .then().body("name", Matchers.equalTo("Drujok"));
    }

    @Test
    public void testGet404() {
        Pet myPet = new Pet();
        myPet.setId(400);
        myPet.setName("Drujok");
        Category category = new Category();
        category.setName("Dog");
        myPet.setCategory(category);
        myPet.setStatus("available");

        RestAssured.given().contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(myPet)
                .post("https://petstore.swagger.io/v2/pet")
                .then().statusCode(200).extract().as(Pet.class);

        Response response = RestAssured.given().contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(myPet)
                .get("https://petstore.swagger.io/v2/pet/1256");

        int statusCode = response.getStatusCode();
        Assertions.assertEquals(statusCode, 404);

    }
}
