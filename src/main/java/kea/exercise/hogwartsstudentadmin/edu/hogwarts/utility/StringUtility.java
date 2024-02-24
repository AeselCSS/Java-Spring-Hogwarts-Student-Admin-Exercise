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

    public static String[] toNameParts(String fullName) {
        int firstSpace = fullName.indexOf(" ");
        int lastSpace = fullName.lastIndexOf(" ");

        // format fullname with toTitleCase
        fullName = toTitleCase(fullName);

        String firstName = fullName.substring(0, firstSpace);
        String middleName = null;
        if (firstSpace != lastSpace) {
            middleName = fullName.substring(firstSpace + 1, lastSpace);
        }
        String lastName = fullName.substring(lastSpace + 1);

        return new String[] {firstName, middleName, lastName};
    }

    public static String toFullName(String firstName, String middleName, String lastName) {
        StringBuilder fullName = new StringBuilder();
        if (firstName != null) {
            fullName.append(toTitleCase(firstName));
        }
        if (middleName != null) {
            if (!fullName.isEmpty()) fullName.append(" ");
            fullName.append(toTitleCase(middleName));
        }
        if (lastName != null) {
            if (!fullName.isEmpty()) fullName.append(" ");
            fullName.append(toTitleCase(lastName));
        }
        return fullName.toString();
    }

}
