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

public class PetshopTetPut {

    @Test
    public void testPut200() {
        Pet myPet = new Pet();
        myPet.setId(700);
        myPet.setName("Leonardo");
        Category category = new Category();
        category.setName("turtle");
        myPet.setCategory(category);
        myPet.setStatus("available");

        Pet myPet2 = new Pet();
        myPet2.setId(700);
        myPet2.setName("Donatello");
        Category category2 = new Category();
        category2.setName("turtle");
        myPet2.setCategory(category2);
        myPet2.setStatus("available");

        RestAssured.given().contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(myPet)
                .post("https://petstore.swagger.io/v2/pet")
                .then().statusCode(200).extract().as(Pet.class);

        RestAssured.given().contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(myPet2)
                .put("https://petstore.swagger.io/v2/pet")
                .then().statusCode(200).extract().as(Pet.class);

        RestAssured.given().contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(myPet)
                .get("https://petstore.swagger.io/v2/pet/700")
                .then().body("name", Matchers.equalTo("Donatello"));
    }

    @Test
    public void testPut404() {
        Pet myPet = new Pet();
        myPet.setId(700);
        myPet.setName("Leonardo");
        Category category = new Category();
        category.setName("turtle");
        myPet.setCategory(category);
        myPet.setStatus("available");

        Pet myPet2 = new Pet();
        myPet2.setId(68451);
        myPet2.setName("Donatello");
        Category category2 = new Category();
        category2.setName("turtle");
        myPet2.setCategory(category2);
        myPet2.setStatus("available");

        RestAssured.given().contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(myPet)
                .post("https://petstore.swagger.io/v2/pet")
                .then().statusCode(200).extract().as(Pet.class);

        Response response = RestAssured.given().contentType(ContentType.JSON)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(myPet2)
                .put("https://petstore.swagger.io/v2/pet");

        int statusCode = response.getStatusCode();
        Assertions.assertEquals(statusCode, 404);
    }
}
