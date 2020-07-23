package com.romanceabroad.ui;

import com.romanceabroad.ui.mainClasses.Enums;
import com.romanceabroad.ui.testData.Data;
import org.testng.annotations.Test;

public class TourToUkraineTests extends BaseUI {
    private String currentUrl;

    @Test(groups = {"smoke", "regression"})
    public void testTourToUkrainePage() {
        homePage.clickOnLink(Enums.HomePageLinksOnHomePage.TOUR_TO_UKRAINE);
        currentUrl = tourToUkrainePage.getCurrentUrl();
        softAssert.assertTrue(Data.tourToUkrainePageTitleExpected.equals(tourToUkrainePage.getTitle()), String.format(Data.incorrectTitleTestMessageFormat, "Tour To Ukraine"));
        softAssert.assertEquals(currentUrl, Data.expectedUrlTourToUkrainePage);
        softAssert.assertAll();
    }
}
