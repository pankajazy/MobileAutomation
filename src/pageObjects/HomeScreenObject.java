package pageObjects;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

public class HomeScreenObject {
	
	@AndroidFindBy(id="com.android.packageinstaller:id/permission_deny_button")
	@iOSFindBy(accessibility="")
	public MobileElement android_Deny;
	
	@AndroidFindBy(id="com.android.packageinstaller:id/permission_allow_button")
	@iOSFindBy(accessibility="")
	public MobileElement android_Allow;
	
	@AndroidFindBy(xpath="//*[@text=\"India's Best Economy Hotels\"]")
	@iOSFindBy(accessibility="")
	public MobileElement homePageTitle;
	
	@AndroidFindBy(className="android.widget.ImageButton")
	@iOSFindBy(accessibility="")
	public MobileElement phoneIcon;
	
	@AndroidFindBy(accessibility="Dial")
	@iOSFindBy(accessibility="")
	public MobileElement androidDialButton;
	
	@AndroidFindBy(id="com.android.contacts:id/digits")
	@iOSFindBy(accessibility="")
	public MobileElement androidDialerField;
	
	//Select dates button while booking
	@AndroidFindBy(id="com.fabhotels.guests:id/btn_bookNow")
	@iOSFindBy(accessibility="")
	public MobileElement selectDatesButton;
	
	//Done button of calendar
	@AndroidFindBy(id="com.fabhotels.guests:id/btn_done")
	@iOSFindBy(accessibility="")
	public MobileElement doneButton;
	
	@AndroidFindBy(id="com.fabhotels.guests:id/btn_bookNow")
	@iOSFindBy(accessibility="")
	public MobileElement bookNowButton;
	
	@AndroidFindBy(id="com.fabhotels.guests:id/et_coupon_code")
	@iOSFindBy(accessibility="")
	public MobileElement couponCodeField;
	
	@AndroidFindBy(id="com.fabhotels.guests:id/btn_apply")
	@iOSFindBy(accessibility="")
	public MobileElement applyButton;
	
	@AndroidFindBy(id="com.fabhotels.guests:id/btn_continue")
	@iOSFindBy(accessibility="")
	public MobileElement continueButton;
	
	@AndroidFindBy(id="com.fabhotels.guests:id/et_full_name")
	@iOSFindBy(accessibility="")
	public MobileElement fullName;
	
	@AndroidFindBy(id="com.fabhotels.guests:id/et_email")
	@iOSFindBy(accessibility="")
	public MobileElement emailId;
	
	@AndroidFindBy(id="com.fabhotels.guests:id/et_mobile_number")
	@iOSFindBy(accessibility="")
	public MobileElement mobileNum;	
	
	public HomeScreenObject(AndroidDriver<MobileElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

}
