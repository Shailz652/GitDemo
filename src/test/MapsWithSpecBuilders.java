package test;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import conf.Config;
import conf.Resources;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddLocation;
import pojo.AddPlace;

public class MapsWithSpecBuilders {
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
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
		.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		
		ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		RequestSpecification request = given().log().all().spec(req).body(add);
		String addResponse = request.when().post(Resources.AddPlace())
				.then().spec(res)
				.extract().response().asString();
		System.out.println(addResponse);
	}
}
