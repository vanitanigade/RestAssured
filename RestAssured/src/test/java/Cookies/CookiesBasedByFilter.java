package Cookies;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.response.Response;

public class CookiesBasedByFilter {
	@Test
	public void getComments(){
		RestAssured.baseURI = "http://localhost:8080/rest/api/2/";
		Response response2 = RestAssured.given()
										.filter(getSession())
										.when()
										.get("issue/RES-14/comment");
		System.out.println("body -> "+response2.body().asPrettyString());
	}	
	
	@Test
	public void deleteComments() {
		RestAssured.baseURI = "http://localhost:8080/rest/api/2/";
		RestAssured.given()
					.filter(getSession())
					.when()
					.get("issue/RES-14/comment");
	}
		public SessionFilter getSession() {
			SessionFilter session = new SessionFilter();
			RestAssured.baseURI = "http://localhost:8080/rest/auth/1/";
			RestAssured.given()
						.header("content-type", "application/json")
						.body("{ \"username\": \"vanitanigade\", \"password\": \"Sachusmera9\" }")
						.filter(session)
						.when()
						.post("session");
			return(session);
		}

	}



