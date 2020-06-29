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

    public void clickOnLink(LinksOnHomePage link) {
        driver.findElement(link.getLocator()).click();
        switch (link) {
            case SIGN_IN:
                wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.SIGN_IN_EMAIL_FIELD));
                break;
            case SEARCH:
                wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.SEARCH_PAGE_SEARCH_PARAMETERS_MIN_AGE_DROPDOWN));
        }
    }

    public void playYouTubeVideo() {
        scrollToElementUsingJS(Locators.FRAME_WITH_YOUTUBE_VIDEO);
        WebElement frame = driver.findElement(Locators.FRAME_WITH_YOUTUBE_VIDEO);
        driver.switchTo().frame(frame);
        driver.findElement(Locators.YOUTUBE_VIDEO_PLAY_BUTTON);
    }

    public enum LinksOnHomePage{
        SEARCH(Locators.NAVBAR_SEARCH_LINK),
        HOME(Locators.NAVBAR_HOME_LINK),
        HOW_IT_WORKS(Locators.NAVBAR_HOW_IT_WORKS_LINK),
        MEDIA(Locators.NAVBAR_MEDIA_LINK),
        GIFTS(Locators.NAVBAR_GIFTS_LINK),
        TOUR_TO_UKRAINE(Locators.NAVBAR_TOUR_TO_UKRAINE_LINK),
        BLOG(Locators.NAVBAR_BLOG_LINK),
        SIGN_IN(Locators.NAVBAR_SIGN_IN_LINK),
        JOIN_FOR_FREE_NOW(Locators.NAVBAR_JOIN_FOR_FREE_NOW_LINK);

        private By locator;

        LinksOnHomePage(By locator) {
            this.locator = locator;
        }

        public By getLocator() {
            return locator;
        }
    }
}
