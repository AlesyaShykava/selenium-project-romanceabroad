package com.romanceabroad.ui;

import com.romanceabroad.ui.mainClasses.Enums;
import com.romanceabroad.ui.locators.Locators;
import com.romanceabroad.ui.testData.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.automation.remarks.testng.VideoListener;
import com.automation.remarks.video.annotations.Video;
import org.testng.annotations.Listeners;

@Listeners(VideoListener.class)
public class RegistrationTests extends BaseUI {

    @Video(name = "registrationHappyPath")
    @Test(groups = {"smoke", "regression"}, dataProvider = "RegistrationDataSetHappyPath", dataProviderClass = DataProviders.class)
    public void registrationHappyPath(String email, String password, String nickName, String phone, String monthDOB,
                                      String dayDOB, String yearDOB, String locationCity, String locationFull) {
        homePage.clickOnLink(Enums.HomePageLinksOnHomePage.JOIN_FOR_FREE_NOW);
        registrationModal.fillInEmail(email);
        registrationModal.fillInPassword(password);
        registrationModal.clickOnNextButton();
        registrationModal.fillInNickName(mediaPage.generateRandomNumber(nickName, 6));
        registrationModal.fillInPhone(phone);
        registrationModal.selectDOBMonth(monthDOB);
        registrationModal.selectDOBDay(dayDOB);
        registrationModal.selectDOBYear(yearDOB);
        registrationModal.selectLocation(locationCity, locationFull);
        if(!registrationModal.isTermsAndConditionsChecked()) {
            registrationModal.checkTermsAndConditionsCheckbox();
        }
    }

    @Video(name = "registrationWithWrongCredentials")
    @Test(groups = {"regression"}, dataProvider = "RegistrationDataSetWrongCredentials", dataProviderClass = DataProviders.class)
    public void registrationWithWrongCredentials(String incorrectEmail, String incorrectPassword) {
        homePage.clickOnLink(Enums.HomePageLinksOnHomePage.JOIN_FOR_FREE_NOW);
        registrationModal.fillInEmail(incorrectEmail);
        registrationModal.fillInPassword(incorrectPassword);
        registrationModal.clickOnPagination();
        String emailIncorrectAlertMessageActual = registrationModal.getEmailAlertMessage();
        String passwordIncorrectAlertMessageActual = registrationModal.getPasswordAlertMessage();
        softAssert.assertTrue(Data.registrationFormEmailIncorrectAlertMessageExpected.equals(emailIncorrectAlertMessageActual));
        softAssert.assertTrue(Data.registrationFormPasswordIncorrectAlertMessageExpected.equals(passwordIncorrectAlertMessageActual));
        softAssert.assertAll();
    }

    @Video(name = "testRegistration2")
    @Test(groups = {"regression"}, dataProvider = "Registration2",dataProviderClass = DataProviders.class)
    public void testRegistration2(String email, String nickName, boolean requirement) {
        homePage.clickOnLink(Enums.HomePageLinksOnHomePage.JOIN_FOR_FREE_NOW);
        registrationModal.fillInEmail(email);
        registrationModal.fillInPassword(Data.password);
        if (!requirement) {
            Assert.assertTrue(registrationModal.isElementDisplayed(Locators.REGISTRATION_INCORRECT_EMAIL_ALERT));
            String emailIncorrectAlertMessageActual = registrationModal.getEmailAlertMessage();
            Assert.assertTrue(Data.registrationFormEmailIncorrectAlertMessageExpected.equals(emailIncorrectAlertMessageActual));
            Assert.assertTrue(registrationModal.isElementDisplayed(Locators.REGISTRATION_NEXT_BUTTON_DISABLED));

        }
        else {
            registrationModal.clickOnNextButton();
            registrationModal.fillInNickName(nickName);
            registrationModal.fillInPhone(Data.phone);
            registrationModal.selectDOBMonth(Data.monthDOB);
            registrationModal.selectDOBDay(Data.dayDOB);
            registrationModal.selectDOBYear(Data.yearDOB);
            registrationModal.selectLocation(Data.locationCity, Data.locationFull);
            if(!registrationModal.isTermsAndConditionsChecked()) {
                registrationModal.checkTermsAndConditionsCheckbox();
            }
        }
    }
}
