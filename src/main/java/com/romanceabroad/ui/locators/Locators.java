package com.romanceabroad.ui.locators;

import org.openqa.selenium.By;

public class Locators {

    //navbar elements on the 'Home' page
    public static final By NAVBAR = By.xpath("//ul[@class='navbar-nav']/li");
    public static final By NAVBAR_SEARCH_LINK = By.xpath("//a[@href='https://romanceabroad.com/users/search']");
    public static final By NAVBAR_HOME_LINK = By.xpath("//a[@href='#']");
    public static final By NAVBAR_HOW_IT_WORKS_LINK = By.xpath("//a[@href='https://romanceabroad.com/content/view/how-it-works']");
    public static final By NAVBAR_MEDIA_LINK = By.xpath("//a[@href='https://romanceabroad.com/media/index']");
    public static final By NAVBAR_GIFTS_LINK = By.xpath("//a[@href='https://romanceabroad.com/store/category-sweets']");
    public static final By NAVBAR_TOUR_TO_UKRAINE_LINK = By.xpath("//a[@href='https://romanceabroad.com/store/sweets/20-tour_to_ukraine']");
    public static final By NAVBAR_BLOG_LINK = By.xpath("//a[@href='https://romanceabroad.com/content/view/blog']");
    public static final By NAVBAR_SIGN_IN_LINK = By.xpath("//a[@href='https://romanceabroad.com/users/login_form'][@id='ajax_login_link']");
    public static final By NAVBAR_JOIN_FOR_FREE_NOW_LINK = By.cssSelector("#show-registration-block");
    public static final By NAVBAR_MOBILE_BUTTON = By.xpath("//button[contains(@class, 'navbar-toggler-right')]");

    //other element on the 'Home' page
    public static final By FRAME_WITH_YOUTUBE_VIDEO = By.xpath("//iframe[@src='https://www.youtube.com/embed/RRECuJzm3IY?start=85']");
    public static final By YOUTUBE_VIDEO_PLAY_BUTTON = By.cssSelector(".ytp-large-play-button.ytp-button");

    //elements on the 'Sign In' page
    public static final By SIGN_IN_EMAIL_FIELD = By.cssSelector("input[id='email'][type='text']");
    public static final By SIGN_IN_PASSWORD_FIELD = By.cssSelector("input[id='password'][name='password']");
    public static final By SIGN_IN_SUBMIT_BUTTON = By.cssSelector("button[type='submit']");
    public static final By SIGN_IN_FORGOT_PASSWORD_LINK = By.xpath("//a[contains(text(), 'Forgot password')]");
    public static final By SIGN_IN_ALERT_POP_UP_DISPLAYED = By.xpath("//div[@id='autogen_error_block'][not(contains(@style, 'display: none'))]");

    //elements on the 'Restore' page
    public static final By RESTORE_PASSWORD_HEADER = By.xpath("//div[@class='page-header']/h1");
    public static final By RESTORE_PASSWORD_HEADER_COMMENT = By.xpath("//p[@class='header-comment']");
    public static final By RESTORE_PASSWORD_EMAIL_FIELD = By.cssSelector("#email");
    public static final By RRESTORE_PASSWORD_SUBMIT_BUTTON = By.cssSelector("input[type='submit']");
    public static final By RESTORE_PASSWORD_ALERT_POP_UP_DISPLAYED = By.xpath("//div[@id='autogen_error_block'][not(contains(@style, 'display: none'))]");

