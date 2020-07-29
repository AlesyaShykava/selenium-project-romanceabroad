package com.romanceabroad.ui;

import com.romanceabroad.ui.mainClasses.Enums;
import com.romanceabroad.ui.testData.Data;
import org.testng.annotations.Test;
import com.automation.remarks.testng.VideoListener;
import com.automation.remarks.video.annotations.Video;
import org.testng.annotations.Listeners;

@Listeners(VideoListener.class)
public class GiftsTests extends BaseUI {
    private String currentUrl;

    @Video(name = "testGiftsPage")
    @Test(groups = {"smoke", "regression"})
    public void testGiftsPage() {
        homePage.clickOnLink(Enums.HomePageLinksOnHomePage.GIFTS);
        currentUrl = giftsPage.getCurrentUrl();
        softAssert.assertTrue(Data.giftsPageTitleExpected.equals(giftsPage.getTitle()), String.format(Data.incorrectTitleTestMessageFormat, "Gifts"));
        softAssert.assertEquals(currentUrl, Data.expectedUrlGiftsPage);
        softAssert.assertAll();
    }

    @Video(name = "checkFooterLinksGiftsPage")
    @Test(groups = {"regression"})
    public void checkFooterLinks() {
        homePage.clickOnLink(Enums.HomePageLinksOnHomePage.GIFTS);
        FooterTestsMethods.checkFooterLinks(giftsPage);
    }
}
