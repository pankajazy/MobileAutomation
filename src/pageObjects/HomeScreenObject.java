package pageObjects;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import utils.BaseCrossPlatformDriver;

public class HomeScreenObject extends BaseCrossPlatformDriver{
  
  @AndroidFindBy(id = "com.android.packageinstaller:id/permission_deny_button")
  @iOSFindBy(accessibility = "")
  public static MobileElement android_Deny;
 
  @AndroidFindBy(id = "com.android.packageinstaller:id/permission_allow_button")
  @iOSFindBy(accessibility = "")
  public static MobileElement android_Allow;

  @AndroidFindBy(xpath = "//*[@text=\"India's Best Economy Hotels\"]")
  @iOSFindBy(accessibility = "")
  public static MobileElement homePageTitle;

  @AndroidFindBy(className = "android.widget.ImageButton")
  @iOSFindBy(accessibility = "")
  public static MobileElement phoneIcon;

  @AndroidFindBy(accessibility = "Dial")
  @iOSFindBy(accessibility = "")
  public static MobileElement androidDialButton;

  @AndroidFindBy(id = "com.android.contacts:id/digits")
  @iOSFindBy(accessibility = "")
  public static MobileElement androidDialerField;

  // Select dates button while booking
  @AndroidFindBy(id = "com.fabhotels.guests:id/btn_bookNow")
  @iOSFindBy(accessibility = "")
  public static MobileElement selectDatesButton;

  // Done button of calendar
  @AndroidFindBy(id = "com.fabhotels.guests:id/btn_done")
  @iOSFindBy(accessibility = "")
  public static MobileElement doneButton;

  @AndroidFindBy(id = "com.fabhotels.guests:id/btn_bookNow")
  @iOSFindBy(accessibility = "")
  public static MobileElement bookNowButton;

  @AndroidFindBy(id = "com.fabhotels.guests:id/et_coupon_code")
  @iOSFindBy(accessibility = "")
  public static MobileElement couponCodeField;

  @AndroidFindBy(id = "com.fabhotels.guests:id/btn_apply")
  @iOSFindBy(accessibility = "")
  public static MobileElement applyButton;

  @AndroidFindBy(id = "com.fabhotels.guests:id/btn_continue")
  @iOSFindBy(accessibility = "")
  public static MobileElement continueButton;

  @AndroidFindBy(id = "com.fabhotels.guests:id/et_full_name")
  @iOSFindBy(accessibility = "")
  public static MobileElement fullName;

  @AndroidFindBy(id = "com.fabhotels.guests:id/et_email")
  @iOSFindBy(accessibility = "")
  public static MobileElement emailId;

  @AndroidFindBy(id = "com.fabhotels.guests:id/et_mobile_number")
  @iOSFindBy(accessibility = "")
  public static MobileElement mobileNum;
  
  public HomeScreenObject(AndroidDriver<MobileElement> drivers) {
    PageFactory.initElements(new AppiumFieldDecorator(drivers), this);
  }

}
