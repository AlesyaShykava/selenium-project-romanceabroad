import org.testng.Assert;
import org.testng.annotations.Test;

public class TourToUkraineTests extends BaseUI {
    private String currentUrl;

    @Test
    public void testTourToUkrainePage() {
        homePage.clickOnLink(Locators.TOUR_TO_UKRAINE_LINK);
        currentUrl = tourToUkrainePage.getCurrentUrl();
        Assert.assertTrue(Data.tourToUkrainePageTitleExpected.equals(tourToUkrainePage.getTitle()), String.format(Data.incorrectTitleTestMessageFormat, "Tour To Ukraine"));
        Assert.assertEquals(currentUrl, Data.expectedUrlTourToUkrainePage);
    }
}
