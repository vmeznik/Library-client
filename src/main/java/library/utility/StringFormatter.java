package library.utility;

public class StringFormatter {

    private StringFormatter() {
    }

    public static String capitalizeWord(String str) {
        String[] words = str.split("\\s");
        String capitalizeWord = "";
        for (String w : words) {
            String first = w.substring(0, 1);
            String afterFirst = w.substring(1);
            capitalizeWord += first.toUpperCase() + afterFirst + " ";
        }
        return capitalizeWord.trim();
    }
}