import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseUI {
    protected String mainURl = "https://romanceabroad.com/";
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions action;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
        action = new Actions(driver);
        wait = new WebDriverWait(driver, 20);
        driver.manage().window().maximize();
        driver.get(mainURl);
    }

    @AfterMethod
    public void afterActions() {
        driver.quit();
    }

    protected void moveMouseToElementAndClick(By locator) {
        action.moveToElement(driver.findElement(locator)).perform();
        driver.findElement(locator).click();
    }

    protected void selectFromDropDownListByValue(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
    }
}
