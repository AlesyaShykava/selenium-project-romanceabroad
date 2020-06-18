import org.testng.annotations.Test;

public class TourToUkraineTests extends BaseUI {
    private String currentUrl;

    @Test
    public void testTourToUkrainePage() {
        homePage.clickOnLink(Locators.TOUR_TO_UKRAINE_LINK);
        currentUrl = tourToUkrainePage.getCurrentUrl();
        softAssert.assertTrue(Data.tourToUkrainePageTitleExpected.equals(tourToUkrainePage.getTitle()), String.format(Data.incorrectTitleTestMessageFormat, "Tour To Ukraine"));
        softAssert.assertEquals(currentUrl, Data.expectedUrlTourToUkrainePage);
        softAssert.assertAll();
    }
}
