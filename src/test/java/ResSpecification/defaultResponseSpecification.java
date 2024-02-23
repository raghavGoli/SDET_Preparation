package ResSpecification;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class defaultResponseSpecification {
    RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
    ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();

    @BeforeClass
    public void beforeClass() {

        requestSpecBuilder.setBaseUri("https://api.getpostman.com")
                .addHeader("X-API-Key", "PMAK-65be22ebd6ddcb0001466280-b59cdaad65bb267f9a0672959088f7ffe2")
                .log(LogDetail.ALL);
        responseSpecBuilder.expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL);
        RestAssured.requestSpecification = requestSpecBuilder.build();
        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void validateResponseBody2() {
        Response response = get("/workspaces");
        assertThat(response.path("workspaces[0].name"), is(equalTo("My Workspace")));
        assertThat(response.path("workspaces[1].type"), is(equalTo("personal")));
        assertThat(response.path("workspaces.name"), hasItems("My Workspace", "postmanLearning"));
    }
}
