package com.netcracker.lab2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: mpogoda
 * Date: 29/11/11
 * Time: 16:45
 * <p/>
 * Just a record in journal
 *
 * @author Michael Pogoda
 * @version 0.1.0
 * @see Importance
 */
public class Record {
    private final Date date;
    private final Importance level;
    private final String source;
    private final String message;

    private static final SimpleDateFormat dateFormat =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final Pattern pattern =
            Pattern.compile("^(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}) ([! \\.]{5}) (\\S+) (.+)$");

    /**
     * Constructor, that takes all fields as separate values
     *
     * @param date    date of record
     * @param source  source of record
     * @param level   importance level of record
     * @param message message of record
     */
    public Record(final Date date, final String source, final Importance level, final String message) {
        this.date = new Date(date.getTime());
        this.source = source.trim();
        this.level = level;
        this.message = message.trim();
    }

    /**
     * Try to parse string as Record.
     *
     * @param string string, representing record in format, similar to one, produced by toString() method
     * @throws ParseException if string cannot be parsed as Record
     */
    public Record(final String string) throws ParseException {
        Matcher matcher = pattern.matcher(string);
        if (!matcher.find()) {
            throw new ParseException("Cannot parse '" + string + "' as Record", 0);
        }

        date = dateFormat.parse(matcher.group(1));
        level = Importance.parse(matcher.group(2));
        source = matcher.group(3).trim();
        message = matcher.group(4).trim();
    }

    public boolean equals(final Record other) {
        return (((other.getDate().getTime() - date.getTime()) / 1000) == 0)
                && (other.getLevel().equals(level))
                && (other.getSource().equals(source))
                && (other.getMessage().equals(message));
    }

    /**
     * Represent Record as string.
     *
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(26 + source.length() + message.length());
        sb.append(dateFormat.format(date))
                .append(' ')
                .append(level.toString())
                .append(' ')
                .append(source)
                .append(' ')
                .append(message);
        return sb.toString();
    }

    public Date getDate() {
        return date;
    }

    public Importance getLevel() {
        return level;
    }

    public String getSource() {
        return source;
    }

    public String getMessage() {
        return message;
    }
}