    //elements on the 'Registration' form
    public static final By REGISTRATION_EMAIL_FIELD = By.xpath("//input[@id='email']");
    public static final By REGISTRATION_PASSWORD_FIELD = By.xpath("//input[@id='password']");
    public static final By REGISTRATION_PAGINATION = By.xpath("//div[@class='paginator']//i[@data-page='3']");
    public static final By REGISTRATION_INCORRECT_EMAIL_ALERT = By.xpath("//input[@id='email']//following-sibling::div[@class='tooltip']");
    public static final By REGISTRATION_INCORRECT_PASSWORD_ALERT = By.xpath("//input[@id='password']//following-sibling::div[@class='tooltip']");
    public static final By REGISTRATION_NEXT_BUTTON_ENABLED = By.xpath("//button[@data-action='next-page'][text()='Next']");
    public static final By REGISTRATION_NEXT_BUTTON_DISABLED = By.xpath("//button[@data-action='next-page'][text()='Next'][@disabled]");
    public static final By REGISTRATION_NICKNAME_TEXT_FIELD = By.xpath("//input[@id='nickname']");
    public static final By REGISTRATION_PHONE_TEXT_FIELD = By.xpath("//input[@name='data[phone]']");
    public static final By REGISTRATION_DAY_SELECTION = By.xpath("//div[@id='daySelect']//span[@class='caret']");
    public static final By REGISTRATION_MONTH_SELECTION = By.xpath("//div[@id='monthSelect']//span[@class='caret']");
    public static final By REGISTRATION_YEAR_SELECTION = By.xpath("//div[@id='yearSelect']//span[@class='caret']");
    public static final By REGISTRATION_DAYS_LIST_ELEMENTS = By.xpath("//li[@data-handler='selectDay']");
    public static final By REGISTRATION_MONTHS_LIST_ELEMENTS = By.xpath("//li[@data-handler='selectMonth']");
    public static final By REGISTRATION_YEARS_LIST_ELEMENTS = By.xpath("//li[@data-handler='selectYear']");
    public static final By REGISTRATION_TERMS_AND_CONDITIONS_CHECKBOX = By.xpath("//input[@id='confirmation']");
    public static final By REGISTRATION_AUTO_FILLING_FORM_LOCATION = By.xpath("//input[@name='region_name']");
    public static final By REGISTRATION_AUTO_SUGGESTIONS_LIST_LOCATION = By.xpath("//div[@class='dropdown dropdown_location']//li");

    //elements on 'Search' page
    public static final By SEARCH_PAGE_SEARCH_PARAMETERS_MOBILE_BUTTON = By.xpath("//div[@class='search-param-button mb10']//a");
    public static final By SEARCH_PAGE_SEARCH_PARAMETERS_MIN_AGE_DROPDOWN = By.xpath("//select[@id='age_min']");
    public static final By SEARCH_PAGE_SEARCH_PARAMETERS_MAX_AGE_DROPDOWN = By.xpath("//select[@id='age_max']");
    public static final By SEARCH_PAGE_SEARCH_PARAMETERS_SEARCH_BUTTON = By.xpath("//input[@id='main_search_button_user_advanced']");
    public static final By SEARCH_PAGE_ORDER_DROPDOWN = By.cssSelector("select[id*='sorter-select']");
    public static final By SEARCH_PAGE_USER_INFO_NAME_AGE = By.xpath("//div[@class='text-overflow'][a]");
    public static final By SEARCH_PAGE_USER_INFO_ALL = By.xpath("//div[@class='text-overflow']");
    public static final By SEARCH_PAGE_PAGINATION_NEXT_BUTTON = By.xpath("//li[@class='next']/a[@data-page]");
    public static final By SEARCH_PAGE_MIN_AGE_OPTIONS = By.xpath("//select[@name='age_min']/option");
    public static final By SEARCH_PAGE_MAX_AGE_OPTIONS = By.xpath("//select[@name='age_max']/option");
    public static final By SEARCH_PAGE_ORDER_OPTIONS = By.xpath("//select[contains(@id,'sorter-select')]/option");
    public static final By SEARCH_PAGE_PEOPLE_FOUND_TITLE = By.xpath("//div[@class='st-info']/div");
    public static final By SEARCH_PAGE_LOADING_SPINNER = By.xpath("//div[@id='autogen_loading_block']");
    public static final By SEARCH_PAGE_PROFILE_LINKS = By.xpath("//a[@class='g-users-gallery__name']");

