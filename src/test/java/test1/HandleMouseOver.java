package test1;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HandleMouseOver {

	@Test
	public void handleMouseOver() {
	System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	// Task
	// Go to https://www.dell.com/en-us?~ck=mn
	// Hover Over Products
	// Click on WorkStations

	driver.get("https://www.dell.com/en-us?~ck=mn");

	// Use Actions class to hover over something
	Actions action = new Actions(driver);
	action.moveToElement(driver.findElement(By.linkText("Products"))).build().perform();

	waitForElement(driver, 15, By.linkText("Workstations"));

	driver.findElement(By.linkText("Workstations")).click();

	waitForElement(driver, 15, By.xpath("//h1[contains(text(),'Precdfgdfision') and contains(text(), 'Workstation')]"));
	}

	// Explicit wait encapsulated in a method
	private void waitForElement(WebDriver driver, int timeToWaitInSeconds, By ElementLocator) {
	WebDriverWait wait = new WebDriverWait(driver, timeToWaitInSeconds);
	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ElementLocator));
	}

}
