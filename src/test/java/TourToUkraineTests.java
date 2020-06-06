import org.testng.Assert;
import org.testng.annotations.Test;

public class TourToUkraineTests extends BaseUI {
    private String currentUrlTourToUkraine;

    @Test
    public void testTourToUkrainePage() {
        driver.findElement(Locators.TOUR_TO_UKRAINE_LINK).click();
        currentUrlTourToUkraine = driver.getCurrentUrl();
        Assert.assertTrue(Data.tourToUkrainePageTitleExpected.equals(driver.getTitle()), String.format(Data.incorrectTitleTestMessageFormat, "Tour To Ukraine"));
        Assert.assertEquals(currentUrlTourToUkraine, Data.expectedUrlTourToUkrainePage);
    }
}
