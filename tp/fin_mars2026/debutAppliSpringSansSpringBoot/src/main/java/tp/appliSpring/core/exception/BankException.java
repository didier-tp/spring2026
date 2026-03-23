package tp.appliSpring.core.exception;

public class BankException extends RuntimeException {

	public BankException() {
	}

	public BankException(String message) {
		super(message);
	}

	public BankException(Throwable cause) {
		super(cause);
	}

	public BankException(String message, Throwable cause) {
		super(message, cause);
	}


}
