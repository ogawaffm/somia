package org.velohaven.somia.model;

public class NameUtils {

    public static boolean isName(String name) {
        return name != null && !name.isEmpty();
    }

    public static String getFullQuotedIdentifier(String... names) {

        StringBuilder sb = new StringBuilder();

        for (String name : names) {
            if (isName(name)) {
                // Was there a non-empty name before this one?
                if (!sb.isEmpty()) {
                    // Yes, add a dot separator
                    sb.append('.');
                }
                sb.append('"').append(name).append('"');
            }
        }

        return sb.toString();
    }

}
