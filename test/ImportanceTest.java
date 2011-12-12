package com.netcracker.lab2.test;

import com.netcracker.lab2.Importance;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by IntelliJ IDEA.
 * User: mpogoda
 * Date: 07/12/11
 * Time: 13:47
 *
 * @author Michael Pogoda
 * @version 0.5.0
 */
public class ImportanceTest {
    @Test
    public void itCorrectlyParsesVeryImportant() throws ParseException {
        assertEquals(Importance.VERY_IMPORTANT,
                Importance.parse(Importance.VERY_IMPORTANT_STRING));
    }

    @Test
    public void itCorrectlyParsesSomehowImportant() throws ParseException {
        assertEquals(Importance.SOMEHOW_IMPORTANT,
                Importance.parse(Importance.SOMEHOW_IMPORTANT_STRING));
    }

    @Test
    public void itCorrectlyParsesRegular() throws ParseException {
        assertEquals(Importance.REGULAR,
                Importance.parse(Importance.REGULAR_STRING));
    }

    @Test
    public void itCorrectlyParsesIDoNotCare() throws ParseException {
        assertEquals(Importance.I_DO_NOT_CARE,
                Importance.parse(Importance.I_DO_NOT_CARE_STRING));
    }

    @Test
    public void itThrowsExceptionIfError() {
        try {
            Importance.parse("asdfgh");
        } catch (ParseException e) {
            return;
        }
        fail("WHERE IS EXCEPTION?");
    }

    @Test
    public void itCorrectlyTransformsIDoNotCare() {
        assertEquals(Importance.I_DO_NOT_CARE_STRING, Importance.I_DO_NOT_CARE.toString());
    }

    @Test
    public void itCorrectlyTransformsRegular() {
        assertEquals(Importance.REGULAR_STRING, Importance.REGULAR.toString());
    }

    @Test
    public void itCorrectlyTransformsSomeHowImportant() {
        assertEquals(Importance.SOMEHOW_IMPORTANT_STRING, Importance.SOMEHOW_IMPORTANT.toString());
    }

    @Test
    public void itCorrectlyTransformsVeryImportant() {
        assertEquals(Importance.VERY_IMPORTANT_STRING, Importance.VERY_IMPORTANT.toString());
    }
}
