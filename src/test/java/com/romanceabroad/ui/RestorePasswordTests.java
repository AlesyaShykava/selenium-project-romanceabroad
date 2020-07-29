package com.romanceabroad.ui;

import com.romanceabroad.ui.mainClasses.Enums;
import com.romanceabroad.ui.testData.Data;
import org.testng.annotations.Test;
import com.automation.remarks.testng.VideoListener;
import com.automation.remarks.video.annotations.Video;
import org.testng.annotations.Listeners;

@Listeners(VideoListener.class)
public class RestorePasswordTests extends BaseUI {
    String currentUrl;

    @Video(name = "restorePasswordIncorrectEmail")
    @Test(groups = {"regression", "integration"})
    public void restorePasswordIncorrectEmail() {
        homePage.clickOnLink(Enums.HomePageLinksOnHomePage.SIGN_IN);
        signInModal.clickOnForgotPasswordLink();
        currentUrl = restorePasswordPage.getCurrentUrl();
        softAssert.assertEquals(currentUrl, Data.expectedUrlRestorePage);
        restorePasswordPage.fillInEmailField(Data.incorrectTestData);
        restorePasswordPage.clickOnSubmitButton();
        restorePasswordPage.checkErrorBlockIsDisplayed();
        softAssert.assertAll();
    }
}
