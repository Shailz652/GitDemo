package test;

import java.util.*;
import io.restassured.path.json.JsonPath;
import userData.BodyPayload;

public class SampleJsonParser {

	public static void main(String[] args) {
		JsonPath js = new JsonPath(BodyPayload.CoursePrice());
		
		// Print no. of courses in Json response
		int CourseCount = js.getInt("courses.size()");
		System.out.println("Course count 1st method: "+CourseCount);
		List<String> CourseList = js.getList("courses");
		System.out.println("Course count 2nd method: "+CourseList.size());
		
		// Print purchase amount
		int PurchaseAmt = js.getInt("dashboard.purchaseAmount");
		System.out.println("Total Courses purchase amount: "+PurchaseAmt);
		
		// Print title of 1st course
		String titleA = js.get("courses[0].title");
		String titleB = js.get("courses.title[1]");
		List<String> titleC = new ArrayList<String>(js.get("courses.title"));
		System.out.println("All Courses: "+titleA+", "+titleB+", "+titleC.get(2));
		
		// Print all courses title with prices
		for(int i=0; i<CourseCount; i++)
		{
			String title = js.get("courses["+i+"].title");
			int price = js.get("courses["+i+"].price");
			System.out.println("Course "+title+" has price of "+price+"/-");
		}
		
		// Print no. of copies sold for all course and then single course
		for(int i=0; i<CourseCount; i++)
		{
			String title = js.get("courses["+i+"].title");
			int copies = js.get("courses["+i+"].copies");
			System.out.println("Course "+title+" has sold "+copies+" copies");
		}
		for(int i=0; i<CourseCount; i++)
		{
			String title = js.get("courses["+i+"].title");
			if(title.equalsIgnoreCase("Cypress")) {
				int copies = js.get("courses["+i+"].copies");
				System.out.println("Course "+title+" has sold "+copies+" copies");
				break;
			}
		}
		
		// Verify if sum of all courses price is equal to purchase amount
		int totalAmount = 0;
		for(int i=0; i<CourseCount; i++)
		{
			int copies = js.get("courses["+i+"].copies");
			int price = js.get("courses["+i+"].price");
			totalAmount = totalAmount + copies * price;
		}
		if(totalAmount == PurchaseAmt)
			System.out.println("Both are matching");
	}
}
