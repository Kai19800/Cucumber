package APIsteps;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class GenerateTokenSteps {
public static String token;

    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";

    @Given("a JWT is generated")
    public void a_jwt_is_generated() {
        RequestSpecification requestToken = given().header("Content-Type", "application/json").
                body("{\n" +
                        "  \"email\": \"kdc.2727@gmail.com\",\n" +
                        "  \"password\": \"Test@123\"\n" +
                        "}");
        Response response = requestToken.when().post("/generateToken.php");
        token = "Bearer " + response.jsonPath().getString("token");
        System.out.println(token);
    }
}
