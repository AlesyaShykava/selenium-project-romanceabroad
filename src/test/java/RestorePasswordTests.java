import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RestorePasswordTests extends BaseUI {

    @Test
    public void restorePasswordIncorrectEmail() {
        driver.findElement(Locators.SIGN_IN_LINK).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.SIGN_IN_PAGE_EMAIL_FIELD));
        driver.findElement(Locators.SIGN_IN_PAGE_FORGOT_PASSWORD_LINK).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.RESTORE_PAGE_EMAIL_FIELD));
        Assert.assertEquals(driver.getCurrentUrl(), Data.expectedUrlRestorePage);
        driver.findElement(Locators.RESTORE_PAGE_EMAIL_FIELD).sendKeys(Data.incorrectTestData);
        driver.findElement(Locators.RESTORE_PAGE_SUBMIT_BUTTON).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.RESTORE_PAGE_ALERT_POP_UP_DISPLAYED));
    }
}
