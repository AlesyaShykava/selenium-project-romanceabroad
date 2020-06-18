import org.testng.annotations.Test;

public class RestorePasswordTests extends BaseUI {
    String currentUrl;

    @Test
    public void restorePasswordIncorrectEmail() {
        homePage.clickOnSignInLink();
        signInModal.clickOnForgotPasswordLink();
        currentUrl = restorePasswordPage.getCurrentUrl();
        softAssert.assertEquals(currentUrl, Data.expectedUrlRestorePage);
        restorePasswordPage.fillInEmailField(Data.incorrectTestData);
        restorePasswordPage.clickOnSubmitButton();
        restorePasswordPage.checkErrorBlockIsDisplayed();
        softAssert.assertAll();
    }
}
