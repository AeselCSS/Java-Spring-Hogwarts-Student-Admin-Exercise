package kea.exercise.hogwartsstudentadmin.edu.hogwarts.utility;

/**
 * Utility class for string manipulation
 */
public class StringUtility {
    /**
     * Method to convert a string to title case
     * @param input
     * @return a string in title case
     */
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

    /**
     * Method to split a full name into its parts
     * @param fullName
     * @return an array of strings with the first name, middle name, and last name
     */
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

    /**
     * Method to convert a full name to a string
     * @param firstName
     * @param middleName
     * @param lastName
     * @return a full name
     */
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
