package mainClasses;

import locators.Locators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInModal extends BaseActions {
    public SignInModal(WebDriver webDriver, WebDriverWait wait) {
        super(webDriver, wait);
    }

    public void clickOnForgotPasswordLink() {
        driver.findElement(Locators.SIGN_IN_FORGOT_PASSWORD_LINK).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.RESTORE_PASSWORD_EMAIL_FIELD));
    }

    public void fillInEmailField(String email) {
        driver.findElement(Locators.SIGN_IN_EMAIL_FIELD).sendKeys(email);
    }
    public void fillInPasswordField(String password) {
        driver.findElement(Locators.SIGN_IN_PASSWORD_FIELD).sendKeys(password);
    }

    public void clickOnSubmitButton() {
        driver.findElement(Locators.SIGN_IN_SUBMIT_BUTTON).click();
    }
}