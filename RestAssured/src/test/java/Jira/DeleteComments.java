package Jira;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteComments {
	@BeforeMethod
	public void setup(){
		RestAssured.baseURI = "http://localhost:8080/rest/api/2/";		
	}
	
	@Test
	public void deleteComments(){
		Response response = RestAssured.given()
										.auth()
										.preemptive()
										.basic("vanitanigade", "Sachusmera9")
										.when()
										.delete("issue/RES-14/comment/10021");
		System.out.println("status code -> "+response.statusCode());
		System.out.println("body -> "+response.body().asPrettyString());
		Assert.assertEquals(response.statusCode(), 204);
		
			
		
		
	}

}
