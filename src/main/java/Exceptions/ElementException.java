package Exceptions;

public class ElementException extends GenericUIException{

	public ElementException(String message) {
		super(message);
	}
	
	public ElementException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ElementException(Throwable cause) {
		super(cause);
	}
}
