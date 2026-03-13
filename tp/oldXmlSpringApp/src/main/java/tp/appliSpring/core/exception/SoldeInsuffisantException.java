package tp.appliSpring.core.exception;

public class SoldeInsuffisantException extends RuntimeException {

	public SoldeInsuffisantException() {
	}

	public SoldeInsuffisantException(String message) {
		super(message);
	}

	public SoldeInsuffisantException(Throwable cause) {
		super(cause);
	}

	public SoldeInsuffisantException(String message, Throwable cause) {
		super(message, cause);
	}

	public SoldeInsuffisantException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
