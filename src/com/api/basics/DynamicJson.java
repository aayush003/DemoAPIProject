package com.api.basics;

import org.testng.annotations.Test;

import com.files.Payload;
import com.files.ReusableMethods;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson 
{

	@Test
	public void addBook()
	{
		
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().log().all().header("Content-Type", "application/json")
		.body(Payload.addBook())
		.when()
		.post("/Library/Addbook.php")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath js = ReusableMethods.rawToJson(response);
		String ID = js.getString("ID");
		
		System.out.println(ID);
	}
	
}
