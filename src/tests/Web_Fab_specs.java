package tests;

import org.junit.Assert;
import org.testng.annotations.Test;

import utils.BaseCrossPlatformDriver;

public class Web_Fab_specs extends BaseCrossPlatformDriver{	
	public Web_Fab_specs()
	{
		System.out.println("Setting browser Mode");
		System.setProperty("browserName", "chrome");
	}
	
  @Test
  public void validateDeepLink() {
	  System.setProperty("browserName", "chrome");
	  driver.get(variable.urlCityDelhi);
	  System.out.println("Title is: " + driver.getTitle());
	  wait(driver.findElementByClassName("head__phone-link"),20);
	  String localityName=driver.findElementByClassName("listing__current-loc-text").getText();
	  Assert.assertEquals(variable.city_delhi, localityName);
	  
  }
}
