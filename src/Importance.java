package com.netcracker.lab2;

import java.text.ParseException;

/**
 * Created by IntelliJ IDEA.
 * User: mpogoda
 * Date: 29/11/11
 * Time: 16:49
 * <p/>
 * Just enum to represent importance of Record
 *
 * @author Michael Pogoda
 * @version 0.0.5
 * @see Record
 */
public enum Importance {
    VERY_IMPORTANT,
    SOMEHOW_IMPORTANT,
    REGULAR,
    I_DO_NOT_CARE;

    public static String VERY_IMPORTANT_STRING = "!!!!!";
    public static String SOMEHOW_IMPORTANT_STRING = "!!!  ";
    public static String REGULAR_STRING = "!    ";
    public static String I_DO_NOT_CARE_STRING = ".    ";

    /**
     * Represent importance level as String.
     *
     * @return String, representing importance level
     */
    @Override
    public String toString() {
        switch (this) {
            case VERY_IMPORTANT:
                return VERY_IMPORTANT_STRING;
            case SOMEHOW_IMPORTANT:
                return SOMEHOW_IMPORTANT_STRING;
            case REGULAR:
                return REGULAR_STRING;
            case I_DO_NOT_CARE:
                return I_DO_NOT_CARE_STRING;
            default:
                return null;
        }
    }

    /**
     * Parse string, returned by toString() instance method and return Importance level,
     * that input string is representing.
     *
     * @param string String to parse
     * @return Importance level
     * @throws ParseException just in case
     */
    public static Importance parse(final String string) throws ParseException {
        if (string.equals(VERY_IMPORTANT_STRING))
            return VERY_IMPORTANT;
        else if (string.equals(SOMEHOW_IMPORTANT_STRING))
            return SOMEHOW_IMPORTANT;
        else if (string.equals(REGULAR_STRING))
            return REGULAR;
        else if (string.equals(I_DO_NOT_CARE_STRING))
            return I_DO_NOT_CARE;
        else
            throw new ParseException("Cannot represent " + string + " as Importance level", 0);
    }
}
