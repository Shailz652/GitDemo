package test;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
//import pojo.API;
import pojo.CourseDetails;
import pojo.GetCourse;

public class APITestPojo {

	public static void main(String[] args) throws InterruptedException {
		
		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AX4XfWgsu9yy-LK79D0b4RgWiz34UFc-wgd6aD1qsT6YeoLAqpKVCtkVA-pr65GXCgvO6g&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none";
		String partialCode = url.split("code=")[1];
		String code = partialCode.split("&scope=")[0];
		System.out.println("Code is "+code);
		
		// After Extracting Code from the URL and then using it on next API
		String tokenResponse = given().urlEncodingEnabled(false).
				queryParams("code", code).
				queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
				queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W").
				queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php").
				queryParams("grant_type", "authorization_code").
		when().log().all().post("https://www.googleapis.com/oauth2/v4/token").
		asString();
		JsonPath js = new JsonPath(tokenResponse);
		String access_token = js.get("access_token");
		System.out.println("Access Token is "+access_token);
		
		// After Extracting token from response and then using it on next API
		GetCourse gcResponse = given().queryParam("access_token", access_token).expect().defaultParser(Parser.JSON).
		when().get("https://rahulshettyacademy.com/getCourse.php").
		as(GetCourse.class);
		System.out.println(gcResponse.getInstructor());
		System.out.println(gcResponse.getLinkedIn());
		
		//List<API> apiCourses = gcResponse.getCourses().getApi(); // with separate child class
		List<CourseDetails> apiCourses = gcResponse.getCourses().getApi(); // with single child class for all
		for(int i=0; i<apiCourses.size(); i++)
		{
			String course = apiCourses.get(i).getCourseTitle();
			if(course.contains("SoapUI"))
				System.out.println("Price of Course "+course+" is "+apiCourses.get(i).getPrice());
		}
		
		System.out.println("------------------------------");
		String [] courseTitles = {"Selenium Webdriver Java", "Cypress", "Protractor"};
		ArrayList<String> courseA = new ArrayList<String>();
		
		List<CourseDetails> webCourses = gcResponse.getCourses().getWebAutomation();
		System.out.println("Web Automation Category:");
		for(int i=0; i<webCourses.size(); i++)
		{
			String course = webCourses.get(i).getCourseTitle();
			float price = webCourses.get(i).getPrice();
			System.out.println("Course "+course+" has price of "+price);
			courseA.add(course);
		}
		List<String> courseB = Arrays.asList(courseTitles);
		Assert.assertTrue(courseA.equals(courseB));
		
		System.out.println("API Category:");
		for(int i=0; i<apiCourses.size(); i++)
		{
			String course = apiCourses.get(i).getCourseTitle();
			float price = apiCourses.get(i).getPrice();
			System.out.println("Course "+course+" has price of "+price);
		}
		
		List<CourseDetails> mobileCourses = gcResponse.getCourses().getMobile();
		System.out.println("Mobile Category:");
		for(int i=0; i<mobileCourses.size(); i++)
		{
			String course = mobileCourses.get(i).getCourseTitle();
			float price = mobileCourses.get(i).getPrice();
			System.out.println("Course "+course+" has price of "+price);
		}
	}
}
