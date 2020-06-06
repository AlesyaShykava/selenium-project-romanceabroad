import org.testng.Assert;
import org.testng.annotations.Test;

public class HowWeWorkTests extends BaseUI {
    private String currentUrlHowItWorks;

    @Test
    public void testHowWeWorkPage() {
        driver.findElement(Locators.HOW_IT_WORKS_LINK).click();
        currentUrlHowItWorks = driver.getCurrentUrl();
        Assert.assertEquals(currentUrlHowItWorks, Data.expectedUrlHowItWorksPage);
        Assert.assertTrue(Data.howItWorksPageTitleExpected.equals(driver.getTitle()), String.format(Data.incorrectTitleTestMessageFormat, "How We Work"));
    }
}
