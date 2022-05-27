package com.netcrecker.services;

import com.netcrecker.model.User;
import com.opencsv.CSVReader;
import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVFileHandler {
    public static final String SCV_USERS = "src/main/resources/files/users.csv";

    public static void writeUserToCSV(User user) {
        try (FileWriter writer = new FileWriter(SCV_USERS, true)) {
            ColumnPositionMappingStrategy<User> mapStrategy = new ColumnPositionMappingStrategy<>();
            mapStrategy.setType(User.class);

            String[] columns = new String[]{"name", "surname", "patronymic",
                    "age", "salary", "mail", "workplace"};
            mapStrategy.setColumnMapping(columns);

            StatefulBeanToCsvBuilder<User> builder = new StatefulBeanToCsvBuilder<>(writer);
            StatefulBeanToCsv<User> beanWriter = builder.withMappingStrategy(mapStrategy).build();

            beanWriter.write(user);
        } catch (CsvRequiredFieldEmptyException | IOException | CsvDataTypeMismatchException e) {
            e.printStackTrace();
        }
    }
    public static List<User> parseCSVtoUsers(String path) {
        List<User> users = null;
        try (CSVReader csvReader = new CSVReader(new FileReader(path));) {
            ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
            strategy.setType(User.class);
            String[] columns = new String[] {"name", "surname", "patronymic", "age", "salary", "mail", "workplace"};
            strategy.setColumnMapping(columns);

            CsvToBean csv = new CsvToBean();
            users = csv.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static int findByFullName(List<User> users, String name, String surname) {
        for (int i = 0; i < users.size(); i++) {
            if (name.equalsIgnoreCase(users.get(i).getName()) &&
                    surname.equalsIgnoreCase(users.get(i).getSurname())) {
                return i;
            }
        }
        return -1;
    }
}