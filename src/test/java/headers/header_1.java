package headers;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class header_1 {

    @Test
    public void passHeaders() {
        given().baseUri("https://5aacfec3-a8cd-4149-a57e-1b7cff06ada1.mock.pstmn.io")
                .header("header1", "value1")
                .header("header2", "value2")
                .log().all()
                .when()
                .get("/myget").then().log().all()
                .assertThat().statusCode(200);

    }

    @Test
    public void passHeaders1() {
        Header h1 = new Header("header1", "value1");
        Header h2 = new Header("header2", "value2");
        Headers headers = new Headers(h1, h2);
        given().baseUri("https://5aacfec3-a8cd-4149-a57e-1b7cff06ada1.mock.pstmn.io")
                .headers(headers)
                .log().all()
                .when()
                .get("/myget").then().log().all()
                .assertThat().statusCode(200);

    }

    @Test
    public void passHeaders2() {
        HashMap<String, String> hm = new HashMap<>();
        hm.put("header1", "value1");
        hm.put("header2", "value2");
        given().baseUri("https://5aacfec3-a8cd-4149-a57e-1b7cff06ada1.mock.pstmn.io")
                .headers(hm)
                .log().all()
                .when()
                .get("/myget").then().log().all()
                .assertThat().statusCode(200);

    }

    @Test
    public void validateResponseHeaders() {
        HashMap<String, String> hm = new HashMap<>();
        hm.put("header1", "value1");
        hm.put("header2", "value2");
        given().baseUri("https://5aacfec3-a8cd-4149-a57e-1b7cff06ada1.mock.pstmn.io")
                .headers(hm)
                .log().all()
                .when()
                .get("/myget").then()
                .assertThat().statusCode(200).headers("responseHeader1", "resvalue1", "responseHeader2", "resvalue2");
    }

    @Test
    public void extractResponseHeaders() {
        HashMap<String, String> hm = new HashMap<>();
        hm.put("header1", "value1");
        hm.put("header2", "value2");
        Headers extractedHeaders = given().baseUri("https://5aacfec3-a8cd-4149-a57e-1b7cff06ada1.mock.pstmn.io")
                .headers(hm)
                .log().all()
                .when()
                .get("/myget").then()
                .assertThat().statusCode(200)
                .extract().headers();
        System.out.println(extractedHeaders.get("responseHeader1").getName());
        System.out.println(extractedHeaders.get("responseHeader1").getValue());
    }

    @Test
    public void extractResponseHeaders2() {
        HashMap<String, String> hm = new HashMap<>();
        hm.put("header1", "value1");
        hm.put("header2", "value2");
        Headers extractedHeaders = given().baseUri("https://5aacfec3-a8cd-4149-a57e-1b7cff06ada1.mock.pstmn.io")
                .headers(hm)
                .log().all()
                .when()
                .get("/myget").then()
                .assertThat().statusCode(200)
                .extract().headers();
        for (Header header : extractedHeaders) {
            System.out.print("Header Name is:" + header.getName() + ", ");
            System.out.println("Header value is:" + header.getValue());
        }
    }

    @Test
    public void extractResponseMultiValueHeaders() {
        Header h1 = new Header("multiValueHeader", "value1");
        Header h2 = new Header("multiValueHeader", "value2");
        Header h3 = new Header("multiValueHeader", "value3");
        Headers headers = new Headers(h1, h2, h3);
        Headers extractedHeaders = given().baseUri("https://6c055a89-b582-4bd4-8044-d8cf2da741eb.mock.pstmn.io")
                .headers(headers)
                .log().all()
                .when()
                .get("/myget").then()
                .assertThat().statusCode(200)
                .extract().headers();
        for (Header header : extractedHeaders) {
            System.out.print("Header Name is:" + header.getName() + ", ");
            System.out.println("Header value is:" + header.getValue());
        }
    }
}
