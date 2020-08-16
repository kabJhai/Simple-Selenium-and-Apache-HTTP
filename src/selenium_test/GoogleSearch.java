/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selenium_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author kabil
 */
public class GoogleSearch {
    public static void main(String [] args) throws InterruptedException{
        System.setProperty("webdriver.chrome.driver", "C:\\webDrivers\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com");
        Thread.sleep(1000);
        
        
    }
}
