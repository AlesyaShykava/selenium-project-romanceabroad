import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTests extends BaseUI {
    private WebDriverWait wait;

    @Test
    public void testSignIn() {
        driver.findElements(Locators.SIGN_IN_LINK).get(Data.indexSignInLink).click();
    }

    @Test
    public void sigInIncorrectCredentials() {
        driver.findElements(Locators.SIGN_IN_LINK).get(Data.indexSignInLink).click();
        wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.SIGN_IN_PAGE_EMAIL_FIELD));
        driver.findElement(Locators.SIGN_IN_PAGE_EMAIL_FIELD).sendKeys(Data.incorrectTestData);
        driver.findElement(Locators.SIGN_IN_PAGE_PASSWORD_FIELD).sendKeys(Data.incorrectTestData);
        driver.findElement(Locators.SIGN_IN_PAGE_SUBMIT_BUTTON).click();
        Assert.assertEquals(driver.getCurrentUrl(), Data.expectedUrlLoginPage);
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.SIGN_IN_PAGE_ALERT_POP_UP_DISPLAYED));
    }

}
