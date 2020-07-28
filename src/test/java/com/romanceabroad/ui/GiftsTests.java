package com.romanceabroad.ui;

import com.romanceabroad.ui.mainClasses.Enums;
import com.romanceabroad.ui.testData.Data;
import org.testng.annotations.Test;

public class GiftsTests extends BaseUI {
    private String currentUrl;

    @Test(groups = {"smoke", "regression"})
    public void testGiftsPage() {
        homePage.clickOnLink(Enums.HomePageLinksOnHomePage.GIFTS);
        currentUrl = giftsPage.getCurrentUrl();
        softAssert.assertTrue(Data.giftsPageTitleExpected.equals(giftsPage.getTitle()), String.format(Data.incorrectTitleTestMessageFormat, "Gifts"));
        softAssert.assertEquals(currentUrl, Data.expectedUrlGiftsPage);
        softAssert.assertAll();
    }

    @Test(groups = {"regression"})
    public void checkFooterLinks() {
        homePage.clickOnLink(Enums.HomePageLinksOnHomePage.GIFTS);
        FooterTestsMethods.checkFooterLinks(giftsPage);
    }
}
