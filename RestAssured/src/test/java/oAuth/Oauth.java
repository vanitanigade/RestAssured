package oAuth;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Oauth {
	
	@BeforeMethod
	public void setup() {
		RestAssured.baseURI = "http://coop.apps.symfonycasts.com/";
	}

	@Test
	public void oAuth() {
		RequestBodyPojo reqPojo = new RequestBodyPojo();
		reqPojo.setAction("The action (e.g. \\\"barn-unlock\\\")");
		reqPojo.setData("A raw, related piece of data if applicable");
		reqPojo.setMessage("Some summary message");
		reqPojo.setSuccess("true");
		
		Response response = RestAssured.given()
										.auth()
										.oauth2(getAccessToken())
										.body(reqPojo)
										.when()
										.post("/api/1975/eggs-count");
				System.out.println("body -> "+response.getBody().asPrettyString());
			EggCountPojo eggCountPojo =	response.getBody().as(EggCountPojo.class);
			System.out.println(eggCountPojo.getMessage());
			Assert.assertEquals(eggCountPojo.getAction(), "eggs-count");
			Assert.assertEquals(eggCountPojo.getSuccess(), "true");
			System.out.println("Status code -> "+response.statusCode());
	}
	
	public String getAccessToken() {
		Response response = RestAssured.given()
									.formParam("client_id", "APISession_1")
									.formParam("client_secret", "b2cb2082dee5e19b0e5c5bce79d7518f")
									.formParam("grant_type", "client_credentials")
									.when()
									.post("token");
		System.out.println("body -> "+response.getBody().asPrettyString());
		//	JsonPath jsPath = new JsonPath(response.getBody().asString());
		//	return jsPath.get("access_token");
		AccessTokenPojo accessTokenPojo = response.getBody().as(AccessTokenPojo.class);
		System.out.println(accessTokenPojo.getAccess_token());
		System.out.println(accessTokenPojo.getExpires_in());
		System.out.println(accessTokenPojo.getScope());
		System.out.println(accessTokenPojo.getToken_type());
		return accessTokenPojo.getAccess_token();
	}
	
}