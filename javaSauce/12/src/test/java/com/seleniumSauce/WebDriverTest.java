package com.seleniumSauce;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
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
 * Simple {@link RemoteWebDriver} test that demonstrates how to run your Selenium tests with <a href="http://saucelabs.com/ondemand">Sauce OnDemand</a>.
 * *
 * @author Ross Rowe
 */
@RunWith(value = Parameterized.class)
public class WebDriverTest {

    private WebDriver driver;

    @Before
    public void setUp() throws Exception {

        DesiredCapabilities capabillities = DesiredCapabilities.firefox();
        capabillities.setCapability("version", "5");
        capabillities.setCapability("platform", Platform.XP);
        this.driver = new RemoteWebDriver(
                new URL("http://fulmajl:e3edf050-90cc-4a8a-9133-4cf33169f436@ondemand.saucelabs.com:80/wd/hub"),
                capabillities);
    }

    @Test
    public void webDriver(WebDriver driver, String url, String verificationString) throws Exception {
        driver.get(url);
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
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
