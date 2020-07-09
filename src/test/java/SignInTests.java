import mainClasses.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import testData.Data;

public class SignInTests extends BaseUI {
    private String currentUrl;

    @Test(groups = {"smoke", "regression"})
    public void testSignIn() {
        homePage.clickOnLink(HomePage.LinksOnHomePage.SIGN_IN);
        currentUrl = blogPage.getCurrentUrl();
        softAssert.assertTrue(Data.signInFormTitleExpected.equals(signInModal.getTitle()), String.format(Data.incorrectTitleTestMessageFormat, "Sign In form"));
    }

    @Test(groups = {"regression"})
    public void sigInIncorrectCredentials() {
        homePage.clickOnLink(HomePage.LinksOnHomePage.SIGN_IN);
        signInModal.fillInEmailField(Data.incorrectTestData);
        signInModal.fillInPasswordField(Data.incorrectTestData);
        signInModal.clickOnSubmitButton();
        loginPage.checkErrorBlockIsDisplayed();
        Assert.assertEquals(loginPage.getCurrentUrl(), Data.expectedUrlLoginPage);
    }
}
