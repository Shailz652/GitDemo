package test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import userData.BodyPayload;

public class ParameterizedBasics {
	@Test(dataProvider = "BooksData")
	public void addBook(String isbn, String aisle)
	{
		RestAssured.baseURI = "http://216.10.245.166";
		
		String AddBookResponse = given().header("Content-Type","application/json").
				body(BodyPayload.AddBook(isbn,aisle)).
				when().post("/Library/Addbook.php").
				then().log().all().assertThat().statusCode(200).
				extract().response().asString();
		JsonPath js = new JsonPath(AddBookResponse);
		String bookID = js.get("ID");
		System.out.println(bookID);
	}
	
	@DataProvider(name = "BooksData")
	public Object[][] getData()
	{
		return new Object[][] {{"I004","A004"},{"I005","A005"},{"I006","A006"}};
	}
}
