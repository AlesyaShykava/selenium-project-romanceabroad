import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SearchTests extends BaseUI {
    private String currentUrlSearch;

    @Test
    public void testSearchPage() {
        driver.findElement(Locators.SEARCH_LINK).click();
        currentUrlSearch = driver.getCurrentUrl();
        Assert.assertEquals(currentUrlSearch, Data.expectedUrlSearchPage);
        Assert.assertTrue(Data.searchPageTitleExpected.equals(driver.getTitle()), String.format(Data.incorrectTitleTestMessageFormat, "Search"));
    }

    @Test
    public void testSearchAndOrder() {
        driver.findElement(Locators.SEARCH_LINK).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        selectFromDropDownListByValue(driver.findElement(Locators.SEARCH_PAGE_SEARCH_PARAMETERS_MIN_AGE_DROPDOWN), Data.minAgeForSearch);
        selectFromDropDownListByValue(driver.findElement(Locators.SEARCH_PAGE_SEARCH_PARAMETERS_MAX_AGE_DROPDOWN), Data.maxAgeForSearch);
        driver.findElement(Locators.SEARCH_PAGE_SEARCH_PARAMETERS_SEARCH_BUTTON).click();
        selectFromDropDownListByValue(driver.findElement(Locators.SEARCH_PAGE_ORDER_DROPDOWN), Data.orderValueDataCreated);
        boolean expectedUserFromSearchDisplayed = driver.findElement(Locators.SEARCH_PAGE_USER_FROM_SEARCH).isDisplayed();
        Assert.assertTrue(expectedUserFromSearchDisplayed);
    }

}
