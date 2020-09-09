package com.romanceabroad.ui;

import com.romanceabroad.ui.mainClasses.Enums;
import com.romanceabroad.ui.testData.Data;
import com.romanceabroad.ui.testData.DataProviders;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.automation.remarks.testng.VideoListener;
import com.automation.remarks.video.annotations.Video;
import org.testng.annotations.Listeners;

@Listeners(VideoListener.class)
public class SignInTests extends BaseUI {
    private String currentUrl;

    @Video(name = "testSignIn")
    @Test(groups = {"smoke", "regression"})
    public void testSignInModalWindow() {
        homePage.clickOnLink(Enums.HomePageLinksOnHomePage.SIGN_IN);
        currentUrl = blogPage.getCurrentUrl();
        softAssert.assertTrue(Data.signInFormTitleExpected.equals(signInModal.getTitle()), String.format(Data.incorrectTitleTestMessageFormat, "Sign In form"));
    }

    @Video(name = "sigInNegativeCheckEmailField")
    @Test(groups = {"regression", "negative"}, dataProviderClass = DataProviders.class, dataProvider = "signInNegativeCheckEmailField")
    public void sigInNegativeCheckEmailField(String email, String password) {
        homePage.clickOnLink(Enums.HomePageLinksOnHomePage.SIGN_IN);
        signInModal.fillInEmailField(email);
        signInModal.fillInPasswordField(password);
        signInModal.clickOnSubmitButton();
        Assert.assertTrue(loginPage.checkErrorBlockIsAppears());
        Assert.assertEquals(loginPage.getCurrentUrl(), Data.expectedUrlLoginPage);
    }

    @Video(name = "sigInNegativeCheckPasswordField")
    @Test(groups = {"regression",  "negative"}, dataProviderClass = DataProviders.class, dataProvider = "signInNegativeCheckPasswordField")
    public void sigInNegativeCheckPasswordField(String email, String password) {
        homePage.clickOnLink(Enums.HomePageLinksOnHomePage.SIGN_IN);
        signInModal.fillInEmailField(email);
        signInModal.fillInPasswordField(password);
        signInModal.clickOnSubmitButton();
        Assert.assertTrue(loginPage.checkErrorBlockIsAppears());
        Assert.assertEquals(loginPage.getCurrentUrl(), Data.expectedUrlLoginPage);
    }

    @Video(name = "signInPasswordCheck")
    @Test(groups = {"regression"}, dataProviderClass = DataProviders.class, dataProvider = "newRequirementsForPassword")
    public void signInPasswordCheck(String password, boolean isPositiveCheck) {
        homePage.clickOnLink(Enums.HomePageLinksOnHomePage.SIGN_IN);
        signInModal.fillInEmailField(Data.email);
        signInModal.fillInPasswordField(password);
        signInModal.clickOnSubmitButton();
        if(!isPositiveCheck) {
            Assert.assertTrue(loginPage.checkErrorBlockIsAppears());
        }
    }
}
