import org.testng.Assert;
import org.testng.annotations.Test;

public class RestorePasswordTests extends BaseUI {
    String currentUrl;

    @Test
    public void restorePasswordIncorrectEmail() {
        homePage.clickOnSignInLink();
        signInModal.clickOnForgotPasswordLink();
        currentUrl = restorePasswordPage.getCurrentUrl();
        Assert.assertEquals(currentUrl, Data.expectedUrlRestorePage);
        restorePasswordPage.fillInEmailField(Data.incorrectTestData);
        restorePasswordPage.clickOnSubmitButton();
        restorePasswordPage.checkErrorBlockIsDisplayed();
    }
}
