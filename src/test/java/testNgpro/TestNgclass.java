package testNgpro;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;




public class TestNgclass{
	
 WebDriver driver;
	@BeforeSuite
	public void driversetup()
	{
		System.setProperty("webdriver.gecko.driver", "/Users/jaura/Downloads/geckodriver");
		
	}
	
	@BeforeTest
	public void initialsetup()
	{   
		driver= new FirefoxDriver();
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		
	
	}
	
	@BeforeTest
	public void openurl()
	{
		driver.get("https://www.demoblaze.com/");
		driver.manage().window().maximize();
	}
	
	
	
	
	@Test(priority=1)
	public void signup() throws InterruptedException
	{ 
		Thread.sleep(3000);
		driver.findElement(By.id("signin2")).click();
		driver.findElement(By.id("sign-username")).sendKeys("sinya");
		driver.findElement(By.id("sign-password")).sendKeys("password");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"signInModal\"]/div/div/div[3]/button[2]")).click();
Thread.sleep(3000);
Alert al=driver.switchTo().alert();

Thread.sleep(2000);
String a = al.getText();
 
System.out.println("Alert displayed is "+ a);
al.accept(); 
Assert.assertEquals(a, "Sign up successful.");
	}
	@Test(priority=2, dependsOnMethods= {"signup"})
	public void login() throws InterruptedException
	{
		Thread.sleep(3000);
driver.findElement(By.id("login2")).click();
		
		driver.findElement(By.id("loginusername")).sendKeys("sinya");
		
			
			driver.findElement(By.id("loginpassword")).sendKeys("password");
			Thread.sleep(3000);
			 driver.findElement(By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[2]")).click();
			 Thread.sleep(5000);
			 String actual= driver.findElement(By.id("logout2")).getText();
			 Assert.assertEquals(actual, "Log out");
	System.out.println("Login Successful");
	}
	
	@Test(priority=3, dependsOnMethods= {"login"})
	public void laptopselect() throws InterruptedException
	{
		Thread.sleep(3000);
		driver.findElement(By.linkText("Laptops")).click();
		Thread.sleep(3000);
	    driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/div[3]/div/div/h4/a")).click();
	    System.out.println("Product is selected");
	    Thread.sleep(3000);
	    Boolean a=driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/div[2]/div/a")).isDisplayed();
	    //driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/div[2]/div/a")).click();
	    if (a=true)
	    	System.out.println("ADD TO CART page gets displayed");
	}
	
	@Test(priority=4, dependsOnMethods= {"login"})
	public void addCartAlert() throws InterruptedException
	{ 
		driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/div[2]/div/a")).click();
		 Thread.sleep(3000);
	Alert al=driver.switchTo().alert();
   String a = al.getText();
    System.out.println("Alert displayed is "+ a);
    Assert.assertEquals(a, "Product added.");
    al.accept();
    
	}
	
	@Test(priority = 5, dependsOnMethods= {"login"})
	public void cartPage() throws InterruptedException
	{
		driver.navigate().to("https://www.demoblaze.com/cart.html");
		Thread.sleep(3000);
		Boolean d= driver.getPageSource().contains("MacBook air");
			  Assert.assertEquals(d, true);
	    {System.out.println("Product is added to cart");}
	}
	
	@AfterTest
	public void close() throws InterruptedException
	{ Thread.sleep(3000);
		driver.close();
	}
	
}
	
	
