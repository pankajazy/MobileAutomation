package pageObjects;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import utils.BaseCrossPlatformDriver;

public class VerifyScreenObject extends BaseCrossPlatformDriver{
  
  @AndroidFindBy(id = "com.fabhotels.guests:id/btn_skip")
  @iOSFindBy(id = "")
  public static MobileElement skipButton;

  @AndroidFindBy(id = "com.fabhotels.guests:id/etMobileNumber")
  @iOSFindBy(id = "")
  public static MobileElement mobileNumber;

  @AndroidFindBy(id = "com.fabhotels.guests:id/tvCountryname")
  @iOSFindBy(id = "")
  public static MobileElement countryName;

  public VerifyScreenObject(AndroidDriver<MobileElement> drivers) {
    PageFactory.initElements(new AppiumFieldDecorator(drivers), this);
  }
}
