package Exceptions;

public class TextBoxElementException extends GenericUIException{

	private static final long serialVersionUID = 1L;

	public TextBoxElementException(String message) {
		super(message);
	}
	public TextBoxElementException(String message, Throwable cause) {
		super(message, cause);
	}
	public TextBoxElementException(Throwable cause) {
		super(cause);
	}


}
