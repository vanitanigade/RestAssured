package httpMethods;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import groovyjarjarantlr.collections.List;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class QueryParams {

	@BeforeMethod
	public void setup() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
	}

	@Test
	public void queryParams(){
		Response response = RestAssured.given()
				.queryParam("userId", "2")
				.queryParam("id", "12")
				.when()
				.get("posts");
		System.out.println("Status code-> "+ response.statusCode());
		System.out.println("body -> "+response.getBody().asPrettyString());
		JsonPath jsPath = new JsonPath(response.getBody().asPrettyString());
		System.out.println("title -> "+ jsPath.get("title"));
		System.out.println("body -> "+ jsPath.get("body"));
		Assert.assertEquals(jsPath.getString("title"), "[in quibusdam tempore odit est dolorem]");

	}


	@Test
	public void queryParams2(){
		Response response = RestAssured.given()
				.queryParam("userId", "2")
				.when()
				.get("posts");
		System.out.println("Status code-> "+ response.statusCode());
		System.out.println("body -> "+response.getBody().asPrettyString());
		JsonPath jsPath = new JsonPath(response.getBody().asPrettyString());
		System.out.println("title -> "+ jsPath.get("title"));
		System.out.println("title size -> "+ jsPath.get("title.size()"));
		System.out.println("3rd title -> "+ jsPath.get("title[2]"));       
		//	Assert.assertEquals(jsPath.getString("title[2]"), "[dolorum ut in voluptas mollitia et saepe quo animi]");

		int count = jsPath.get("title.size()");
		java.util.List<String> titles = new ArrayList<String>();
		titles.add(null);

		for (int i = 2; i<count; i++){
			System.out.println(jsPath.get("title["+i+"]"));
			//	Assert.assertEquals(jsPath.get("title["+i+"]"), titles.get(i));

		}

	}


	@Test
	public void queryParamsNested() {
		Response response = RestAssured.given()
				.when()
				.get("posts/2/comments");
		System.out.println("status code => "+response.statusCode());
		System.out.println("body -> "+response.getBody().asPrettyString());
	}
}