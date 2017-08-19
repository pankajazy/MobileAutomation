package pages;

import pageObjects.HomeScreenObject;
import utils.BaseCrossPlatformDriver;

public class HomeScreen extends BaseCrossPlatformDriver {

  HomeScreenObject fh = new HomeScreenObject(driver);

  public void homePage() {

    skip();
    fh.android_Deny.click();
    fh.homePageTitle.isDisplayed();
  }

  public void fabCities(String cityName) {
    homePage();
    // waiting for city to be populated
    wait(place(cityName), 10);
    place(cityName).click();

  }

  public void viewCutomerCareNum() {
    homePage();
    fh.phoneIcon.click();
    // waiting for dialer
    wait(fh.androidDialButton, 10);
  }

}
