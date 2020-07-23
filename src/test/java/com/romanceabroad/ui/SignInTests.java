package com.romanceabroad.ui;

import com.romanceabroad.ui.mainClasses.Enums;
import com.romanceabroad.ui.testData.Data;
import com.romanceabroad.ui.testData.DataProviders;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTests extends BaseUI {
    private String currentUrl;

    @Test(groups = {"smoke", "regression"})
    public void testSignIn() {
        homePage.clickOnLink(Enums.HomePageLinksOnHomePage.SIGN_IN);
        currentUrl = blogPage.getCurrentUrl();
        softAssert.assertTrue(Data.signInFormTitleExpected.equals(signInModal.getTitle()), String.format(Data.incorrectTitleTestMessageFormat, "Sign In form"));
    }

    @Test(groups = {"regression", "negative"}, dataProviderClass = DataProviders.class, dataProvider = "signInNegativeCheckEmailField")
    public void sigInNegativeCheckEmailField(String email, String password) {
        homePage.clickOnLink(Enums.HomePageLinksOnHomePage.SIGN_IN);
        signInModal.fillInEmailField(email);
        signInModal.fillInPasswordField(password);
        signInModal.clickOnSubmitButton();
        loginPage.checkErrorBlockIsDisplayed();
        Assert.assertEquals(loginPage.getCurrentUrl(), Data.expectedUrlLoginPage);
    }

    @Test(groups = {"regression",  "negative"}, dataProviderClass = DataProviders.class, dataProvider = "signInNegativeCheckPasswordField")
    public void sigInNegativeCheckPasswordField(String email, String password) {
        homePage.clickOnLink(Enums.HomePageLinksOnHomePage.SIGN_IN);
        signInModal.fillInEmailField(email);
        signInModal.fillInPasswordField(password);
        signInModal.clickOnSubmitButton();
        loginPage.checkErrorBlockIsDisplayed();
        Assert.assertEquals(loginPage.getCurrentUrl(), Data.expectedUrlLoginPage);
    }
}
