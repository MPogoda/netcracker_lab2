package com.netcracker.lab2;

/**
 * Created by IntelliJ IDEA.
 * User: mpogoda
 * Date: 11/12/11
 * Time: 18:52
 * To change this template use File | Settings | File Templates.
 */
public class RecordNotFoundException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public RecordNotFoundException(String message) {
        super(message);
    }
}
