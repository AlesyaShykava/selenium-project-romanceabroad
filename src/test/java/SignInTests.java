import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTests extends BaseUI {
    private String currentUrl;

    @Test
    public void testSignIn() {
        homePage.clickOnLink(HomePage.LinksOnHomePage.SIGN_IN);
        currentUrl = blogPage.getCurrentUrl();
        softAssert.assertTrue(Data.signInFormTitleExpected.equals(signInModal.getTitle()), String.format(Data.incorrectTitleTestMessageFormat, "Sign In form"));
    }

    @Test
    public void sigInIncorrectCredentials() {
        homePage.clickOnLink(HomePage.LinksOnHomePage.SIGN_IN);
        signInModal.fillInEmailField(Data.incorrectTestData);
        signInModal.fillInPasswordField(Data.incorrectTestData);
        signInModal.clickOnSubmitButton();
        loginPage.checkErrorBlockIsDisplayed();
        Assert.assertEquals(loginPage.getCurrentUrl(), Data.expectedUrlLoginPage);
    }
}
