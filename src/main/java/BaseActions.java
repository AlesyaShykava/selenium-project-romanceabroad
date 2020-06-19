import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

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

    protected void selectFromDropDownListByValue(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
    }

    public static String generateNewNumber(String name, int length){
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
}
