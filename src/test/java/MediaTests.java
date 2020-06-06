import org.testng.Assert;
import org.testng.annotations.Test;

public class MediaTests extends BaseUI {
    private String currentUrlMedia;

    @Test
    public void testMediaPage() {
        driver.findElement(Locators.MEDIA_LINK).click();
        currentUrlMedia = driver.getCurrentUrl();
        Assert.assertTrue(Data.mediaPageTitleExpected.equals(driver.getTitle()), String.format(Data.incorrectTitleTestMessageFormat, "Media"));
        Assert.assertEquals(currentUrlMedia, Data.expectedUrlMediaPage);
    }
}
