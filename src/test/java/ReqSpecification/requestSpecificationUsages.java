package ReqSpecification;

import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class requestSpecificationUsages {

    @Test
    public void requestSpec_1() {
        RequestSpecification requestSpecification =
                given().baseUri("https://jsonplaceholder.typicode.com");

        given().spec(requestSpecification)
                .when().get("/todos")
                .then().log().all()
                .assertThat().statusCode(200);
    }

    @Test
    public void requestSpec_2() {
        RequestSpecification requestSpecification =
                given().baseUri("https://jsonplaceholder.typicode.com");

        given(requestSpecification)
                .when().get("/todos")
                .then().log().all()
                .assertThat().statusCode(200);
    }
}
