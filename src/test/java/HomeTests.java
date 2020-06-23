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
        homePage.clickOnLink(HomePage.LinksOnHomePage.HOME);
        currentUrl = homePage.getCurrentUrl();
        softAssert.assertEquals(currentUrl, Data.expectedUrlHomePage);
        softAssert.assertTrue(Data.homePageTitleExpected.equals(homePage.getTitle()), String.format(Data.incorrectTitleTestMessageFormat, "Home"));
        softAssert.assertAll();
    }

    @Test
    public void testYouTubeVideoLink() {
        homePage.playYouTubeVideo();
    }
}
