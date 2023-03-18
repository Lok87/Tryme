package test1;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTest {
	@Test
	public void validUsersShouldBeAbleToLogIn() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("http://techfios.com/test/billing/?ng=admin/");

		driver.findElement(By.id("username")).sendKeys("techfiosdemo@gmail.com");
		Thread.sleep(2000);
		driver.findElement(By.name("password")).sendKeys("abc123");
		Thread.sleep(2000);
		driver.findElement(By.name("login")).click();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h2[contains(text(),'Dashboard')]"))));
		driver.close();
		driver.quit();
	}

}
