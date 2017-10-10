package com.unit4test.testbase;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.unit4test.pageobjects.HomePage;
import com.unit4test.pageobjects.LoginPage;
import com.unit4test.testdriver.TestDriver;
import com.unit4test.utility.Constants;

/**
 * @author Dixit.Bedi
 *
 */

public class LoginTestDriverCases extends TestDriver {

	public LoginTestDriverCases() {
		super();
	}

	/**
	 * This Test Case verifies the 
	 * page opened is the expected 
	 * login page using title
	 * 
	 */
	@Test(priority=1)
	public void verifyTitle() {
		test = report.createTest("Verify Title");
		test.info("Fetching the page Title");
		//Actual Title Displayed
		String actualtitle = driver.getTitle();
		//Expected Title Displayed
		String expectedtitle = Constants.TITLE;
		test.info("Comparing the fetched title with expected title");
		//Verify the actual title is a expected title
		assertEquals(actualtitle, expectedtitle);
		test.pass("Title Verified");
	}

	/**
	 * This Test Case verifies the 
	 * login by entering the right 
	 * value in the respective fields
	 * and this test case depends on
	 * verifyTitle
	 * @throws InterruptedException 
	 * 
	 */
	@Test(priority=2,dependsOnMethods="verifyTitle")
	public void verifyLogin() throws InterruptedException  {
		test = report.createTest("Login in the Application");
		LoginPage login= PageFactory.initElements(driver, LoginPage.class);
		HomePage logout = PageFactory.initElements(driver, HomePage.class);
		test.info("Checking whether username field is displayed");
		//Verifying if User name field is displayed
		login.userName().isDisplayed();
		test.info("Clearing any previous value from the field");
		//Clearing the content of the User name field,if any
		login.userName().clear();
		//Adding wait for visual
		Thread.sleep(1000);
		test.info("Input the field value");
		//Input the User name value
		login.userName().sendKeys(Constants.USERNAME);
		test.info("Checking whether client field is displayed");
		//Verifying if Client field is displayed
		login.client().isDisplayed();
		test.info("Clearing any previous value from the field");
		//Clearing the content of the Client field,if any
		login.client().clear();
		//Adding wait for visual
		Thread.sleep(1000);
		test.info("Input the field value");
		//Input the Client value
		login.client().sendKeys(Constants.CLIENT);
		test.info("Checking whether password field is displayed");
		//Verifying if Password field is displayed
		login.password().isDisplayed();
		test.info("Clearing any previous value from the field");
		//Clearing the content of the Password field,if any
		login.password().clear();
		//Adding wait for visual
		Thread.sleep(1000);
		test.info("Input the field value");
		//Input the Password value
		login.password().sendKeys(Constants.PASSWORD);
		test.info("Checking whether login button is displayed");
		//Verifying if Login button is displayed
		login.clickLogInButton().isDisplayed();
		test.info("Clicking on the Login Button");
		//Clicking the Login button
		login.clickLogInButton().click();
		//Adding Implicit Wait
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		test.info("Verifying the Logged in user");
		//Passing the user name of the login user
		String userlogin = logout.userLogin().getText();
		//Verify the actual value is expected value
		assertEquals(userlogin,Constants.USERLOGIN);
		test.pass("User able to login");
	}

	/**
	 * This Test Case verifies the 
	 * logout by clicking the user
	 * logo on the homepage and from
	 * the list selecting logout button
	 * and logging out of the application
	 * This testcase is dependent on verifyLogin
	 * @throws InterruptedException 
	 * 
	 */
	@Test(priority=3,dependsOnMethods="verifyLogin")
	public void verifyLogout() throws InterruptedException {
		test = report.createTest("Logging out of the Application");
		HomePage logout = PageFactory.initElements(driver, HomePage.class);
		//Adding wait for visual
		Thread.sleep(1000);
		//Adding Implicit Wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		test.info("Clicking on the User Logo");
		//Clicking the User Logo for options
		logout.userlogo().click();
		//Adding wait for visual
		Thread.sleep(1000);
		test.info("Clicking on the Logout Button");
		//Clicking on Logout button
		logout.logout().click();
		//Implicit wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//Actual Title Displayed
		String actualtitle = driver.getTitle();
		//Expected Title Displayed
		String expectedtitle = Constants.TITLE;
		test.info("Verifying the Logged in user is sucessfully logged out");
		//Verify the actual title is a expected title
		assertEquals(actualtitle, expectedtitle);
		test.pass("Successfully logged out of the application");
	}

