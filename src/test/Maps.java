package test;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import conf.Config;
import conf.Resources;
import pojo.AddLocation;
import pojo.AddPlace;

public class Maps {
	public static void main(String[] args)
	{
		Config.BaseURL();
		
		AddPlace add = new AddPlace();
		add.setAccuracy(89);
		add.setAddress("A-39, NSEL, UP, India");
		add.setLanguage("Marathi-IN");
		add.setName("New Place Setters");
		add.setPhone_number("0121 2001100 121");
		add.setWebsite("www.newplace.co.in");
		List<String> types = new ArrayList<String>();
		types.add("Banking");
		types.add("Finance");
		types.add("Service");
		add.setTypes(types);
		AddLocation loc = new AddLocation();
		loc.setLat(35.54887);
		loc.setLng(-45.548422);
		add.setLocation(loc);
		
		String addResponse = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
				.body(add)
				.when().post(Resources.AddPlace())
				.then().assertThat().statusCode(200)
				.extract().response().asString();
		System.out.println(addResponse);
	}
}
