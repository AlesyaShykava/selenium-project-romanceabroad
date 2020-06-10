import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInModal extends BaseActions {
    public SignInModal(WebDriver webDriver, WebDriverWait wait) {
        super(webDriver, wait);
    }

    public void clickOnForgotPasswordLink() {
        driver.findElement(Locators.SIGN_IN_PAGE_FORGOT_PASSWORD_LINK).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.RESTORE_PAGE_EMAIL_FIELD));
    }

    public void fillInEmailField(String email) {
        driver.findElement(Locators.SIGN_IN_PAGE_EMAIL_FIELD).sendKeys(email);
    }
    public void fillInPasswordField(String password) {
        driver.findElement(Locators.SIGN_IN_PAGE_PASSWORD_FIELD).sendKeys(password);
    }

    public void clickOnSubmitButton() {
        driver.findElement(Locators.SIGN_IN_PAGE_SUBMIT_BUTTON).click();
    }
}
