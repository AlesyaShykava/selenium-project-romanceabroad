package mainClasses;

import locators.Locators;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.net.ssl.HttpsURLConnection;
import java.net.*;
import java.util.*;

public class BaseActions {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions action;
    protected Random random = new Random();

    public BaseActions(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        action = new Actions(driver);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    protected void moveMouseToElementAndClick(By locator) {
        action.moveToElement(driver.findElement(locator)).perform();
        driver.findElement(locator).click();
    }

    protected void selectFromDropDownListByValue(By dropDownLocator, String value) {
        Select select = new Select(driver.findElement(dropDownLocator));
        select.selectByValue(value);
    }

    public void selectFromDropDownListByIndex(By dropDownLocator, int index) {
        Select select = new Select(driver.findElement(dropDownLocator));
        select.selectByIndex(index);
    }

    public void selectFromDropDownListByText(By dropDownLocator, String text) {
        Select select = new Select(driver.findElement(dropDownLocator));
        select.selectByVisibleText(text);
    }

    public void clickOnElementFromList(By liLocator, String valueToClick) {
        List<WebElement> elements = driver.findElements(liLocator);
        for (int i = 0; i < elements.size(); i++) {
            WebElement element = elements.get(i);
            String text = element.getText();
            if(text != null && text.contains(valueToClick)) {
                element.click();
            }
        }
    }

    public boolean isElementDisplayed(By locator) {
        return driver.findElement(locator).isDisplayed();
    }

    public static String generateRandomNumber(String name, int length){
        return name + RandomStringUtils.random(length, "172984757");
    }

    protected void scrollToElementUsingJS(By locator) {
        WebElement element = driver.findElement(locator);
        String script = "arguments[0].scrollIntoView();";
        JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
        jsExecutor.executeScript(script, element);
    }

    protected void infiniteScrollUntilElementIsLoaded(By locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
        String script = "window.scrollTo(0, document.body.scrollHeight)";
        while (driver.findElements(locator).size() == 0) {
            jsExecutor.executeScript(script);
        }
    }

    protected void waitThreadSleepSec(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException :" + e.getMessage());
        }
    }

    protected void waitThreadSleepMS(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException :" + e.getMessage());
        }
    }

    protected void sendKeysJS(By locator, String text) {
        String script = String.format("arguments[0].setAttribute('value', '%s')", text);
        ((JavascriptExecutor)driver).executeScript(script, driver.findElement(locator));
    }

    /**@return value int, error code -1*/
    public int selectRandomOptionFromDropDown(By dropDownLocator, String dropDownName) {
        int randomIndexToSelect = -1;
        try {
            WebElement webElementDropDown = driver.findElement(dropDownLocator);
            scrollToElementUsingJS(dropDownLocator);
            Select select = new Select(webElementDropDown);
            randomIndexToSelect = random.nextInt(select.getOptions().size());
            select.selectByIndex(randomIndexToSelect);
            System.out.println(dropDownName + " : " + select.getFirstSelectedOption().getText());
        } catch (NoSuchElementException e) {
            System.out.println("NoSuchElementException: " + e.getMessage());
        }
        return randomIndexToSelect;
    }

    public Map<String, Integer> checkLinksOnWebPage(String tagName, String attribute) {
        Map<String, Integer> mappingLinksWithResponseCode = new HashMap<>();
        List<WebElement> listOfElements = driver.findElements(By.xpath(String.format(Locators.LINKS_CHECKS_STRING_FORMAT, tagName)));
        for(int i = 0; i < listOfElements.size(); i++) {
            WebElement element = listOfElements.get(i);
            String urlValue = element.getAttribute(attribute);
            int responseCode = checkLinkStatus(urlValue);
            mappingLinksWithResponseCode.put(urlValue, responseCode);
        }
        System.out.println("Total number of elements: " + listOfElements.size());
        return mappingLinksWithResponseCode;
    }

    /**@return value int, error code -1*/
    protected int checkLinkStatus(String urlValue) {
        int responseCode = -1;
        try {
            URL url = new URL(urlValue);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(3000);
            HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
            httpURLConnection.connect();
            responseCode = httpURLConnection.getResponseCode();
            System.out.println(urlValue + " - " + httpURLConnection.getResponseMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage() + " : " + urlValue + " : " + responseCode);
        }
        return responseCode;
    }

    /**@return value int, error code -1*/
    public int getSizeDropDownList(By locator) {
        int size = -1;
        try{
            WebElement dropDownList = driver.findElement(locator);
            scrollToElementUsingJS(locator);
            Select select = new Select(dropDownList);
            size = select.getOptions().size();
        } catch (NoSuchElementException e) {
            System.out.println("getSizeDropDownList error " + e.getMessage());
        }
        return size;
    }

    public void clickOnContactUs() {
        scrollToElementUsingJS(Locators.FOOTER_MENU_CONTACT_US);
        driver.findElement(Locators.FOOTER_MENU_CONTACT_US).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.CONTACT_US_PAGE_REASON_DROPDOWN));
    }

    public String getH1Title() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.H1_TITLE));
        return driver.findElement(Locators.H1_TITLE).getText();
    }
}
