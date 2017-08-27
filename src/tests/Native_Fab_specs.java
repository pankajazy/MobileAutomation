package tests;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidKeyCode;
import pageObjects.HomeScreenObject;
import pageObjects.PaymentScreenObject;
import pageObjects.SearchFormScreenObject;
import pages.HomeScreen;
import utils.BaseCrossPlatformDriver;
import utils.GestureActions;
import utils.Log;


public class Native_Fab_specs extends BaseCrossPlatformDriver {
 
  //Setting type of application
  @BeforeTest
  public void init()
  {
    System.setProperty("ApplicationType", "native");
  }
  
  @Test(enabled = true,description ="List all the localities and number of hotels (count) in each locality.")
  public void countLocalitiesAndHotels() throws InterruptedException {
    
    String endOfScroll = "//*[@class='android.view.View' and @index=3]";
    // Method that will navigate to provided cities
    HomeScreen.fabCities(variable.city_delhi);
    HashMap<String, Integer> count = null;
    // Clicking each tab and getting values
    for (int i = 1; i < 6; i++) {
      // It will store localities and number of hotels present
      count= new HashMap<String, Integer>();
      String xpathForTab = "//*[@class='android.support.v7.app.a$c' and @index=" + i+ "]//*[@class='android.widget.TextView' and @index=0]";
      wait(driver.findElementByXPath(xpathForTab), 10);
      MobileElement localityTab = driver.findElementByXPath(xpathForTab);
      String ss = localityTab.getText();
      localityTab.click();
      Log.info("Counting Hotels under : "+ ss);
      localityTab = null;
      // This will return 1 if we reached at end of vertical scroll of list
      List<MobileElement> elements = driver.findElementsByXPath(endOfScroll);
      // It will store the unique hotel name and we can get the number of hotels in each locality
      LinkedHashSet<String> property_count = new LinkedHashSet<String>();
      // Scrolling till end of page
      while (elements.size() == 0) {
        // Adding hotels
        property_count.add(driver.findElementById("com.fabhotels.guests:id/tv_property_name").getText());
        elements = driver.findElementsByXPath(endOfScroll);
        GestureActions.swipeVerticle(0.4);
      }
      count.put(ss, property_count.size());
    }
    Log.info("List all the localities and number of hotels (count) in each locality "
        + Arrays.asList(count));

  }

  @Test(enabled = true,description= "Verify contact number for customer care")
  public void verifyContactNumber() throws IOException {
    
    // Clicking on HomeScreen phone icon
    HomeScreen.viewCutomerCareNum();
    // Getting the mobile number
    String numDisplayed = HomeScreenObject.androidDialerField.getText();
    Log.info("Number Displayed: " + numDisplayed);
    // Asserting the number
    Assert.assertEquals(numDisplayed, variable.customer_care_num);

  }

  @Test(enabled = true,description="when user clicks on ‘Pay At Hotel’ verify that coupon is removed for all the payment sections ")
  public void verifyCoupon() throws InterruptedException {
    
    // clicking on city
    HomeScreen.fabCities(variable.city_delhi);
    // Selecting first listed hotel
    SearchFormScreenObject.propertyName.click();
    // Choosing given dates
    HomeScreenObject.selectDatesButton.click();
    HomeScreenObject.doneButton.click();
    // clicking on book now button
    HomeScreenObject.bookNowButton.click();
    GestureActions.swipeVerticle(0.6);
    GestureActions.swipeVerticle(0.6);
    wait(HomeScreenObject.couponCodeField, 10);
    // Applying coupon
    HomeScreenObject.couponCodeField.sendKeys(variable.coupon_FABAPP25);
    HomeScreenObject.applyButton.click();
    // Asserting that coupon is applied successfully
    Assert.assertTrue(HomeScreenObject.continueButton.isDisplayed(), "Coupun is applied Successfully");
    HomeScreenObject.continueButton.click();
    GestureActions.swipeVerticle(0.6);
    // Filling Mandatory details
    HomeScreenObject.fullName.sendKeys(variable.sample_Name);
    HomeScreenObject.emailId.sendKeys(variable.sample_Email);
    HomeScreenObject.mobileNum.sendKeys(variable.sample_mobile);
    driver.pressKeyCode(AndroidKeyCode.BACK);
    // pressing enter
    PaymentScreenObject.proceedToPayButton.click();
    GestureActions.swipeVerticle(0.7);
    wait(PaymentScreenObject.payNowButton, 10);
    // Getting the discounted cost
    String discountedPriceText = PaymentScreenObject.payNowButton.getText();
    // Getting original price
    String originalPriceText = PaymentScreenObject.payAtHotelButton.getText();
    int discountedPrice = Integer.parseInt(discountedPriceText.replaceAll("[\\D]", ""));
    int originalPrice = Integer.parseInt(originalPriceText.replaceAll("[\\D]", ""));
    // Printing discounted money
    Log.info("Original Cost: " + originalPrice + "Discounted amount: "+ (originalPrice - discountedPrice));
    PaymentScreenObject.payAtHotelButton.click();
    HomeScreenObject.android_Deny.click();
    PaymentScreenObject.cancelOypDialog.click();

  }

  @Test(enabled = true,description="Location and hotel Counts on SearchFormScreen")
  public void SearchFormScreenCount() throws InterruptedException {
    
    Map<String, Integer> placesAndCount = new HashMap<String, Integer>();
    LinkedHashSet<String> locality_count = new LinkedHashSet<String>();
    List<MobileElement> locations;
    HomeScreen.homePage();
    // Clicking on home screen search tool bar
    SearchFormScreenObject.homeSearchToolbar.click();
    Thread.sleep(4000);
    // Scrolling till the end of city have reached
    while (!locality_count.contains("Visakhapatnam")) {
      locations = driver.findElementsById("com.fabhotels.guests:id/tvSearchItemCity");
      // Adding Hotels
      for (MobileElement temp : locations) {
        String discription;
        String cityName = temp.getText();
        Thread.sleep(3000);
        //Scrolling slightly in case element text is not visible
        try {
          discription = driver.findElementByXPath("//*[@text=" + '"' + cityName + '"'+ "]/following-sibling::*[@resource-id='com.fabhotels.guests:id/tvSearchItemDescription']").getText();
        } catch (Exception e) {
          GestureActions.swipeVerticle(0.4);
          continue;
        }
        discription = driver.findElementByXPath("//*[@text=" + '"' + cityName + '"'+ "]/following-sibling::*[@resource-id='com.fabhotels.guests:id/tvSearchItemDescription']").getText();
        //Getting hotel count out of text
        int hotelCount = Integer.parseInt(discription.replaceAll("[\\D]", ""));
        //Adding locality and hotels in that locality
        placesAndCount.put(cityName, hotelCount);
        locality_count.add(cityName);
      }
      GestureActions.swipeVerticle(0.7);
    }
    Log.info("List of all the localities and number of hotels (count) in each locality :");
    for (Map.Entry<String, Integer> entry : placesAndCount.entrySet())
    {
      Log.info("Location = " + entry.getKey() + ", number of hotels present = " + entry.getValue());
    }
  }
 

}
