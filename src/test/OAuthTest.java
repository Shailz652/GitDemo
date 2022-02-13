package test;
import static io.restassured.RestAssured.given;

//import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.path.json.JsonPath;

public class OAuthTest {

	public static void main(String[] args) throws InterruptedException {
		
		// Opening browser, hitting URL and getting URL after login
		/*System.setProperty("webdriver.chrome.driver","C:\\My Stuff\\Work\\Code\\Webdriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("shailesh.ac652@gmail.com");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys("9411820728sku");
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		String url = driver.getCurrentUrl();*/
		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AX4XfWgSUH0UBFVTgU-yIdh0Nf7Dyp9TIU1iOMhRNvgXZBcWlB9TAO2rEUEssoJ0g8lEBQ&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none";
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
		String response = given().queryParam("access_token", access_token).
		when().log().all().get("https://rahulshettyacademy.com/getCourse.php").
		asString();
		System.out.println(response);
	}
}
