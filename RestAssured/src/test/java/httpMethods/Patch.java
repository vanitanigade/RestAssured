package httpMethods;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Patch {

	@BeforeMethod
	public void setup() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
	}

	@Test
	public void patchMethod() {
		Response response = RestAssured.given()
				.header("Content-type", "application/json; charset=UTF-8")
				.body("{\"title\": \"new title\"}")
				.when()
				.patch("posts/1");
		System.out.println("status code -> "+response.statusCode());
		System.out.println("body -> "+response.getBody().asString());
		System.out.println("header => "+response.header("Date"));
		Assert.assertEquals(response.statusCode(), 200);
	}

}
