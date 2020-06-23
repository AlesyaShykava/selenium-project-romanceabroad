import org.testng.annotations.Test;

public class RegistrationTests extends BaseUI {

    @Test
    public void registrationHappyPath() {
        homePage.clickOnLink(HomePage.LinksOnHomePage.JOIN_FOR_FREE_NOW);
        registrationModal.fillInEmail(Data.correctEmail);
        registrationModal.fillInPassword(Data.correctPassword);
        registrationModal.clickOnNextButton();
        registrationModal.fillInNickNameWithCorrectRandomData();
        registrationModal.fillInPhone(Data.correctPhone);
        registrationModal.selectDOBDay(14);
        registrationModal.selectDOBMonth(12);
        registrationModal.selectDOBYear(1990);
        if(!registrationModal.isTermsAndConditionsChecked()) {
            registrationModal.checkTermsAndConditionsCheckbox();
        }
    }

    @Test
    public void registrationWithWrongCredentials() {
        homePage.clickOnLink(HomePage.LinksOnHomePage.JOIN_FOR_FREE_NOW);
        registrationModal.fillInEmail(Data.incorrectTestData);
        registrationModal.fillInPassword(Data.incorrectTestData);
        registrationModal.clickOnPagination();
        String emailIncorrectAlertMessageActual = registrationModal.getEmailAlertMessage();
        String passwordIncorrectAlertMessageActual = registrationModal.getPasswordAlertMessage();
        softAssert.assertTrue(Data.registrationFormEmailIncorrectAlertMessageExpected.equals(emailIncorrectAlertMessageActual));
        softAssert.assertTrue(Data.registrationFormPasswordIncorrectAlertMessageExpected.equals(passwordIncorrectAlertMessageActual));
        softAssert.assertAll();
    }
}
