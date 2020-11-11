package assignmentTestNG;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Task1 {
	WebDriver driver;
	String verifyCheckInMonth;
	String checkInDay;
	String verifyCheckOutMonth;
	String checkOutDay;

	@BeforeMethod
	public void setUp() throws InterruptedException {
		int noOfDays = 7; 

		//CODE TO GET THE CHECKIN DETAILS		
				
		Calendar cal = Calendar.getInstance();  //Code to get the Current Date
				
		Formatter fmt0 = new Formatter();
				
		fmt0.format("%tB", cal);
				
		String currentMonth = fmt0.toString();		
				
		cal.add(Calendar.DAY_OF_YEAR, noOfDays);  // Code to reach the after a week date								
				
		verifyCheckInMonth=new SimpleDateFormat("MMM").format(cal.getTime()); //code to get the checkInMonth for verify the test case
		
		//code to get check in month
		Formatter fmt = new Formatter();
				
		fmt.format("%tB", cal);
				
		String checkInMonth = fmt.toString();

		//code to get check in year	
		Date date = cal.getTime();
					
		int year=date.getYear()+1900;
				
		String checkInYear = Integer.toString(year);
				
		String checkIn = checkInMonth + " " + checkInYear;   //This is for matching check in month and year
				
		//code to get the check in date		
		int day = date.getDate();
				
		checkInDay = Integer.toString(day);
				
		//CODE TO GET THE CHECKOUT DETAILS
				
		Calendar cal1 = Calendar.getInstance();
				
		cal1.add(Calendar.DAY_OF_YEAR, noOfDays*2);
		
		verifyCheckOutMonth=new SimpleDateFormat("MMM").format(cal1.getTime()); //code to get the checkInMonth for verify the test case
		
		//code to get checkout month
		Formatter fmt1 = new Formatter();
				
		fmt1.format("%tB", cal1);
				
		String checkOutMonth = fmt1.toString();

		//code to get checkout year	
		Date date1 = cal1.getTime();
				
		int year1=date1.getYear()+1900;
				
		String checkOutYear = Integer.toString(year1);
				
		String checkOut = checkOutMonth + " " + checkOutYear; //This is for matching checkout month and year
				
		//code to get the checkout date
		int day1 = date1.getDate();	
				
		checkOutDay = Integer.toString(day1);
				
		//Code to start the Chrome Automated Driver
				
		System.setProperty("webdriver.chrome.driver", "F:/chromedriver.exe");
				
		driver = new ChromeDriver();
				
		driver.get("http://www.airbnb.com");
				
		driver.manage().window().maximize();
				
		driver.findElement(By.xpath("//*[@id=\"bigsearch-query-detached-query\"]")).sendKeys("Rome, Italy");

		//CODE TO GO AND SELECT THE CHECKIN DATE		
		driver.findElement(By.xpath("//header/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[2]")).click();
				
		String excludedCheckInMonth = driver.findElement(By.xpath("//div[contains(text(),'"+currentMonth+" 2020')]")).getText();
					
		if(!excludedCheckInMonth.equals(checkIn)) 
		{
			driver.findElement(By.xpath("//header/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/div[3]/div[4]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/button[1]/span[1]/*[1]")).click();
		}
				
		//driver.findElement(By.xpath("//div[contains(text(),'"+checkInMonth+" 2020')]")).click();
		WebDriverWait wait = new WebDriverWait(driver,30);
				
		//List <WebElement> allDates=wait.until(driver.findElements(By.xpath("/html/body/div[4]/div/div/div/div[1]/div[1]/div/header/div/div[2]/div[2]/div/div/div/form/div/div/div[3]/div[4]/section/div/div/div[1]/div/div/div/div[2]/div[2]/div/div[2]/div/table/tbody/tr/td/div/div/div")));
				
		wait.until(new ExpectedCondition<Boolean>() {
	        @Override
		    public Boolean apply(WebDriver driver) {
				int count=driver.findElements(By.xpath("/html/body/div[4]/div/div/div/div[1]/div[1]/div/header/div/div[2]/div[2]/div/div/div/form/div/div/div[3]/div[4]/section/div/div/div[1]/div/div/div/div[2]/div[2]/div/div[2]/div/table/tbody/tr/td/div/div/div")).size();
				List<WebElement> allDates = driver.findElements(By.xpath("/html/body/div[4]/div/div/div/div[1]/div[1]/div/header/div/div[2]/div[2]/div/div/div/form/div/div/div[3]/div[4]/section/div/div/div[1]/div/div/div/div[2]/div[2]/div/div[2]/div/table/tbody/tr/td/div/div/div"));  
				return allDates.size() == count; //try to make it dynamic
			}
		});
				
		Thread.sleep(2000);
		List<WebElement> allDates = driver.findElements(By.xpath("/html/body/div[4]/div/div/div/div[1]/div[1]/div/header/div/div[2]/div[2]/div/div/div/form/div/div/div[3]/div[4]/section/div/div/div[1]/div/div/div/div[2]/div[2]/div/div[2]/div/table/tbody/tr/td/div/div/div"));
				
		for(WebElement elem:allDates)
		{	
			String s1 = elem.getText();
			if(s1.equals(checkInDay)) 
			{
				elem.click();
				break;
			}
		}

		Thread.sleep(2000);
		//CODE TO GET THE CHECKOUT DATE
		String excludedCheckOutMonth = driver.findElement(By.xpath("//div[contains(text(),'"+checkInMonth+" 2020')]")).getText();
				
		System.out.println("This is check in month extracted from website " + excludedCheckOutMonth);
					
		if(!excludedCheckOutMonth.equals(checkOut)) 
		{
			driver.findElement(By.xpath("//header/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/div[3]/div[4]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/button[1]/span[1]/*[1]")).click();
		}
				
		WebDriverWait wait1 = new WebDriverWait(driver,30);
				
		wait.until(new ExpectedCondition<Boolean>() 
		{
				@Override
			public Boolean apply(WebDriver driver) 
			{
				int count1=driver.findElements(By.xpath("/html/body/div[4]/div/div/div/div[1]/div[1]/div/header/div/div[2]/div[2]/div/div/div/form/div/div/div[3]/div[4]/section/div/div/div[1]/div/div/div/div[2]/div[2]/div/div[2]/div/table/tbody/tr/td/div/div/div")).size();
				List<WebElement> allDates1 = driver.findElements(By.xpath("/html/body/div[4]/div/div/div/div[1]/div[1]/div/header/div/div[2]/div[2]/div/div/div/form/div/div/div[3]/div[4]/section/div/div/div[1]/div/div/div/div[2]/div[2]/div/div[2]/div/table/tbody/tr/td/div/div/div"));  
				return allDates1.size() == count1; //try to make it dynamic
			}
		});
				
		Thread.sleep(2000);
		List<WebElement> allDates1 = driver.findElements(By.xpath("/html/body/div[4]/div/div/div/div[1]/div[1]/div/header/div/div[2]/div[2]/div/div/div/form/div/div/div[3]/div[4]/section/div/div/div[1]/div/div/div/div[2]/div[2]/div/div[2]/div/table/tbody/tr/td/div/div/div"));
				
		for(WebElement elem:allDates1)
		{	
			if(elem.getText().equals(checkOutDay)) 
				{
					elem.click();
					break;
				}
		}

				
		Thread.sleep(2000);
				
		driver.findElement(By.xpath("//div[contains(text(),'Add guests')]")).click();
				
		Thread.sleep(2000);
				
		driver.findElement(By.xpath("/html/body/div[4]/div/div/div/div[1]/div[1]/div/header/div/div[2]/div[2]/div/div/div/form/div/div/div[5]/div[2]/div/section/div/div/div[1]/div[2]/button[2]")).click();
				
		Thread.sleep(2000);
				
		driver.findElement(By.xpath("/html/body/div[4]/div/div/div/div[1]/div[1]/div/header/div/div[2]/div[2]/div/div/div/form/div/div/div[5]/div[3]/div/section/div/div/div[1]/div[2]/button[2]")).click();
				
		Thread.sleep(2000);
				
		driver.findElement(By.xpath("/html/body/div[4]/div/div/div/div[1]/div[1]/div/header/div/div[2]/div[2]/div/div/div/form/div/div/div[5]/div[3]/div/section/div/div/div[2]/div[2]/button[2]")).click();
				
		Thread.sleep(2000);
				
		driver.findElement(By.xpath("/html/body/div[4]/div/div/div/div[1]/div[1]/div/header/div/div[2]/div[2]/div/div/div/form/div/div/div[5]/div[4]/button/span[1]/span")).click();
				
		Thread.sleep(5000);

	}
	
	@Test   //Test to verify applied filters
	public void verifyAppliedFilterTest() throws InterruptedException {
		
		String expectedDateFilter;
		Thread.sleep(3000);
		if(verifyCheckInMonth.equals(verifyCheckOutMonth)) {
			expectedDateFilter = verifyCheckInMonth + " " + checkInDay + " – " + checkOutDay;
		}
		else 
		{
			expectedDateFilter = verifyCheckInMonth + " " + checkInDay + " – " + verifyCheckOutMonth + checkOutDay;
		}
		System.out.println(expectedDateFilter);
		Thread.sleep(2000);
		String actualDateFilter=driver.findElement(By.xpath("/html/body/div[4]/div/div/div/div[1]/div[1]/div/header/div/div[2]/div[1]/div/button[2]/div")).getText();
		System.out.println(actualDateFilter);
		Thread.sleep(2000);
		String guests=driver.findElement(By.xpath("//header/div[1]/div[2]/div[1]/div[1]/button[3]/div[1]")).getText();
		Thread.sleep(2000);
		String location=driver.findElement(By.xpath("/html/body/div[4]/div/div/div/div[1]/div[1]/div/header/div/div[2]/div[1]/div/button[1]/div")).getText();
		Thread.sleep(2000);
		Assert.assertEquals(actualDateFilter, expectedDateFilter);
		Thread.sleep(2000);
		Assert.assertEquals(guests, "3 guests");
		Thread.sleep(2000);
		Assert.assertEquals(location, "Rome");
		Thread.sleep(2000);
	}
	
	
	@Test  //Test to verify that the properties can allocate minimum number of guests
	public void verifyPropertiesTest() throws InterruptedException {
		
		List <WebElement> allSuggestions = driver.findElements(By.xpath("/html/body/div[4]/div/div/div/div[1]/main/div/div/div[1]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div/div/div/div/div/div/div/div/div/a"));
		
		Thread.sleep(3000);
		
		for(WebElement elem1:allSuggestions)
		{	
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(0,250)");
			elem1.click();
			Thread.sleep(7000);
		    ArrayList <String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		    Thread.sleep(2000);
		    driver.switchTo().window(tabs2.get(1));
		    Thread.sleep(2000);
			String noOfGuests=driver.findElement(By.xpath("//span[contains(text(),' guests')]")).getText();
			Thread.sleep(2000);
			String convertNoOfGuests = noOfGuests.replaceAll("[^0-9]", "");
			int intnoofguests = Integer.parseInt(convertNoOfGuests);
			Assert.assertFalse(intnoofguests < 3);
			driver.close();
		}
		Thread.sleep(2000);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
