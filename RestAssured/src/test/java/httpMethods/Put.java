package httpMethods;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Put {

	@BeforeMethod
	public void setup() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
	}

	@Test
	public void PutMethod() {
		Response response = RestAssured.given()
				.header("Content-type", "application/json; charset=UTF-8")
				.body("{\r\n"
						+ "    \"id\": 1,\r\n"
						+ "    \"title\": \"foo\",\r\n"
						+ "    \"body\": \"bar\",\r\n"
						+ "    \"userId\": \"1\"\r\n"
						+ "}")
				.when()
				.put("posts/1");
		System.out.println("status code -> "+response.statusCode());
		System.out.println("body -> "+response.getBody().asString());
		Assert.assertEquals(response.statusCode(), 200);
	}
}
