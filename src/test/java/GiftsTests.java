import org.testng.Assert;
import org.testng.annotations.Test;

public class GiftsTests extends BaseUI {
    private String currentUrlGifts;

    @Test
    public void testGiftsPage() {
        driver.findElement(Locators.GIFTS_LINK).click();
        currentUrlGifts = driver.getCurrentUrl();
        Assert.assertTrue(Data.giftsPageTitleExpected.equals(driver.getTitle()), String.format(Data.incorrectTitleTestMessageFormat, "Gifts"));
        Assert.assertEquals(currentUrlGifts, Data.expectedUrlGiftsPage);
    }
}
