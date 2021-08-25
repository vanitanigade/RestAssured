package httpMethods;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PostMethod {

	@BeforeMethod
	public void setup() {

		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
	}

	@Test
	public void postMethod() {
		Response response = RestAssured.given()
				.header("Content-Type", "application/json; charset=utf-8")
				.body("{    \"title\": \"foo\",\r\n"
						+ "    \"body\": \"bar\",\r\n"
						+ "    \"userId\": \"1\"\r\n"
						+ "}")
				.when()
				.post("posts");
		System.out.println("Response -> "+ response);
		System.out.println("status code -> "+response.statusCode());
		System.out.println("body -> "+response.getBody().asPrettyString());
		System.out.println("time -> "+response.time());
		Assert.assertEquals(response.statusCode(), 201);
	}

}
