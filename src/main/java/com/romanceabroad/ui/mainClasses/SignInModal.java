package com.romanceabroad.ui.mainClasses;

import com.aventstack.extentreports.Status;
import com.romanceabroad.ui.locators.Locators;
import com.romanceabroad.ui.reportUtil.Reports;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInModal extends BaseActions {
    public SignInModal(WebDriver webDriver, WebDriverWait wait) {
        super(webDriver, wait);
    }

    public void clickOnForgotPasswordLink() {
        Reports.log(Status.INFO, "Clicking on 'Forgot Password' link");
        driver.findElement(Locators.SIGN_IN_FORGOT_PASSWORD_LINK).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.RESTORE_PASSWORD_EMAIL_FIELD));
    }

    public void fillInEmailField(String email) {
        Reports.log(Status.INFO, "Fill in email field:" + email);
        driver.findElement(Locators.SIGN_IN_EMAIL_FIELD).sendKeys(email);
        wait.until(ExpectedConditions.elementToBeClickable(Locators.SIGN_IN_SUBMIT_BUTTON));
    }

    public void fillInPasswordField(String password) {
        Reports.log(Status.INFO, "Fill in email field:" + password);
        driver.findElement(Locators.SIGN_IN_PASSWORD_FIELD).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(Locators.SIGN_IN_SUBMIT_BUTTON));
    }

    public void clickOnSubmitButton() {
        Reports.log(Status.INFO, "Clicking on submit button");
        driver.findElement(Locators.SIGN_IN_SUBMIT_BUTTON).click();
    }
}
