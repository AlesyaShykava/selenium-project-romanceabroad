package com.romanceabroad.ui;

import com.romanceabroad.ui.mainClasses.Enums;
import com.romanceabroad.ui.testData.Data;
import org.testng.annotations.Test;
import com.automation.remarks.testng.VideoListener;
import com.automation.remarks.video.annotations.Video;
import org.testng.annotations.Listeners;

@Listeners(VideoListener.class)
public class TourToUkraineTests extends BaseUI {
    private String currentUrl;

    @Video(name = "testTourToUkrainePage")
    @Test(groups = {"smoke", "regression"})
    public void testTourToUkrainePage() {
        homePage.clickOnLink(Enums.HomePageLinksOnHomePage.TOUR_TO_UKRAINE);
        currentUrl = tourToUkrainePage.getCurrentUrl();
        softAssert.assertTrue(Data.tourToUkrainePageTitleExpected.equals(tourToUkrainePage.getTitle()), String.format(Data.incorrectTitleTestMessageFormat, "Tour To Ukraine"));
        softAssert.assertEquals(currentUrl, Data.expectedUrlTourToUkrainePage);
        softAssert.assertAll();
    }

    @Video(name = "checkFooterLinksTourToUkrainePAge")
    @Test(groups = {"regression"})
    public void checkFooterLinks() {
        homePage.clickOnLink(Enums.HomePageLinksOnHomePage.TOUR_TO_UKRAINE);
        FooterTestsMethods.checkFooterLinks(tourToUkrainePage);
    }
}
