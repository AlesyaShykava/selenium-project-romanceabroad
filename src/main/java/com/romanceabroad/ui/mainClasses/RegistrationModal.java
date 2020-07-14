package com.romanceabroad.ui.mainClasses;

import com.romanceabroad.ui.locators.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationModal extends BaseActions {
    public RegistrationModal(WebDriver webDriver, WebDriverWait wait) {
        super(webDriver, wait);
    }

    public void fillInEmail(String email) {
        driver.findElement(Locators.REGISTRATION_EMAIL_FIELD).sendKeys(email);
    }

    public void fillInPassword(String password) {
        driver.findElement(Locators.REGISTRATION_PASSWORD_FIELD).sendKeys(password);
    }

    public void clickOnNextButton() {
        driver.findElement(Locators.REGISTRATION_NEXT_BUTTON_ENABLED).click();
    }

    public void fillInNickName(String nickName) {
        driver.findElement(Locators.REGISTRATION_NICKNAME_TEXT_FIELD).sendKeys(nickName);
    }

    public void fillInPhone(String phone) {
        sendKeysJS(Locators.REGISTRATION_PHONE_TEXT_FIELD, phone);
    }

    public void selectDOBDay(String day) {
        driver.findElement(Locators.REGISTRATION_DAY_SELECTION).click();
        clickOnElementFromList(Locators.REGISTRATION_DAYS_LIST_ELEMENTS, day);
    }

    public void selectDOBMonth(String month) {
        driver.findElement(Locators.REGISTRATION_MONTH_SELECTION).click();
        clickOnElementFromList(Locators.REGISTRATION_MONTHS_LIST_ELEMENTS, month);
    }

    public void selectDOBYear(String year) {
        driver.findElement(Locators.REGISTRATION_YEAR_SELECTION).click();
        clickOnElementFromList(Locators.REGISTRATION_YEARS_LIST_ELEMENTS, year);
    }

    public void checkTermsAndConditionsCheckbox() {
        driver.findElement(Locators.REGISTRATION_TERMS_AND_CONDITIONS_CHECKBOX).click();
    }

    public void selectLocation(String partOfLocation, String locationFull) {
        waitThreadSleepSec(3);
        driver.findElement(Locators.REGISTRATION_AUTO_FILLING_FORM_LOCATION).clear();
        driver.findElement(Locators.REGISTRATION_AUTO_FILLING_FORM_LOCATION).sendKeys(partOfLocation);
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.REGISTRATION_AUTO_SUGGESTIONS_LIST_LOCATION));
        clickOnElementFromList(Locators.REGISTRATION_AUTO_SUGGESTIONS_LIST_LOCATION, locationFull);
    }

    public boolean isTermsAndConditionsChecked() {
        return driver.findElement(Locators.REGISTRATION_TERMS_AND_CONDITIONS_CHECKBOX).isSelected();
    }

    public void clickOnPagination() {
        driver.findElement(Locators.REGISTRATION_PAGINATION).click();
    }

    public String getPasswordAlertMessage() {
        return getAlertMessage(Locators.REGISTRATION_INCORRECT_PASSWORD_ALERT);
    }

    public String getEmailAlertMessage() {
        return getAlertMessage(Locators.REGISTRATION_INCORRECT_EMAIL_ALERT);
    }

    private String getAlertMessage(By field) {
        return driver.findElement(field).getText();
    }
}
