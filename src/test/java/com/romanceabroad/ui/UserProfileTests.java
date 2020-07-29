package com.romanceabroad.ui;

import com.romanceabroad.ui.locators.Locators;
import com.romanceabroad.ui.mainClasses.Enums;
import com.romanceabroad.ui.mainClasses.UserProfilePage;
import com.romanceabroad.ui.testData.Data;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.automation.remarks.testng.VideoListener;
import com.automation.remarks.video.annotations.Video;
import org.testng.annotations.Listeners;

import java.util.Random;

@Listeners(VideoListener.class)
public class UserProfileTests extends BaseUI {
    private Random random = new Random();

    @Video(name = "testTabs")
    @Test(groups = {"smoke", "regression"})
    public void testTabs() {
        homePage.clickOnLink(Enums.HomePageLinksOnHomePage.SEARCH);
        boolean isSearchNeeded = true;
        while (isSearchNeeded) {
            searchPage.selectRandomOptionFromDropDown(Locators.SEARCH_PAGE_SEARCH_PARAMETERS_MIN_AGE_DROPDOWN, "minAge");
            searchPage.clickOnSearchButton();
            isSearchNeeded = searchPage.getListOfUserInfoNameAndAgeFirstPage().isEmpty();
            if(isSearchNeeded) continue;

            int randomIndex = random.nextInt(searchPage.getListOfUserInfoNameAndAgeFirstPage().size());
            searchPage.clickOnProfileLinkByIndex(randomIndex);
            String activeTab = userProfilePage.getActiveTab();
            Assert.assertEquals(activeTab, Data.userProfilePageExpectedActiveTab);
            UserProfilePage.TabLinks[] tabs = UserProfilePage.TabLinks.values();
            for(UserProfilePage.TabLinks tab : tabs) {
                userProfilePage.clickOnTab(tab);
            }
        }
    }
}
