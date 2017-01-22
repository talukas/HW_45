package core;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Sign_Up_Validation {
	
	 WebDriver driver;
	 
	 @BeforeMethod()
		public void Validate_html_TitleLaunch_Sign_Up_page(){
		 
		 System.setProperty("webdriver.gecko.driver", "./src/test/resources/geckodriver");
			
			driver = new FirefoxDriver(); 
		 	driver.manage().window().maximize();
		 	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 	driver.get("http://alex.academy/exercises/signup/v1/");
	 }
		 	@Test(priority=1) //T.C 01.01.01
			public void  Navigate_Main_page() {

			System.out.println(driver.getTitle());
			AssertJUnit.assertTrue(driver.getTitle().contains("Sign Up"));
			}

		 	@Test(priority=2) //T.C 02.01.01
			public void  Main_page_Title_Validation() {
		 		
		 	String  Title = driver.findElement(By.id("id_f_title")).getText();  	
	
		 	System.out.println(Title);
		 	AssertJUnit.assertTrue(Title.contains("Sign Up"));
			
			}

		 	@Test(priority=3, dataProvider = "Sign_Up") //T.C 28.01.01
			public void  Sign_Up_Valid_Credantials(String first_name, String last_name, String email, String phone ) throws InterruptedException {
		 		
		 	driver.findElement(By.id("id_fname")).sendKeys(first_name); 
		 	driver.findElement(By.id("id_lname")).sendKeys(last_name); 
		 	driver.findElement(By.id("id_email")).sendKeys(email); 
		 	driver.findElement(By.id("id_phone")).sendKeys(phone); 
		 	driver.findElement(By.id("id_submit_button")).click();
		 	
		 	Thread.sleep(3_000L);
		 	
		 	
		 	System.out.println(driver.getTitle());
		 	
		 	AssertJUnit.assertTrue(driver.getTitle().contains("Confirmation"));
		 	Thread.sleep(3_000L);
		 	}
		 	
		 	@Test(priority=4, dataProvider = "Sign_Up_Inv") //T.C 28.01.02
			public void  Sign_Up_Invalid_Credantials(String first_name, String last_name, String email, String phone ) throws InterruptedException {
		 		
		 	driver.findElement(By.id("id_fname")).sendKeys(first_name); 
		 	driver.findElement(By.id("id_lname")).sendKeys(last_name); 
		 	driver.findElement(By.id("id_email")).sendKeys(email); 
		 	driver.findElement(By.id("id_phone")).sendKeys(phone); 
		 	driver.findElement(By.id("id_submit_button")).click();
		 	String Error = driver.findElement(By.id("ErrorLine")).getText();
		 	Thread.sleep(1_000L);
		 	
		 	
		 	System.out.println(Error);
		 	
		 	AssertJUnit.assertTrue(Error.contains("Invalid Last Name"));
		 	
		 	}
		 	
		 	
		 	@AfterMethod
		 	  public void afterMethod() {
		 		  
		 		 driver.quit();
		 	  }

		 	@DataProvider(name="Sign_Up")
		 	public Object[][] Data()
		 	{
		 	Object[][] data=new Object [1][4];

		 	data[0][0]="Aleks";
		 	data[0][1]="Smith";
		 	data[0][2]="ts@m.com";
		 	data[0][3]="9898989898";

		 	return data;
		 	}
		 	
		 	@DataProvider(name="Sign_Up_Inv")
		 	public Object[][] Data1()
		 	{
		 	Object[][] data=new Object [1][4];

		 	data[0][0]="Aleks";
		 	data[0][1]="Wu";
		 	data[0][2]="ts@m.com";
		 	data[0][3]="9898989898";

		 	return data;
		 	}
}
