import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTests extends BaseUI {
    private WebDriverWait wait;

    @Test
    public void registrationHappyPath() {
        driver.findElement(Locators.JOIN_FOR_FREE_NOW_LINK).click();
        driver.findElement(Locators.REGISTRATION_FORM_EMAIL_FIELD).sendKeys(Data.correctEmail);
        driver.findElement(Locators.REGISTRATION_FORM_PASSWORD_FIELD).sendKeys(Data.correctPassword);
        driver.findElement(Locators.REGISTRATION_FORM_NEXT_BUTTON).click();
        driver.findElement(Locators.REGISTRATION_FORM_NICKNAME_TEXT_FIELD).sendKeys(Data.correctNickName);
        driver.findElement(Locators.REGISTRATION_FORM_PHONE_TEXT_FIELD).sendKeys(Data.correctPhone);
        driver.findElement(Locators.REGISTRATION_FORM_DAY_SELECT_LINK).click();
        WebElement dayToSelect = driver.findElement(Locators.REGISTRATION_FORM_DAY_14);
        Actions action = new Actions(driver);
        action.moveToElement(dayToSelect).perform();
        driver.findElement(Locators.REGISTRATION_FORM_DAY_14).click();
        wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(Locators.REGISTRATION_FORM_MONTH_SELECTION_CARET));
        driver.findElement(Locators.REGISTRATION_FORM_MONTH_SELECTION_CARET).click();
        WebElement monthToSelect = driver.findElement(Locators.REGISTRATION_FORM_MONTH_DECEMBER);
        action.moveToElement(monthToSelect).perform();
        driver.findElement(Locators.REGISTRATION_FORM_MONTH_DECEMBER).click();
        wait.until(ExpectedConditions.elementToBeClickable(Locators.REGISTRATION_FORM_YEAR_SELECTION_CARET));
        driver.findElement(Locators.REGISTRATION_FORM_YEAR_SELECTION_CARET).click();
        WebElement yearToSelect = driver.findElement(Locators.REGISTRATION_FORM_YEAR_1990);
        action.moveToElement(yearToSelect).perform();
        driver.findElement(Locators.REGISTRATION_FORM_YEAR_1990).click();

    }

    @Test
    public void registrationWithWrongCredentials() {
        driver.findElement(Locators.JOIN_FOR_FREE_NOW_LINK).click();
        WebElement emailField = driver.findElement(Locators.REGISTRATION_FORM_EMAIL_FIELD);
        emailField.sendKeys(Data.incorrectTestData);
        WebElement passwordField = driver.findElement(Locators.REGISTRATION_FORM_PASSWORD_FIELD);
        passwordField.sendKeys(Data.incorrectTestData);
        driver.findElement(Locators.REGISTRATION_FORM_PAGINATION).click();
        String emailIncorrectAlertMessageActual = driver.findElement(Locators.REGISTRATION_FORM_INCORRECT_EMAIL_ALERT).getText();
        String passwordIncorrectAlertMessageActual = driver.findElement(Locators.REGISTRATION_FORM_INCORRECT_PASSWORD_ALERT).getText();
        Assert.assertTrue(Data.registrationFormEmailIncorrectAlertMessageExpected.equals(emailIncorrectAlertMessageActual));
        Assert.assertTrue(Data.registrationFormPasswordIncorrectAlertMessageExpected.equals(passwordIncorrectAlertMessageActual));
    }
}
