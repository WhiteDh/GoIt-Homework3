import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConverterJSON {
    public void fromTxtToJSON() {
        List<User> users = readUsersFromFile("user.txt");
        writeUsersToJson(users, "user.json");
    }

    private List<User> readUsersFromFile(String fileName) {
        List<User> users = new ArrayList<>();
        try (FileReader fileReader = new FileReader(fileName)) {
            StringBuilder stringBuilder = new StringBuilder();
            int symbol;
            String line;
            boolean isFirstLine = true;

            while ((symbol = fileReader.read()) != -1) {
                if (symbol == '\n') {
                    line = stringBuilder.toString();
                    stringBuilder.setLength(0);
                    if (!isFirstLine) {
                        users.add(parseUser(line));
                    } else {
                        isFirstLine = false;
                    }
                } else {
                    stringBuilder.append((char) symbol);
                }
            }

            if (!stringBuilder.isEmpty()) {
                users.add(parseUser(stringBuilder.toString()));
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return users;
    }

    private User parseUser(String line) {
        String[] parts = line.split(" ");
        String name = parts[0];
        int age = Integer.parseInt(parts[1]);
        double salary = Double.parseDouble(parts[2]);
        return new User(name, age, salary);
    }

    private void writeUsersToJson(List<User> users, String fileName) {
        try (Writer writer = new FileWriter(fileName)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(users, writer);
        } catch (IOException e) {
            System.out.println("Error writing to JSON: " + e.getMessage());
        }
    }
}

class User {
    private String name;
    private int age;
    private double salary;

    public User(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }
}