	/**
	 * This Test Case verifies the 
	 * login by entering the wrong username
	 * and right value in the other respective 
	 * fields and this test case depends on
	 * verifyLogout
	 * @throws InterruptedException 
	 * 
	 */
	@Test(priority=4,dependsOnMethods="verifyLogout")
	public void wrongUserNameEntered() throws InterruptedException {
		test = report.createTest("Wrong UserName Entered");
		LoginPage login= PageFactory.initElements(driver, LoginPage.class);
		test.info("Checking whether username field is displayed");
		//Verifying if User name field is displayed
		login.userName().isDisplayed();
		test.info("Clearing any previous value from the field");
		//Clearing the content of the User name field,if any
		login.userName().clear();
		//Adding wait for visual
		Thread.sleep(1000);
		test.info("Input the field value");
		//Input the Wrong User name value
		login.userName().sendKeys(Constants.WRONGUSERNAME);
		test.info("Checking whether client field is displayed");
		//Verifying if Client field is displayed
		login.client().isDisplayed();
		test.info("Clearing any previous value from the field");
		//Clearing the content of the Client field,if any
		login.client().clear();
		//Adding wait for visual
		Thread.sleep(1000);
		test.info("Input the field value");
		//Input the Client value
		login.client().sendKeys(Constants.CLIENT);
		//Verifying if Password field is displayed
		login.password().isDisplayed();
		//Clearing the content of the Password field,if any
		login.password().clear();
		//Adding wait for visual
		Thread.sleep(1000);
		test.info("Checking whether password field is displayed");
		//Input the Password value
		login.password().sendKeys(Constants.PASSWORD);
		test.info("Checking whether login button is displayed");
		//Verifying if Login button is displayed
		login.clickLogInButton().isDisplayed();
		test.info("Clicking on the Login Button");
		//Clicking the Login button
		login.clickLogInButton().click();
		//Implicit wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//Error Message on Incorrect Login
		String loginerror = login.loginError().getText();
		//Checks Logout if actual value is equal to expected value
		assertEquals(loginerror, Constants.ERRORMESSAGE);
		test.pass("Not Able to login with wrong details");
	}

	/**
	 * This Test Case verifies the 
	 * login by entering the wrong Client name
	 * and right value in the other respective 
	 * fields and this test case depends on
	 * verifyLogout
	 * @throws InterruptedException 
	 * 
	 */
	@Test(priority=5,dependsOnMethods="verifyLogout")
	public void wrongClientNameEntered() throws InterruptedException {
		test = report.createTest("Wrong Client Name Entered");
		LoginPage login= PageFactory.initElements(driver, LoginPage.class);
		test.info("Checking whether username field is displayed");
		//Verifying if User name field is displayed
		login.userName().isDisplayed();
		test.info("Clearing any previous value from the field");
		//Clearing the content of the User name field,if any
		login.userName().clear();
		//Adding wait for visual
		Thread.sleep(1000);
		test.info("Clearing any previous value from the field");
		//Input the User name value
		login.userName().sendKeys(Constants.USERNAME);
		test.info("Checking whether client field is displayed");
		//Verifying if Client field is displayed
		login.client().isDisplayed();
		test.info("Clearing any previous value from the field");
		//Clearing the content of the Client field,if any
		login.client().clear();
		//Adding wait for visual
		Thread.sleep(1000);
		test.info("Input the field value");
		//Input the Wrong Client value
		login.client().sendKeys(Constants.WRONGCLIENT);
		test.info("Checking whether password field is displayed");
		//Verifying if Password field is displayed
		login.password().isDisplayed();
		test.info("Clearing any previous value from the field");
		//Clearing the content of the Password field,if any
		login.password().clear();
		//Adding wait for visual
		Thread.sleep(1000);
		test.info("Input the field value");
		//Input the Password value
		login.password().sendKeys(Constants.PASSWORD);
		test.info("Checking whether login button is displayed");
		//Verifying if Login button is displayed
		login.clickLogInButton().isDisplayed();
		test.info("Clicking on the Login Button");
		//Clicking the Login button
		login.clickLogInButton().click();
		//Implicit wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//Error Message on Incorrect Login
		String loginerror = login.loginError().getText();
		//Checks Logout if actual value is equal to expected value
		assertEquals(loginerror, Constants.ERRORMESSAGE);
		test.pass("Not Able to login with wrong details");
	}

