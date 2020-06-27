import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class SearchTests extends BaseUI {
    private String currentUrl;
    Random random = new Random();
    public static final boolean TC81 = true;
    public static final boolean TC82 = true;
    public static final boolean TC83 = true;
    public static final boolean TC84 = true;
    public static final boolean TC85 = true;
    public static final boolean TC86 = true;
    public static final boolean TC87 = true;
    public static final boolean TC88 = true;

    @Test(groups = {"smoke", "regression"}, enabled = TC81)
    public void testSearchPageTC81() {
        homePage.clickOnLink(HomePage.LinksOnHomePage.SEARCH);
        currentUrl = searchPage.getCurrentUrl();
        softAssert.assertEquals(currentUrl, Data.expectedUrlSearchPage);
        softAssert.assertTrue(Data.searchPageTitleExpected.equals(searchPage.getTitle()), String.format(Data.incorrectTitleTestMessageFormat, "Search"));
        softAssert.assertAll();
    }

    @Test(groups = {"smoke", "regression"}, enabled = TC82, dataProviderClass = Data.class, dataProvider = "minMaxAgeOrderWomanSummaryDataSet")
    public void testSearchAndOrderTC82(Integer minAge, Integer maxAge, String order, String womanSummary) {
        homePage.clickOnLink(HomePage.LinksOnHomePage.SEARCH);
        searchPage.performSearchBasedOnMinAndMaxAgeParameters(minAge, maxAge);
        searchPage.setUpOrder(order);
        boolean expectedUserFromSearchDisplayed = searchPage.getListOfWomenSummaryFirstPage().contains(womanSummary);
        Assert.assertTrue(expectedUserFromSearchDisplayed);
    }

    @Test(groups = {"regression"}, enabled = TC83, dataProviderClass = Data.class, dataProvider = "minMaxAgeDataSet")
    public void checkAgeInSearchResultCorrespondGivenParametersTC83(Integer minAge, Integer maxAge) {
        homePage.clickOnLink(HomePage.LinksOnHomePage.SEARCH);
        searchPage.performSearchBasedOnMinAndMaxAgeParameters(minAge, maxAge);
        List<String> listWomanSummary = searchPage.getListOfWomenSummaryAll();
        if(listWomanSummary.size() > 0) {
            for (int i = 0; i < listWomanSummary.size(); i++) {
                String womanSummary = listWomanSummary.get(i);
                int ageOfWoman = Integer.parseInt(womanSummary.substring(womanSummary.length() - 2));
                boolean isAgeCorrespondGivenParameters = ageOfWoman >= minAge && ageOfWoman <= maxAge;
                softAssert.assertTrue(isAgeCorrespondGivenParameters, String.format("Expected between: %d - %d, actual: %d", minAge, maxAge, ageOfWoman));
            }
        } else {
            System.out.println("Search result is empty, please select another data for test");
        }
        softAssert.assertAll();
    }

    @Test(groups = {"regression"}, enabled = TC84, dataProviderClass = Data.class, dataProvider = "minMaxAgeDataSet")
    public void checkPeopleFoundNumberOnTheTitleOfResultPageTC84(Integer minAge, Integer maxAge) {
        homePage.clickOnLink(HomePage.LinksOnHomePage.SEARCH);
        searchPage.performSearchBasedOnMinAndMaxAgeParameters(minAge, maxAge);
        List<String> listWomanSummary = searchPage.getListOfWomenSummaryAll();
        String peopleFoundTitle = searchPage.getPeopleFoundTitle();
        if(!peopleFoundTitle.contains(Integer.toString(listWomanSummary.size()))) {
            Assert.fail("The incorrect number of founded people is displayed on the title");
        }
    }

    @Test(groups = {"regression"}, enabled = TC85)
    public void checkSearchParametersMinAgeValuesTC85(){
        homePage.clickOnLink(HomePage.LinksOnHomePage.SEARCH);
        List<Integer> minAgeValues = searchPage.getMinAgeDropDownValues();
        int sizeOfListWithMinAgeValues = minAgeValues.size();
        boolean isNumberOfValuesEqualsToExpected = (sizeOfListWithMinAgeValues == Data.searchParametersMaxAgeExpected - Data.searchParametersMinAgeExpected + 1);
        Assert.assertTrue(isNumberOfValuesEqualsToExpected, "Number of options in min_age dropdown does not correspond to expected");
        softAssert.assertTrue(minAgeValues.get(0) == Data.searchParametersMinAgeExpected);
        softAssert.assertTrue(minAgeValues.get(sizeOfListWithMinAgeValues - 1) == Data.searchParametersMaxAgeExpected);
        for(int i = 0; i < sizeOfListWithMinAgeValues; i++) {
            softAssert.assertTrue(minAgeValues.get(i) == Data.searchParametersMinAgeExpected + i);
        }
        softAssert.assertAll();
    }

    @Test(groups = {"regression"}, enabled = TC86)
    public void checkSummaryOnSearchPageCorrespondToInfoOnProfilePageTC86(){
        homePage.clickOnLink(HomePage.LinksOnHomePage.SEARCH);
        boolean isSearchNeeded = true;
        while (isSearchNeeded) {
            searchPage.selectRandomOptionFromDropDown(Locators.SEARCH_PAGE_SEARCH_PARAMETERS_MIN_AGE_DROPDOWN, "minAge");
            searchPage.clickOnSearchButton();
            isSearchNeeded = searchPage.getListOfWomenSummaryFirstPage().isEmpty();
            if(isSearchNeeded) continue;

            int randomIndex = random.nextInt(searchPage.getListOfWomenSummaryFirstPage().size());

            String womanSummary = searchPage.getWomanSummaryByIndex(randomIndex);
            String userNameSearchPage = womanSummary.substring(0, womanSummary.indexOf(", "));
            int ageSearchPage = Integer.parseInt(womanSummary.substring(womanSummary.length() - 2));

            searchPage.clickOnProfileLinkByIndex(randomIndex);
            String userNameProfilePage = profilePage.getUserName();
            int ageProfilePage = profilePage.getAge();

            Assert.assertEquals(userNameSearchPage, userNameProfilePage, String.format("Expected name: %s, actual name: ", userNameSearchPage, ageProfilePage));
            Assert.assertEquals(ageSearchPage, ageProfilePage, String.format("Expected age: %d, actual age: %d", ageSearchPage, ageProfilePage));
            break;
        }
    }

    @Test(groups = {"regression"},  enabled = TC87)
    public void checkAllOrderOptionCanBeSelectedTC87() {
        homePage.clickOnLink(HomePage.LinksOnHomePage.SEARCH);
        int sizeOrderDropDown = searchPage.getSizeDropDownList(Locators.SEARCH_PAGE_ORDER_DROPDOWN);
        for(int i = 0; i < sizeOrderDropDown; i++) {
            searchPage.selectFromDropDownListByIndex(Locators.SEARCH_PAGE_ORDER_DROPDOWN, i);
            boolean isOptionDisplayed = searchPage.isElementDisplayed(Locators.SEARCH_PAGE_ORDER_OPTIONS);
            Assert.assertTrue(isOptionDisplayed);
        }
    }

    @Test(groups = {"regression"},  enabled = TC88)
    public void testScreenshotSearchPageTC88() {
        homePage.clickOnLink(HomePage.LinksOnHomePage.SEARCH);
        File tempFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(tempFile, new File("screenshots/searchPageScreenshot.png"));
        } catch (IOException e) {
            System.out.println("Screenshot was not saved");
        }
    }
}
