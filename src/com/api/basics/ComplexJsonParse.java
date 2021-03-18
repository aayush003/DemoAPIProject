package com.api.basics;

import com.files.Payload;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) 
	
	{
		// TODO Auto-generated method stub
		
		
		JsonPath js = new JsonPath(Payload.CoursePrice());
		
		//Print no of courses
		int count = js.getInt("courses.size()");
		System.out.println(count);
		
		//Print purchased amount
		System.out.println(js.getInt("dashboard.purchaseAmount"));
		
		//Print All course titles and their respective Prices
		for(int i = 0; i < count; i++)
		{
			
			System.out.print((js.getString("courses["+i+"].title")) +": ");
			System.out.println(js.getInt("courses["+i+"].price"));
			
		}
		
		
		//Print no of copies sold by RPA Course
		
		for(int i = 0; i < count; i++)
		{
			String course_titles = js.getString("courses["+i+"].title");
			
			if(course_titles.equalsIgnoreCase("RPA"))
			{
				//copies sold
				System.out.print((js.getString("courses["+i+"].title")) +" copies sold: ");
				System.out.println(js.getString("courses["+i+"].copies"));
				break;
			}
		}
	}

}
