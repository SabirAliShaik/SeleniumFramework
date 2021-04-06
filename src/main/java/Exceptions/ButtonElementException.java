package Exceptions;

public class ButtonElementException extends GenericUIException {

	private static final long serialVersionUID = 1L;
	
	public ButtonElementException(String message) {
		super(message);
	}
	public ButtonElementException(String message, Throwable cause) {
		super(message, cause);
	}
	public ButtonElementException(Throwable cause) {
		super(cause);
	}
}
