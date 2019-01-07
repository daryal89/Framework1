package com.Framework.java;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestBase {

	WebDriver driver;
	Properties prop;

	@BeforeMethod
	public void bTest() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\creat\\eclipse-workspace\\Framework\\src\\main\\resources\\configfiles\\config.properties");
		prop.load(fis);

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\creat\\eclipse-workspace\\Framework\\src\\main\\resources\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.get("https://www.facebook.com");
	}

	@Test(priority = 1)
	public void login() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(prop.getProperty("username"));
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(prop.getProperty("password"));
		driver.findElement(By.xpath("//input[@value='Log In']")).click();

		System.out.println("Test ran successful");
	}

	@Test(priority = 0)
	public void register() {
		driver.findElement(By.name("firstname")).sendKeys(prop.getProperty("fname"));
		driver.findElement(By.name("lastname")).sendKeys(prop.getProperty("lname"));
		driver.findElement(By.name("reg_email__")).sendKeys(prop.getProperty("email"));
		driver.findElement(By.name("reg_email_confirmation__")).sendKeys(prop.getProperty("email"));
		driver.findElement(By.name("reg_passwd__")).sendKeys(prop.getProperty("pw"));

		Select s1 = new Select(driver.findElement(By.name("birthday_month")));
		s1.selectByIndex(3);

		Select s2 = new Select(driver.findElement(By.name("birthday_day")));
		s2.selectByValue("5");

		Select s3 = new Select(driver.findElement(By.name("birthday_year")));
		s3.selectByVisibleText("1992");

		driver.findElement(By.xpath("//input[@type='radio' and @value='1']")).click();

		driver.findElement(By.xpath("//button[@name='websubmit']")).click();

		System.out.println("registration passed");

	}

	@AfterMethod()
	public void tearDown() {
		driver.close();
	}
}
