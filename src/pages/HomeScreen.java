package pages;

import pageObjects.HomeScreenObject;
import utils.BaseCrossPlatformDriver;

public class HomeScreen extends BaseCrossPlatformDriver {

  public static void homePage() {
    
    skip();
    HomeScreenObject.android_Deny.click();
    HomeScreenObject.homePageTitle.isDisplayed();
  }

  public static void fabCities(String cityName) {
    homePage();
    // waiting for city to be populated
    wait(place(cityName), 10);
    place(cityName).click();

  }

  public static void viewCutomerCareNum() {
    homePage();
    HomeScreenObject.phoneIcon.click();
    // waiting for dialer
    wait(HomeScreenObject.androidDialButton, 10);
  }

}
