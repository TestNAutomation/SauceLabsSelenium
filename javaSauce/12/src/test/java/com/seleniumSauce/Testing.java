package com.seleniumSauce;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.URL;
import java.util.Date;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: fjodorekstrom
 * Date: 12/6/12
 * Time: 5:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class Testing {
    //Class variables
    private WebDriver driver;
    private String[] bannerData;
    private String[] titleName;

    //Initiate the Testing() construct
    Testing t1 = new Testing();

    /**for(int i = 0; i<t1.bannerData.length; i++){
        String url = bannerData[i].toString();
        String verificationString = titleName[i];
        try {
            @Test
                    WebDriverTest.webDriver(url, verificationString);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        JUnitCore junit = new JUnitCore();
        junit.run(WebDriverTest.class);
        //Result result = junit.run(WebDriverTest.class);
        System.out.println("Testing against: " + bannerData[i]);
        System.out.println("Test completed");
    } **/

    //Construct
    public Testing(){
        WebDriverTest webdriverTest = new WebDriverTest();
        String[] bannerData = {"http://www.amazon.com/", "http://www.google.com/", "http://avail.com/demo/index.html"};
        String[] titleName = {"Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more"
                , "Google", "Welcome to Opus - The premium sporting goods supplier"};
        JUnitCore junit = new JUnitCore();
    }

    @Before
    public void setUp() throws Exception {

        DesiredCapabilities capabillities = DesiredCapabilities.firefox();
        capabillities.setCapability("version", "5");
        capabillities.setCapability("platform", Platform.XP);
        this.driver = new RemoteWebDriver(
                new URL("http://fulmajl:e3edf050-90cc-4a8a-9133-4cf33169f436@ondemand.saucelabs.com:80/wd/hub"),
                capabillities);
    }
    /**
    //A method for running the webDriver() test method for each index in the url[] array
    public void runTests(String url[], String verificationString[]) throws Exception {
        for(int i = 0; i<t1.bannerData.length; i++){
            @Test
                    webDriver(url[i], verificationString[i]);
        }
    }     **/
    @Test// The test method that is called in runTests()
    public void webDriver(String url, String verificationString) throws Exception {
        driver.get(url);
        System.out.println("Testing against: " + url);

        // Check that title is correct
        String actualTitle = driver.getTitle();
        assertEquals(verificationString, actualTitle);

        // Take screenshot
        WebDriver augmentedDriver = new Augmenter().augment(driver);
        File screenshot = ((TakesScreenshot)augmentedDriver).
                getScreenshotAs(OutputType.FILE);

        // Modify and save image
        String timeStmp = new Date().toString().substring(11, 19);
        String screenshotName = driver.getCurrentUrl().substring(7, 21) + "@CET:" + timeStmp.replaceAll(":", ".") + ".png";
        String savePath = "/Users/fjodorekstrom/Desktop/Selenium screenshots/" + screenshotName.substring(0, screenshotName.length() - 4) + "/";
        System.out.println("Saving " + screenshotName + " to " + savePath);
        FileUtils.copyFile(screenshot, new File(savePath + screenshotName));
        System.out.println("Test completed!");
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

}
