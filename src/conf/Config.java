package conf;

import io.restassured.RestAssured;

public class Config {
	public static void BaseURL() {
		RestAssured.baseURI = "https://rahulshettyacademy.com/";
	}
}
