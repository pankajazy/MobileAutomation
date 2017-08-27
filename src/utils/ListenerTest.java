package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.google.common.io.Files;

public class ListenerTest extends BaseCrossPlatformDriver implements ITestListener {


  public void onTestStart(ITestResult result) {
    //Displaying Start of Test 
    Log.startTestCase(result.getMethod().getMethodName());
    
  }

  public void onTestSuccess(ITestResult result) {
    Log.endTestCase(result.getMethod().getMethodName());
  }

  public void onTestFailure(ITestResult result) {
    if (!result.isSuccess()) {
      Log.failed();
      String userDirector = System.getProperty("user.dir");
      String customeLocation = "//screenshots//";
      String failureImageFileName = userDirector + customeLocation+result.getMethod().getMethodName()+"-"+new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime()) + ".png";
      File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      try {

        Files.copy(scrFile, new File(failureImageFileName));
      } catch (IOException e) {
        e.printStackTrace();
      }
      // String userDirector = System.getProperty("user.dir") + "/";
      Reporter.log("<a href=\"" + failureImageFileName + "\"><img src=\"file:///"
          + failureImageFileName + "\" alt=\"\"" + "height='100' width='100'/> " + "<br />");
      // Reporter.log("<a href=\""+ failureImageFileName + "\">");
      Reporter.setCurrentTestResult(null);
      Reporter.log(result.getName() + "--Test method failed\n");

    }

  }

  public void onTestSkipped(ITestResult result) {

  }

  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

  }

  public void onFinish(ITestContext arg0) {


  }

  public void onStart(ITestContext arg0) {

  }

}
