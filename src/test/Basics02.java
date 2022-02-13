package test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;

import conf.Config;
import conf.ParseJson;
import conf.Resources;
import userData.BodyPayload;

public class Basics02 {
	
	// Add place -> Update added place -> Get place to validate if updated address is available
	public static void main(String[] args) {
		Config.BaseURL();
		
		// Add Place API
		String addResponse = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(BodyPayload.AddPlace())
		.when().post(Resources.AddPlace())
		.then().assertThat().statusCode(200)
		.body("scope", equalTo("APP"))
		.extract().response().asString();
		
		System.out.println(addResponse);
		JsonPath jsn = ParseJson.rawToJson(addResponse);		// Json Parsing
		String placeID = jsn.getString("place_id");
//		String placeID = ParseJson.rawToJson(addResponse).getString("place_id");
		System.out.println(placeID);
		
		// Update Place API
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(BodyPayload.UpdatePlace(placeID))
		.when().put(Resources.PutPlace())
		.then().log().all().assertThat().statusCode(200)
		.body("msg", equalTo("Address successfully updated"));
		
		// Get Updated Address
		String getResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeID)
		.when().get(Resources.GetPlace())
		.then().assertThat().statusCode(200)
		.extract().response().asString();
		
		System.out.println(getResponse);
		JsonPath adr = ParseJson.rawToJson(getResponse);		// Json Parsing
		String expAddress = adr.getString("address");
		System.out.println("\n"+expAddress);
		System.out.println(BodyPayload.newAddress);
		
		// Assert(validate) using Testng or Cucumber Junit
		Assert.assertEquals(BodyPayload.newAddress, expAddress);
	}
}
