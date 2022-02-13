package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;
import userData.BodyPayload;

public class TotalPrice {
	@Test
	public void totalCoursePrice()
	{
		JsonPath js = new JsonPath(BodyPayload.CoursePrice());
		int CourseCount = js.getInt("courses.size()");
		int PurchaseAmt = js.getInt("dashboard.purchaseAmount");
		int totalAmount = 0;
		for(int i=0; i<CourseCount; i++)
		{
			int copies = js.get("courses["+i+"].copies");
			int price = js.get("courses["+i+"].price");
			totalAmount += copies * price;
		}
		if(totalAmount == PurchaseAmt)
			System.out.println("Both are matching");
		Assert.assertEquals(totalAmount, PurchaseAmt);
	}
}
