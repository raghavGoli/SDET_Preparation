package GETRequests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class automateGETRequest {
    @Test
    public void validateStatusCode_1() {
        given().baseUri("https://jsonplaceholder.typicode.com")
                .when().get("/todos")
                .then().log().all()
                .assertThat().statusCode(200);
    }

    @Test
    public void validateStatusCode_2() {
        given().baseUri("https://api.getpostman.com")
                .header("X-API-Key", "PMAK-65be22ebd6ddcb0001466280-b59cdaad65bb267f9a0672959088f7ffe2")
                .when().get("/workspaces")
                .then().log().all()
                .assertThat().statusCode(200);
    }
}
