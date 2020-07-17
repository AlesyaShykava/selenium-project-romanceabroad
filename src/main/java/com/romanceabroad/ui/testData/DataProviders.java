package com.romanceabroad.ui.testData;

import org.testng.annotations.DataProvider;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataProviders {

    @DataProvider(name = "RegistrationDataSetHappyPath")
    public static Object[][] getRegistrationDataSetHappyPath() throws IOException {
        List<Object[]> data = new ArrayList<>();
        Files.readAllLines(Paths.get("resources/RegistrationHP.csv")).stream().forEach(s -> {
            Object[] line = s.split(",");
            data.add(new Object[]{line[0], line[1], line[2], line[3], line[4], line[5], line[6], line[7], line[8]});
        });
        return data.toArray(new Object[data.size()][]);
    }

    @DataProvider(name = "RegistrationDataSetWrongCredentials")
    public static Object[][] getRegistrationDataSetIncorrectEmailPassword() throws IOException {
        List<Object[]> data = new ArrayList<>();
        Files.readAllLines(Paths.get("resources/RegistrationWrongCredentials.csv")).stream().forEach(s -> {
            Object[] line = s.split(",");
            data.add(new Object[]{line[0], line[1]});
        });
        return data.toArray(new Object[data.size()][]);
    }

    @DataProvider(name = "Registration2")
    public static Object[][] testRegistration2() {
        return new Object[][] {
                {Data.email1, Data.nickname, true},
                {Data.email2, Data.nickname, false},
                {Data.email3, Data.nickname, true},
        };
    }

    @DataProvider(name = "minMaxAgeDataSet")
    public static Object[][] getMinMaxAgeDataSet() {
        Object[][] data = new Integer[3][2];
        data[0][0] = 20;    data[0][1] = 30;
        data[1][0] = 30;    data[1][1] = 40;
        data[2][0] = 40;    data[2][1] = 50;
        return data;
    }

    @DataProvider(name = "minMaxAgeOrderDataSet")
    public static Object[][] getMaxAgeOrderDataSet() {
        return new Object[][] {
                {"18", "80", Data.searchPageOrderDropDownTextDefault},
                {"18", "19", Data.searchPageOrderDropDownTextName},
                {"30", "40", Data.searchPageOrderDropDownTextViewsCount},
                {"60", "80", Data.searchPageOrderDropDownTextDataCreated},
        };
    }

    @DataProvider(name = "minMaxAgeOrderUserInfoDataSet")
    public static Object[][] getMinMaxAgeOrderUserInfoDataSet() {
        Object[][] data = new Object[3][4];
        data[0][0] = 20;    data[0][1] = 30;    data[0][2] = Data.searchPageOrderDropDownTextName;            data[0][3] = "Tanya, 25";
        data[1][0] = 30;    data[1][1] = 40;    data[1][2] = Data.searchPageOrderDropDownTextViewsCount;      data[1][3] = "Solomia Wyshnevetska, 31";
        data[2][0] = 40;    data[2][1] = 50;    data[2][2] = Data.searchPageOrderDropDownTextDataCreated;     data[2][3] = "Sveta, 45";
        return data;
    }
}
