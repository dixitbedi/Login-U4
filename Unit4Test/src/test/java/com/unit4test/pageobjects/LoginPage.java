package com.unit4test.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

	@FindBy(xpath=".//input[@placeholder='User Name']")
	private WebElement username;

	@FindBy(xpath=".//*[@name='client']")
	private WebElement client;

	@FindBy(xpath=".//*[@name='password']")
	private WebElement password;

	@FindBy(xpath=".//form[@name='form']//following::button[1]")
	private WebElement loginbutton;

	@FindBy(xpath=".//span[text()='Ugyldig brukernavn eller passord' or text()='Username or Password or Client is incorrect']")
	private WebElement loginerror;

	public WebElement userName() {
		return username;
	}

	public WebElement client() {
		return client;
	}

	public WebElement password() {
		return password;
	}

	public WebElement clickLogInButton() {
		return loginbutton;
	}

	public WebElement loginError() {
		return loginerror;
	}


}