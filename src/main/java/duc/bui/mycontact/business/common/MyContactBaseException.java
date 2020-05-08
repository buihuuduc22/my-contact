package duc.bui.mycontact.business.common;

import lombok.Data;

@Data
public class MyContactBaseException extends Exception {
	private ErrorInfo error;

	public MyContactBaseException(Throwable cause, ErrorInfo error) {
		super(cause);
		this.error = ErrorInfo.INTERNAL_SERVER_ERROR;
	}

	public MyContactBaseException(ErrorInfo error) {
		this.error = error;
	}

	@Override
	public String getMessage() {
		if (error != null) return error.getMessage();
		return super.getMessage();
	}
}
