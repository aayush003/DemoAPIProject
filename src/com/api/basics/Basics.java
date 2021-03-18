package com.api.basics;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import com.files.Payload;
import com.files.ReusableMethods;

public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		//Add Place
		
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(Payload.AddPlace())
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP")).extract().response().asString();
		
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		String place_ID = js.getString("place_id");
		
		System.out.println(place_ID);
		
		
		//Update Place
		
		String newAddress = "Chandni Chowk";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(Payload.PutPlace(place_ID, newAddress))
		.when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//Get Place
		
		String getPlace =  given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id", place_ID)
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		JsonPath js1 = ReusableMethods.rawToJson(getPlace);
		String actual_addr = js1.getString("address");
		System.out.println(actual_addr);
		
		//Assert using TestNG
		
		Assert.assertEquals(newAddress, actual_addr);
		

	}

}
