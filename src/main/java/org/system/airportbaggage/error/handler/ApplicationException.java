/**
 * 
 */
package org.system.airportbaggage.error.handler;

/**
 * @author vpatel004c
 *
 */
public class ApplicationException extends Exception{

	private static final long serialVersionUID = 1L;

	private String code;

	private String errorText;

	/**
	 * @param errorCode
	 * @param detailMessage
	 */
	public ApplicationException(String code, String errorText) {
		super(errorText);
		this.code = code;
		this.errorText = errorText;
	}

	/**
	 * @param errorCode
	 */
	public ApplicationException(String code) {
		super();
		this.code = code;

	}

	/**
	 * Default Constructor
	 */
	public ApplicationException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ApplicationException(String code, String errorText, Throwable cause) {
		super(errorText, cause);
		this.code = code;
		this.errorText = errorText;
	}

	/**
	 * @param cause
	 */
	public ApplicationException(Throwable cause) {
		super(cause);
	}

	/**
	 * This method returns the existing error code
	 * 
	 * @return errorCode
	 */
	public String getErrorCode() {
		return this.code;
	}

	/**
	 * This method returns the existing error code
	 * 
	 * @return errorText
	 */
	public String getErrorText() {
		return this.errorText;
	}

}
