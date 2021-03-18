package com.api.basics;
import static io.restassured.RestAssured.given;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.path.json.JsonPath;

public class oAuthTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "D:\\chromeDriver\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifygdhfb");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		//login scenario using selenium-enter email
		driver.findElement(By.xpath("//div[@class='BHzsHc']")).click();
		WebElement enter_email = driver.findElement(By.xpath("//input[@type='email']"));
		enter_email.sendKeys("abc123");
		driver.findElement(By.xpath("//button[@type='button']//span[contains(text(), 'Next')]/following-sibling::div[@class='VfPpkd-RLmnJb']")).click();
		
		//login scenario using selenium-enter password
		WebElement enter_pass = driver.findElement(By.xpath("//input[@type='password']"));
		enter_pass.sendKeys("password");
		driver.findElement(By.xpath("//button[@type='button']//span[contains(text(), 'Next')]/following-sibling::div[@class='VfPpkd-RLmnJb']")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		//reterive code
		String url = driver.getCurrentUrl();
		String partial_code = url.split("code=")[1];
		String code_fetched = partial_code.split("&scope")[0];
		
		
		String access_token_response = given().urlEncodingEnabled(false)
				.queryParams("code",code_fetched)
				.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
				.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
				.queryParams("grant_type","authorization_code")
				.when().log().all()
				.post("https://www.googleapis.com/oauth2/v4/token").asString();

		JsonPath js = new JsonPath(access_token_response);
		String access_token_fetched = js.getString("access_token");

		String response = given().queryParam("access_token", access_token_fetched)
				.when().log().all()
				.get("https://rahulshettyacademy.com/getCourse.php").asString();

		System.out.println(response);

	}

}
