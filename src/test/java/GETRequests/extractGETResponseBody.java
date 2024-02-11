package GETRequests;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class extractGETResponseBody {

    @Test
    public void extractResponse() {
        Response response = given().baseUri("https://api.getpostman.com")
                .header("X-API-Key", "PMAK-65be22ebd6ddcb0001466280-b59cdaad65bb267f9a0672959088f7ffe2")
                .when().get("/workspaces")
                .then().log().all()
                .assertThat().statusCode(200).extract().response();
        System.out.println(response.asPrettyString());

    }


}
