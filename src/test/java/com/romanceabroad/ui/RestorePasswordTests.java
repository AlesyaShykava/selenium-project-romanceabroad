package com.romanceabroad.ui;

import com.romanceabroad.ui.mainClasses.HomePage;
import com.romanceabroad.ui.testData.Data;
import org.testng.annotations.Test;

public class RestorePasswordTests extends BaseUI {
    String currentUrl;

    @Test(groups = {"regression", "integration"})
    public void restorePasswordIncorrectEmail() {
        homePage.clickOnLink(HomePage.LinksOnHomePage.SIGN_IN);
        signInModal.clickOnForgotPasswordLink();
        currentUrl = restorePasswordPage.getCurrentUrl();
        softAssert.assertEquals(currentUrl, Data.expectedUrlRestorePage);
        restorePasswordPage.fillInEmailField(Data.incorrectTestData);
        restorePasswordPage.clickOnSubmitButton();
        restorePasswordPage.checkErrorBlockIsDisplayed();
        softAssert.assertAll();
    }
}