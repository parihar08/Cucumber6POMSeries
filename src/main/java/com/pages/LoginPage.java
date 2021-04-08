package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	
	private WebDriver driver;
	
	//1. By Locators:
	
	private By emailId = By.id("email");
	private By password = By.id("passwd");
	private By signInBtn = By.id("SubmitLogin");
	private By ForgotPwdLink = By.linkText("Forgot your password?");
	
	//2. Constructor of the Login Page class
	
	public LoginPage(WebDriver driver){
		this.driver=driver;
	}
	
	//3. Page Actions - Features
	
	public String getLoginPageTitle(){
		return driver.getTitle();
	}
	
	public boolean isForgotPwdLinkExist(){
		return driver.findElement(ForgotPwdLink).isDisplayed();
		
	}
	
	public void enterUsername(String username){
		driver.findElement(emailId).sendKeys(username);
	}
	
	public void enterPassword(String pwd){
		driver.findElement(password).sendKeys(pwd);
	}
	
	public void clickOnLoginBtn(){
		driver.findElement(signInBtn).click();
	}
	
	public AccountsPage doLogin(String un,String pwd){
		System.out.println("Login with "+un + " and "+pwd);
		driver.findElement(emailId).sendKeys(un);
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(signInBtn).click();
		return new AccountsPage(driver);
	}
	
	
	

}
