package com.netcrecker.services;

import com.netcrecker.model.User;
import com.opencsv.CSVReader;
import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public static List<User> parse( MultipartFile file) throws IOException {

        BufferedReader reader = new BufferedReader(new
                InputStreamReader(file.getInputStream(), "UTF-8"));

        // считываем построчно
        String line = null;
        Scanner scanner = null;
        List<User> users = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            User user = new User();
            scanner = new Scanner(line);
            scanner.useDelimiter(",");
            while (scanner.hasNext()) {
                String data = scanner.next();
                user.setName(data);
                String data1 = scanner.next();
                user.setSurname(data1);
                String data2 = scanner.next();
                user.setPatronymic(data2);
                String data3 = scanner.next();
                user.setAge(Integer.parseInt(data3));
                String data4 = scanner.next();
                user.setSalary(Integer.parseInt(data4));
                String data5 = scanner.next();
                user.setEmail(data5);
                String data6 = scanner.next();
                user.setWorkplace(data6);
            }
            users.add(user);
        }
        reader.close();

        return  users;
    }
}