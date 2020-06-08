import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTests extends BaseUI {

    @Test
    public void registrationHappyPath() {
        driver.findElement(Locators.JOIN_FOR_FREE_NOW_LINK).click();
        driver.findElement(Locators.REGISTRATION_FORM_EMAIL_FIELD).sendKeys(Data.correctEmail);
        driver.findElement(Locators.REGISTRATION_FORM_PASSWORD_FIELD).sendKeys(Data.correctPassword);
        driver.findElement(Locators.REGISTRATION_FORM_NEXT_BUTTON).click();
        driver.findElement(Locators.REGISTRATION_FORM_NICKNAME_TEXT_FIELD).sendKeys(Data.correctNickName);
        driver.findElement(Locators.REGISTRATION_FORM_PHONE_TEXT_FIELD).sendKeys(Data.correctPhone);
        driver.findElement(Locators.REGISTRATION_FORM_DAY_SELECT_LINK).click();
        moveMouseToElementAndClick(Locators.REGISTRATION_FORM_DAY_14);
        driver.findElement(Locators.REGISTRATION_FORM_MONTH_SELECTION_CARET).click();
        moveMouseToElementAndClick(Locators.REGISTRATION_FORM_MONTH_DECEMBER);
        driver.findElement(Locators.REGISTRATION_FORM_YEAR_SELECTION_CARET).click();
        moveMouseToElementAndClick(Locators.REGISTRATION_FORM_YEAR_1990);
    }


    @Test
    public void registrationWithWrongCredentials() {
        driver.findElement(Locators.JOIN_FOR_FREE_NOW_LINK).click();
        driver.findElement(Locators.REGISTRATION_FORM_EMAIL_FIELD).sendKeys(Data.incorrectTestData);
        driver.findElement(Locators.REGISTRATION_FORM_PASSWORD_FIELD).sendKeys(Data.incorrectTestData);
        driver.findElement(Locators.REGISTRATION_FORM_PAGINATION).click();
        String emailIncorrectAlertMessageActual = driver.findElement(Locators.REGISTRATION_FORM_INCORRECT_EMAIL_ALERT).getText();
        String passwordIncorrectAlertMessageActual = driver.findElement(Locators.REGISTRATION_FORM_INCORRECT_PASSWORD_ALERT).getText();
        Assert.assertTrue(Data.registrationFormEmailIncorrectAlertMessageExpected.equals(emailIncorrectAlertMessageActual));
        Assert.assertTrue(Data.registrationFormPasswordIncorrectAlertMessageExpected.equals(passwordIncorrectAlertMessageActual));
    }
}
