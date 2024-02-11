package ReqSpecification;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class requestSpecBuilder {

    RequestSpecification requestSpecification;

    @BeforeClass
    public void beforeClass() {
    /*   requestSpecification = given().baseUri("https://api.getpostman.com")
                .header("X-API-Key", "PMAK-65be22ebd6ddcb0001466280-b59cdaad65bb267f9a0672959088f7ffe2")
                .log().all();
*/
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://api.getpostman.com")
                .addHeader("X-API-Key", "PMAK-65be22ebd6ddcb0001466280-b59cdaad65bb267f9a0672959088f7ffe2")
                .log(LogDetail.ALL);
        requestSpecification = requestSpecBuilder.build();
    }

    @Test
    public void validateStatusCode() {

        Response response = given().spec(requestSpecification).get("/workspaces")
                .then().log().all().extract().response();
        assertThat(response.statusCode(), is(equalTo(200)));

    }

    @Test
    public void validateResponseBody2() {
        Response response = given().spec(requestSpecification).get("/workspaces")
                .then().log().all().extract().response();
        assertThat(response.path("workspaces[0].name"), is(equalTo("My Workspace")));
        assertThat(response.path("workspaces[1].type"), is(equalTo("personal")));
        assertThat(response.path("workspaces.name"), hasItems("My Workspace", "postmanLearning"));
    }
}
