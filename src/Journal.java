package com.netcracker.lab2;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: mpogoda
 * Date: 11/12/11
 * Time: 09:21
 */
public interface Journal {
    /**
     * Add record to journal
     *
     * @param record record to be added
     * @throws RecordAlreadyExistsException if record already present in journal
     */
    public void add(Record record) throws RecordAlreadyExistsException;

    /**
     * Add all records from other journal
     *
     * @param journal other journal
     * @throws RecordAlreadyExistsException if some record already present in journal
     */
    public void add(Journal journal) throws RecordAlreadyExistsException;

    /**
     * Remove record from journal
     *
     * @param record record to be removed
     * @throws RecordNotFoundException if record wasn't found in journal
     */
    public void remove(Record record) throws RecordNotFoundException;

    /**
     * Get record at position in journal, specified by index
     *
     * @param index position in journal, started from 0
     * @return record, located at this position
     * @throws WrongIndexException if index is out of bounds
     */
    public Record get(int index) throws WrongIndexException;

    /**
     * Modify record, specified by its position in journal.
     *
     * @param index  position of record to be modified.
     * @param record new value for this record
     * @throws WrongIndexException          if index is out of bounds
     * @throws RecordAlreadyExistsException if new record already exists somewhere in journal
     */
    public void set(int index, Record record) throws WrongIndexException, RecordAlreadyExistsException;

    /**
     * Insert new record at specified position.
     *
     * @param index  position in journal for new record
     * @param record new record
     * @throws WrongIndexException          if index is out of bounds
     * @throws RecordAlreadyExistsException if value of new record already exists in journal
     */
    public void insert(int index, Record record) throws WrongIndexException, RecordAlreadyExistsException;

    /**
     * Remove record specified by its index
     *
     * @param index position of record to be deleted
     * @throws WrongIndexException if index is out of bounds
     */
    public void remove(int index) throws WrongIndexException;

    /**
     * Remove range of records from journal.
     *
     * @param fromIndex left inclusive bound
     * @param toIndex   right exclusive bound
     * @throws WrongIndexException if some index is out of bounds
     */
    public void remove(int fromIndex, int toIndex) throws WrongIndexException;

    /**
     * Remove all records from journal.
     */
    public void removeAll();

    /**
     * @return number of records in journal.
     */
    public int size();

    /**
     * Find all records, string representation of which contains specific string.
     *
     * @param string
     * @return new journal with filtered records
     */
    public Journal filter(String string);

    /**
     * Find all records between two dates.
     *
     * @param fromDate
     * @param toDate
     * @return new journal with filtered records
     */
    public Journal filter(Date fromDate, Date toDate);

    /**
     * Sort records by date.
     */
    public void sortByDate();

    /**
     * Sort records by Importance level first, then by date.
     */
    public void sortByImportanceDate();

    /**
     * Sort records by Importance, then by source and then by date.
     */
    public void sortByImportanceSourceDate();

    /**
     * Sort records by source and then by date.
     */
    public void sortBySourceDate();

    /**
     * Print all records to standard output.
     */
    public void printRecords();
}
