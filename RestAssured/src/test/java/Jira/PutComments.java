package Jira;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PutComments {
	
	@BeforeMethod
	public void setup(){
		RestAssured.baseURI = "http://localhost:8080/rest/api/2/";		
	}
	
	@Test
	public void putComments(){
		Response response =	RestAssured.given()
										.auth()
										.preemptive()
										.basic("vanitanigade", "Sachusmera9")
										.header("Content-Type", "application/json; charset=UTF-8")
										.body("{\r\n"
												+ "    \"body\": \"This comment is updated from RestAssured by Put method\",\r\n"
												+ "    \"visibility\": {\r\n"
												+ "        \"type\": \"role\",\r\n"
												+ "        \"value\": \"Administrators\"\r\n"
												+ "    }\r\n"
												+ "}")
										.when()
										.put("issue/RES-14/comment/10005");
		System.out.println("response body -> "+response.body().asPrettyString());
		System.out.println("status code -> "+response.statusCode());
		Assert.assertEquals(response.statusCode(), 200);
					
	}

}
