/**
 * 
 */
package com.letv.quartz.container;

/**
 */
public abstract class BaseException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int code = 0;
	
	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}
	
	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}
	/**
	 * 
	 */
	public BaseException() {
		
	}

	
	public BaseException(int code) {
		this.code = code;
	}
	/**
	 * @param message
	 */
	public BaseException(int code, String message) {
		
		super(message);
		this.code = code;
	}

	public BaseException(String message) {
		super(message);
	}
	
	/**
	 * @param cause
	 */
	public BaseException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Throwable#getCause()
	 */
	@Override
	public Throwable getCause() {
		return super.getCause();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
