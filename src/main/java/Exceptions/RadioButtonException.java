package Exceptions;

public class RadioButtonException extends GenericUIException{

	private static final long serialVersionUID = 1L;
	
	public RadioButtonException(String message) {
		super(message);
	}
	
	public RadioButtonException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public RadioButtonException(Throwable cause) {
		super(cause);
	}
}
