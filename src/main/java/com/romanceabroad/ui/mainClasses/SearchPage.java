package com.romanceabroad.ui.mainClasses;

import com.aventstack.extentreports.Status;
import com.romanceabroad.ui.locators.Locators;
import com.romanceabroad.ui.reportUtil.Reports;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends BaseActions implements FooterActions {
    public SearchPage(WebDriver webDriver, WebDriverWait wait) {
        super(webDriver, wait);
    }

    @Override
    public String getTitle() {
        System.out.println("getTitle method from SearchPage class");
        return driver.getTitle();
    }

    public void clickMobileSearchParametersButton() {
        driver.findElement(Locators.SEARCH_PAGE_SEARCH_PARAMETERS_MOBILE_BUTTON).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.SEARCH_PAGE_SEARCH_PARAMETERS_MIN_AGE_DROPDOWN));
    }

    public void performSearchBasedOnMinAndMaxAgeParametersByValue(int minAgeForSearch, int maxAgeForSearch) {
        selectMinAgeByValue(minAgeForSearch);
        selectMaxAgeByValue(maxAgeForSearch);
        clickOnSearchButton();
    }

    public void selectMinAgeByValue(int minAgeForSearch) {
        Reports.log(Status.INFO, String.format("Select age from min_age_dropdown: %s", minAgeForSearch));
        selectFromDropDownListByValue(Locators.SEARCH_PAGE_SEARCH_PARAMETERS_MIN_AGE_DROPDOWN, Integer.toString(minAgeForSearch));
        wait.until(ExpectedConditions.elementToBeClickable(Locators.SEARCH_PAGE_SEARCH_PARAMETERS_MAX_AGE_DROPDOWN));
    }

    public void selectMaxAgeByValue(int maxAgeForSearch) {
        Reports.log(Status.INFO, String.format("Select age from max_age_dropdown: %s", maxAgeForSearch));
        selectFromDropDownListByValue(Locators.SEARCH_PAGE_SEARCH_PARAMETERS_MAX_AGE_DROPDOWN, Integer.toString(maxAgeForSearch));
        waitThreadSleepSec(1);
    }

    public void clickOnSearchButton() {
        Reports.log(Status.INFO,"Click on search button");
        driver.findElement(Locators.SEARCH_PAGE_SEARCH_PARAMETERS_SEARCH_BUTTON).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(Locators.SEARCH_PAGE_LOADING_SPINNER));
    }

    public void setUpOrderByText(String orderText) {
        Reports.log(Status.INFO, String.format("Select order from order_dropdown", orderText));
        selectFromDropDownListByText(Locators.SEARCH_PAGE_ORDER_DROPDOWN, orderText);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(Locators.SEARCH_PAGE_LOADING_SPINNER));
    }

    public boolean isUserPresentInSearchResult(By locator) {
        return driver.findElement(locator).isDisplayed();
    }

    public boolean pagination_isNextButtonPresent() {
        return driver.findElements(Locators.SEARCH_PAGE_PAGINATION_NEXT_BUTTON).size() != 0;
    }

    public void pagination_clickOnNextButton() {
        Reports.log(Status.INFO, "Click on SEARCH_PAGE_PAGINATION_NEXT_BUTTON");
        driver.findElement(Locators.SEARCH_PAGE_PAGINATION_NEXT_BUTTON).click();
    }

    public List<String> getListOfUserInfoNameAndAgeAllPages() {
        List<String> result = new ArrayList<>();
        while(true) {
            List<WebElement> listWomanSummaryPageResult = driver.findElements(Locators.SEARCH_PAGE_USER_INFO_NAME_AGE);
            for(int i = 0; i < listWomanSummaryPageResult.size(); i++) {
                result.add(listWomanSummaryPageResult.get(i).getText());
            }
            if (pagination_isNextButtonPresent()) {
                pagination_clickOnNextButton();
                wait.until(ExpectedConditions.invisibilityOfElementLocated(Locators.SEARCH_PAGE_LOADING_SPINNER));
            }
            else break;
        }
        return result;
    }

    public List<String> getListOfUserInfoNameAndAgeFirstPage() {
        List<String> result = new ArrayList<>();
        List<WebElement> listWomanSummaryPageResult = driver.findElements(Locators.SEARCH_PAGE_USER_INFO_NAME_AGE);
        for(int i = 0; i < listWomanSummaryPageResult.size(); i++) {
            result.add(listWomanSummaryPageResult.get(i).getText());
        }
        return result;
    }

    public List<String> getListOfUserInfoAllFirstPage() {
        List<String> result = new ArrayList<>();
        List<WebElement> listWomanSummaryPageResult = driver.findElements(Locators.SEARCH_PAGE_USER_INFO_ALL);
        for(int i = 0; i < listWomanSummaryPageResult.size(); i++) {
            result.add(listWomanSummaryPageResult.get(i).getText());
        }
        return result;
    }

    public List<Integer> getMinAgeDropDownValues() {
        List<WebElement> minAgeOptionsElements = driver.findElements(Locators.SEARCH_PAGE_MIN_AGE_OPTIONS);
        List<Integer> minAgeValues = new ArrayList<>();
        for(WebElement element : minAgeOptionsElements) {
            minAgeValues.add(Integer.parseInt(element.getAttribute("value")));
        }
        return minAgeValues;
    }

    public String getPeopleFoundTitle() {
        return driver.findElement(Locators.SEARCH_PAGE_PEOPLE_FOUND_TITLE).getText();
    }

    public void clickOnProfileLinkByIndex(int index) {
        List<WebElement> linksToProfilePage = driver.findElements(Locators.SEARCH_PAGE_PROFILE_LINKS);
        WebElement profileLink = linksToProfilePage.get(index);
        Reports.log(Status.INFO, String.format("Click on profile link from search result by index: %d", index));
        profileLink.click();
        Reports.log(Status.INFO, "Wait for loading profile page");
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.PROFILE_PAGE_USER_NAME));
    }

    public String getUserInfoByIndex(int index) {
        Reports.log(Status.INFO, String.format("Get user info by %d index", index));
        return driver.findElements(Locators.SEARCH_PAGE_USER_INFO_NAME_AGE).get(index).getText();
    }
}
