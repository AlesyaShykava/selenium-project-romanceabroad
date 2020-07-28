package com.romanceabroad.ui.testData;

import com.romanceabroad.ui.mainClasses.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Data {

    //'Search' page
    public static final String searchPageTitleExpected = "Meet single Ukrainian women online: dating and marriage at Marry Ukrainian Lady";
    public static final String expectedUrlSearchPage = "https://romanceabroad.com/users/search";
    public static final String searchPageOrderDropDownValueDefault = "default";
    public static final String searchPageOrderDropDownValueName = "name";
    public static final String searchPageOrderDropDownValueViewsCount = "views_count";
    public static final String searchPageOrderDropDownValueDataCreated = "date_created";
    public static final String searchPageOrderDropDownTextDefault = "Default";
    public static final String searchPageOrderDropDownTextName = "Name";
    public static final String searchPageOrderDropDownTextViewsCount = "Views";
    public static final String searchPageOrderDropDownTextDataCreated = "Registration date";

    //test data for search page
    public static final int searchParametersMinAgeExpected = 18;
    public static final int searchParametersMaxAgeExpected = 80;

    //'Home' page
    public static final String homePageTitleExpected = "The Best Free Ukrainian Dating Site| Romanceabroad.Com";
    public static final String expectedUrlHomePage = "https://romanceabroad.com/#";
    public static final int expectedNumberOfLinksInNavbar = 8;

    //'How It Works' page
    public static final String howItWorksPageTitleExpected = "Real Ukrainian women for marriage: legitimate dating site Marry Ukrainian Lady";
    public static final String howItWorksPageH1TitleExpected = "Ukrainian women for marriage";
    public static final String expectedUrlHowItWorksPage = "https://romanceabroad.com/content/view/how-it-works";

    //'Media' page
    public static final String mediaPageTitleExpected = "Ukrainian women photos: ladies pictures at Marry Ukrainian Lady";
    public static final String mediaPageExpectedUrlMediaPage = "https://romanceabroad.com/media/index";
    public static final String mediaPageExpectedTitleGallery = "Ukrainian women photos";
    public static final String mediaPageExpectedTitleAllTab = "Gallery";
    public static final String mediaPageExpectedTitlePhotoTab = "Photo gallery";
    public static final String mediaPageExpectedTitleVideoTab = "Video gallery";
    public static final String mediaPageExpectedTitleAlbumTab = "Gallery albums";
    public static final String mediaPageTextVideoTabText = "On our website you have access to photos of all different kinds of women.";

    //'Gifts' page
    public static final String giftsPageTitleExpected = "Pilot Group: Gift store - Categories";
    public static final String expectedUrlGiftsPage = "https://romanceabroad.com/store/category-sweets";

    //'Tour To Ukraine' page
    public static final String tourToUkrainePageTitleExpected = "Pilot Group: Gift store - Item";
    public static final String expectedUrlTourToUkrainePage = "https://romanceabroad.com/store/sweets/20-tour_to_ukraine";

    //'Blog' page
    public static final String blogPageTitleExpected = "Blog";
    public static final String expectedUrlBlogPage = "https://romanceabroad.com/content/view/blog";
    public static final ArrayList<String> blogPageLinksSkipCheck = new ArrayList<>(Arrays.asList("How it works",
            "Kharkov dating agency", "Mail order girls", "Real Ukrainian brides", "Marriage agency in Ukraine",
            "Kiev dating site", "How to marry Ukrainian lady", "Free Ukrainian dating site",
            "9 Factors to Keep in Mind When Dating a Ukrainian Woman",
            "Is There a Difference Between Dating or Courting a Ukrainian Woman?"));

    //'Restore Password' page
    public static final String expectedUrlRestorePage = "https://romanceabroad.com/users/restore";

    //'Login' page
    public static final String expectedUrlLoginPage = "https://romanceabroad.com/users/login_form";

    //'User Profile' page
    public static String userProfilePageExpectedActiveTab = "Profile";

    //'Registration' form
    public static final String registrationFormEmailIncorrectAlertMessageExpected = "Email is incorrect, please try again";
    public static final String registrationFormPasswordIncorrectAlertMessageExpected = "Please choose a password that is at least 6 characters long";

    //test data for registration flow
    public static final String incorrectTestData = "abc";
    public static String email1 = "11@gmail.com";
    public static String email2 = "11yahoo.com";
    public static String email3 = "11@inbox.com";
    public static String password = "123456789";
    public static String nickname1stPart = "nickName";
    public static final int lengthSecondPartOfNickName = 5;
    public static String nickname = RegistrationModal.generateRandomNumber(Data.nickname1stPart, lengthSecondPartOfNickName);
    public static String phone = "222222222";
    public static String dayDOB = "7";
    public static String monthDOB = "1";
    public static String yearDOB = "1987";
    public static String locationCity = "Sydney";
    public static String locationFull = "State of New South Wales";

    //'Sign In' form
    public static final String signInFormTitleExpected = "The Best Free Ukrainian Dating Site| Romanceabroad.Com";

    //'Contact Us' page
    public static final String contactUsReasonDropDownTextPreSales = "Pre-Sales Questions";
    public static final String contactUsReasonDropDownTextTechnicalSupport = "Technical support";
    public static final String contactUsReasonDropDownTextOther = "Other";
    public static final String contactUsH1TitleExpected = "Contact administrator";

    //'Site map' page
    public static final String siteMapH1TitleExpected = "Site map";

    //'Privacy' page
    public static final String privacyH1TitleExpected = "Privacy Policy";

    //'Terms of use'
    public static final String termsOfUseH1TitleExpected = "Terms of use";

    //test data for contact us page
    public static final String email = "example@abc.abc";
    public static final String name = "nick";
    public static String subject = "Technical support";
    public static String message = "Technical support";

    //error messages for test
    public static final String incorrectTitleTestMessageFormat = "Incorrect title of '%s' Page";
}
