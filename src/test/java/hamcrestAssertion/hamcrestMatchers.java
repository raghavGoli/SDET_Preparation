package hamcrestAssertion;

import io.restassured.config.LogConfig;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class hamcrestMatchers {

    @Test
    public void hamcrestMatchersUsage() {

        Set<String> hs = new HashSet<String>();
        hs.add("X-API-Key");
        hs.add("Accept");
        Response response = given().baseUri("https://api.getpostman.com")
                .header("X-API-Key", "PMAK-65be22ebd6ddcb0001466280-b59cdaad65bb267f9a0672959088f7ffe2")
                .config(config.logConfig(LogConfig.logConfig().blacklistHeaders(hs)))
                .log().all()
                .get("/workspaces")
                .then()
                .assertThat().statusCode(200).extract().response();

        JsonPath jsonPath = new JsonPath(response.asPrettyString());
        assertThat(jsonPath.getString("workspaces[0]    .name"), equalTo("My Workspace"));
        assertThat(jsonPath.getList("workspaces.name"), contains("My Workspace", "postmanLearning", "postmanLearning2"));
        assertThat(jsonPath.getList("workspaces.name"), containsInAnyOrder("postmanLearning", "postmanLearning2", "My Workspace"));
        assertThat(jsonPath.getList("workspaces.name"), hasSize(3));
        assertThat(jsonPath.getList("workspaces.name"), not(hasItem("myTest")));
        assertThat(jsonPath.getString("workspaces[0].name"), not(equalTo(Collections.EMPTY_LIST)));
        assertThat(jsonPath.getList("workspaces.name"), hasItem("My Workspace"));
        assertThat(jsonPath.getList("workspaces.name"), hasItems("My Workspace", "postmanLearning", "postmanLearning2"));
        assertThat(jsonPath.getList("workspaces.name"), is(not(empty())));
        assertThat(jsonPath.getList("workspaces.type"), everyItem(startsWith("personal")));
        assertThat(jsonPath.getMap("workspaces[0]"), hasKey("id"));
        assertThat(jsonPath.getMap("workspaces[0]"), hasValue("personal"));
        assertThat(jsonPath.getMap("workspaces[0]"), not(equalTo(Collections.EMPTY_MAP)));
        assertThat(jsonPath.getString("workspaces[0].type"), allOf(startsWith("personal"), containsString("personal")));


    }

}


