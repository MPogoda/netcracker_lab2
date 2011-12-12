package com.netcracker.lab2.test;

import com.netcracker.lab2.Importance;
import com.netcracker.lab2.Record;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: mpogoda
 * Date: 08/12/11
 * Time: 14:52
 * <p/>
 * <p/>
 * Test Record class
 */
public class RecordTest {
    @Test
    public void isRecordImmutable() {
        String message = "msg";
        String source = "src";
        Importance level = Importance.I_DO_NOT_CARE;
        Date date = new Date();
        Record r1 = new Record(date, source, level, message);
        message = "";
        source = "";
        level = Importance.VERY_IMPORTANT;
        date.setTime(0);
        Record r2 = new Record(date, source, level, message);

        assertFalse(r1.equals(r2));
    }

    @Test
    public void isEqualsWorks() {
        Record r1 = new Record(new Date(0), "a", Importance.I_DO_NOT_CARE, "b");
        Record r2 = new Record(new Date(0), "a", Importance.I_DO_NOT_CARE, "b");
        Record r3 = new Record(new Date(1000), "a", Importance.I_DO_NOT_CARE, "b");
        Record r4 = new Record(new Date(0), "b", Importance.I_DO_NOT_CARE, "b");
        Record r5 = new Record(new Date(0), "a", Importance.VERY_IMPORTANT, "b");
        Record r6 = new Record(new Date(0), "a", Importance.I_DO_NOT_CARE, "a");
        assertTrue(r1.equals(r2));
        assertFalse(r1.equals(r3));
        assertFalse(r1.equals(r4));
        assertFalse(r1.equals(r5));
        assertFalse(r1.equals(r6));
    }

    @Test
    public void isToStringWorksWithConstructors() {
        Record r1 = new Record(new Date(), "asdfgh", Importance.I_DO_NOT_CARE, "asdfghfdsadf sdfgds fdg sdf s");
        Record r2 = r1;
        try {                          //        Record r2 = r1;
            r2 = new Record(r1.toString());
        } catch (ParseException e) {
            fail("cannot parse");
        }
        assertTrue(r1.equals(r2));
    }
}
