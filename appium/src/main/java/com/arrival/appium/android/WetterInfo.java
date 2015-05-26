package com.arrival.appium.android;

/**
 * Created by Aaron Kutekidila  on 21/05/15.
 */

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;


/**
 * Test -android uiautomator locator strategy
 */
public class WetterInfo {

private static String url = "http://127.0.0.1:4723/wd/hub";
private static AndroidDriver wd;


//WetterInfo Android-Elemente
private static String __StartApp_Button_Navigations = "";
private static String __StartApp_Button_SearchButton = "";
private static String __StartApp_Button_OrtPLZButton = "";
private static String __StratApp_Button_AktuellerStandort = "";
private static String __StratApp_View_MainView = "";


private static String __Navigation_Leiste_MeineOrte = "";
private static String __Navigation_Leiste_Wetterkarte = "";
private static String __Navigation_Leiste_NRadar = "";
private static String __Navigation_Leiste_NFilm = "";
private static String __Navigation_Leiste_Wettervideos = "";
private static String __Navigation_Leiste_Datenschutz = "";
private static String __Navigation_Leiste_Impressum = "";
private static String __Navigation_Leiste_wetterInfoMobil = "";
private static String __Navigation_Leiste_wetterInfoFacebook = "";

  /*
  private static String __MeineOrte_Button_Aktuell			 	="";
  private static String __MeineOrte_Button_Vorhersage 			="";
  private static String __MeineOrte_Button_Radar				="";
  private static String __MeineOrte_Text_Stadname 				="";
  private static String __MeineOrte_Liste_Wetterdaten 			="";
  private static String __MeineOrte_Liste_Buttons_Pfeile 		="";
  private static String __MeineOrte_Liste_PullToReloadTextfeld 	="";
  private static String __MeineOrte_View_DataView				="";
  private static String __MeineOrte_View_DataView_List			="";


  private static String __Search_Leiste_TextFeld_PLZOrt			="";
  private static String __Search_Leiste_Buttton_Back			="";
  private static String __Search_Leiste_Button_AktuellerStandort="";
  private static String __Search_Leiste_List_CheckBox			="";
  private static String __Search_Leiste_List_MoveButton			="";
  private static String __Search_Leiste_List_Orte				="";
  private static String __Search_Leiste_View_DataView			="";

  private static String __Wetterkarte_Button_Aktuell			 	="";
  private static String __Wetterkarte_Button_Morgen 			="";
  private static String __Wetterkarte_B_Übermorgen			="";
  private static String __MeineOrte_Text_Stadname 				="";
  private static String __MeineOrte_Liste_Wetterdaten 			="";
  private static String __MeineOrte_Liste_Buttons_Pfeile 		="";
  private static String __MeineOrte_Liste_PullToReloadTextfeld 	="";
  private static String __MeineOrte_View_DataView				="";
  private static String __MeineOrte_View_DataView_List			="";

  private static String __Search_Leiste_TextFeld_PLZOrt			="";
  private static String __Search_Leiste_TextFeld_PLZOrt			="";
  private static String __Search_Leiste_TextFeld_PLZOrt			="";
  private static String __Search_Leiste_TextFeld_PLZOrt			="";
  private static String __Search_Leiste_TextFeld_PLZOrt			="";
  private static String __Search_Leiste_TextFeld_PLZOrt			="";
  private static String __Search_Leiste_TextFeld_PLZOrt			="";
  private static String __Search_Leiste_TextFeld_PLZOrt			="";
  private static String __Search_Leiste_TextFeld_PLZOrt			="";*/

@Before
public void setup() throws Exception {
    /*File appDir = new File("/Users/hendriklohrum/Desktop/Appium");
    File app = new File(appDir, "wetterinfo_aligned.apk");

    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
    capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
    wd = new AndroidDriver(new URL(url), capabilities);*/


	DesiredCapabilities capabilities = new DesiredCapabilities();
	capabilities.setCapability("appium-version", "1.0");
	capabilities.setCapability("platformName", "Android");
	capabilities.setCapability("platformVersion", "4.4");
	capabilities.setCapability("deviceName", "20715382");
	capabilities.setCapability("app", "/Users/tecdesdev/Desktop/Appium/wetterinfo_aligned.apk");
	capabilities.setCapability("appPackage", "com.telekom.wetterinfo");
	//capabilities.setCapability("appActivity", ".app.DialogActivity");
	wd = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
	wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
}


@After
public void tearDown() throws Exception {
	wd.quit();
}



