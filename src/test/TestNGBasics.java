package test;

import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;
import userData.BodyPayload;

public class TestNGBasics {
	JsonPath js = new JsonPath(BodyPayload.CoursePrice());
	int CourseCount = js.getInt("courses.size()");
	@Test
	public void GetTotalCourses()
	{
		System.out.println("Course count 1st method: "+CourseCount);
		List<String> CourseList = js.getList("courses");
		System.out.println("Course count 2nd method: "+CourseList.size());
	}
	@Test
	public void GetPurchaseAmount()
	{
		int PurchaseAmt = js.getInt("dashboard.purchaseAmount");
		System.out.println("Total Courses purchase amount: "+PurchaseAmt);
	}
	@Test
	public void GetAllCourses()
	{
		String titleA = js.get("courses[0].title");
		String titleB = js.get("courses.title[1]");
		List<String> titleC = new ArrayList<String>(js.get("courses.title"));
		System.out.println("All Courses: "+titleA+", "+titleB+", "+titleC.get(2));
	}
	@Test
	public void GetCoursePrices()
	{
		for(int i=0; i<CourseCount; i++)
		{
			String title = js.get("courses["+i+"].title");
			int price = js.get("courses["+i+"].price");
			System.out.println("Course "+title+" has price of "+price+"/-");
		}
	}
	@Test
	public void GetCopiesSold()
	{
		for(int i=0; i<CourseCount; i++)
		{
			String title = js.get("courses["+i+"].title");
			int copies = js.get("courses["+i+"].copies");
			System.out.println("Course "+title+" has sold "+copies+" copies");
		}
	}
}
