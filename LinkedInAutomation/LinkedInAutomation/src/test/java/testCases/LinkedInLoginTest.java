package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class LinkedInLoginTest {
	WebDriver driver;

	@BeforeClass
	public void setUp() {

		// Initialize WebDriver
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}

	@Test(priority = 1)
	public void testValidLogin() {
		// Navigate to LinkedIn
		driver.get("https://www.linkedin.com/");

		// Enter valid credentials and click login
		driver.findElement(By.id("session_key")).sendKeys("automationjava123@gmail.com");
		driver.findElement(By.id("session_password")).sendKeys("Auto@123456");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();

		// Verify successful login
		WebElement profileNav = driver.findElement(By.xpath("//nav[contains(@class,'global-nav__nav--signedin')]"));
		Assert.assertTrue(profileNav.isDisplayed(), "Login failed");
	}

	@Test(priority = 2)
	public void testInvalidLogin() {
		// Navigate to LinkedIn
		driver.get("https://www.linkedin.com/");

		// Enter invalid credentials and click login
		driver.findElement(By.id("session_key")).sendKeys("invalid_username");
		driver.findElement(By.id("session_password")).sendKeys("invalid_password");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();

		// Verify error message for invalid login
		WebElement errorMsg = driver.findElement(By.xpath("//span[contains(@class,'form__subtext--error')]"));
		Assert.assertTrue(errorMsg.isDisplayed(), "Error message for invalid login not displayed");
	}

	@Test(priority = 3)
	public void testEmptyFieldsLogin() {
		// Navigate to LinkedIn
		driver.get("https://www.linkedin.com/");

		// Click login without entering any credentials
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();

		// Verify error message for empty fields
		WebElement errorMsg = driver.findElement(By.xpath("//span[contains(@class,'form__subtext--error')]"));
		Assert.assertTrue(errorMsg.isDisplayed(), "Error message for empty fields not displayed");
	}

	@Test(priority = 4)
	public void testRememberMeOption() {
		// Navigate to LinkedIn
		driver.get("https://www.linkedin.com/");

		// Enter valid credentials, enable Remember Me, and click login
		driver.findElement(By.id("session_key")).sendKeys("your_valid_username");
		driver.findElement(By.id("session_password")).sendKeys("your_valid_password");
		driver.findElement(By.name("remember")).click();
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();

		// Verify if Remember Me functionality works
		WebElement rememberMeCheckbox = driver.findElement(By.name("remember"));
		Assert.assertTrue(rememberMeCheckbox.isSelected(), "Remember Me functionality not working");
	}

	@Test(priority = 5)
	public void testPasswordReset() {
		// Navigate to LinkedIn
		driver.get("https://www.linkedin.com/");

		// Click on the "Forgot password?" link
		driver.findElement(By.linkText("Forgot password?")).click();

		// Verify if the password reset page is loaded
		WebElement resetPageHeader = driver.findElement(By.xpath("//h2[text()='Reset your password']"));
		Assert.assertTrue(resetPageHeader.isDisplayed(), "Password reset page not loaded");
	}

	@Test(priority = 6)
	public void testLoginPageAccessibility() {
		// Navigate to LinkedIn login page
		driver.get("https://www.linkedin.com/login");

		// Verify if the login page is accessible and loads correctly
		WebElement loginForm = driver.findElement(By.id("login"));
		Assert.assertTrue(loginForm.isDisplayed(), "Login page not accessible or not loaded correctly");
	}

	@Test(priority = 7)
	public void testLoginRedirect() {
		// Navigate to LinkedIn
		driver.get("https://www.linkedin.com/");

		// Click on "Join now" link
		driver.findElement(By.linkText("Join now")).click();

		// Verify if the registration page is loaded
		WebElement registrationPageHeader = driver.findElement(By.xpath("//h2[text()='Be great at what you do']"));
		Assert.assertTrue(registrationPageHeader.isDisplayed(), "Registration page not loaded");
	}

	@Test(priority = 8)
	public void testSessionExpiration() {
		// Navigate to LinkedIn and login
		driver.get("https://www.linkedin.com/");
		driver.findElement(By.id("session_key")).sendKeys("your_valid_username");
		driver.findElement(By.id("session_password")).sendKeys("your_valid_password");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();

		// Simulate session expiration (by deleting cookies)
		driver.manage().deleteAllCookies();

		// Refresh the page and verify if redirected to the login page
		driver.navigate().refresh();
		WebElement loginForm = driver.findElement(By.id("login"));
		Assert.assertTrue(loginForm.isDisplayed(), "Session expiration not handled properly");
	}

	@Test(priority = 9)
	public void testLoginWithUpperCaseUsername() {
		// Navigate to LinkedIn
		driver.get("https://www.linkedin.com/");

		// Enter username in uppercase
		driver.findElement(By.id("session_key")).sendKeys("YOUR_VALID_USERNAME");

		// Enter valid password and click login
		driver.findElement(By.id("session_password")).sendKeys("your_valid_password");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();

		// Verify successful login
		WebElement profileNav = driver.findElement(By.xpath("//nav[contains(@class,'global-nav__nav--signedin')]"));
		Assert.assertTrue(profileNav.isDisplayed(), "Login failed with uppercase username");
	}

	@Test(priority = 10)
	public void testLoginWithUpperCasePassword() {
		// Navigate to LinkedIn
		driver.get("https://www.linkedin.com/");

		// Enter valid username and password in uppercase
		driver.findElement(By.id("session_key")).sendKeys("your_valid_username");
		driver.findElement(By.id("session_password")).sendKeys("YOUR_VALID_PASSWORD");

		// Click login
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();

		// Verify successful login
		WebElement profileNav = driver.findElement(By.xpath("//nav[contains(@class,'global-nav__nav--signedin')]"));
		Assert.assertTrue(profileNav.isDisplayed(), "Login failed with uppercase password");
	}

	@Test(priority = 11)
	public void testLoginWithRememberMeEnabled() {
		// Navigate to LinkedIn
		driver.get("https://www.linkedin.com/");

		// Enter valid credentials, enable Remember Me, and click login
		driver.findElement(By.id("session_key")).sendKeys("your_valid_username");
		driver.findElement(By.id("session_password")).sendKeys("your_valid_password");
		driver.findElement(By.name("remember")).click();
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();

		// Verify successful login
		WebElement profileNav = driver.findElement(By.xpath("//nav[contains(@class,'global-nav__nav--signedin')]"));
		Assert.assertTrue(profileNav.isDisplayed(), "Login failed with Remember Me enabled");
	}

	@Test(priority = 12)
	public void testLoginWithIncorrectCaptcha() {
		// Navigate to LinkedIn
		driver.get("https://www.linkedin.com/");

		// Enter valid credentials
		driver.findElement(By.id("session_key")).sendKeys("your_valid_username");
		driver.findElement(By.id("session_password")).sendKeys("your_valid_password");

		// Enter incorrect captcha (assuming there's a captcha)
		driver.findElement(By.id("captcha")).sendKeys("incorrect_captcha");

		// Click login
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();

		// Verify if error message for incorrect captcha is displayed
		WebElement errorMsg = driver.findElement(By.xpath("//span[contains(@class,'error-for-captcha')]"));
		Assert.assertTrue(errorMsg.isDisplayed(), "Error message for incorrect captcha not displayed");
	}

	@Test(priority = 13)
	public void testLoginWithInvalidCredentialsMultipleTimes() {
		// Navigate to LinkedIn
		driver.get("https://www.linkedin.com/");

		// Attempt login with invalid credentials multiple times
		for (int i = 0; i < 3; i++) {
			driver.findElement(By.id("session_key")).sendKeys("invalid_username");
			driver.findElement(By.id("session_password")).sendKeys("invalid_password");
			driver.findElement(By.xpath("//button[text()='Sign in']")).click();

			// Verify if error message for invalid login is displayed
			WebElement errorMsg = driver.findElement(By.xpath("//span[contains(@class,'form__subtext--error')]"));
			Assert.assertTrue(errorMsg.isDisplayed(), "Error message for invalid login not displayed");

			// Clear input fields for next attempt
			driver.findElement(By.id("session_key")).clear();
			driver.findElement(By.id("session_password")).clear();
		}
	}

	@Test(priority = 14)
	public void testLoginWithDisabledAccount() {
		// Navigate to LinkedIn
		driver.get("https://www.linkedin.com/");

		// Enter disabled account credentials and click login
		driver.findElement(By.id("session_key")).sendKeys("disabled_account_username");
		driver.findElement(By.id("session_password")).sendKeys("disabled_account_password");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();

		// Verify if error message for disabled account is displayed
		WebElement errorMsg = driver.findElement(By.xpath("//span[contains(@class,'form__subtext--error')]"));
		Assert.assertTrue(errorMsg.isDisplayed(), "Error message for disabled account not displayed");
	}

	@Test(priority = 15)
	public void testLoginWithExpiredSession() {
		// Simulate an expired session (e.g., by manually invalidating the session
		// cookie)
		// For demonstration purposes, this test will navigate to the LinkedIn homepage
		// assuming the session is already expired

		// Navigate to LinkedIn homepage
		driver.get("https://www.linkedin.com/");

		// Verify if redirected to the login page due to the expired session
		WebElement loginForm = driver.findElement(By.id("login"));
		Assert.assertTrue(loginForm.isDisplayed(), "Session expiration not handled properly");
	}

	@Test(priority = 16)
	public void testLoginWithSpecialCharacters() {
		// Navigate to LinkedIn
		driver.get("https://www.linkedin.com/");

		// Enter username with special characters
		driver.findElement(By.id("session_key")).sendKeys("special@username");

		// Enter valid password and click login
		driver.findElement(By.id("session_password")).sendKeys("your_valid_password");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();

		// Verify successful login
		WebElement profileNav = driver.findElement(By.xpath("//nav[contains(@class,'global-nav__nav--signedin')]"));
		Assert.assertTrue(profileNav.isDisplayed(), "Login failed with special characters in username");
	}

	@Test(priority = 17)
	public void testLoginWithIncorrectEmailFormat() {
		// Navigate to LinkedIn
		driver.get("https://www.linkedin.com/");

		// Enter invalid email format
		driver.findElement(By.id("session_key")).sendKeys("invalid_email_format");

		// Enter valid password and click login
		driver.findElement(By.id("session_password")).sendKeys("your_valid_password");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();

		// Verify error message for incorrect email format
		WebElement errorMsg = driver.findElement(By.xpath("//span[contains(@class,'error-for-email')]"));
		Assert.assertTrue(errorMsg.isDisplayed(), "Error message for incorrect email format not displayed");
	}

	@Test(priority = 18)
	public void testLoginWithEmptyPassword() {
		// Navigate to LinkedIn
		driver.get("https://www.linkedin.com/");

		// Enter valid username and empty password
		driver.findElement(By.id("session_key")).sendKeys("your_valid_username");
		driver.findElement(By.id("session_password")).sendKeys("");

		// Click login
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();

		// Verify error message for empty password
		WebElement errorMsg = driver.findElement(By.xpath("//span[contains(@class,'error-for-password')]"));
		Assert.assertTrue(errorMsg.isDisplayed(), "Error message for empty password not displayed");
	}

	@Test(priority = 19)
	public void testLoginWithExpiredCredentials() {
		// Navigate to LinkedIn
		driver.get("https://www.linkedin.com/");

		// Enter expired credentials and click login
		driver.findElement(By.id("session_key")).sendKeys("expired_username");
		driver.findElement(By.id("session_password")).sendKeys("expired_password");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();

		// Verify error message for expired credentials
		WebElement errorMsg = driver.findElement(By.xpath("//span[contains(@class,'form__subtext--error')]"));
		Assert.assertTrue(errorMsg.isDisplayed(), "Error message for expired credentials not displayed");
	}

	@Test(priority = 20)
	public void testLoginWithLockedAccount() {
		// Navigate to LinkedIn
		driver.get("https://www.linkedin.com/");

		// Enter locked account credentials and click login
		driver.findElement(By.id("session_key")).sendKeys("locked_account_username");
		driver.findElement(By.id("session_password")).sendKeys("locked_account_password");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();

		// Verify error message for locked account
		WebElement errorMsg = driver.findElement(By.xpath("//span[contains(@class,'form__subtext--error')]"));
		Assert.assertTrue(errorMsg.isDisplayed(), "Error message for locked account not displayed");
	}

	@Test(priority = 21)
	public void testLoginWithUnconfirmedEmail() {
		// Navigate to LinkedIn
		driver.get("https://www.linkedin.com/");

		// Enter unconfirmed email and click login
		driver.findElement(By.id("session_key")).sendKeys("unconfirmed_email@example.com");
		driver.findElement(By.id("session_password")).sendKeys("your_password");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();

		// Verify error message for unconfirmed email
		WebElement errorMsg = driver.findElement(By.xpath("//span[contains(@class,'form__subtext--error')]"));
		Assert.assertTrue(errorMsg.isDisplayed(), "Error message for unconfirmed email not displayed");
	}

	@Test(priority = 22)
	public void testLoginWithExpiredVerificationCode() {
		// Navigate to LinkedIn
		driver.get("https://www.linkedin.com/");

		// Enter email and expired verification code and click login
		driver.findElement(By.id("session_key")).sendKeys("your_email@example.com");
		driver.findElement(By.id("verification_code")).sendKeys("expired_code");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();

		// Verify error message for expired verification code
		WebElement errorMsg = driver.findElement(By.xpath("//span[contains(@class,'form__subtext--error')]"));
		Assert.assertTrue(errorMsg.isDisplayed(), "Error message for expired verification code not displayed");
	}

	@Test(priority = 23)
	public void testLoginWithInvalidCredentialsFormat() {
		// Navigate to LinkedIn
		driver.get("https://www.linkedin.com/");

		// Enter invalid credentials format and click login
		driver.findElement(By.id("session_key")).sendKeys("invalid@usern^ame");
		driver.findElement(By.id("session_password")).sendKeys("invalid@pa$$word");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();

		// Verify error message for invalid credentials format
		WebElement errorMsg = driver.findElement(By.xpath("//span[contains(@class,'form__subtext--error')]"));
		Assert.assertTrue(errorMsg.isDisplayed(), "Error message for invalid credentials format not displayed");
	}

	@Test(priority = 24)
	public void testLoginWithWhitespaceInCredentials() {
		// Navigate to LinkedIn
		driver.get("https://www.linkedin.com/");

		// Enter credentials with leading/trailing whitespaces and click login
		driver.findElement(By.id("session_key")).sendKeys("  your_email@example.com  ");
		driver.findElement(By.id("session_password")).sendKeys("  your_password  ");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();

		// Verify successful login
		WebElement profileNav = driver.findElement(By.xpath("//nav[contains(@class,'global-nav__nav--signedin')]"));
		Assert.assertTrue(profileNav.isDisplayed(), "Login failed with whitespace in credentials");
	}

	@Test(priority = 25)
	public void testLoginWithInvalidCaptcha() {
		// Navigate to LinkedIn
		driver.get("https://www.linkedin.com/");

		// Enter valid credentials
		driver.findElement(By.id("session_key")).sendKeys("your_valid_username");
		driver.findElement(By.id("session_password")).sendKeys("your_valid_password");

		// Enter invalid captcha
		driver.findElement(By.id("captcha")).sendKeys("invalid_captcha");

		// Click login
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();

		// Verify error message for invalid captcha
		WebElement errorMsg = driver.findElement(By.xpath("//span[contains(@class,'error-for-captcha')]"));
		Assert.assertTrue(errorMsg.isDisplayed(), "Error message for invalid captcha not displayed");
	}

	@Test(priority = 26)
	public void testLoginWithCustomErrorMessage() {
		// Navigate to LinkedIn
		driver.get("https://www.linkedin.com/");

		// Enter valid credentials
		driver.findElement(By.id("session_key")).sendKeys("your_valid_username");
		driver.findElement(By.id("session_password")).sendKeys("your_valid_password");

		// Click login
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();

		// Verify if custom error message is displayed
		WebElement errorMsg = driver.findElement(By.xpath("//span[contains(@class,'custom-error-message')]"));
		Assert.assertTrue(errorMsg.isDisplayed(), "Custom error message not displayed");
		Assert.assertEquals(errorMsg.getText(), "This account is currently inactive.",
				"Incorrect custom error message");
	}

	@AfterClass
	public void tearDown() {
		// Close the browser
		driver.quit();
	}
}