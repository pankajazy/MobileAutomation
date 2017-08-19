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
  public MobileElement creditCard;

  @AndroidFindBy(xpath = "//*[@text='Debit Card']")
  @iOSFindBy(accessibility = "")
  public MobileElement debitCard;

  @AndroidFindBy(xpath = "//*[@text='Netbanking']")
  @iOSFindBy(accessibility = "")
  public MobileElement netBanking;

  @AndroidFindBy(xpath = "//*[@text='Wallets']")
  @iOSFindBy(accessibility = "")
  public MobileElement wallets;

  @AndroidFindBy(id = "com.fabhotels.guests:id/btnPay")
  @iOSFindBy(accessibility = "")
  public MobileElement payNowButton;

  @AndroidFindBy(id = "com.fabhotels.guests:id/btnPayAtHotel")
  @iOSFindBy(accessibility = "")
  public MobileElement payAtHotelButton;

  @AndroidFindBy(id = "com.fabhotels.guests:id/ivClose")
  @iOSFindBy(accessibility = "")
  public MobileElement cancelOypDialog;

  @AndroidFindBy(id = "com.fabhotels.guests:id/btn_pay")
  @iOSFindBy(accessibility = "")
  public MobileElement proceedToPayButton;

  public PaymentScreenObject(AndroidDriver<MobileElement> driver) {
    PageFactory.initElements(new AppiumFieldDecorator(driver), this);
  }

}
