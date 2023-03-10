package com.learnautomation.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.base.BaseClass;
import com.google.dataProvider.DataProviders;
import com.google.pages.Dashboard;
import com.google.pages.LoginPage;

public class LoginTest extends BaseClass
{
	LoginPage login;
	Dashboard dash;
	public WebDriver driver;
	
	@BeforeMethod
	public void setupDriver()
	{
		driver=getDriver();
	}

	@Test(description = "Login to application",dataProvider = "login",dataProviderClass = DataProviders.class)
	public void loginApp(String uname,String pass)
	{
		login=new LoginPage(driver);
		
		dash=login.loginToApplication(uname,pass);
		
		Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "Login failed");
		
	}
	
	@Test(description = "Logout from application",dependsOnMethods = "loginApp")
	public void logoutTest()
	{
		dash.logOut();
		
		Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Logout failed");
	}
	

}
