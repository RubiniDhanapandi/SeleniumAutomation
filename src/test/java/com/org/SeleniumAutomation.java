//<editor-fold desc="Description">
//region Description

package com.org;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Test;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

import static java.lang.System.*;

public class SeleniumAutomation {

    //region Description
    private static WebDriver driver;
    private JavascriptExecutor js;
    private String emailID = "test8879927@gmail.com";
    private String passWord = "Password";
    private static String firstName = "Tester";
    private String lastName = "Testerng";
    private String customerDate = "25";
    private String customerYear = "1994";
    private String customerAddress = "Lauriston Close";
    private String customerCity = "Manchester";
    private String customerZipCode = "95532";
    private String customerPhone = "096543327888";
    private String automationUrl = "http://automationpractice.com/index.php";
    private String webDriverPath = "C:\\Users\\vijay\\Downloads\\chromedriver\\chromedriver_win32\\chromedriver.exe";
    //endregion

    @Before
    public void launchBrowser() {
        setProperty("webdriver.chrome.driver", webDriverPath);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    @Test()
    public void testOrderAll() throws NullPointerException {
        launchUrl();
        newUserLogin();
        addNewItemToCart();
        existingUserLogin();
    }

    private void launchUrl() {

        driver.get(automationUrl);
        driver.manage().window().maximize();
    }

    private void addNewItemToCart() {
        if (driver.findElement(By.xpath("//p[@class=\"info-account\"]")).isDisplayed()) {
            driver.findElement(By.xpath("//ul[@class=\"sf-menu clearfix menu-content sf-js-enabled sf-arrows\"]/li/a[@title=\"Women\"]")).click();
        }
        driver.findElement(By.xpath("//select[@id=\"selectProductSort\"]/option[@value=\"price:desc\"]")).click();
        if (driver.findElement(By.xpath("//div[@class=\"product-count\"]")).isDisplayed()) {
            driver.findElement(By.xpath("(//h5[@itemprop=\"name\"]//a[@class=\"product-name\"])[1]")).click();
            driver.findElement(By.xpath("//span[contains(text(),\"Add to cart\")]")).click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (driver.findElement(By.xpath("//div[@class=\"layer_cart_product col-xs-12 col-md-6\"]")).isDisplayed()) {
                driver.findElement(By.xpath("//span[@title=\"Continue shopping\"]")).click();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.findElement(By.xpath("//div[@class=\"cart_block block exclusive\"]/div[@class=\"block_content\"]")).isDisplayed();
            driver.findElement(By.xpath("//a[@title=\"Log me out\"]")).click();

        }
    }

    private void newUserLogin() throws NullPointerException {
        driver.findElement(By.xpath("//div[@class='header_user_info'][a]")).click();
        driver.findElement(By.xpath("//input[@id=\"email_create\"]")).sendKeys(emailID);
        driver.findElement(By.xpath("//div[@class=\"submit\"]")).submit();
        driver.findElement(By.xpath("//div[@class='radio']/span/input[@id='id_gender2']")).click();
        driver.findElement(By.xpath("//input[@id=\"customer_firstname\"]")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@id=\"customer_lastname\"]")).sendKeys(lastName);
        driver.findElement(By.xpath("//input[@type=\"password\"]")).sendKeys(passWord);

        Select days = new Select(driver.findElement(By.xpath("//select[@id=\"days\"]")));
        days.selectByValue(customerDate);

        Select month = new Select(driver.findElement(By.xpath("//select[@id=\"months\"]")));
        month.selectByIndex(3);

        Select year = new Select(driver.findElement(By.xpath("//select[@id=\"years\"]")));
        year.selectByValue(customerYear);

        driver.findElement(By.xpath("//input[@id=\"newsletter\"]")).click();
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)", "");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//input[@name=\"address1\"]")).sendKeys(customerAddress);
        driver.findElement(By.xpath("//input[@name=\"city\"]")).sendKeys(customerCity);
        Select state = new Select(driver.findElement(By.xpath("//select[@name=\"id_state\"]")));
        state.selectByIndex(2);
        driver.findElement(By.xpath("//input[@name=\"postcode\"]")).sendKeys(customerZipCode);
        driver.findElement(By.xpath("//select[@name=\"id_country\"]")).click();
        driver.findElement(By.xpath("//select[@name=\"id_country\"]/option[@value=\"21\"]"));
        js.executeScript("window.scrollBy(0,1000)");
        driver.findElement(By.xpath("//input[@id=\"phone_mobile\"]")).sendKeys(customerPhone);
        driver.findElement(By.xpath("//button[@name=\"submitAccount\"]")).click();
    }

    private void existingUserLogin() {

        driver.findElement(By.xpath("//a[@class=\"login\"]")).click();
        WebElement email = driver.findElement(By.xpath("//input[@id=\"email\"]"));
        email.sendKeys(emailID);
        WebElement password = driver.findElement(By.xpath("//input[@id=\"passwd\"]"));
        password.sendKeys(passWord);
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//p[@class=\"submit\"]/button[@type=\"submit\"]")).click();
        driver.findElement(By.xpath("//div[@class=\"shopping_cart\"]")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (driver.findElement(By.xpath("//div[@class=\"cart_block block exclusive\"]")).isDisplayed()) {
            driver.findElement(By.xpath("//dt[@class=\"first_item\"]")).isDisplayed();
        } else {
            System.out.println("Item is not added to cart");
        }

    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}
//endregion
//endregion
//</editor-fold>


