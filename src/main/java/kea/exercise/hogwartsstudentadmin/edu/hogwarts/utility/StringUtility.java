package kea.exercise.hogwartsstudentadmin.edu.hogwarts.utility;

public class StringUtility {
    public static String toTitleCase(String... input) {
        if (input == null || input.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String s : input) {
            sb.append(s.substring(0, 1).toUpperCase()).append(s.substring(1).toLowerCase()).append(" ");
        }
        return sb.toString().trim();
    }

}
