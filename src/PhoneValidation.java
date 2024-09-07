import java.io.*;
public class PhoneValidation {
    public void read() {
        try (FileReader fileReader = new FileReader("file.txt")) {
            StringBuilder stringBuilder = new StringBuilder();
            int symbol;
            String line;
            while ((symbol = fileReader.read()) != -1) {
                if (symbol == '\n') {

                    line = stringBuilder.toString();
                    if (isPhoneNumber(line)) {
                        System.out.println(line);
                    }
                    stringBuilder.setLength(0);
                } else stringBuilder.append((char) symbol);

            }
            //перевіряємо останній рядрк!!!!!!!!
            if (!stringBuilder.isEmpty()) {
                if (isPhoneNumber(stringBuilder.toString())) System.out.println(stringBuilder.toString());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private boolean isPhoneNumber(String line) {
        //паттерн для перевірки номеру
        return (line.trim().matches("\\(\\d{3}\\) \\d{3}-\\d{4}") || line.trim().matches("\\d{3}-\\d{3}-\\d{4}"));

    }

}
