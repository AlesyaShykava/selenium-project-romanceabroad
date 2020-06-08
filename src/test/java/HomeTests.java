import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeTests extends BaseUI {
    private String currentUrlHome;

    @Test
    public void testNumberOfLinksOfNavbar() {
        int actualNumberOfLinksInNavbar = driver.findElements(Locators.NAVBAR).size();
        Assert.assertEquals(actualNumberOfLinksInNavbar, Data.expectedNumberOfLinksInNavbar);
    }

    @Test
    public void testHomePage() {
        driver.findElement(Locators.HOME_LINK).click();
        currentUrlHome = driver.getCurrentUrl();
        Assert.assertEquals(currentUrlHome, Data.expectedUrlHomePage);
        Assert.assertTrue(Data.homePageTitleExpected.equals(driver.getTitle()), String.format(Data.incorrectTitleTestMessageFormat, "Home"));
    }

    @Test
    public void testYouTubeVideoLink() {
        WebElement frame = driver.findElement(Locators.FRAME_WITH_YOUTUBE_VIDEO);
        action.moveToElement(frame).perform();
        driver.switchTo().frame(frame);
        driver.findElement(Locators.YOUTUBE_VIDEO_PLAY_BUTTON);
    }
}
