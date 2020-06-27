import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class HomeTests extends BaseUI {
    private String currentUrl;

    @Test(priority = 1, groups = {"regression"})
    public void testNumberOfLinksOfNavbar() {
        int actualNumberOfLinksInNavbar = homePage.getNumberOfLinksInNavbar();
        Assert.assertEquals(actualNumberOfLinksInNavbar, Data.expectedNumberOfLinksInNavbar);
    }

    @Test(groups = {"smoke", "regression"})
    public void testHomePage() {
        homePage.clickOnLink(HomePage.LinksOnHomePage.HOME);
        currentUrl = homePage.getCurrentUrl();
        softAssert.assertEquals(currentUrl, Data.expectedUrlHomePage);
        softAssert.assertTrue(Data.homePageTitleExpected.equals(homePage.getTitle()), String.format(Data.incorrectTitleTestMessageFormat, "Home"));
        softAssert.assertAll();
    }

    @Test(priority = 3, groups = {"regression", "integration"})
    public void testYouTubeVideoLink() {
        homePage.playYouTubeVideo();
    }

    @Test(priority = 3, groups = {"regression", "integration"})
    public void testLinksResponseCode() {
        Map<String, Integer> linksWithCodeResponse = homePage.checkLinksOnWebPage("a", "href");
        for(Map.Entry<String, Integer> pair : linksWithCodeResponse.entrySet()) {
            boolean isResponseSuccess = Integer.valueOf(200).equals(pair.getValue());
            softAssert.assertTrue(isResponseSuccess, String.format("For the ulr %s expected code was 200, but found %d", pair.getKey(), pair.getValue()));
        }
        softAssert.assertAll();
    }

    @Test(priority = 2, groups = {"regression"})
    public void testImgResponseCode() {
        Map<String, Integer> linksWithCodeResponse = homePage.checkLinksOnWebPage("img", "src");
        for(Map.Entry<String, Integer> pair : linksWithCodeResponse.entrySet()) {
            boolean isResponseSuccess = Integer.valueOf(200).equals(pair.getValue());
            softAssert.assertTrue(isResponseSuccess, String.format("For the ulr %s expected code was 200, but found %d", pair.getKey(), pair.getValue()));
        }
        softAssert.assertAll();
    }
}
