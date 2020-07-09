import mainClasses.HomePage;
import org.testng.annotations.Test;
import testData.Data;

public class MediaTests extends BaseUI {
    private String currentUrl;

    @Test(groups = {"smoke", "regression"})
    public void testMediaPage() {
        homePage.clickOnLink(HomePage.LinksOnHomePage.MEDIA);
        currentUrl = mediaPage.getCurrentUrl();
        softAssert.assertTrue(Data.mediaPageTitleExpected.equals(mediaPage.getTitle()), String.format(Data.incorrectTitleTestMessageFormat, "Media"));
        softAssert.assertEquals(currentUrl, Data.expectedUrlMediaPage);
        softAssert.assertAll();
    }
}
