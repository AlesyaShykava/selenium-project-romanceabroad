import mainClasses.HomePage;
import org.testng.annotations.Test;
import testData.Data;

public class ContactUsTests extends BaseUI {

    @Test
    public void contactAdministrator() {
        homePage.clickOnLink(HomePage.LinksOnHomePage.SEARCH);
        searchPage.clickOnContactUs();
        contactUsPage.selectReasonByText(Data.contactUsReasonDropDownTextOther);
        contactUsPage.fillInName(contactUsPage.generateRandomNumber(Data.name, 6));
        contactUsPage.fillInEmail(Data.email);
        contactUsPage.fillInSubject(Data.subject);
        contactUsPage.fillInMessage(Data.message);
    }
}
