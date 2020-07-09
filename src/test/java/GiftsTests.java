import mainClasses.HomePage;
import org.testng.annotations.Test;
import testData.Data;

public class GiftsTests extends BaseUI {
    private String currentUrl;

    @Test(groups = {"smoke", "regression"})
    public void testGiftsPage() {
        homePage.clickOnLink(HomePage.LinksOnHomePage.GIFTS);
        currentUrl = giftsPage.getCurrentUrl();
        softAssert.assertTrue(Data.giftsPageTitleExpected.equals(giftsPage.getTitle()), String.format(Data.incorrectTitleTestMessageFormat, "Gifts"));
        softAssert.assertEquals(currentUrl, Data.expectedUrlGiftsPage);
        softAssert.assertAll();
    }
}
