package tests;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.Assert;

import org.testng.annotations.BeforeTest;

import org.testng.annotations.Test;

import Pages.Guru99HomePage;

import Pages.Guru99Login;

public class Test99GuruLogin {

    String driverPath = "C:\\geckodriver.exe";
    
    WebDriver driver;

    Guru99Login objLogin;

    Guru99HomePage objHomePage;

    @BeforeTest

    public void setup(){

	System.setProperty("webdriver.gecko.driver", driverPath);
        
	System.setProperty("webdriver.chrome.driver","D:\\chrome_driver\\chromedriver.exe");
	 driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("http://demo.guru99.com/V4/");

    }

    /**

     * This test case will login in http://demo.guru99.com/V4/

     * Verify login page title as guru99 bank

     * Login to application

     * Verify the home page using Dashboard message

     */

    @Test(priority=1)

    public void test_Home_Page_Appear_Correct(){

        //Create Login Page object

    objLogin = new Guru99Login(driver);

    //Verify login page title

    String loginPageTitle = objLogin.getLoginTitle();

    Assert.assertTrue(loginPageTitle.toLowerCase().contains("guru99 bank"));

    //login to application

    objLogin.loginToGuru99("mgr123", "mgr!23");

    // go the next page

    objHomePage = new Guru99HomePage(driver);

    //Verify home page

    Assert.assertTrue(objHomePage.getHomePageDashboardUserName().toLowerCase().contains("manger id : mgr123"));

    }
    
    @Test(priority=2)
    public void testLogout() throws InterruptedException {
    	driver.findElement(By.linkText("Log out")).click();
    	Thread.sleep(10);
    	String logout_title=driver.getTitle();
    	Assert.assertEquals(logout_title, "http://demo.guru99.com/V4/manager/Logout.php");
    	driver.close();
    	
    }
    
}