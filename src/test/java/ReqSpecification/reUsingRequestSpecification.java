package ReqSpecification;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class reUsingRequestSpecification {
    RequestSpecification requestSpecification;

    @BeforeClass
    public void beforeClass() {
        requestSpecification = given().baseUri("https://api.getpostman.com")
                .header("X-API-Key", "PMAK-65be22ebd6ddcb0001466280-b59cdaad65bb267f9a0672959088f7ffe2")
                .log().all();
    }

    @Test
    public void validateStatusCode() {

        Response response = requestSpecification.get("/workspaces")
                .then().log().all().extract().response();
        assertThat(response.statusCode(), is(equalTo(200)));

    }

    @Test
    public void validateResponseBody2() {
        Response response = requestSpecification.get("/workspaces")
                .then().log().all().extract().response();
        assertThat(response.path("workspaces[0].name"), is(equalTo("My Workspace")));
        assertThat(response.path("workspaces[1].type"), is(equalTo("personal")));
        assertThat(response.path("workspaces.name"), hasItems("My Workspace", "postmanLearning"));
    }
}
