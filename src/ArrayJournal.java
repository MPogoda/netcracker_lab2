package com.netcracker.lab2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: mpogoda
 * Date: 11/12/11
 * Time: 09:29
 */
public class ArrayJournal implements Journal {
    Record[] records;
    private int size;
    final static int BLOCK_SIZE = 16;

    public ArrayJournal() {
        size = 0;
        records = null;
    }

    public ArrayJournal(ArrayJournal other) {
        this.size = other.size();
        this.records = new Record[other.records.length];
        System.arraycopy(other.records, 0, this.records, 0, this.size);
    }

    @Override
    public void add(Record record) throws RecordAlreadyExistsException {
        if ((records == null) || (size == (records.length - 1))) {
            Record[] newRecords = new Record[size + BLOCK_SIZE];
            if (records != null) {
                System.arraycopy(records, 0, newRecords, 0, size);
            }
            records = newRecords;
        }

        for (int i = 0; i < size; ++i) {
            if (records[i].equals(record)) {
                throw new RecordAlreadyExistsException("Record '" + record.toString() + "' already exists in journal.");
            }
        }
        records[size++] = record;
    }

    @Override
    public void add(Journal journal) throws RecordAlreadyExistsException {
        for (int i = 0; i < journal.size(); ++i) {
            try {
                this.add(journal.get(i));
            } catch (WrongIndexException e) {
                assert false; // for statement goes through existing indices, so no error here
            }
        }
    }

    @Override
    public void remove(Record record) throws RecordNotFoundException {
        for (int i = 0; i < size; ++i) {
            if (records[i].equals(record)) {
                try {
                    this.remove(i);
                } catch (WrongIndexException e) {
                    assert false; // for statement goes through existing indices, so no error here
                }
                return;
            }
        }
        throw new RecordNotFoundException("Record '" + record.toString() + "' not found.");
    }

    @Override
    public Record get(int index) throws WrongIndexException {
        if ((index < size) && (index >= 0)) {
            return records[index];
        } else {
            throw new WrongIndexException("Wrong index '" + index + "'.");
        }

    }

    @Override
    public void set(int index, Record record) throws WrongIndexException, RecordAlreadyExistsException {
        if ((index >= size) || (index < 0)) {
            throw new WrongIndexException("Wrong index '" + index + "'.");
        }
        for (int i = 0; i < size; ++i) {
            if (records[i].equals(record) && (index != i)) {
                throw new RecordAlreadyExistsException("Record '" + record.toString() + "' already exists in journal.");
            }
        }
        records[index] = record;
    }

    @Override
    public void insert(int index, Record record) throws WrongIndexException, RecordAlreadyExistsException {
        if ((index > size) || (index < 0)) {
            throw new WrongIndexException("Index '" + index + "' is wrong.");
        }
        this.add(record);
        for (int i = size; i > index; --i) {
            records[i] = records[i - 1];
        }
        records[index] = record;
    }

    @Override
    public void remove(int index) throws WrongIndexException {
        if ((index >= size) || (index < 0)) {
            throw new WrongIndexException("Index '" + index + "' is wrong.");
        }
        System.arraycopy(records, index + 1, records, index, --size - index);
    }

    @Override
    public void remove(int fromIndex, int toIndex) throws WrongIndexException {
        for (int i = 0; i < (toIndex - fromIndex); ++i) {
            this.remove(fromIndex);
        }
    }

    @Override
    public void removeAll() {
        records = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Journal filter(String string) {
        ArrayJournal filterResult = new ArrayJournal();
        for (int i = 0; i < size; ++i) {
            if (records[i].toString().contains(string)) {
                try {
                    filterResult.add(records[i]);
                } catch (RecordAlreadyExistsException e) {
                    assert false; // no repeats of records in input journal -> so no repeats in output
                }
            }
        }
        return filterResult;
    }

    @Override
    public Journal filter(Date fromDate, Date toDate) {
        ArrayJournal filterResult = new ArrayJournal();
        for (int i = 0; i < size; ++i) {
            if ((records[i].getDate().compareTo(fromDate) >= 0) && (records[i].getDate().compareTo(toDate) <= 0)) {
                try {
                    filterResult.add(records[i]);
                } catch (RecordAlreadyExistsException e) {
                    assert false;  // no repeats of records in input journal -> so no repeats in output
                }
            }
        }
        return filterResult;
    }

    @Override
    public void sortByDate() {
        Arrays.sort(records, 0, size(), new Comparator<Record>() {
            @Override
            public int compare(Record o1, Record o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
    }

    @Override
    public void sortByImportanceDate() {
        Arrays.sort(records, 0, size, new Comparator<Record>() {
            @Override
            public int compare(Record o1, Record o2) {
                int importanceCompare = o1.getLevel().compareTo(o2.getLevel());
                if (importanceCompare != 0) {
                    return importanceCompare;
                } else {
                    return o1.getDate().compareTo(o2.getDate());
                }
            }
        });
    }

    @Override
    public void sortByImportanceSourceDate() {
        Arrays.sort(records, 0, size, new Comparator<Record>() {
            @Override
            public int compare(Record o1, Record o2) {
                int importanceCompare = o1.getLevel().compareTo(o2.getLevel());
                if (importanceCompare != 0) {
                    return importanceCompare;
                } else {
                    int sourceCompare = o1.getSource().compareTo(o2.getSource());
                    if (sourceCompare != 0) {
                        return sourceCompare;
                    } else {
                        return o1.getDate().compareTo(o2.getDate());
                    }
                }
            }
        });
    }

    @Override
    public void sortBySourceDate() {
        Arrays.sort(records, 0, size, new Comparator<Record>() {
            @Override
            public int compare(Record o1, Record o2) {
                int sourceCompare = o1.getSource().compareTo(o2.getSource());
                if (sourceCompare != 0) {
                    return sourceCompare;
                } else {
                    return o1.getDate().compareTo(o2.getDate());
                }
            }
        });
    }

    @Override
    public void printRecords() {
        for (int i = 0; i < size; ++i) {
            System.out.println(records[i].toString());
        }
    }
}
