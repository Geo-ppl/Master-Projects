package com.countries.info.exception;

/**
 * Custom exception class for handling errors related to Country API operations.
 */

public class CountryAPIException extends Exception {

	private static final long serialVersionUID = 1L; // Unique ID for serialization.
	
    public CountryAPIException() {
        super();
    }

    public CountryAPIException(String message) {
        super(message);
    }

    public CountryAPIException(String message, Throwable cause) {
        super(message, cause);
    }

    public CountryAPIException(Throwable cause) {
        super(cause);
    }

 // Advanced constructor allowing for suppression and writable stack trace options.
    protected CountryAPIException(String message, Throwable cause, 
                                  boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}