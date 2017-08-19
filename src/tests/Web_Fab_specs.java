package tests;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import utils.BaseCrossPlatformDriver;

public class Web_Fab_specs extends BaseCrossPlatformDriver {
  @BeforeTest
  public void init()
  {
    System.setProperty("ApplicationType", "web");
  }

  @Test(timeOut=300000)
  public void validateDeepLink() throws InterruptedException {
    driver.get(variable.urlCityDelhi);
    System.out.println("Title is: " + driver.getTitle());
    Thread.sleep(3000);
    String localityName = driver.findElementByClassName("listing__current-loc-text").getText();
    Assert.assertEquals(variable.city_delhi, localityName);
    driver.findElementById("wzrk-cancel").click();
    JavascriptExecutor jse = (JavascriptExecutor)driver;
    Thread.sleep(3000);
    //Scrolling up
    jse.executeScript("window.scrollBy(250,0)", "");
    Thread.sleep(3000);
    List<MobileElement> namesOfHotels=driver.findElementsByXPath("//*[contains(@content-desc,'FabHotel ') and @clickable='true']");
    Thread.sleep(3000);
    System.out.println("Total "+namesOfHotels.size()+" are found in "+variable.urlCityDelhi+ " and those are :");
    for(MobileElement temp:namesOfHotels)
    {
      System.out.println("Hotel Name :"+temp.getText());
    }
    
   
  }
}
