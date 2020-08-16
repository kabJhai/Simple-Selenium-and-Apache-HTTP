/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selenium_test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Software Automated Testing");
        searchBox.submit();
//        WebElement searchButton = driver.findElement(By.name("btnK"));
//        searchButton.click();

        List<WebElement> results =  driver.findElements(By.className("g"));
        if(results.size() > 0){
            WebElement firstResult = results.get(0);
            WebElement resultOneLink = firstResult.findElement(By.tagName("a"));
            resultOneLink.click();
            Thread.sleep(5000);
        }else{
            
        }
        
        
    }
}
