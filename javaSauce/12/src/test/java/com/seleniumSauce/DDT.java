package com.seleniumSauce;

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
        test.setUp();
        String[] bannerData = {"http://www.amazon.com/", "http://www.google.com/", "http://avail.com/demo/index.html"};
        String[] titleName = {"Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more"
                , "Google", "Welcome to Opus - The premium sporting goods supplier"};
        for(int i = 0; i<bannerData.length; i++){
            System.out.println("Testing against: " + bannerData[i]);
            test.webDriver(bannerData[i], titleName[i]);
            System.out.println("Test completed");
        }
        test.tearDown();
    }
}
