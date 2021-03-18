package com.api.basics;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import com.pojo.AddPlace;
import com.pojo.Location;

public class SpecBuilderTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RequestSpecification req  = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setAddress("30, Chandni Chowk, Delhi-6");
		p.setLanguage("English");
		p.setPhone_number("(+91) 999 999 8888");
		p.setWebsite("https://google.com");
		p.setName("Babu rao house");
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);

		Location l = new Location();
		l.setLat(-38.1234);
		l.setLng(33.1234);
		p.setLocation(l);
		
		ResponseSpecification response_specification = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		RequestSpecification res = given().spec(req)
		.body(p);
		
		Response response = res.when()
		.post("/maps/api/place/add/json")
		.then().spec(response_specification).extract().response();
		
		String response_string = response.asString();
		System.out.println(response_string);
		
	}

}
