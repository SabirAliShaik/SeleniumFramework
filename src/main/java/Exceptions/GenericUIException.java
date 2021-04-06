package Exceptions;

public class GenericUIException extends GenericException {

	public GenericUIException(String message) {
		super(message);
	}
	public GenericUIException(String message, Throwable cause) {
		super(message, cause);
	}
	public GenericUIException(Throwable cause) {
		super(cause);
	}
}
