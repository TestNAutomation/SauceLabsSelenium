package com.seleniumSauce;

import org.junit.Test;
import org.junit.runner.JUnitCore;

/**
 * Created with IntelliJ IDEA.
 * User: fjodorekstrom
 * Date: 11/30/12
 * Time: 2:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class DDT {

    public static void main(String[] args) throws Exception {
        WebDriverTest test = new WebDriverTest();
        String[] bannerData = {"http://www.amazon.com/", "http://www.google.com/", "http://avail.com/demo/index.html"};
        String[] titleName = {"Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more"
                , "Google", "Welcome to Opus - The premium sporting goods supplier"};

        test.setUp();

        for(int i = 0; i<bannerData.length; i++){
            String url = bannerData[i].toString();
                String title = titleName[i];
                WebDriverTest.webDriver();
                JUnitCore junit = new JUnitCore();
                junit.run(WebDriverTest.class);
                //Result result = junit.run(WebDriverTest.class);
            System.out.println("Testing against: " + bannerData[i]);
            System.out.println("Test completed");
        }
        test.tearDown();
    }

    @Test
    public void TestRunner(WebDriverTest test, String bannerData, String titleName) throws Exception {
        test.webDriver(bannerData, titleName);

    }
}
