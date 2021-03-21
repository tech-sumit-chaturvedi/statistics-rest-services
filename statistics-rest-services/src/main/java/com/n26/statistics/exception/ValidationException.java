package com.n26.statistics.exception;

/**
* This is a customized exception class, used when validation throws exception
* @author Sumit.Chaturvedi
* @since 2021-20-03 03:48
*/
@SuppressWarnings("serial")
public class ValidationException  extends Exception {
	
	public ValidationException(String message) {
		super(message);
	}
}
