package test;
import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import userData.JIRAPayload;
import io.restassured.path.json.JsonPath;
import conf.ParseJson;

public class JiraTests {

	public static void main(String[] args) {
		RestAssured.baseURI = "http://localhost:8080/";
		// Get login session ID
		/*String login = given().header("Content-Type","application/json").
		body(JIRAPayload.Login()).log().all().
		when().post("rest/auth/1/session").
		then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = ParseJson.rawToJson(login);
		String sessionName = js.get("session.name");
		String sessionID = js.get("session.value");
		
		// Using session variables in header
		given().pathParam("key", "RA-1").header("Content-Type","application/json").header("Cookie",sessionName+"="+sessionID).
		body(JIRAPayload.AddCommentA()).
		when().post("rest/api/2/issue/{key}/comment").
		then().log().all().assertThat().statusCode(201);*/
		
		// Filter session method to get session details and using it in service
		SessionFilter session = new SessionFilter();
		
		given().relaxedHTTPSValidation().header("Content-Type","application/json").
		body(JIRAPayload.Login()).log().all().
		filter(session).
		when().post("rest/auth/1/session").
		then().log().all().assertThat().statusCode(200);
		
		// Adding Comment
		String newComment = "This is for comment matching";
		String JiraComment = given().pathParam("key", "RA-1").header("Content-Type","application/json").
		body(JIRAPayload.AddCommentB(newComment)).
		filter(session).
		when().post("rest/api/2/issue/{key}/comment").
		then().log().all().assertThat().statusCode(201).extract().response().asString();
		JsonPath js = ParseJson.rawToJson(JiraComment);
		int commentID = js.getInt("id");
		
		// Adding attachment
		given().pathParam("key", "RA-1").header("X-Atlassian-Token","no-check").header("Content-Type","multipart/form-data").
		filter(session).multiPart("file",new File("JiraAttachment01.txt")).
		when().post("rest/api/2/issue/{key}/attachments").
		then().log().all().assertThat().statusCode(200);
		
		// Get issue
		String issueDetails = given().pathParam("key", "RA-1").queryParam("fields", "summary,comment").
		filter(session).
		when().get("rest/api/2/issue/{key}").
		then().log().all().assertThat().statusCode(200).extract().response().asString();

		js = ParseJson.rawToJson(issueDetails);
		int count = js.get("fields.comment.comments.size()");
		for(int i=0; i<count; i++)
		{
			int ID = js.getInt("fields.comment.comments["+i+"].id");
			System.out.println(ID);
			if(commentID == ID)
			{
				String comments = js.get("fields.comment.comments["+i+"].body");
				System.out.println(comments);
				Assert.assertEquals(comments, newComment);
			}	
		}
		for(int i=0; i<count; i++)
		{
			int ID = js.getInt("fields.comment.comments["+i+"].id");
			String comments = js.get("fields.comment.comments["+i+"].body");
			if(comments.equalsIgnoreCase(newComment))
				System.out.println("Comment match on ID: "+ID);
		}
	}

}
