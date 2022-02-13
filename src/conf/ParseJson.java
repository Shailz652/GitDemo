package conf;

import io.restassured.path.json.JsonPath;

public class ParseJson 
{
	public static JsonPath rawToJson(String response) 
	{
		JsonPath jsonString = new JsonPath(response);
		return jsonString;
	}
}
