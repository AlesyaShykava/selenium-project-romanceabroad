import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTests extends BaseUI {
    private String currentUrlSearch;

    @Test
    public void testSearchPage() {
        driver.findElement(Locators.SEARCH_LINK).click();
        currentUrlSearch = driver.getCurrentUrl();
        Assert.assertEquals(currentUrlSearch, Data.expectedUrlSearchPage);
        Assert.assertTrue(Data.searchPageTitleExpected.equals(driver.getTitle()), String.format(Data.incorrectTitleTestMessageFormat, "Search"));
    }

}
