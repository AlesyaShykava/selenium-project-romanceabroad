import org.testng.annotations.Test;

public class HowWeWorkTests extends BaseUI {
    private String currentUrl;

    @Test
    public void testHowWeWorkPage() {
        homePage.clickOnLink(Locators.HOW_IT_WORKS_LINK);
        currentUrl = howWeWorkPage.getCurrentUrl();
        softAssert.assertEquals(currentUrl, Data.expectedUrlHowItWorksPage);
        softAssert.assertTrue(Data.howItWorksPageTitleExpected.equals(howWeWorkPage.getTitle()), String.format(Data.incorrectTitleTestMessageFormat, "How We Work"));
        softAssert.assertAll();
    }
}
