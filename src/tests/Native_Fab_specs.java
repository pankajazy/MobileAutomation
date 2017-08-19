package tests;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
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

public class Native_Fab_specs extends BaseCrossPlatformDriver {

  @BeforeTest
  public void init()
  {
    System.setProperty("ApplicationType", "native");
  }
 
  @Test(enabled = true, timeOut = 300000)
  public void countLocalitiesAndHotels() throws InterruptedException {

    HomeScreen page_homeScreen = new HomeScreen();
    String endOfScroll = "//*[@class='android.view.View' and @index=3]";
    // Method that will navigate to provided cities
    page_homeScreen.fabCities(variable.city_delhi);
    // It will store localities and number of hotels present
    HashMap<String, Integer> count = new HashMap<String, Integer>();
    // Clicking each tab and getting values
    for (int i = 1; i < 6; i++) {
      String xpathForTab = "//*[@class='android.support.v7.app.a$c' and @index=" + i+ "]//*[@class='android.widget.TextView' and @index=0]";
      wait(driver.findElementByXPath(xpathForTab), 10);
      MobileElement localityTab = driver.findElementByXPath(xpathForTab);
      String ss = localityTab.getText();
      localityTab.click();
      System.out.println("At index " + i + " NAME " + ss);
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
    }
    System.out.println("List all the localities and number of hotels (count) in each locality "
        + Arrays.asList(count));

  }

  @Test(enabled = true, timeOut = 300000)
  public void verifyContactNumber() {

    HomeScreen page_homeScreen = new HomeScreen();
    HomeScreenObject hso = new HomeScreenObject(driver);
    // Clicking on HomeScreen phone icon
    page_homeScreen.viewCutomerCareNum();
    // Getting the mobile number
    String numDisplayed = hso.androidDialerField.getText();
    System.out.println("Number Displayed: " + numDisplayed);
    // Asserting the number
    Assert.assertEquals(numDisplayed, variable.customer_care_num);

  }

  @Test(enabled = true, timeOut = 300000)
  public void verifyCoupon() throws InterruptedException {

    HomeScreen page_homeScreen = new HomeScreen();
    PaymentScreenObject pso = new PaymentScreenObject(driver);
    HomeScreenObject hso = new HomeScreenObject(driver);
    SearchFormScreenObject sfso = new SearchFormScreenObject(driver);
    // clicking on city
    page_homeScreen.fabCities(variable.city_delhi);
    // Selecting first listed hotel
    sfso.propertyName.click();
    // Choosing given dates
    hso.selectDatesButton.click();
    hso.doneButton.click();
    // clicking on booknow button
    hso.bookNowButton.click();
    GestureActions.swipeVerticle(0.5);
    wait(hso.couponCodeField, 10);
    // Applying coupon
    hso.couponCodeField.sendKeys(variable.coupon_FABAPP25);
    hso.applyButton.click();
    // Asserting that coupon is applied successfully
    Assert.assertTrue(hso.continueButton.isDisplayed(), "Coupun is applied Successfully");
    hso.continueButton.click();
    GestureActions.swipeVerticle(0.6);
    // Filling Mandatory details
    hso.fullName.sendKeys(variable.sample_Name);
    hso.emailId.sendKeys(variable.sample_Email);
    hso.mobileNum.sendKeys(variable.sample_mobile);
    driver.pressKeyCode(AndroidKeyCode.BACK);
    // pressing enter
    pso.proceedToPayButton.click();
    GestureActions.swipeVerticle(0.7);
    wait(pso.payNowButton, 10);
    // Getting the discounted cost
    String discountedPriceText = pso.payNowButton.getText();
    // Getting original price
    String originalPriceText = pso.payAtHotelButton.getText();
    int discountedPrice = Integer.parseInt(discountedPriceText.replaceAll("[\\D]", ""));
    int originalPrice = Integer.parseInt(originalPriceText.replaceAll("[\\D]", ""));
    // Printing discounted money
    System.out.println("Original Cost: " + originalPrice + "Discounted amount: "+ (originalPrice - discountedPrice));
    pso.payAtHotelButton.click();
    hso.android_Deny.click();
    pso.cancelOypDialog.click();

  }

  @Test(enabled = true,timeOut = 300000)
  public void SearchFormScreenCount() throws InterruptedException {
    Map<String, Integer> placesAndCount = new HashMap<String, Integer>();
    LinkedHashSet<String> locality_count = new LinkedHashSet<String>();
    List<MobileElement> locations;
    HomeScreen page_homeScreen = new HomeScreen();
    SearchFormScreenObject sfso = new SearchFormScreenObject(driver);
    page_homeScreen.homePage();
    // Clicking on home screen search tool bar
    sfso.homeSearchToolbar.click();
    Thread.sleep(3000);
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
    System.out.println("List of all the localities and number of hotels (count) in each locality :");
    for (Map.Entry<String, Integer> entry : placesAndCount.entrySet())
    {
      System.out.println("Location = " + entry.getKey() + ", number of hotels present = " + entry.getValue());
    }
  }

}
