import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RegistrationTests extends BaseUI {

    @DataProvider(name = "RegistrationDataSetHappyPath")
    public static Object[][] getRegistrationDataSetHappyPath() throws IOException {
        List<Object[]> data = new ArrayList<>();
        Files.readAllLines(Paths.get("resources/RegistrationHP.csv")).stream().forEach(s -> {
            Object[] line = s.split(",");
            data.add(new Object[]{line[0], line[1], line[2], line[3], line[4], line[5], line[6], line[7], line[8]});
        });
        return data.toArray(new Object[data.size()][]);
    }

    @DataProvider(name = "RegistrationDataSetWrongCredentials")
    public static Object[][] getRegistrationDataSetIncorrectEmailPassword() throws IOException {
        List<Object[]> data = new ArrayList<>();
        Files.readAllLines(Paths.get("resources/RegistrationWrongCredentials.csv")).stream().forEach(s -> {
            Object[] line = s.split(",");
            data.add(new Object[]{line[0], line[1]});
        });
        return data.toArray(new Object[data.size()][]);
    }

    @Test(groups = {"smoke", "regression"}, dataProvider = "RegistrationDataSetHappyPath")
    public void registrationHappyPath(String email, String password, String nickName, String phone, String monthDOB,
                                      String dayDOB, String yearDOB, String locationCity, String locationFull) {
        homePage.clickOnLink(HomePage.LinksOnHomePage.JOIN_FOR_FREE_NOW);
        registrationModal.fillInEmail(email);
        registrationModal.fillInPassword(password);
        registrationModal.clickOnNextButton();
        registrationModal.fillInNickName(mediaPage.generateRandomNumber(nickName, 6));
        registrationModal.fillInPhone(phone);
        registrationModal.selectDOBMonth(monthDOB);
        registrationModal.selectDOBDay(dayDOB);
        registrationModal.selectDOBYear(yearDOB);
        registrationModal.selectLocation(locationCity, locationFull);
        if(!registrationModal.isTermsAndConditionsChecked()) {
            registrationModal.checkTermsAndConditionsCheckbox();
        }
    }

    @Test(groups = {"regression"}, dataProvider = "RegistrationDataSetWrongCredentials")
    public void registrationWithWrongCredentials(String incorrectEmail, String incorrectPassword) {
        homePage.clickOnLink(HomePage.LinksOnHomePage.JOIN_FOR_FREE_NOW);
        registrationModal.fillInEmail(incorrectEmail);
        registrationModal.fillInPassword(incorrectPassword);
        registrationModal.clickOnPagination();
        String emailIncorrectAlertMessageActual = registrationModal.getEmailAlertMessage();
        String passwordIncorrectAlertMessageActual = registrationModal.getPasswordAlertMessage();
        softAssert.assertTrue(Data.registrationFormEmailIncorrectAlertMessageExpected.equals(emailIncorrectAlertMessageActual));
        softAssert.assertTrue(Data.registrationFormPasswordIncorrectAlertMessageExpected.equals(passwordIncorrectAlertMessageActual));
        softAssert.assertAll();
    }
}
