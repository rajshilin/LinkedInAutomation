package rough;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class SendConnectionRequest extends LinkedInLogin {

	@Test
	public void sendRequest() {
		setUp(); // Call the setUp method from the superclass to initialize WebDriver and log in

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		try {
			// Locate and click on the search box
			WebElement searchBox = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@aria-label='Search']")));
			searchBox.click();

			// Enter the search query
			searchBox.sendKeys("RajAutomation");
			searchBox.sendKeys(Keys.ENTER);

			// Wait for search results to load and click on the connect button
			WebElement connectButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Connect']")));
			connectButton.click();

			// Add a custom message
			String customMessage = "Hello Raj automation, How Are you?";
			WebElement customMessageBox = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@id='custom-message']")));
			customMessageBox.sendKeys(customMessage);

			// Send connection request
			WebElement sendRequestButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='ember230']")));
			sendRequestButton.click();
		} finally {
			tearDown(); // Ensure to quit WebDriver after test execution
		}
	}
}