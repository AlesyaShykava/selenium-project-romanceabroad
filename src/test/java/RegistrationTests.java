import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTests extends BaseUI {

    @Test
    public void registrationHappyPath() {
        homePage.clickOnLink(Locators.JOIN_FOR_FREE_NOW_LINK);
        registrationModal.fillInEmail(Data.correctEmail);
        registrationModal.fillInPassword(Data.correctPassword);
        registrationModal.clickOnNextButton();
        registrationModal.fillInNickNameWithCorrectRandomData();
        registrationModal.fillInPhone(Data.correctPhone);
        registrationModal.selectDOB(Locators.REGISTRATION_FORM_DAY_14, Locators.REGISTRATION_FORM_MONTH_DECEMBER, Locators.REGISTRATION_FORM_YEAR_1990);
        registrationModal.checkTermsAndConditionsCheckbox();
    }

    @Test
    public void registrationWithWrongCredentials() {
        homePage.clickOnLink(Locators.JOIN_FOR_FREE_NOW_LINK);
        registrationModal.fillInEmail(Data.incorrectTestData);
        registrationModal.fillInPassword(Data.incorrectTestData);
        registrationModal.clickOnPagination();
        String emailIncorrectAlertMessageActual = registrationModal.getEmailAlertMessage();
        String passwordIncorrectAlertMessageActual = registrationModal.getPasswordAlertMessage();
        Assert.assertTrue(Data.registrationFormEmailIncorrectAlertMessageExpected.equals(emailIncorrectAlertMessageActual));
        Assert.assertTrue(Data.registrationFormPasswordIncorrectAlertMessageExpected.equals(passwordIncorrectAlertMessageActual));
    }
}