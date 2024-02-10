package GETRequests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class automateGETResponseBody {

    public void validateResponseBody() {
        given().baseUri("https://api.getpostman.com")
                .header("X-API-Key", "PMAK-65be22ebd6ddcb0001466280-b59cdaad65bb267f9a0672959088f7ffe2")
                .when().get("/workspaces")
                .then().log().all()
                .assertThat()
                .body("workspaces.name", hasItems("My Workspace", "postmanLearning"),
                        "workspaces.type", hasItems("personal", "personal"),
                        "workspaces[0].name", is(equalTo("My Workspace")),
                        "workspaces[1].type", is(equalTo("personal")),
                        "workspaces.size()", is(equalTo(2)),
                        "workspaces.name", hasItem("My Workspace"));


    }
}
