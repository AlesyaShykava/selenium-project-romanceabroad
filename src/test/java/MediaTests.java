import org.testng.Assert;
import org.testng.annotations.Test;

public class MediaTests extends BaseUI {
    private String currentUrl;

    @Test
    public void testMediaPage() {
        homePage.clickOnLink(Locators.MEDIA_LINK);
        currentUrl = mediaPage.getCurrentUrl();
        softAssert.assertTrue(Data.mediaPageTitleExpected.equals(mediaPage.getTitle()), String.format(Data.incorrectTitleTestMessageFormat, "Media"));
        softAssert.assertEquals(currentUrl, Data.expectedUrlMediaPage);
        softAssert.assertAll();
    }
}
