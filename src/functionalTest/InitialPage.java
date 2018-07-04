package functionalTest;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import element.RegionElements;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import toolsAndDefine.Constant;

public class InitialPage {
	static AndroidDriver<?> driver;
	Constant constant = new Constant();
	RegionElements elemen = new RegionElements(); 
	
	@BeforeTest
	public void setUp() throws MalformedURLException{
		DesiredCapabilities caps = new DesiredCapabilities();
	
		caps = DesiredCapabilities.android();
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, constant.DEVICE_NAME);
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		// caps.setCapability(MobileCapabilityType.NO_RESET, true);
		
		// Define application
		caps.setCapability("appPackage",constant.PACKAGE);
		caps.setCapability("appActivity",constant.PACKAGE_ACTIVITY);
		
		URL appiumUrl = new URL("http://127.0.0.1:4723/wd/hub");
		driver = new AndroidDriver<>(appiumUrl, caps); 
	}
	
	@Test
	// Part 01 - Verify Select Region page
	public void Snap01_SelectRegion() throws Exception{
		Thread.sleep(1000);
		driver.findElement(By.id(RegionElements.DROPDOWN_REGION_BUTTON)).click();
		driver.findElement(By.xpath(RegionElements.REGION_INDONESIA)).click();
		driver.findElement(By.id(RegionElements.DROPDOWN_LANGUAGE_BUTTON)).click();
		driver.findElement(By.xpath(RegionElements.LANGUAGE_INDONESIA)).click();
		driver.findElement(By.id(RegionElements.BUTTON_NEXT)).click();
	}
	
	@Test
	// Part 02 - Introduction Page
	public void Snap02_IntroductionPage() throws Exception{
		for(int a=0;a<2;a++) {
			driver.findElement(By.id(RegionElements.BUTTON_INTRO_NEXT)).click();
		}
		driver.findElement(By.id(RegionElements.BUTTON_INTRO_OK)).click();
	}
	
	@AfterTest
	public static void setDown() {
		driver.quit();
	}
}
