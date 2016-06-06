/**
 * 
 */
package com.letv.quartz.container;


/**
 * @author bjzhongdegen
 *
 */
public class BuildCronTriggerException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int code = 20702;

	/**
	 * 
	 */
	public BuildCronTriggerException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param code
	 */
	public BuildCronTriggerException(int code) {
		super(code);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param code
	 * @param message
	 */
	public BuildCronTriggerException(int code, String message) {
		super(code, message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public BuildCronTriggerException(String message) {
		super(code, message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public BuildCronTriggerException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public BuildCronTriggerException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
