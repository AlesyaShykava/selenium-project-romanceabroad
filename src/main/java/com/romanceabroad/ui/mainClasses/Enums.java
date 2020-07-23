package com.romanceabroad.ui.mainClasses;

import com.romanceabroad.ui.locators.Locators;
import org.openqa.selenium.By;

public class Enums {
    public enum MediaPageTabLinks {
        All(Locators.MEDIA_PAGE_TAB_LINK_All),
        Photo(Locators.MEDIA_PAGE_TAB_LINK_PHOTO),
        Video(Locators.MEDIA_PAGE_TAB_LINK_VIDEO),
        Albums(Locators.MEDIA_PAGE_TAB_LINK_ALBUMS);

        private By locator;

        public By getLocator() {
            return locator;
        }

        MediaPageTabLinks(By locator) {
                this.locator = locator;
        }
    }

    public enum HomePageLinksOnHomePage {
        SEARCH(Locators.NAVBAR_SEARCH_LINK),
        HOME(Locators.NAVBAR_HOME_LINK),
        HOW_IT_WORKS(Locators.NAVBAR_HOW_IT_WORKS_LINK),
        MEDIA(Locators.NAVBAR_MEDIA_LINK),
        GIFTS(Locators.NAVBAR_GIFTS_LINK),
        TOUR_TO_UKRAINE(Locators.NAVBAR_TOUR_TO_UKRAINE_LINK),
        BLOG(Locators.NAVBAR_BLOG_LINK),
        SIGN_IN(Locators.NAVBAR_SIGN_IN_LINK),
        JOIN_FOR_FREE_NOW(Locators.NAVBAR_JOIN_FOR_FREE_NOW_LINK);

        private By locator;

        HomePageLinksOnHomePage(By locator) {
            this.locator = locator;
        }

        public By getLocator() {
            return locator;
        }
    }
}
