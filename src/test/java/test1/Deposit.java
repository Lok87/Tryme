package test1;

import static org.junit.Assert.assertEquals;

import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class Deposit {

	

	@Test
	public void userShouldBeAbleToAddDeposit() throws InterruptedException {
	System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
	WebDriver driver = new ChromeDriver();
	 driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	driver.get("http://techfios.com/test/billing/?ng=admin/");
	

	
	WebElement usernameField = driver.findElement(By.xpath("(//input[@class='form-control'])[1]"));
	WebElement passwordField = driver.findElement(By.xpath("//input[contains(@type, 'pass')]"));
	WebElement signInButton = driver
	.findElement(By.xpath("//*[contains(@class, 'success block') and @type='submit']"));
	
	By addDepositeButtonLocator = By.linkText("Add Deposit");
	By addDepositPanelHeaderLocator = By.xpath("//h5[contains(text(), 'Add Deposit')]");

	String expectedTitle = "Login - TechFios Test Application - Billing";
	String actualTitle = driver.getTitle();

	if (expectedTitle.toLowerCase().contains(actualTitle.toLowerCase())) {
	System.out.println("Test Pass" + " Actual Title: " + actualTitle + " and Expected Title: " + expectedTitle
	+ " - Matched!!!");
	} else {
	System.out.println("Test Failed" + " Actual Title: " + actualTitle + " and Expected Title: " + expectedTitle
	+ " - Didn't Match!!!");
	throw new RuntimeException("Test Failed.");
	}

	usernameField.sendKeys("techfiosdemo@gmail.com");
	passwordField.sendKeys("abc123");
	signInButton.click();

	// Explicit Wait
	// WebDriverWait wait = new WebDriverWait(driver, 5);
	// wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(addDepositeButtonLocator));

	
	waitForElement(driver, 10, addDepositeButtonLocator);

	
	driver.findElement(addDepositeButtonLocator).click();

	
	waitForElement(driver, 10, addDepositPanelHeaderLocator);

	boolean status = false;
	try {
	status = driver.findElement(addDepositPanelHeaderLocator).isDisplayed();
	} catch (NoSuchElementException e) {
	status = false;
	}

	
	Assert.assertTrue("Add Deposit Page didn't Display", status);

	WebElement accountDropDown = driver.findElement(By.id("account"));

	// Selecting using Select Class from DropDown
	Select select = new Select(accountDropDown);
	// select.selectByValue("Lok");
	select.selectByIndex(5);

	
	Random rnd = new Random();
	int randomNumber = rnd.nextInt(9999);
	String description = "Lunch" + randomNumber;
	String amount = String.valueOf(randomNumber * 3);

	driver.findElement(By.name("description")).sendKeys(description);
	driver.findElement(By.name("amount")).sendKeys(amount);
	driver.findElement(By.id("submit")).click();

	By firstTableDataLocator = By.xpath("//a[contains(text(), '" + description + "')]");
	waitForElement(driver, 20, firstTableDataLocator);

	System.out.println(description);
	System.out.println(driver.findElement(firstTableDataLocator).getText());

	assertEquals("Did not deposit!", description, driver.findElement(firstTableDataLocator).getText());

	 driver.close();
	 driver.quit();
	}

	private void waitForElement(WebDriver driver, int i, By by) {
	WebDriverWait wait = new WebDriverWait(driver, i);
	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
	}

}
