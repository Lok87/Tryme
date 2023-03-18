package test1;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ScrollUpDown {

	@Test
	public void scrollUpOrDown() throws InterruptedException {
	System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	//Actions action = new Actions(driver);
	driver.get("https://www.yahoo.com/");
	scrollDown(driver);
	Thread.sleep(2000);
	scrollUp(driver);
	}

	private void scrollDown(WebDriver a) {
	((JavascriptExecutor)a).executeScript("scroll(0,2000)");
	}
	private void scrollUp(WebDriver a) {
	JavascriptExecutor js = (JavascriptExecutor)a;
	js.executeScript("scroll(2000,0)");
	}
}