 /* @Test
  public void swipen()
  {
	  aktuelleStandort();
	  WebElement element = wd.findElement(By.id("com.telekom.wetterinfo:id/main_activity_content_frame"));
  }*/

@Test
public void addCity() {
	WebElement element = wd.findElement(By.id("com.telekom.wetterinfo:id/locations_overview_fragment_empty_view_search_button"));
	// assertEquals("Ort/PLZ suchen", element.getTagName());
	element.click();

	WebElement element1 = wd.findElement(By.id("com.telekom.wetterinfo:id/search_activity_head_menu_background"));
	element1.sendKeys("Darmstadt");

	WebElement element0 = wd.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[2]"));
	element0.click();

}


@Test
public void aktuelleStandort() {
	WebElement element = wd.findElement(By.id("com.telekom.wetterinfo:id/locations_overview_fragment_empty_view_location_button"));
	element.click();
	WebElement element1 = wd.findElement(By.id("com.telekom.wetterinfo:id/application_alert_dialog_positive_button"));
	element1.click();

	WebElement element3 = wd.findElement(By.id("com.telekom.wetterinfo:id/locations_item_fragment_current_location_city"));

	String text = element3.getText();
	assertTrue(text.contains("Darmstadt"));
}
  /*
  @Test
  public void deleteCity(){
 	 addCity();
 	 WebElement element = wd.findElement(By.id("com.telekom.wetterinfo:id/main_activity_head_menu_main_screen_button_right_menu"));
 	 element.click();

 	 WebElement element1 = wd.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[2]/android.view.View[2]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.ListView[1]/android.view.View[1]/android.widget.RelativeLayout[1]/android.widget.CheckBox[1]"));
 	 element1.click();


 	 WebElement element2 = wd.findElement(By.id("com.telekom.wetterinfo:id/right_menu_location_delete_menu_deleted_icon"));
 	 element2.click();

 	 WebElement element3 = wd.findElement(By.id("com.telekom.wetterinfo:id/application_alert_dialog_positive_button"));
 	 element3.click();
  }


 @Test
 public void clickWetterkarte()
 {
	 WebElement element0 = wd.findElement(By.id("com.telekom.wetterinfo:id/main_activity_head_menu_main_screen_button_navigation"));
	 element0.click();
	 WebElement element1 = wd.findElement(By.xpath("/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[3]/android.view.View[2]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.ListView[1]/android.widget.RelativeLayout[2]"));
	 element1.click();
	 pauseTest(10000);
 }

 @Test
 public void sucheMitLupe()
 {
	 WebElement element0 = wd.findElement(By.id("com.telekom.wetterinfo:id/main_activity_head_menu_main_screen_button_navigation"));
	 element0.click();
	 WebElement element1 = wd.findElement(By.xpath(""));
	 element1.click();

	 WebElement element2 = wd.findElement(By.id("com.telekom.wetterinfo:id/search_activity_head_menu_background"));
	  element2.sendKeys("Darmstadt");
	 pauseTest(10000);
 }
*/

/**
 * Not testable funktions
 *
 * @throws InterruptedException
 */

public void pauseTest(int millisec) {
	try {
		Thread.sleep(millisec);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

//@Test
public void swipeLeftToRight() throws InterruptedException {
	aktuelleStandort();
	WebElement element1 = wd.findElement(By.xpath("/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget."
			                                              + "RelativeLayout[1]/android.widget.RelativeLayout[2]/android"
			                                              + ".view.View[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]"));
	wd.swipe(50, 500, 800, 500, 1);
	wd.wait(10000);
}

public void swipeRigthToLeft() {
	HashMap<String, Double> swipeObject = new HashMap<String, Double>();
	swipeObject.put("touchCount", 1.0);
	swipeObject.put("startX", 500.00);
	swipeObject.put("startY", 800.00);
	swipeObject.put("endX", 50.00);
	swipeObject.put("endY", 800.00);
	swipeObject.put("duration", 1.00);

	((JavascriptExecutor) wd).executeScript("mobile: swipe", swipeObject);
}
}
