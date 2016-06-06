/**
 * 
 */
package com.letv.quartz.container;

/**
 * @author bjzhongdegen
 *
 */
public class JobAndTriggerExisting extends BaseException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int code = 20704;
	/**
	 * 
	 */
	public JobAndTriggerExisting() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param code
	 */
	public JobAndTriggerExisting(int code) {
		super(code);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param code
	 * @param message
	 */
	public JobAndTriggerExisting(int code, String message) {
		super(code, message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public JobAndTriggerExisting(String message) {
		super(code, message);
	}

	/**
	 * @param cause
	 */
	public JobAndTriggerExisting(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public JobAndTriggerExisting(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
