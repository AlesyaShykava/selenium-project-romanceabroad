package com.romanceabroad.ui;

import com.romanceabroad.ui.mainClasses.Enums;
import com.romanceabroad.ui.testData.Data;
import org.testng.annotations.Test;
import com.automation.remarks.testng.VideoListener;
import com.automation.remarks.video.annotations.Video;
import org.testng.annotations.Listeners;

@Listeners(VideoListener.class)
public class ContactUsTests extends BaseUI {

    @Video(name = "contactAdministrator")
    @Test(groups = {"regression"})
    public void contactAdministrator() {
        homePage.clickOnLink(Enums.HomePageLinksOnHomePage.SEARCH);
        searchPage.clickOnContactUs();
        contactUsPage.selectReasonByText(Data.contactUsReasonDropDownTextOther);
        contactUsPage.fillInName(contactUsPage.generateRandomNumber(Data.name, 6));
        contactUsPage.fillInEmail(Data.email);
        contactUsPage.fillInSubject(Data.subject);
        contactUsPage.fillInMessage(Data.message);
    }

    @Video(name = "checkFooterLinksContactUsPage")
    @Test(groups = {"regression"})
    public void checkFooterLinks() {
        homePage.clickOnLink(Enums.HomePageLinksOnHomePage.BLOG);
        FooterTestsMethods.checkFooterLinks(contactUsPage);
    }
}
