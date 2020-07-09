import mainClasses.HomePage;
import org.testng.annotations.Test;
import testData.Data;

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
