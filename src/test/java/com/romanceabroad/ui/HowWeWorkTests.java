package com.romanceabroad.ui;

import com.romanceabroad.ui.mainClasses.Enums;
import com.romanceabroad.ui.testData.Data;
import org.testng.annotations.Test;
import com.automation.remarks.testng.VideoListener;
import com.automation.remarks.video.annotations.Video;
import org.testng.annotations.Listeners;

@Listeners(VideoListener.class)
public class HowWeWorkTests extends BaseUI {
    private String currentUrl;

    @Video(name = "testHowWeWorkPage")
    @Test(groups = {"smoke", "regression"})
    public void testHowWeWorkPage() {
        homePage.clickOnLink(Enums.HomePageLinksOnHomePage.HOW_IT_WORKS);
        currentUrl = howWeWorkPage.getCurrentUrl();
        softAssert.assertEquals(currentUrl, Data.expectedUrlHowItWorksPage);
        softAssert.assertTrue(Data.howItWorksPageTitleExpected.equals(howWeWorkPage.getTitle()), String.format(Data.incorrectTitleTestMessageFormat, "How We Work"));
        softAssert.assertAll();
    }
}
