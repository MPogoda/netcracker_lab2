package com.netcracker.lab2;

/**
 * Created by IntelliJ IDEA.
 * User: mpogoda
 * Date: 11/12/11
 * Time: 18:44
 */
public class RecordAlreadyExistsException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public RecordAlreadyExistsException(String message) {
        super(message);
    }
}
