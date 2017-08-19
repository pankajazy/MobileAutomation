package tests;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidKeyCode;
import pageObjects.HomeScreenObject;
import pageObjects.PaymentScreenObject;
import pageObjects.SearchFormScreenObject;
import pages.HomeScreen;
import utils.BaseCrossPlatformDriver;
import utils.GestureActions;

public class Native_Fab_specs extends BaseCrossPlatformDriver{
	
  @Test(enabled=false)
  public void countLocalitiesAndHotels() throws InterruptedException{
	  HomeScreen page_homeScreen=new HomeScreen();
	  String endOfScroll="//*[@class='android.view.View' and @index=3]";
	  
	  //Method that will navigate to provided Fab cities
	  page_homeScreen.fabCities(variable.city_delhi);
	  HashMap<String, Integer> count=new HashMap<String, Integer>();
	  //Clicking each tab and getting values
	  for(int i=1;i<6;i++)
	  {
		  String xpathForTab="//*[@class='android.support.v7.app.a$c' and @index="+i+"]//*[@class='android.widget.TextView' and @index=0]";
		  wait(driver.findElementByXPath(xpathForTab),10);
		  MobileElement localityTab=driver.findElementByXPath(xpathForTab);
		  String ss=localityTab.getText();
		  localityTab.click();
		  System.out.println("At index "+i+" NAME "+ss);
		  localityTab=null;
		  //This will return 1 if we reached at end of vertical scroll of list
		  List<MobileElement> elements = driver.findElementsByXPath(endOfScroll) ;
		  //It will store the unique hotel name and we can get the number of hotels in each locality
		  LinkedHashSet<String> property_count=new LinkedHashSet<String>();
		  //Scrolling till end of page is not found
		  while(elements.size()==0)
		  {		
			  //Adding hotel
			  property_count.add(driver.findElementById("com.fabhotels.guests:id/tv_property_name").getText());
			  elements =driver.findElementsByXPath(endOfScroll);
			  GestureActions.swipeVerticle();	 
		  }
	  }
	  System.out.println("List all the localities and number of hotels (count) in each locality "+Arrays.asList(count));

} 

	  
@Test(enabled=false)
public void verifyContactNumber()
	{
	 HomeScreen page_homeScreen=new HomeScreen();
	HomeScreenObject hso=new HomeScreenObject(driver);
	page_homeScreen.viewCutomerCareNum();
	String numDisplayed=hso.androidDialerField.getText();
	System.out.println("Number Displayed: "+numDisplayed);
	Assert.assertEquals(numDisplayed, variable.customer_care_num);
	
	}

@Test(enabled=false)
public void verifyCoupon() throws InterruptedException
{
	HomeScreen page_homeScreen=new HomeScreen();
	PaymentScreenObject pso=new PaymentScreenObject(driver);
	HomeScreenObject hso=new HomeScreenObject(driver);
	SearchFormScreenObject sfso=new SearchFormScreenObject(driver);
	page_homeScreen.fabCities(variable.city_delhi);
	sfso.propertyName.click();
	hso.selectDatesButton.click();
	hso.doneButton.click();
	hso.bookNowButton.click();
	GestureActions.swipeVerticle();
	GestureActions.swipeVerticle();
	wait(hso.couponCodeField,10);
	hso.couponCodeField.sendKeys(variable.coupon_FABAPP25);
	hso.applyButton.click();
	//Asserting that coupon is applied successfully
	Assert.assertTrue(hso.continueButton.isDisplayed(), "Coupun is applied Successfully");
	hso.continueButton.click();
	GestureActions.swipeVerticle();
	GestureActions.swipeVerticle();
	hso.fullName.sendKeys(variable.sample_Name);
	hso.emailId.sendKeys(variable.sample_Email);
	hso.mobileNum.sendKeys(variable.sample_mobile);
	driver.pressKeyCode(AndroidKeyCode.BACK);
	//pressing enter
	pso.proceedToPayButton.click();
	GestureActions.swipeVerticle();
	GestureActions.swipeVerticle();
	GestureActions.swipeVerticle();
	//Getting the discounted cost
	wait(pso.payNowButton,10);
	String discountedPriceText=pso.payNowButton.getText();
	String originalPriceText=pso.payAtHotelButton.getText();
	int discountedPrice=Integer.parseInt(discountedPriceText.replaceAll("[\\D]", ""));
	int originalPrice=Integer.parseInt(originalPriceText.replaceAll("[\\D]", ""));
	//Printing discounted money
	System.out.println("Original Cost: "+originalPrice+"Discounted amount: "+(originalPrice-discountedPrice));
	pso.payAtHotelButton.click();
	hso.android_Deny.click();
	pso.cancelOypDialog.click();

}

@Test
public void SearchFormScreenCount() throws InterruptedException
	{
	LinkedHashSet<String> locality_count=new LinkedHashSet<String>();
	HomeScreen page_homeScreen=new HomeScreen();
	SearchFormScreenObject sfso=new SearchFormScreenObject(driver);
	page_homeScreen.homePage();
	sfso.homeSearchToolbar.click();
	MobileElement me;
	while(!locality_count.contains("Visakhapatnam"))
	  {		
		me=sfso.searchCity;
		  //Adding hotel
		locality_count.add(me.getText());
		GestureActions.swipeVerticle();	 
	  }
	
	  Iterator<String> itr = locality_count.iterator();
	    while (itr.hasNext()) {
	        System.out.println(itr.next());
	    }
	}

}



