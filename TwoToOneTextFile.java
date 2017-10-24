import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class TwoToOneTextFile {
    public static String loadTextFromFile(File file) {
        if (file == null) {
            throw new IllegalArgumentException("Null file pointer");
        }
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String fileContent;
            for (; (fileContent = bufferedReader.readLine()) != null; ) {
                stringBuilder.append(fileContent);
//                stringBuilder.append(System.lineSeparator());
            }
        } catch (IOException ex) {
            System.out.println(ex.fillInStackTrace());
        }
        return stringBuilder.toString();
    }

    public static String[] slicingStringToWords(String fileContent) {
        String[] words = fileContent.split("[ .,\n!?:;&@]");
        return words;
    }

    public static boolean checkIsWordsPresentInFile(String word, String fileContent) {
        String[] wordsArray = slicingStringToWords(fileContent);
        for (String wordOther : wordsArray) {
            if (word.equals(wordOther)) {
                return true;
            }
        }
        return false;
    }

    public static void saveTextToFile(String fileContent, File file) {
        if (file == null || fileContent == null) {
            throw new IllegalArgumentException("Null pointer");
        }
        try (PrintWriter pw = new PrintWriter(file)) {
            pw.println(fileContent);
        } catch (IOException ex) {
            System.out.println(ex.fillInStackTrace());
        }
    }

    public static void sameWordsFilter(File one, File two, File result) {
        if (one == null || two == null || result == null) {
            throw new IllegalArgumentException("Null pointer");
        }
        StringBuilder stringBuilder = new StringBuilder();
        String fileOne = loadTextFromFile(one);
        String fileTwo = loadTextFromFile(two);

        String[] words = slicingStringToWords(fileOne);
        for (String word : words) {
            if (checkIsWordsPresentInFile(word, fileTwo)) {
                stringBuilder.append(word + " ");
            }
        }
        saveTextToFile(stringBuilder.toString(), result);
    }
}
