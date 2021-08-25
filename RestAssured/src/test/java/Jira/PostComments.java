package Jira;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pojo.Visibility;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PostComments {

	@BeforeMethod
	public void setup() {
		RestAssured.baseURI = "http://localhost:8080/rest/api/2/";
	}
	@Test
	public void postComments() {
		
		Visibility visibility = new Visibility();
		visibility.setType("role");
		visibility.setValue("Administrators");
		
		AddCommentRootPojo reqPojo = new AddCommentRootPojo();
		reqPojo.setBody("This body comment is changed from RestAssured script");
		reqPojo.setVisibility(visibility);
		
		Response response = RestAssured.given()
										.auth()
										.preemptive()
										.basic("vanitanigade", "Sachusmera9")
										.header("Content-Type", "application/json")
										.body(reqPojo)
										.when()
										.post("issue/RES-14/comment");
		System.out.println("status code -> "+response.statusCode());
		System.out.println("body -> " + response.body().asPrettyString());
		JsonPath jsPath = new JsonPath(response.body().asPrettyString());
		System.out.println("comment body -> "+jsPath.get("body"));


	}


}

