package test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;

public class Basics01 {

	public static void main(String[] args) {
		// Validate Add place API
		// Given - all input details
		// When - submit the API with resource and http method
		// Then - validate the response
		
		RestAssured.baseURI = "https://rahulshettyacademy.com/";
		// given query parameters, authorization, headers, body
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -128.383494,\r\n"
				+ "    \"lng\": 133.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 70,\r\n"
				+ "  \"name\": \"Someone house\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, Some layout, Noida 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"park place\",\r\n"
				+ "    \"shop place\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"Hindi-IN\"\r\n"
				+ "}\r\n"
				+ "")
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200)
		.body("scope", equalTo("APP"))
		.header("Access-Control-Allow-Methods", equalTo("POST"))
		.header("Access-Control-Allow-Methods", "POST")
		.header("Server", "Apache/2.4.18 (Ubuntu)");
	}
}