    //elements on 'Profile' page
    public static final By PROFILE_PAGE_USER_NAME = By.xpath("//div[contains(@class, 'user-all-description')]/h1");
    public static final By PROFILE_PAGE_AGE = By.xpath("//span[contains(text(), 'years old')]");
    public static final By PROFILE_PAGE_USER_PHOTO = By.xpath("//div/div/a[@id='user_photo']");
    public static final By PROFILE_PAGE_SERVICES_MENU = By.xpath("//div/span/button[@id='services-menu']");
    public static final By PROFILE_PAGE_LAST_REGISTERED_USERS_REFRESH = By.xpath("//div/span/i[@class='fa fa-refresh']");
    public static final By PROFILE_PAGE_LAST_REGISTERED_USERS_TITLE = By.xpath("//div[@id='last_registered_users']//div//span");
    public static final By PROFILE_PAGE_TAB_LINK_WALL = By.xpath("//a[contains(text(),'Wall')]");
    public static final By PROFILE_PAGE_TAB_LINK_PROFILE = By.xpath("//a[contains(text(),'Profile')]");
    public static final By PROFILE_PAGE_TAB_LINK_GALLERY = By.xpath("//a[contains(text(),'Gallery')]");
    public static final By PROFILE_PAGE_TABS_LIST = By.xpath("//div[@class='profile-menu clearfix']//li");

    //elements on 'Contact Us' page
    public static final By CONTACT_US_PAGE_REASON_DROPDOWN = By.xpath("//select[@name='id_reason']");
    public static final By CONTACT_US_PAGE_USER_NAME_INPUT = By.xpath("//input[@name='user_name']");
    public static final By CONTACT_US_PAGE_USER_EMAIL_INPUT = By.xpath("//input[@name='user_email']");
    public static final By CONTACT_US_PAGE_SUBJECT_INPUT = By.xpath("//input[@name='subject']");
    public static final By CONTACT_US_PAGE_MESSAGE_TEXTAREA = By.xpath("//textarea[@name='message']");

    //elements on 'Blog' page
    public static final By BLOG_LINK_OF_ARTICLES = By.xpath("//ul[contains(@class, 'content-pages-tree')]//li");

    //elements on 'Media' page
    public static final By MEDIA_PAGE_TAB_LINKS = By.xpath("//ul[@id='gallery_filters']//a");
    public static final By MEDIA_PAGE_TAB_LINK_All = By.xpath("//ul[@id='gallery_filters']//a[contains(@href,'all')]");
    public static final By MEDIA_PAGE_TAB_LINK_PHOTO = By.xpath("//ul[@id='gallery_filters']//a[contains(@href,'photo')]");
    public static final By MEDIA_PAGE_TAB_LINK_VIDEO = By.xpath("//ul[@id='gallery_filters']//a[contains(@href,'video')]");
    public static final By MEDIA_PAGE_TAB_LINK_ALBUMS = By.xpath("//ul[@id='gallery_filters']//a[contains(@href,'albums')]");

    //elements with common com.romanceabroad.ui.locators on different pages
    public static final String LINKS_CHECKS_STRING_FORMAT = "//%s[not(contains(@href,'javascript') or contains(@href,'mailto') or contains(@href,'callto'))]";
    public static final By H1_TITLE = By.xpath("//h1");

    //main menu
    public static final By MAIN_MENU_SLIDE_MENU = By.xpath("//div//a//i[contains(@class,'fa-bars')]");
    public static final By MAIN_MENU_GIFT = By.xpath("//div//a//i[contains(@class,'fa-gift')]");
    public static final By MAIN_MENU_TRIP_TO_UKRAINE = By.xpath("//a[@href='https://romanceabroad.com/store/sweets/5-vip_individual_tour_to_ukraine'][@data-item]");
    public static final By MAIN_MENU_SEARCH = By.xpath("//div//form//button[@id='main_search_button_user_line']");
    public static final By MAIN_MENU_LOGIN = By.xpath("//div//div//a[@id='ajax_login_link']");

    //footer menu
    public static final By FOOTER_MENU = By.className("footer-menu");
    public static final By FOOTER_MENU_LINKS = By.xpath("//div[@class='footer-menu']//a//div");
    public static final By FOOTER_MENU_CONTACT_US = By.xpath("//*[contains(@id,'footer-menu-tickets-item')]//div");
}
