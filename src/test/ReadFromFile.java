package test;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class ReadFromFile {
	// Read content of file -> convert content of file to Byte -> convert Byte data to String
	@Test
	public void addBook() throws IOException
	{
		RestAssured.baseURI = "http://216.10.245.166";
		String jsonFileData = new String(Files.readAllBytes(Paths.get("C:\\My Stuff\\Work\\Rest Assured Docs\\AddBook.json")));
		String AddBookResponse = given().log().all().header("Content-Type","application/json").
				body(jsonFileData).
				when().post("/Library/Addbook.php").
				then().log().all().assertThat().statusCode(200).
				extract().response().asString();
		JsonPath js = new JsonPath(AddBookResponse);
		String bookID = js.get("ID");
		System.out.println(bookID);
	}
}
