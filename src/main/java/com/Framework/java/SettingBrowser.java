package com.Framework.java;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

public class SettingBrowser {

	public WebDriver driver;

	@Test
	public void setUp() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\creat\\eclipse-workspace\\Framework\\src\\main\\resources\\configfiles\\config.properties");
		prop.load(fis);

		String browserName = prop.getProperty("browser");
		System.out.println(browserName);

	
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\creat\\eclipse-workspace\\Framework\\src\\main\\resources\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\creat\\eclipse-workspace\\Framework\\src\\main\\resources\\drivers\\geckodriver.exe");
			driver = new ChromeDriver();
		} else {
			System.setProperty("webdriver.ie.driver",
					"C:\\Users\\creat\\eclipse-workspace\\Framework\\src\\main\\resources\\drivers\\IEdriver.exe");
			driver = new InternetExplorerDriver();
		}
		
	}
	
	

}
