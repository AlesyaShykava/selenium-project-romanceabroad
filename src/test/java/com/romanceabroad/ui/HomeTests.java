package com.romanceabroad.ui;

import com.romanceabroad.ui.mainClasses.Enums;
import com.romanceabroad.ui.testData.Data;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.automation.remarks.testng.VideoListener;
import com.automation.remarks.video.annotations.Video;
import org.testng.annotations.Listeners;

import java.util.Map;

@Listeners(VideoListener.class)
public class HomeTests extends BaseUI {
    private String currentUrl;

    @Video(name = "testNumberOfLinksOfNavbar")
    @Test(groups = {"regression"})
    public void testNumberOfLinksOfNavbar() {
        int actualNumberOfLinksInNavbar = homePage.getNumberOfLinksInNavbar();
        Assert.assertEquals(actualNumberOfLinksInNavbar, Data.expectedNumberOfLinksInNavbar);
    }

    @Video(name = "testHomePage")
    @Test(groups = {"smoke", "regression"})
    public void testHomePage() {
        homePage.clickOnLink(Enums.HomePageLinksOnHomePage.HOME);
        currentUrl = homePage.getCurrentUrl();
        softAssert.assertEquals(currentUrl, Data.expectedUrlHomePage);
        softAssert.assertTrue(Data.homePageTitleExpected.equals(homePage.getTitle()), String.format(Data.incorrectTitleTestMessageFormat, "Home"));
        softAssert.assertAll();
    }

    @Video(name = "testYouTubeVideoLink")
    @Test(priority = 3, groups = {"regression", "integration"})
    public void testYouTubeVideoLink() {
        homePage.playYouTubeVideo();
    }

    @Video(name = "testLinksResponseCode")
    @Test(priority = 3, groups = {"regression", "integration"})
    public void testLinksResponseCode() {
        Map<String, Integer> linksWithCodeResponse = homePage.checkLinksOnWebPage("a", "href");
        for(Map.Entry<String, Integer> pair : linksWithCodeResponse.entrySet()) {
            boolean isResponseSuccess = Integer.valueOf(200).equals(pair.getValue());
            softAssert.assertTrue(isResponseSuccess, String.format("For the ulr %s expected code was 200, but found %d", pair.getKey(), pair.getValue()));
        }
        softAssert.assertAll();
    }

    @Video(name = "testImgResponseCode")
    @Test(priority = 2, groups = {"regression"})
    public void testImgResponseCode() {
        Map<String, Integer> linksWithCodeResponse = homePage.checkLinksOnWebPage("img", "src");
        for(Map.Entry<String, Integer> pair : linksWithCodeResponse.entrySet()) {
            boolean isResponseSuccess = Integer.valueOf(200).equals(pair.getValue());
            softAssert.assertTrue(isResponseSuccess, String.format("For the ulr %s expected code was 200, but found %d", pair.getKey(), pair.getValue()));
        }
        softAssert.assertAll();
    }
}
