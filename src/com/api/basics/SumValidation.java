package com.api.basics;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.files.Payload;

import io.restassured.path.json.JsonPath;

public class SumValidation 
{

	@Test
	public void sumOfCourses()
	{
		JsonPath js = new JsonPath(Payload.CoursePrice());
		int count = js.getInt("courses.size()");
		
		int purchase_amount=0;
		int sum = 0;
		
		for(int i = 0; i < count; i++)
		{
			sum = sum + (js.getInt("courses["+i+"].price")) * (js.getInt("courses["+i+"].copies"));
		}
		System.out.println(sum);
		
		purchase_amount = js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(sum, purchase_amount);
	}
	
}
