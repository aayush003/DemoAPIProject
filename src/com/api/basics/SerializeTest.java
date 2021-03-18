package com.api.basics;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import com.pojo.AddPlace;
import com.pojo.Location;

public class SerializeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
		
		Response res = given().queryParam("key", "qaclick123")
		.body(p)
		.when()
		.post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response();
		
		String response_string = res.asString();
		System.out.println(response_string);
		
	}

}
