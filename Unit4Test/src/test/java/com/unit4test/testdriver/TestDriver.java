package com.unit4test.testdriver;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.unit4test.utility.Constants;

public class TestDriver {

	public WebDriver driver;
	public ExtentReports report;
	public ExtentTest test;
	public ExtentHtmlReporter htmlReporter;
	public DateFormat dateformat = new SimpleDateFormat(" MM-dd-yyyy HHmmss");
	public Date date = new Date();
	
	@BeforeTest
	public void config() {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(Constants.EXTENTREPORTPATH + dateformat.format(date) + ".html");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle("Unit 4 Login Test");
        htmlReporter.config().setEncoding("utf-8");
		report = new ExtentReports();
		report.attachReporter(htmlReporter);
	}

	@Parameters("browser")
	@BeforeTest
	public void setBrowser(String browser)
	{
		if (browser.equalsIgnoreCase("Firefox")) {
			System.out.println("Launching Firefox");
			System.setProperty("webdriver.firefox.marionette", Constants.FIREFOXPATH);
			DesiredCapabilities dc = DesiredCapabilities.firefox();
			dc.setPlatform(Platform.WINDOWS);
			driver = new FirefoxDriver();
		}
		else if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", 
					Constants.CHROMEPATH);
			driver = new ChromeDriver();

		}
		else {
			throw new IllegalArgumentException("Invalid browser value!!");
		}
		driver.get(Constants.URL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		if(result.getStatus()==ITestResult.FAILURE) {
			try {
			    Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
			    String browserName = cap.getBrowserName().toLowerCase();
			    String version = cap.getVersion().toString();
				TakesScreenshot ts = (TakesScreenshot) driver;
				File source = ts.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(source,new File(Constants.SCREENSHOTS + result.getName() + dateformat.format(date) +" "+ browserName +" "+ version + Constants.SCREENSHOTEXTENSION));
			} catch (Exception e) {
				e.printStackTrace();
			}
			test.fail("Test Case Failed");
		}
		report.flush();
	}
	
	@AfterTest
	public void closing() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}
}
