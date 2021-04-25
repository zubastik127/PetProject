package petshop;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import models.pet.Category;
import models.pet.Pet;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class PetshopTestPost {

    @Test
    public void testPost200() {
        Pet myPet = new Pet();
        myPet.setId(1000);
        myPet.setName("Kuzya");
        Category category = new Category();
        category.setName("Cat");
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
                .get("https://petstore.swagger.io/v2/pet/1000")
                .then().body("name", Matchers.equalTo("Kuzya"));
    }
}
