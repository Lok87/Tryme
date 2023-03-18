package test1;

import java.util.Random;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Deposit1 {

	

	@Test
	public void addAndVerifyDeposit() throws InterruptedException {
	
	System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
	
	WebDriver driver = new ChromeDriver();
	
	driver.get("http://techfios.com/test/billing/?ng=admin/");

	driver.findElement(By.id("username")).sendKeys("techfiosdemo@gmail.com");
	
	driver.findElement(By.name("password")).sendKeys("abc123");
	
	driver.findElement(By.name("login")).click();
	driver.findElement(By.linkText("Add Deposit")).click();
	

	Thread.sleep(3000);
	WebElement element = driver.findElement(By.name("account"));
	Select select = new Select(element);
	select.selectByIndex(8);
	//driver.findElement(By.xpath("//select[@id='account']/option[@value='Sarker']")).click();
	Random rnd = new Random();
	int randomNumber = rnd.nextInt(999);
	String description = "Shopping"+randomNumber;
	String amount = String.valueOf(randomNumber);
	
	driver.findElement(By.name("amount")).sendKeys(amount);
	driver.findElement(By.id("submit")).click();
	Thread.sleep(5000);
	By recentDepositLocator = By.linkText(description);
	WebDriverWait wait = new WebDriverWait(driver, 10);
	wait.until(ExpectedConditions.visibilityOfElementLocated(recentDepositLocator));
	boolean status = driver.findElement(recentDepositLocator).isDisplayed();
	if(status==true) {
	System.out.println("Test Passed");
	} else System.out.println("Test Failed!!");
	}

}
