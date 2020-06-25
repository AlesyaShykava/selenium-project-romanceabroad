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
        driver.findElement(Locators.REGISTRATION_FORM_NICKNAME_TEXT_FIELD).sendKeys(generateRandomNumber(Data.correctNickName, 6));
    }

    public void fillInPhone(String phone) {
        sendKeysJS(Locators.REGISTRATION_FORM_PHONE_TEXT_FIELD, phone);
    }

    public void selectDOBDay(int day) {
        driver.findElement(Locators.REGISTRATION_FORM_DAY_SELECT_LINK).click();
        By dayLocator = getLocatorBODDay(day);
        moveMouseToElementAndClick(dayLocator);
    }

    public void selectDOBMonth(int month) {
        driver.findElement(Locators.REGISTRATION_FORM_MONTH_SELECTION_CARET).click();
        By monthLocator = getLocatorBODMonth(month - 1);
        moveMouseToElementAndClick(monthLocator);
    }

    public void selectDOBYear(int year) {
        driver.findElement(Locators.REGISTRATION_FORM_YEAR_SELECTION_CARET).click();
        By yearLocator = getLocatorBODYear(year);
        moveMouseToElementAndClick(yearLocator);
    }

    public void checkTermsAndConditionsCheckbox() {
        driver.findElement(Locators.REGISTRATION_FORM_TERMS_AND_CONDITIONS_CHECKBOX).click();
    }

    public boolean isTermsAndConditionsChecked() {
        return driver.findElement(Locators.REGISTRATION_FORM_TERMS_AND_CONDITIONS_CHECKBOX).isSelected();
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

    private String getAlertMessage(By field) {
        return driver.findElement(field).getText();
    }

    private By getLocatorBODDay(int day) {
        return By.xpath(String.format(Locators.REGISTRATION_FORM_STRING_FORMAT_DAY, day));
    }

    private By getLocatorBODMonth(int month) {
        return By.xpath(String.format(Locators.REGISTRATION_FORM_STRING_FORMAT_MONTH, month));
    }

    private By getLocatorBODYear(int year) {
        return By.xpath(String.format(Locators.REGISTRATION_FORM_STRING_FORMAT_YEAR, year));
    }
}
