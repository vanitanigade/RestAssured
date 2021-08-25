package httpMethods;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Delete {

	@BeforeMethod
	public void setup() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
	}

	@Test
	public void deleteMethod() {
		Response response = RestAssured.when()
				.delete("posts/1");
		System.out.println("status code -> "+response.statusCode());
		System.out.println("body -> "+response.getBody().asPrettyString());
		Assert.assertEquals(response.statusCode(), 200);


	}
}
