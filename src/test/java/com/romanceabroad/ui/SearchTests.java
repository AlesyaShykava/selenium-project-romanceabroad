package com.romanceabroad.ui;

import com.romanceabroad.ui.locators.Locators;
import com.romanceabroad.ui.mainClasses.HomePage;
import com.romanceabroad.ui.testData.Data;
import com.romanceabroad.ui.testData.DataProviders;
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
    public static final boolean TC801 = true;
    public static final boolean TC802 = true;
    public static final boolean TC803 = true;
    public static final boolean TC804 = true;
    public static final boolean TC805 = true;
    public static final boolean TC806 = true;
    public static final boolean TC807 = true;
    public static final boolean TC808 = true;
    public static final boolean TC809 = true;

    @Test(groups = {"smoke", "regression"}, enabled = TC801)
    public void testSearchPageTC801() {
        homePage.clickOnLink(HomePage.LinksOnHomePage.SEARCH);
        currentUrl = searchPage.getCurrentUrl();
        softAssert.assertEquals(currentUrl, Data.expectedUrlSearchPage);
        softAssert.assertTrue(Data.searchPageTitleExpected.equals(searchPage.getTitle()), String.format(Data.incorrectTitleTestMessageFormat, "Search"));
        softAssert.assertAll();
    }

    @Test(groups = {"smoke", "regression"}, enabled = TC802, dataProviderClass = DataProviders.class, dataProvider = "minMaxAgeOrderUserInfoDataSet")
    public void testSearchAndOrderTC802(Integer minAge, Integer maxAge, String order, String womanSummary) {
        homePage.clickOnLink(HomePage.LinksOnHomePage.SEARCH);
        searchPage.performSearchBasedOnMinAndMaxAgeParametersByValue(minAge, maxAge);
        searchPage.setUpOrderByValue(order);
        boolean expectedUserFromSearchDisplayed = searchPage.getListOfUserInfoFirstPage().contains(womanSummary);
        Assert.assertTrue(expectedUserFromSearchDisplayed);
    }

    @Test(groups = {"regression"}, enabled = TC803, dataProviderClass = DataProviders.class, dataProvider = "minMaxAgeDataSet")
    public void checkPeopleFoundNumberTC803(Integer minAge, Integer maxAge) {
        homePage.clickOnLink(HomePage.LinksOnHomePage.SEARCH);
        searchPage.performSearchBasedOnMinAndMaxAgeParametersByValue(minAge, maxAge);
        List<String> listWomanSummary = searchPage.getListOfUserInfoAllPages();
        String peopleFoundTitle = searchPage.getPeopleFoundTitle();
        if(!peopleFoundTitle.contains(Integer.toString(listWomanSummary.size()))) {
            Assert.fail("The incorrect number of founded people is displayed on the title");
        }
    }

    @Test(groups = {"regression"}, enabled = TC804)
    public void checkSearchParametersMinAgeValuesTC804(){
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

    @Test(groups = {"regression"}, enabled = TC805)
    public void checkSummaryOnSearchPageCorrespondToInfoOnProfilePageTC805(){
        homePage.clickOnLink(HomePage.LinksOnHomePage.SEARCH);
        boolean isSearchNeeded = true;
        while (isSearchNeeded) {
            searchPage.selectRandomOptionFromDropDown(Locators.SEARCH_PAGE_SEARCH_PARAMETERS_MIN_AGE_DROPDOWN, "minAge");
            searchPage.clickOnSearchButton();
            isSearchNeeded = searchPage.getListOfUserInfoFirstPage().isEmpty();
            if(isSearchNeeded) continue;

            int randomIndex = random.nextInt(searchPage.getListOfUserInfoFirstPage().size());

            String womanSummary = searchPage.getUserInfoByIndex(randomIndex);
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

    @Test(groups = {"regression"},  enabled = TC806)
    public void checkAllOrderOptionCanBeSelectedTC806() {
        homePage.clickOnLink(HomePage.LinksOnHomePage.SEARCH);
        int sizeOrderDropDown = searchPage.getSizeDropDownList(Locators.SEARCH_PAGE_ORDER_DROPDOWN);
        for(int i = 0; i < sizeOrderDropDown; i++) {
            searchPage.selectFromDropDownListByIndex(Locators.SEARCH_PAGE_ORDER_DROPDOWN, i);
            boolean isOptionDisplayed = searchPage.isElementDisplayed(Locators.SEARCH_PAGE_ORDER_OPTIONS);
            Assert.assertTrue(isOptionDisplayed);
        }
    }

    @Test(groups = {"regression"},  enabled = TC807)
    public void testScreenshotSearchPageTC807() {
        homePage.clickOnLink(HomePage.LinksOnHomePage.SEARCH);
        File tempFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(tempFile, new File("screenshots/searchPageScreenshot.png"));
        } catch (IOException e) {
            System.out.println("Screenshot was not saved");
        }
    }

    @Test(groups = {"regression"}, enabled = TC808, dataProviderClass = DataProviders.class, dataProvider = "minMaxAgeDataSet")
    public void checkAgeInSearchResultCorrespondToSelectedMinAndMaxAgesTC808(Integer minAge, Integer maxAge) {
        homePage.clickOnLink(HomePage.LinksOnHomePage.SEARCH);
        searchPage.performSearchBasedOnMinAndMaxAgeParametersByValue(minAge, maxAge);
        List<String> listWomanSummary = searchPage.getListOfUserInfoAllPages();
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

    @Test(dataProviderClass = DataProviders.class, dataProvider = "minMaxAgeOrderDataSet", groups = {"regression"},  enabled = TC809)
    public void searchDifferentResultsTC809(String minAge, String maxAge, String searchOrder) {
        int min = Integer.parseInt(minAge);
        int max = Integer.parseInt(maxAge);

        homePage.clickOnLink(HomePage.LinksOnHomePage.SEARCH);
        searchPage.selectMinAgeByValue(min);
        searchPage.selectMaxAgeByValue(max);
        searchPage.setUpOrderByValue(searchOrder);
        searchPage.clickOnSearchButton();
        List<String> listOfUserInfo = searchPage.getListOfUserInfoAllPages();
    }
}