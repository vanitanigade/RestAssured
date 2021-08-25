package Cookies;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CookiesBasedByJsonPath {

	@Test
	public void cookiesBased(){
		
		RestAssured.baseURI = "http://localhost:8080/rest/auth/1/";
		Response response = RestAssured.given()
										.header("content-type", "application/json")
										.body("{ \"username\": \"vanitanigade\", \"password\": \"Sachusmera9\" }")
										.when()
										.post("session");
		System.out.println("body ->" +response.getBody().asPrettyString());
		JsonPath jsPath = new JsonPath(response.getBody().asPrettyString());
		String sessionId = "JSESSIONID"+"="+jsPath.get("session.value");
	
		RestAssured.baseURI = "http://localhost:8080/rest/api/2/";
		Response response2 = RestAssured.given()
									.header("cookie", sessionId)
									.when()
									.get("issue/RES-14/comment");
		System.out.println("body -> "+response2.body().asPrettyString());

	}
}