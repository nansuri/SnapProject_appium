package functionalTest;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import element.LoginElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import toolsAndDefine.Constant;

public class LoginPage {
	AndroidDriver<?> driver;
	Constant constant = new Constant();
	LoginElement elemen = new LoginElement(); 
	
	@BeforeClass
	public void setUp() throws MalformedURLException{
		DesiredCapabilities caps = new DesiredCapabilities();
	
		caps = DesiredCapabilities.android();
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, constant.DEVICE_NAME);
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		
		// Define application
		caps.setCapability("appPackage",constant.PACKAGE);
		caps.setCapability("appActivity",constant.PACKAGE_ACTIVITY);
		
		URL appiumUrl = new URL("http://127.0.0.1:4723/wd/hub");
		driver = new AndroidDriver<>(appiumUrl, caps); 
	}
	
	@Test
	// Part 01 - Verify user login using invalid password 
	public void Snap01_LoginInvalid() throws Exception{
		Thread.sleep(2000);
		driver.findElement(By.id(elemen.signInButton)).click();
		driver.findElement(By.id(elemen.fieldEmail)).sendKeys(constant.EMAIL_INVALID);
		driver.findElement(By.id(elemen.fieldPassword)).sendKeys(constant.PASS_VALID);
		driver.findElement(By.id(elemen.buttonLogin)).click();
	}
	
	@Test
	// Part 02 - Verify user fill Forgot Password using unregistered Email
	public void Snap01_ForgotPassUnregistered() throws Exception{
		Thread.sleep(2000);
		driver.findElement(By.id(elemen.signInButton)).click();
		driver.findElement(By.id(elemen.forgotPasswordButton)).click();
		driver.findElement(By.id(elemen.forgotPasswordEmailField)).sendKeys(constant.EMAIL_INVALID);
		driver.findElement(By.id(elemen.buttonForgot)).click();
	}
	
	@Test
	// Part 03 - Verify user login using valid password 
	public void Snap01_LoginValid() throws Exception{
		Thread.sleep(2000);
		driver.findElement(By.id(elemen.signInButton)).click();
		driver.findElement(By.id(elemen.fieldEmail)).sendKeys(constant.EMAIL_VALID);
		driver.findElement(By.id(elemen.fieldPassword)).sendKeys(constant.PASS_VALID);
		driver.findElement(By.id(elemen.buttonLogin)).click();
	}
	
	@Test
	// Part 04 - Verify user fill Forgot Password using registered Email
	public void Snap01_ForgotPassRegistered() throws Exception{
		Thread.sleep(2000);
		driver.findElement(By.id(elemen.signInButton)).click();
		driver.findElement(By.id(elemen.forgotPasswordButton)).click();
		driver.findElement(By.id(elemen.forgotPasswordEmailField)).sendKeys(constant.EMAIL_VALID);
		driver.findElement(By.id(elemen.buttonForgot)).click();
	}
}