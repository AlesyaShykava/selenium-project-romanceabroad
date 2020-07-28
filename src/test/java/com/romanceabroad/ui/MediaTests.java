package com.romanceabroad.ui;

import com.romanceabroad.ui.mainClasses.Enums;
import com.romanceabroad.ui.testData.Data;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MediaTests extends BaseUI {
    private String currentUrl;
    private String actualTitle;

    @Test(groups = {"smoke", "regression"})
    public void testMediaPage() {
        homePage.clickOnLink(Enums.HomePageLinksOnHomePage.MEDIA);
        currentUrl = mediaPage.getCurrentUrl();
        softAssert.assertTrue(Data.mediaPageTitleExpected.equals(mediaPage.getTitle()), String.format(Data.incorrectTitleTestMessageFormat, "Media"));
        softAssert.assertEquals(currentUrl, Data.mediaPageExpectedUrlMediaPage);
        softAssert.assertAll();
    }

    @Test(groups = {"smoke", "regression"})
    public void testUserTabs() {
        homePage.clickOnLink(Enums.HomePageLinksOnHomePage.MEDIA);
        Enums.MediaPageTabLinks[] userTabs = Enums.MediaPageTabLinks.values();
        actualTitle = mediaPage.getH1Title();
        Assert.assertEquals(actualTitle, Data.mediaPageExpectedTitleGallery);

        for (int i = 0; i < userTabs.length; i++) {
            mediaPage.clickOnTabLink(userTabs[i]);
            actualTitle = mediaPage.getH1Title();
            if(i == 0) {
                Assert.assertEquals(actualTitle, Data.mediaPageExpectedTitleAllTab);
            } else if(i == 1) {
                Assert.assertEquals(actualTitle, Data.mediaPageExpectedTitlePhotoTab);
            } else if(i == 2) {
                Assert.assertEquals(actualTitle, Data.mediaPageExpectedTitleVideoTab);
                String textMedia = mediaPage.getTextFromVideoTab();
                if(textMedia.contains((Data.mediaPageTextVideoTabText))) {
                    System.out.println("Text media is correct!");
                }
            } else if(i == 3) {
                Assert.assertEquals(actualTitle, Data.mediaPageExpectedTitleAlbumTab);
                mediaPage.waitThreadSleepSec(2);
                Assert.assertTrue(mediaPage.isElementDisplayed(By.xpath("//span[@data-click='album']")));
            }
        }
    }

    @Test(groups = {"regression"})
    public void checkFooterLinks() {
        homePage.clickOnLink(Enums.HomePageLinksOnHomePage.MEDIA);
        FooterTestsMethods.checkFooterLinks(mediaPage);
    }
}
