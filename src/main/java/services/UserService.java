package services;

import dto.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class UserService {
    private static final String baseUrl = "https://petstore.swagger.io/v2";
    private static final String createUserUrl = "/user";

    RequestSpecification spec;

    public UserService() {
        spec = given()
                .contentType(ContentType.JSON)
                .baseUri(baseUrl);
    }

    public UserService(String customUrl) {
        spec = given()
                .contentType(ContentType.JSON)
                .baseUri(customUrl);
    }

/*    public void exampleTest() {
        given()
                .baseUri("https://petstore.swagger.io")
                .header("Content-Type", "application/json")
                .basePath("/v2")
                .param("id", 2)
                .log().all()
                .body("")
                .when()
                .post()
                .then()
                .log().all();
    }*/

    public Response addUserRequest(User user) {
        return given()
                .spec(spec)
                .with()
                .body(user)
                .when()
                .log().all()
                .post(createUserUrl);
    }
}
