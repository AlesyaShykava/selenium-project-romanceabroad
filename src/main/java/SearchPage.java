import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends BaseActions {
    public SearchPage(WebDriver webDriver, WebDriverWait wait) {
        super(webDriver, wait);
    }

    public void performSearchBasedOnMinAndMaxAgeParameters(int minAgeForSearch, int maxAgeForSearch) {
        selectFromDropDownListByValue(driver.findElement(Locators.SEARCH_PAGE_SEARCH_PARAMETERS_MIN_AGE_DROPDOWN), Integer.toString(minAgeForSearch));
        wait.until(ExpectedConditions.elementToBeClickable(Locators.SEARCH_PAGE_SEARCH_PARAMETERS_MAX_AGE_DROPDOWN));
        selectFromDropDownListByValue(driver.findElement(Locators.SEARCH_PAGE_SEARCH_PARAMETERS_MAX_AGE_DROPDOWN), Integer.toString(maxAgeForSearch));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) { }
        driver.findElement(Locators.SEARCH_PAGE_SEARCH_PARAMETERS_SEARCH_BUTTON).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(Locators.SEARCH_PAGE_LOADING_SPINNER));
    }

    public void setUpOrder(String orderValueDataCreated) {
        selectFromDropDownListByValue(driver.findElement(Locators.SEARCH_PAGE_ORDER_DROPDOWN), orderValueDataCreated);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(Locators.SEARCH_PAGE_LOADING_SPINNER));
    }

    public boolean isUserPresentInSearchResult(By locator) {
        return driver.findElement(locator).isDisplayed();
    }

    public boolean pagination_isNextButtonPresent() {
        return driver.findElements(Locators.SEARCH_PAGE_PAGINATION_NEXT_BUTTON).size() != 0;
    }

    public void pagination_clickOnNextButton() {
        driver.findElement(Locators.SEARCH_PAGE_PAGINATION_NEXT_BUTTON).click();
    }

    public List<String> getListOfWomenSummaryAll() {
        List<String> result = new ArrayList<>();
        while(true) {
            List<WebElement> listWomanSummaryPageResult = driver.findElements(Locators.SEARCH_PAGE_SEARCH_RESULT_WOMEN_SUMMARY);
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

    public List<String> getListOfWomenSummaryFirstPage() {
        List<String> result = new ArrayList<>();
        List<WebElement> listWomanSummaryPageResult = driver.findElements(Locators.SEARCH_PAGE_SEARCH_RESULT_WOMEN_SUMMARY);
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
        profileLink.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.PROFILE_PAGE_USER_NAME));
    }

    public String getWomanSummaryByIndex(int index) {
        return driver.findElements(Locators.SEARCH_PAGE_SEARCH_RESULT_WOMEN_SUMMARY).get(index).getText();
    }
}
