package com.romanceabroad.ui;

import com.romanceabroad.ui.locators.Locators;
import com.romanceabroad.ui.mainClasses.HomePage;
import com.romanceabroad.ui.mainClasses.MediaPage;
import com.romanceabroad.ui.testData.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class MediaTests extends BaseUI {
    private String currentUrl;
    private String actualTitle;

    @Test(groups = {"smoke", "regression"})
    public void testMediaPage() {
        homePage.clickOnLink(HomePage.LinksOnHomePage.MEDIA);
        currentUrl = mediaPage.getCurrentUrl();
        softAssert.assertTrue(Data.mediaPageTitleExpected.equals(mediaPage.getTitle()), String.format(Data.incorrectTitleTestMessageFormat, "Media"));
        softAssert.assertEquals(currentUrl, Data.mediaPageExpectedUrlMediaPage);
        softAssert.assertAll();
    }

    @Test(groups = {"smoke", "regression"})
    public void testUserTabs() {
        homePage.clickOnLink(HomePage.LinksOnHomePage.MEDIA);
        MediaPage.TabLinks[] userTabs = MediaPage.TabLinks.values();
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
            } else if(i == 3) {
                Assert.assertEquals(actualTitle, Data.mediaPageExpectedTitleAlbumTab);
            }
        }
    }
}
