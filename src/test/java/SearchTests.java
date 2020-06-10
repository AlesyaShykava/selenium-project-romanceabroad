import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTests extends BaseUI {
    private String currentUrl;

    @Test
    public void testSearchPage() {
        homePage.clickOnLink(Locators.SEARCH_LINK);
        currentUrl = searchPage.getCurrentUrl();
        Assert.assertEquals(currentUrl, Data.expectedUrlSearchPage);
        Assert.assertTrue(Data.searchPageTitleExpected.equals(searchPage.getTitle()), String.format(Data.incorrectTitleTestMessageFormat, "Search"));
    }

    @Test
    public void testSearchAndOrder() {
        homePage.clickOnLink(Locators.SEARCH_LINK);
        searchPage.performSearchBasedOnMinAndMaxAgeParameters(Data.minAgeForSearch, Data.maxAgeForSearch);
        searchPage.setUpOrder(Data.orderValueDataCreated);
        boolean expectedUserFromSearchDisplayed = searchPage.isUserPresentInSearchResult(Locators.SEARCH_PAGE_USER_FROM_SEARCH);
        Assert.assertTrue(expectedUserFromSearchDisplayed);
    }

}
