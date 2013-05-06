package com.gipermarket.exception;

/**
 * SubsystemConnectionException throws when connection to model layer is interrupted.
 * 
 * @author sergey.evliukhin
 */
public class SubsystemConnectionException extends Exception {

	private static final long serialVersionUID = -210967985965056221L;

	public SubsystemConnectionException() {
	}

	public SubsystemConnectionException(Throwable e) {
		super(e);
	}

}
