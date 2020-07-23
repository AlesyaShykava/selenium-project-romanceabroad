package com.romanceabroad.ui;

import com.romanceabroad.ui.mainClasses.Enums;
import com.romanceabroad.ui.testData.Data;
import org.testng.annotations.Test;

public class ContactUsTests extends BaseUI {

    @Test
    public void contactAdministrator() {
        homePage.clickOnLink(Enums.HomePageLinksOnHomePage.SEARCH);
        searchPage.clickOnContactUs();
        contactUsPage.selectReasonByText(Data.contactUsReasonDropDownTextOther);
        contactUsPage.fillInName(contactUsPage.generateRandomNumber(Data.name, 6));
        contactUsPage.fillInEmail(Data.email);
        contactUsPage.fillInSubject(Data.subject);
        contactUsPage.fillInMessage(Data.message);
    }
}
