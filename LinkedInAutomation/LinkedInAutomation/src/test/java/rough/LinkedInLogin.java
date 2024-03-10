package rough;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class LinkedInLogin {

	public static String username = "automationjava123@gmail.com";
	public static String password = "Auto@123456";
	public static WebDriver driver;

	@BeforeClass
	public static void setUp() {
		// Initialize ChromeDriver
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Navigate to LinkedIn
		driver.get("https://www.linkedin.com/");

		// Login
		WebElement email = driver.findElement(By.xpath("//input[@id='session_key']"));
		WebElement pass = driver.findElement(By.xpath("//input[@id='session_password']"));
		WebElement submitBtn = driver.findElement(By.xpath("//button[@type='submit']"));

		email.sendKeys(username);
		pass.sendKeys(password);
		submitBtn.click();
	}

	@AfterClass
	public static void tearDown() {
		// Quit WebDriver after all tests are executed
		if (driver != null) {
			driver.quit();
		}
	}
}