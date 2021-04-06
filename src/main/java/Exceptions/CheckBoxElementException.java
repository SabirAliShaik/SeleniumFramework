package Exceptions;

public class CheckBoxElementException extends GenericUIException{

	private static final long serialVersionUID = 1L;

	public CheckBoxElementException(String message) {
		super(message);
	}
	public CheckBoxElementException(String message, Throwable cause) {
		super(message, cause);
	}
	public CheckBoxElementException(Throwable cause) {
		super(cause);
	}
}
