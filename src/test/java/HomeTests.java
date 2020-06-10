import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeTests extends BaseUI {
    private String currentUrl;

    @Test
    public void testNumberOfLinksOfNavbar() {
        int actualNumberOfLinksInNavbar = homePage.getNumberOfLinksInNavbar();
        Assert.assertEquals(actualNumberOfLinksInNavbar, Data.expectedNumberOfLinksInNavbar);
    }

    @Test
    public void testHomePage() {
        homePage.clickOnLink(Locators.HOME_LINK);
        currentUrl = homePage.getCurrentUrl();
        Assert.assertEquals(currentUrl, Data.expectedUrlHomePage);
        Assert.assertTrue(Data.homePageTitleExpected.equals(homePage.getTitle()), String.format(Data.incorrectTitleTestMessageFormat, "Home"));
    }

    @Test
    public void testYouTubeVideoLink() {
        homePage.playYouTubeVideo();
    }
}
