package GETRequests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class extractSingleFieldFromResponse {

    @Test
    public void validateSingleFieldUsingHamcrest() {
        Response response = given().baseUri("https://api.getpostman.com")
                .header("X-API-Key", "PMAK-65be22ebd6ddcb0001466280-b59cdaad65bb267f9a0672959088f7ffe2")
                .when().get("/workspaces")
                .then().log().all()
                .assertThat().statusCode(200).extract().response();

        JsonPath jsonPath = new JsonPath(response.asPrettyString());
        System.out.println(jsonPath.getString("workspaces[0].name"));
        assertThat(jsonPath.getString("workspaces[0].name"), equalTo("My Workspace"));


    }
}
