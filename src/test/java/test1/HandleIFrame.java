package test1;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HandleIFrame {

	@Test
	public void handleiFrames() {
	System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	

	driver.get("https://docs.oracle.com/javase/8/docs/api/");
	
	driver.switchTo().frame("packageListFrame");

	driver.findElement(By.linkText("java.awt.color")).click();

	
	driver.switchTo().defaultContent();


	driver.switchTo().frame(1);
	
	waitForElement(driver, 40, By.linkText("ColorSpace"));
	driver.findElement(By.linkText("ColorSpace")).click();

	driver.switchTo().defaultContent();
	driver.switchTo().frame(2);
	driver.findElement(By.linkText("Serializable")).click();
	}

	//Explicit wait encapsulated in a method
	private void waitForElement(WebDriver driver, int timeToWaitInSeconds, By ElementLocator) {
	WebDriverWait wait = new WebDriverWait(driver, timeToWaitInSeconds);
	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ElementLocator));
	}

}
