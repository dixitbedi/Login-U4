package com.unit4test.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

	@FindBy(xpath=".//span[text()='Happy']")
	private WebElement homepagehappy;

	@FindBy(xpath=".//img[@alt='Usr']")
	private WebElement userlogo;

	@FindBy(xpath=".//ul[@class='dropdown-menu']/li[2]")
	private WebElement logout;

	@FindBy(xpath=".//span[@class='user_name']")
	private WebElement userlogin;

	public WebElement userlogo() {
		return userlogo;
	}

	public WebElement logout() {
		return logout;
	}

	public WebElement homepagehappy() {
		return homepagehappy;
	}

	public WebElement userLogin() {
		return userlogin;
	}
}

