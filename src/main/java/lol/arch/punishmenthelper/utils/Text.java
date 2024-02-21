package lol.arch.punishmenthelper.utils;

import lombok.experimental.UtilityClass;
import net.md_5.bungee.api.ChatColor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class Text {
    /**
     * Colorize a list. (Useful for lore)
     * @param list List typeof String
     * @return Colorized List typeof String
     */
    public List<String> colorizeList(List<String> list) {
        if (list == null) return null;
        if (list.isEmpty()) return null;
        List<String> ret = new ArrayList<>();
        for (String line : list) ret.add(colorize(line));
        return ret;
    }

    /**
     * Colorize  a string.
     * @param str String with color codes or hex codes.
     * @return Colorized String
     */
    public String colorize(String str) {
        if (str == null) return null;
        Pattern unicode = Pattern.compile("\\\\u\\+[a-fA-F0-9]{4}");
        Matcher match = unicode.matcher(str);
        while (match.find()) {
            String code = str.substring(match.start(),match.end());
            str = str.replace(code,Character.toString((char) Integer.parseInt(code.replace("\\u+",""),16)));
            match = unicode.matcher(str);
        }
        Pattern pattern = Pattern.compile("&#[a-fA-F0-9]{6}");
        match = pattern.matcher(str);
        while (match.find()) {
            String color = str.substring(match.start(),match.end());
            str = str.replace(color, ChatColor.valueOf(color.replace("&","")) + "");
            match = pattern.matcher(str);
        }
        return ChatColor.translateAlternateColorCodes('&',str);
    }

    /**
     * Strip color codes from a string. (Doesn't remove hex codes)
     * @param str String with color codes.
     * @return String without color codes.
     */
    public String stripColorCodes(String str) {
        return str.replaceAll("&(?! ).", "");
    }

    /**
     * Capitalizes the first letter in a string.
     * @param str String
     * @return Capitalized String
     */
    public String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public List<String> lore(List<String> lore, Object... args) {
        List<String> ret = new ArrayList<>();
        for (String line : lore) ret.add(message(line, args));
        return ret;
    }

    /**
     * Formats a string into a component.
     * @param message string with mini message formatted colours and or placeholders
     * @param args arguments for {@link Text#formatPlaceholders(String, Object...)}
     * @return formatted component
     */
    public String message(String message, Object... args) {
        message = formatPlaceholders(message, args);

        return colorize(message);
    }

    /**
     * Formats Strings with placeholders
     * @param message message with placeholders: {index}
     * @param args things to replace with
     * @return formatted string
     */
    public String formatPlaceholders(String message, Object... args) {
        for (int i = 0; i < args.length; i++) {
            if (!message.contains("{" + i + "}")) {
                continue;
            }

            message = message.replace("{" + i + "}", String.valueOf(args[i]));
        }
        return message;
    }

    /**
     * If only Java had fucking extension methods I wouldn't need this class...
     * @param needle The string to find
     * @param haystack The strings to check if they match the needle, while ignoring case.
     * @return true if one of the needles matches, false otherwise.
     */
    public boolean equalsIgnoreCaseAny(String needle, String... haystack) {
        if (haystack.length < 1) {
            return false;
        }

        for (String match : haystack) {
            if (needle.equalsIgnoreCase(match)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Converts a Throwable to a string
     * @param throwable just an exception
     * @return string Formatted for a discord webhook
     */
    public String stackTraceToString(Throwable throwable) {
        StackTraceElement[] elements = throwable.getStackTrace();
        StringBuilder builder = new StringBuilder("\n> " + throwable.getMessage() + ":\n");

        for (StackTraceElement element : elements) {
            builder.append('\n').append("`").append(element).append("`");
        }

        return builder.substring(1);
    }
}