	/**
	 * This Test Case verifies the 
	 * login by entering the wrong Password
	 * and right value in the other respective 
	 * fields and this test case depends on
	 * verifyLogout
	 * @throws InterruptedException 
	 * 
	 */
	@Test(priority=6,dependsOnMethods="verifyLogout")
	public void wrongPasswordEntered() throws InterruptedException {
		test = report.createTest("Wrong Password Entered");
		LoginPage login= PageFactory.initElements(driver, LoginPage.class);
		test.info("Checking whether username field is displayed");
		//Verifying if User name field is displayed
		login.userName().isDisplayed();
		test.info("Clearing any previous value from the field");
		//Clearing the content of the User name field,if any
		login.userName().clear();
		//Adding wait for visual
		Thread.sleep(1000);
		test.info("Input the field value");
		//Input the  User name value
		login.userName().sendKeys(Constants.USERNAME);
		test.info("Checking whether client field is displayed");
		//Verifying if Client field is displayed
		login.client().isDisplayed();
		test.info("Clearing any previous value from the field");
		//Clearing the content of the Client field,if any
		login.client().clear();
		//Adding wait for visual
		Thread.sleep(1000);
		test.info("Input the field value");
		//Input the Client value
		login.client().sendKeys(Constants.CLIENT);
		test.info("Checking whether password field is displayed");
		//Verifying if Password field is displayed
		login.password().isDisplayed();
		test.info("Clearing any previous value from the field");
		//Clearing the content of the Password field,if any
		login.password().clear();
		//Adding wait for visual
		Thread.sleep(1000);
		test.info("Input the field value");
		//Input the Wrong Password value
		login.password().sendKeys(Constants.WRONGPASSWORD);
		test.info("Checking whether login button is displayed");
		//Verifying if Login button is displayed
		login.clickLogInButton().isDisplayed();
		test.info("Clicking on the Login Button");
		//Clicking the Login button
		login.clickLogInButton().click();
		//Implicit wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//Error Message on Incorrect Login
		String loginerror = login.loginError().getText();
		//Checks Logout if actual value is equal to expected value
		assertEquals(loginerror, Constants.ERRORMESSAGE);
		test.pass("Not Able to login with wrong details");
	}

	/**
	 * This Test Case verifies the 
	 * login by entering the wrong User name 
	 * and Password and right value in the other 
	 * respective fields and this test case 
	 * depends on verifyLogout
	 * @throws InterruptedException 
	 * 
	 */
	@Test(priority=7,dependsOnMethods="verifyLogout")
	public void wrongUsernameClientEntered() throws InterruptedException {
		test = report.createTest("Wrong Username Client Entered");
		LoginPage login= PageFactory.initElements(driver, LoginPage.class);
		test.info("Checking whether username field is displayed");
		//Verifying if User name field is displayed
		login.userName().isDisplayed();
		test.info("Clearing any previous value from the field");
		//Clearing the content of the User name field,if any
		login.userName().clear();
		//Adding wait for visual
		Thread.sleep(1000);
		test.info("Input the field value");
		//Input the Wrong User name value
		login.userName().sendKeys(Constants.WRONGUSERNAME);
		test.info("Checking whether client field is displayed");
		//Verifying if Client field is displayed
		login.client().isDisplayed();
		test.info("Clearing any previous value from the field");
		//Clearing the content of the Client field,if any
		login.client().clear();
		//Adding wait for visual
		Thread.sleep(1000);
		test.info("Input the field value");
		//Input the Wrong Client value
		login.client().sendKeys(Constants.WRONGCLIENT);
		test.info("Checking whether password field is displayed");
		//Verifying if Password field is displayed
		login.password().isDisplayed();
		test.info("Clearing any previous value from the field");
		//Clearing the content of the Password field,if any
		login.password().clear();
		//Adding wait for visual
		Thread.sleep(1000);
		test.info("Input the field value");
		//Input the Password value
		login.password().sendKeys(Constants.PASSWORD);
		test.info("Checking whether login button is displayed");
		//Verifying if Login button is displayed
		login.clickLogInButton().isDisplayed();
		test.info("Clicking on the Login Button");
		//Clicking the Login button
		login.clickLogInButton().click();
		//Implicit wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//Error Message on Incorrect Login
		String loginerror = login.loginError().getText();
		//Checks Logout if actual value is equal to expected value
		assertEquals(loginerror, Constants.ERRORMESSAGE);
		test.pass("Not Able to login with wrong details");
	}

