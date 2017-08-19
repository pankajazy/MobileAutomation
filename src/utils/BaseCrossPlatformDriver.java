package utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import commonVaribles.SharedVariables;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import pageObjects.HomeScreenObject;
import pageObjects.SearchFormScreenObject;
import pageObjects.VerifyScreenObject;
import pages.HomeScreen;

//Note: Maximum timeout is set to 5 min using timeout property of test annotation.

public class BaseCrossPlatformDriver {

  public static AndroidDriver<MobileElement> driver;
  String appiumServiceUrl;
  private static AppiumDriverLocalService service;

  @BeforeTest(enabled = true)
  public void beforeClass() {
    System.out.println("Starting Server");
    service = AppiumDriverLocalService.buildDefaultService();
    service.start();
    appiumServiceUrl = service.getUrl().toString();
    if (service == null || !service.isRunning()) {
      throw new AppiumServerHasNotBeenStartedLocallyException(
          "An appium server node is not started!");
    }

  }

  @AfterTest(enabled = false)
  public void afterClass() {
    if (service != null) {
      service.stop();
    }

  }

  @BeforeMethod
  public void setUp() throws Exception {
    System.out.println("Starting: ");
    if (System.getProperty("ApplicationType").equalsIgnoreCase("web")) {
      System.out.println("Launching web browser");
      androidBrowser();
    } else if(System.getProperty("ApplicationType").equalsIgnoreCase("native")){
      System.out.println("Launching Native App");
      androidCaps();
    }
  }

  @AfterMethod
  public void tearDown() throws Exception {
    if (driver != null) {
      driver.quit();
    }
  }

  private void androidBrowser() throws IOException {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("browserName", "chrome");
    capabilities.setCapability("deviceName", "KFUKGI85VWTG4D4H");
    capabilities.setCapability("platformName", "Android");
    capabilities.setCapability("platformVersion", "6.0");
    driver = new AndroidDriver<MobileElement>(new URL(appiumServiceUrl), capabilities);
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  private void androidCaps() throws IOException {
    File classpathRoot = new File(System.getProperty("user.dir"));
    File appDir = new File(classpathRoot, "./Apps/");
    System.out.println("app directory" + appDir);
    File app = new File(appDir.getCanonicalPath(), "FabHotels.apk");
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("deviceName", "9fc26a437d14");
    capabilities.setCapability("platformName", "Android");
    capabilities.setCapability("newCommandTimeout", 10);
    capabilities.setCapability("platformVersion", "6.0");
    capabilities.setCapability("app", app.getAbsolutePath());
    capabilities.setCapability("appPackage", "com.fabhotels.guests");
    capabilities.setCapability("appActivity", "app.fabhotels.MainActivity");
    driver = new AndroidDriver<MobileElement>(new URL(appiumServiceUrl), capabilities);
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  public static void skip() {
    new WebDriverWait(driver, 30)
        .until(ExpectedConditions.elementToBeClickable(new VerifyScreenObject(driver).skipButton))
        .click();

  }

  // Common text xpath for cities and localities
  public static MobileElement place(String location) {
    MobileElement me = driver.findElementByXPath("//*[contains(@text,'" + location + "')]");
    return me;

  }

  // Explicit wait
  public static void wait(MobileElement me, long sec) {
    WebDriverWait wd = new WebDriverWait(driver, sec);
    wd.until(ExpectedConditions.visibilityOf(me));
  }

  // PageObjects
  // public HomeScreenObject hso=new HomeScreenObject(driver);
  // public SearchFormScreenObject pso=new SearchFormScreenObject(driver);

  // Variables
  public SharedVariables variable = new SharedVariables();

  // Pages functions

}
