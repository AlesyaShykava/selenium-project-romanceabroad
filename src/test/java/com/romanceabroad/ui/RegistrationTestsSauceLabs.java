package com.romanceabroad.ui;

import com.automation.remarks.testng.VideoListener;
import com.aventstack.extentreports.Status;
import com.romanceabroad.ui.mainClasses.Enums;
import com.romanceabroad.ui.reportUtil.Reports;
import com.romanceabroad.ui.testData.Data;
import com.romanceabroad.ui.testData.DataProvidersSauceLabs;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(VideoListener.class)
public class RegistrationTestsSauceLabs extends BaseUI {

    @Test(groups = {"regression"}, dataProvider = "RegistrationDataSetWrongCredentials", dataProviderClass = DataProvidersSauceLabs.class)
    public void registrationWithWrongCredentials(String incorrectEmail, String incorrectPassword) {
        homePage.clickOnLink(Enums.HomePageLinksOnHomePage.JOIN_FOR_FREE_NOW);
        registrationModal.fillInEmail(incorrectEmail);
        registrationModal.fillInPassword(incorrectPassword);
        registrationModal.clickOnPagination();
        String emailIncorrectAlertMessageActual = registrationModal.getEmailAlertMessage();
        String passwordIncorrectAlertMessageActual = registrationModal.getPasswordAlertMessage();
        boolean isEmailAlertCorrect = Data.registrationFormEmailIncorrectAlertMessageExpected.equals(emailIncorrectAlertMessageActual);
        if(isEmailAlertCorrect) {
            Reports.log(Status.INFO, String.format("Alert message for email field correct: %s", emailIncorrectAlertMessageActual));
            softAssert.assertTrue(true);
        } else {
            Reports.log(Status.FAIL, String.format("Alert message for email field is incorrect. Expected: %s, Actual: %s",
                    Data.registrationFormEmailIncorrectAlertMessageExpected, emailIncorrectAlertMessageActual));
            softAssert.fail("");
        }
        boolean isPasswordAlertCorrect = Data.registrationFormPasswordIncorrectAlertMessageExpected.equals(passwordIncorrectAlertMessageActual);
        if(isPasswordAlertCorrect) {
            Reports.log(Status.INFO, String.format("Alert message for password field correct: %s", passwordIncorrectAlertMessageActual));
            softAssert.assertTrue(true);
        } else {
            Reports.log(Status.FAIL, String.format("Alert message for password field is incorrect. Expected: %s, Actual: %s",
                    Data.registrationFormPasswordIncorrectAlertMessageExpected, passwordIncorrectAlertMessageActual));
            softAssert.fail("");
        }
        softAssert.assertAll();
    }
}