	/**
	 * This Test Case verifies the 
	 * login by entering the wrong Client and Password
	 * and right value in the other respective 
	 * fields and this test case depends on
	 * verifyLogout
	 * @throws InterruptedException 
	 * 
	 */
	@Test(priority=8,dependsOnMethods="verifyLogout")
	public void wrongClientPasswordEntered() throws InterruptedException {
		test = report.createTest("Wrong Client Password Entered");
		LoginPage login= PageFactory.initElements(driver, LoginPage.class);
		test.info("Checking whether username field is displayed");
		//Verifying if User name field is displayed
		login.userName().isDisplayed();
		test.info("Clearing any previous value from the field");
		//Clearing the content of the User name field,if any
		login.userName().clear();
		//Adding wait for visual
		Thread.sleep(1000);
		test.info("Input the field value");
		//Input the  User name value
		login.userName().sendKeys(Constants.USERNAME);
		test.info("Checking whether client field is displayed");
		//Verifying if Client field is displayed
		login.client().isDisplayed();
		test.info("Clearing any previous value from the field");
		//Clearing the content of the Client field,if any
		login.client().clear();
		//Adding wait for visual
		Thread.sleep(1000);
		test.info("Input the field value");
		//Input the Wrong Client value
		login.client().sendKeys(Constants.WRONGCLIENT);
		test.info("Checking whether password field is displayed");
		//Verifying if Password field is displayed
		login.password().isDisplayed();
		test.info("Clearing any previous value from the field");
		//Clearing the content of the Password field,if any
		login.password().clear();
		//Adding wait for visual
		Thread.sleep(1000);
		test.info("Input the field value");
		//Input the Wrong Password value
		login.password().sendKeys(Constants.WRONGPASSWORD);
		test.info("Checking whether login button is displayed");
		//Verifying if Login button is displayed
		login.clickLogInButton().isDisplayed();
		test.info("Clicking on the Login Button");
		//Clicking the Login button
		login.clickLogInButton().click();
		//Implicit wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//Error Message on Incorrect Login
		String loginerror = login.loginError().getText();
		//Checks Logout if actual value is equal to expected value
		assertEquals(loginerror, Constants.ERRORMESSAGE);
		test.pass("Not Able to login with wrong details");
	}

	/**
	 * This Test Case verifies the 
	 * login by entering the wrong User name and 
	 * Password and right value in the other 
	 * respective fields and this test case depends
	 * on verifyLogout
	 * @throws InterruptedException 
	 * 
	 */
	@Test(priority=9,dependsOnMethods="verifyLogout")
	public void wrongUsernamePasswordEntered() throws InterruptedException {
		test = report.createTest("Wrong Username Password Entered");
		LoginPage login= PageFactory.initElements(driver, LoginPage.class);
		test.info("Checking whether username field is displayed");
		//Verifying if User name field is displayed
		login.userName().isDisplayed();
		test.info("Clearing any previous value from the field");
		//Clearing the content of the User name field,if any
		login.userName().clear();
		//Adding wait for visual
		Thread.sleep(1000);
		test.info("Input the field value");
		//Input the Wrong User name value
		login.userName().sendKeys(Constants.WRONGUSERNAME);
		test.info("Checking whether client field is displayed");
		//Verifying if Client field is displayed
		login.client().isDisplayed();
		test.info("Clearing any previous value from the field");
		//Clearing the content of the Client field,if any
		login.client().clear();
		//Adding wait for visual
		Thread.sleep(1000);
		test.info("Input the field value");
		//Input the Client value
		login.client().sendKeys(Constants.CLIENT);
		test.info("Checking whether password field is displayed");
		//Verifying if Password field is displayed
		login.password().isDisplayed();
		test.info("Clearing any previous value from the field");
		//Clearing the content of the Password field,if any
		login.password().clear();
		//Adding wait for visual
		Thread.sleep(1000);
		test.info("Input the field value");
		//Input the Wrong Password value
		login.password().sendKeys(Constants.WRONGPASSWORD);
		test.info("Checking whether login button is displayed");
		//Verifying if Login button is displayed
		login.clickLogInButton().isDisplayed();
		test.info("Clicking on the Login Button");
		//Clicking the Login button
		login.clickLogInButton().click();
		//Implicit wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//Error Message on Incorrect Login
		String loginerror = login.loginError().getText();
		//Checks Logout if actual value is equal to expected value
		assertEquals(loginerror, Constants.ERRORMESSAGE);
		test.pass("Not Able to login with wrong details");
	}

