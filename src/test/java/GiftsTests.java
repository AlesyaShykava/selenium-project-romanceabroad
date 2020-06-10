import org.testng.Assert;
import org.testng.annotations.Test;

public class GiftsTests extends BaseUI {
    private String currentUrl;

    @Test
    public void testGiftsPage() {
        homePage.clickOnLink(Locators.GIFTS_LINK);
        currentUrl = giftsPage.getCurrentUrl();
        Assert.assertTrue(Data.giftsPageTitleExpected.equals(giftsPage.getTitle()), String.format(Data.incorrectTitleTestMessageFormat, "Gifts"));
        Assert.assertEquals(currentUrl, Data.expectedUrlGiftsPage);
    }
}
