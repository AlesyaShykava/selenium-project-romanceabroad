import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationModal extends BaseActions {
    public RegistrationModal(WebDriver webDriver, WebDriverWait wait) {
        super(webDriver, wait);
    }

    public void fillInEmail(String email) {
        driver.findElement(Locators.REGISTRATION_FORM_EMAIL_FIELD).sendKeys(email);
    }

    public void fillInPassword(String password) {
        driver.findElement(Locators.REGISTRATION_FORM_PASSWORD_FIELD).sendKeys(password);
    }

    public void clickOnNextButton() {
        driver.findElement(Locators.REGISTRATION_FORM_NEXT_BUTTON).click();
    }

    public void fillInNickNameWithCorrectRandomData() {
        driver.findElement(Locators.REGISTRATION_FORM_NICKNAME_TEXT_FIELD).sendKeys(generateNewNumber(Data.correctNickName, 6));
    }

    public void fillInPhone(String phone) {
        driver.findElement(Locators.REGISTRATION_FORM_PHONE_TEXT_FIELD).sendKeys(phone);
    }

    public void selectDOB(By day, By month, By year) {
        driver.findElement(Locators.REGISTRATION_FORM_DAY_SELECT_LINK).click();
        moveMouseToElementAndClick(day);
        driver.findElement(Locators.REGISTRATION_FORM_MONTH_SELECTION_CARET).click();
        moveMouseToElementAndClick(month);
        driver.findElement(Locators.REGISTRATION_FORM_YEAR_SELECTION_CARET).click();
        moveMouseToElementAndClick(year);
    }

    public void checkTermsAndConditionsCheckbox() {
        driver.findElement(Locators.REGISTRATION_FORM_TERMS_AND_CONDITIONS_CHECKBOX).click();
    }

    public void clickOnPagination() {
        driver.findElement(Locators.REGISTRATION_FORM_PAGINATION).click();
    }

    public String getPasswordAlertMessage() {
        return getAlertMessage(Locators.REGISTRATION_FORM_INCORRECT_PASSWORD_ALERT);
    }

    public String getEmailAlertMessage() {
        return getAlertMessage(Locators.REGISTRATION_FORM_INCORRECT_EMAIL_ALERT);
    }

    private String getAlertMessage(By incorrectField) {
        return driver.findElement(incorrectField).getText();
    }
}
