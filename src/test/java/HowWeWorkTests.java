import mainClasses.HomePage;
import org.testng.annotations.Test;
import testData.Data;

public class HowWeWorkTests extends BaseUI {
    private String currentUrl;

    @Test(groups = {"smoke", "regression"})
    public void testHowWeWorkPage() {
        homePage.clickOnLink(HomePage.LinksOnHomePage.HOW_IT_WORKS);
        currentUrl = howWeWorkPage.getCurrentUrl();
        softAssert.assertEquals(currentUrl, Data.expectedUrlHowItWorksPage);
        softAssert.assertTrue(Data.howItWorksPageTitleExpected.equals(howWeWorkPage.getTitle()), String.format(Data.incorrectTitleTestMessageFormat, "How We Work"));
        softAssert.assertAll();
    }
}
