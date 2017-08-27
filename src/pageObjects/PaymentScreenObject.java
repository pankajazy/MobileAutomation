package pageObjects;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

public class PaymentScreenObject {

  @AndroidFindBy(xpath = "//*[@text='Credit Card']")
  @iOSFindBy(accessibility = "")
  public static MobileElement creditCard;

  @AndroidFindBy(xpath = "//*[@text='Debit Card']")
  @iOSFindBy(accessibility = "")
  public static MobileElement debitCard;

  @AndroidFindBy(xpath = "//*[@text='Netbanking']")
  @iOSFindBy(accessibility = "")
  public static MobileElement netBanking;

  @AndroidFindBy(xpath = "//*[@text='Wallets']")
  @iOSFindBy(accessibility = "")
  public static MobileElement wallets;

  @AndroidFindBy(id = "com.fabhotels.guests:id/btnPay")
  @iOSFindBy(accessibility = "")
  public static MobileElement payNowButton;

  @AndroidFindBy(id = "com.fabhotels.guests:id/btnPayAtHotel")
  @iOSFindBy(accessibility = "")
  public static MobileElement payAtHotelButton;

  @AndroidFindBy(id = "com.fabhotels.guests:id/ivClose")
  @iOSFindBy(accessibility = "")
  public static MobileElement cancelOypDialog;

  @AndroidFindBy(id = "com.fabhotels.guests:id/btn_pay")
  @iOSFindBy(accessibility = "")
  public static MobileElement proceedToPayButton;

  public PaymentScreenObject(AndroidDriver<MobileElement> drivers) {
    PageFactory.initElements(new AppiumFieldDecorator(drivers), this);
  }

}
