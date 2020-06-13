import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BaseActions{
    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public int getNumberOfLinksInNavbar() {
        return driver.findElements(Locators.NAVBAR).size();
    }

    public void clickOnLink(By locator) {
        driver.findElement(locator).click();
    }

    public void clickOnSignInLink() {
        driver.findElements(Locators.SIGN_IN_LINK).get(Data.indexSignInLink).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.SIGN_IN_PAGE_EMAIL_FIELD));
    }

    public void clickOnSearchLink() {
        driver.findElement(Locators.SEARCH_LINK).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.SEARCH_PAGE_SEARCH_PARAMETERS_MIN_AGE_DROPDOWN));
    }

    public void playYouTubeVideo() {
        WebElement frame = driver.findElement(Locators.FRAME_WITH_YOUTUBE_VIDEO);
        action.moveToElement(frame).perform();
        driver.switchTo().frame(frame);
        driver.findElement(Locators.YOUTUBE_VIDEO_PLAY_BUTTON);
    }
}
