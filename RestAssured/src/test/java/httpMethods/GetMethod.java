package httpMethods;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetMethod {


	@BeforeMethod 
	public void setup() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
	}

	@Test
	public void getMethod() {
		Response response = RestAssured.given()
				.when()
				.get("posts/1");
		System.out.println("Status code-> "+response.getStatusCode());
		System.out.println("Body -> "+response.getBody().asString());
		System.out.println("Cookies -> "+response.getCookies());
		System.out.println("Headers -> "+response.getHeader("Content-Type"));
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("statusCode assertion passed");
		Assert.assertEquals(response.getHeader("Content-Type"), "application/json; charset=utf-8");
		
	}
		
		@Test
		public void getMethod_2() {
			Response response = RestAssured.given()
					.when()
					.get("posts/2");
			System.out.println("Status code-> "+response.getStatusCode());
			System.out.println("Body -> "+response.getBody().asString());
			System.out.println("Cookies -> "+response.getCookies());
			System.out.println("Headers -> "+response.getHeader("Content-Type"));
			Assert.assertEquals(response.getStatusCode(), 200);
			System.out.println("statusCode assertion passed");
			Assert.assertEquals(response.getHeader("Content-Type"), "application/json; charset=utf-8");

	}

}