	/**
	 * This Test Case verifies the 
	 * login by entering the wrong User name and 
	 * Password and right value in the other 
	 * respective fields and this test case depends
	 * on verifyLogout
	 * @throws InterruptedException 
	 * 
	 */
	@Test(priority=10,dependsOnMethods="verifyLogout")
	public void wrongUsernameClientPasswordEntered() throws InterruptedException {
		test = report.createTest("Wrong Username Client Password Entered");
		LoginPage login= PageFactory.initElements(driver, LoginPage.class);
		test.info("Checking whether username field is displayed");
		//Verifying if User name field is displayed
		login.userName().isDisplayed();
		test.info("Clearing any previous value from the field");
		//Clearing the content of the User name field,if any
		login.userName().clear();
		//Adding wait for visual
		Thread.sleep(1000);
		test.info("Input the field value");
		//Input the Wrong User name value
		login.userName().sendKeys(Constants.WRONGUSERNAME);
		test.info("Checking whether client field is displayed");
		//Verifying if Client field is displayed
		login.client().isDisplayed();
		test.info("Clearing any previous value from the field");
		//Clearing the content of the Client field,if any
		login.client().clear();
		//Adding wait for visual
		Thread.sleep(1000);
		test.info("Input the field value");
		//Input the Client value
		login.client().sendKeys(Constants.WRONGPASSWORD);
		test.info("Checking whether password field is displayed");
		//Verifying if Password field is displayed
		login.password().isDisplayed();
		test.info("Clearing any previous value from the field");
		//Clearing the content of the Password field,if any
		login.password().clear();
		//Adding wait for visual
		Thread.sleep(1000);
		test.info("Input the field value");
		//Input the Wrong Password value
		login.password().sendKeys(Constants.WRONGPASSWORD);
		test.info("Checking whether login button is displayed");
		//Verifying if Login button is displayed
		login.clickLogInButton().isDisplayed();
		test.info("Clicking on the Login Button");
		//Clicking the Login button
		login.clickLogInButton().click();
		//Implicit wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//Error Message on Incorrect Login
		String loginerror = login.loginError().getText();
		//Checks Logout if actual value is equal to expected value
		assertEquals(loginerror, Constants.ERRORMESSAGE);
		test.pass("Not Able to login with wrong details");
	}

	/**
	 * This Test Case verifies the 
	 * login by not entering value in the respective 
	 * fields and this test case depends on
	 * verifyLogout
	 * 
	 */
	@Test(priority=11,dependsOnMethods="verifyLogout")
	public void blankLoginEntered() {
		test = report.createTest("Blank Login Fields");
		LoginPage login= PageFactory.initElements(driver, LoginPage.class);
		test.info("Checking whether username field is displayed");
		//Verifying if User name field is displayed
		login.userName().isDisplayed();
		test.info("Clearing any previous value from the field");
		//Clearing the content of the User name field,if any
		login.userName().clear();
		test.info("Checking whether client field is displayed");
		//Verifying if Client field is displayed
		login.client().isDisplayed();
		test.info("Clearing any previous value from the field");
		//Clearing the content of the Client field,if any
		login.client().clear();
		test.info("Checking whether password field is displayed");
		//Verifying if Password field is displayed
		login.password().isDisplayed();
		test.info("Clearing any previous value from the field");
		//Clearing the content of the Password field,if any
		login.password().clear();
		test.info("Checking whether login button is displayed");
		//Verifying if Login button is displayed
		login.clickLogInButton().isDisplayed();
		test.info("Clicking on the Login Button");
		//Clicking the Login button
		login.clickLogInButton().click();
		//Implicit wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//Error Message on Incorrect Login
		String loginerror = login.loginError().getText();
		//Checks Logout if actual value is equal to expected value
		assertEquals(loginerror, Constants.ERRORMESSAGE);
		test.pass("Not Able to login with Blank details");
	}
}
