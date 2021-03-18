package com.api.basics;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

public class JiraTest {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		String message = "Hi how are you? ";
		
		RestAssured.baseURI = "http://localhost:8080/";
		
		//login Scenario
		SessionFilter session = new SessionFilter();
		
		String response = given().header("Content-Type", "application/json")
		.body("{ \"username\": \"aayush.jain003\", \"password\": \"Test@123\" }")
		.log().all().filter(session)
		.when()
		.post("rest/auth/1/session")
		.then().log().all()
		.extract().response().asString();
		
		
		//Add Comment
		String add_comment =  given().pathParam("id", "10018").log().all()
		.header("Content-Type", "application/json")
		.body("{\r\n" + 
				"    \"body\": \""+message+"\",\r\n" + 
				"    \"visibility\": {\r\n" + 
				"        \"type\": \"role\",\r\n" + 
				"        \"value\": \"Administrators\"\r\n" + 
				"    }\r\n" + 
				"}")
		.filter(session)
		.when()
		.post("rest/api/2/issue/{id}/comment")
		.then().log().all()
		.assertThat().statusCode(201)
		.extract().response().asString();
		
		JsonPath js = new JsonPath(add_comment);
		String comment_id = js.get("id");
		
		//Add Attachment
		given().header("X-Atlassian-Token", "no-check")
		.filter(session)
		.pathParam("id", "10018")
		.header("Content-Type", "multipart/form-data")
		.multiPart("file", new File("D:\\Eclipse workspace-20200724T163020Z-001\\DemoApiProject\\jira.txt"))
		.when()
		.post("rest/api/2/issue/{id}/attachments")
		.then().log().all()
		.assertThat().statusCode(200);
				
		//Get Issue
		String issue_details =  given().filter(session)
		.pathParam("id", "10018")
		.queryParam("fields", "comment")
		.log().all()
		.when()
		.get("rest/api/2/issue/{id}")
		.then().log().all()
		.extract().response().asString();
		
		JsonPath js1 = new JsonPath(issue_details);
		int comment_count = js1.getInt("fields.comment.comments.size()");
		
		for(int i = 0; i < comment_count ; i++)
		{
			
			String comment_id_reterived = js1.getString("fields.comment.comments["+i+"].id");
			if(comment_id_reterived.equalsIgnoreCase(comment_id))
			{
				String message_reterived = js1.getString("fields.comment.comments["+i+"].body");
				System.out.println(message_reterived);
				Assert.assertEquals(message_reterived, message);
			}
			
		}

	}

}
