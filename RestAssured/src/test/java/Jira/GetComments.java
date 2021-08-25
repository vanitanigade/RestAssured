package Jira;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pojo.Author;
import Pojo.Comment;
import Pojo.RootPojo;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetComments {

	@BeforeMethod
	public void setup(){
		RestAssured.baseURI = "http://localhost:8080/rest/api/2/";
	}

	@Test
	public void getComments() {
		Response response = RestAssured.given()
										.auth()
										.preemptive()
										.basic("vanitanigade", "Sachusmera9")
										.when()
										.get("issue/RES-14/comment");
	/*	JsonPath jsPath = new JsonPath(response.body().asPrettyString());
		System.out.println("jsPath body -> "+jsPath.get("comments[0].body"));
		System.out.println("key - > "+jsPath.get("comments[0].author.key"));
		System.out.println("status code -> "+response.statusCode());
		System.out.println("response body -> "+response.body().asPrettyString());
		Assert.assertEquals(jsPath.get("comments[0].body"), "This comment is updated from Postman by Put method");
	*/
		RootPojo rootPojo = response.body().as(RootPojo.class);
		System.out.println(rootPojo.getMaxResults());
		System.out.println(rootPojo.getComments());
			List<Comment> comments = rootPojo.getComments();
			System.out.println(comments.get(1).getUpdateAuthor().getEmailAddress());
		for (Comment comment : rootPojo.getComments()) {
		//	Author author = comment.getAuthor();
		//	author.getEmailAddress();
			System.out.println(comment.getAuthor().getEmailAddress());     //instead of 2 line code, we get it in 1 line
			System.out.println(comment.getBody());
			System.out.println(comment.getUpdateAuthor().getAvatarUrls().get_24x24());

		}

	}

}
