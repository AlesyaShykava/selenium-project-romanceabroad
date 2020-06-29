import org.testng.annotations.Test;

public class RegistrationTests extends BaseUI {

    @Test(groups = {"smoke", "regression", "integration"})
    public void registrationHappyPath() {
        homePage.clickOnLink(HomePage.LinksOnHomePage.JOIN_FOR_FREE_NOW);
        registrationModal.fillInEmail(Data.correctEmail);
        registrationModal.fillInPassword(Data.correctPassword);
        registrationModal.clickOnNextButton();
        registrationModal.fillInNickName(mediaPage.generateRandomNumber(Data.correctNickName, 6));
        registrationModal.fillInPhone(Data.correctPhone);
        registrationModal.selectDOBMonth(Data.month);
        registrationModal.selectDOBDay(Data.day);
        registrationModal.selectDOBYear(Data.year);
        registrationModal.selectLocation(Data.locationCity, Data.location);
        if(!registrationModal.isTermsAndConditionsChecked()) {
            registrationModal.checkTermsAndConditionsCheckbox();
        }
    }

    @Test(groups = {"regression"})
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
