import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeTests extends BaseUI {
    private String currentUrlHome;

    @Test
    public void testNumberOfLinksOfNavbar() {
        int actualNumberOfLinksInNavbar = driver.findElements(Locators.NAVBAR).size();
        Assert.assertEquals(actualNumberOfLinksInNavbar, Data.expectedNumberOfLinksInNavbar);
    }

    @Test
    public void testHomePage() {
        driver.findElement(Locators.HOME_LINK).click();
        currentUrlHome = driver.getCurrentUrl();
        Assert.assertEquals(currentUrlHome, Data.expectedUrlHomePage);
        Assert.assertTrue(Data.homePageTitleExpected.equals(driver.getTitle()), String.format(Data.incorrectTitleTestMessageFormat, "Home"));
    }

}
