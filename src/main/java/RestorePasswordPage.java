import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RestorePasswordPage extends BaseActions {
    public RestorePasswordPage(WebDriver webDriver, WebDriverWait wait) {
        super(webDriver, wait);
    }

    public void fillInEmailField(String email) {
        driver.findElement(Locators.RESTORE_PASSWORD_EMAIL_FIELD).sendKeys(email);
    }

    public void clickOnSubmitButton() {
        driver.findElement(Locators.RRESTORE_PASSWORD_SUBMIT_BUTTON).click();
    }

    public void checkErrorBlockIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.RESTORE_PASSWORD_ALERT_POP_UP_DISPLAYED));
    }
}
