import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class SignInTests extends BaseUI {

    @Test
    public void testSignIn() {
        driver.findElements(Locators.SIGN_IN_LINK).get(Data.indexSignInLink).click();
    }

    @Test
    public void sigInIncorrectCredentials() {
        homePage.clickOnSignInLink();
        signInModal.fillInEmailField(Data.incorrectTestData);
        signInModal.fillInPasswordField(Data.incorrectTestData);
        signInModal.clickOnSubmitButton();
        loginPage.checkErrorBlockIsDisplayed();
        Assert.assertEquals(loginPage.getCurrentUrl(), Data.expectedUrlLoginPage);
    }
}
