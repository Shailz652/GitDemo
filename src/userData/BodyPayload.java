package userData;

public class BodyPayload {

	public static String AddPlace() {
		return "{\r\n"
				+ "    \"location\": {\r\n"
				+ "        \"lat\": -128.383494,\r\n"
				+ "        \"lng\": 133.427362\r\n"
				+ "    },\r\n"
				+ "    \"accuracy\": 70,\r\n"
				+ "    \"name\": \"Someone house\",\r\n"
				+ "    \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "    \"address\": \"29, Some layout, Noida 201\",\r\n"
				+ "    \"types\": [\r\n"
				+ "        \"park place\",\r\n"
				+ "        \"shop place\"\r\n"
				+ "    ],\r\n"
				+ "    \"website\": \"http://google.com\",\r\n"
				+ "    \"language\": \"Hindi-IN\"\r\n"
				+ "}";
	}
	public static String newAddress = "70, New layout, Noida 301";
	public static String UpdatePlace(String placeID) {
		return "{\r\n"
				+ "\"place_id\":\""+placeID+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "";
	}
	public static String CoursePrice()
	{
		
		return "{\r\n"
				+ "\"dashboard\": {\r\n"
				+ "\"purchaseAmount\": 910,\r\n"
				+ "\"website\": \"rahulshettyacademy.com\"\r\n"
				+ "},\r\n"
				+ "\"courses\": [\r\n"
				+ "{\r\n"
				+ "\"title\": \"Selenium Python\",\r\n"
				+ "\"price\": 50,\r\n"
				+ "\"copies\": 6\r\n"
				+ "},\r\n"
				+ "{\r\n"
				+ "\"title\": \"Cypress\",\r\n"
				+ "\"price\": 40,\r\n"
				+ "\"copies\": 4\r\n"
				+ "},\r\n"
				+ "{\r\n"
				+ "\"title\": \"RPA\",\r\n"
				+ "\"price\": 45,\r\n"
				+ "\"copies\": 10\r\n"
				+ "}\r\n"
				+ "]\r\n"
				+ "}";
	}
	public static String AddBook(String isbn, String aisle)
	{
		String addBookJson = "{\r\n"
				+ "\r\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
				+ "\"isbn\":\""+isbn+"\",\r\n"
				+ "\"aisle\":\""+aisle+"\",\r\n"
				+ "\"author\":\"John Doe\"\r\n"
				+ "}\r\n"
				+ "";
		return addBookJson;
	}
}
