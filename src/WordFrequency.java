import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

public class WordFrequency {
    private HashMap<String, Integer> frequency = new HashMap<>();

    private String readFile() {
        StringBuilder stringBuilder;
        try (FileReader fileReader = new FileReader("word.txt")) {
            stringBuilder = new StringBuilder();
            int symbol;
            while ((symbol = fileReader.read()) != -1) {
                stringBuilder.append((char) symbol);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stringBuilder.toString();
    }

    public void printFrequency() {
        String text = readFile();

        //паттерн для розділювання слів за пробілами і переносом
        String[] words = text.split("\\s+");

        //щоб слова не повторювались
        Set<String> uniqueWords = new LinkedHashSet<>();
        for (String word : words) {
            uniqueWords.add(word);
            frequency.put(word, frequency.getOrDefault(word, 0) + 1);
        }

        String[] newArray = sortArray(uniqueWords.toArray(new String[0]));
        for (String word : newArray) {
            System.out.println(word + " " + frequency.get(word));
        }
    }

    private String[] sortArray(String[] words) {
        boolean isSorted = false;
        String buf;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < words.length - 1; i++) {
                if (frequency.get(words[i]) < frequency.get(words[i + 1])) {
                    isSorted = false;

                    buf = words[i];
                    words[i] = words[i + 1];
                    words[i + 1] = buf;
                }
            }
        }
        return words;
    }
}
