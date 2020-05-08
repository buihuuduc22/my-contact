package duc.bui.mycontact.business.common;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

@Data
public class ErrorInfo {
	private static final Logger logger = LoggerFactory.getLogger(ErrorInfo.class);
	public static Properties properties;

	static {
		try {
			properties = new Properties();
			InputStream is = ErrorInfo.class.getClassLoader().getResourceAsStream("error_info.properties");
			System.out.println(">>>>>>>>" + is);
			properties.load(is);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	private int code;
	private String message;

	public ErrorInfo(int code, String message) {
		this.code = code;
		this.message = message;
	}
	private static int UNKNOWN_ERROR_CODE = 1000;

	public static final ErrorInfo INTERNAL_SERVER_ERROR = new ErrorInfo(UNKNOWN_ERROR_CODE, properties.getProperty("internal.server.error"));

	public static final ErrorInfo CONTACT_EXISTED_ERROR = new ErrorInfo(1001, properties.getProperty("contact.existed.error"));

	public static final ErrorInfo BAD_REQUEST_ERROR = new ErrorInfo(1002, properties.getProperty("bad.request.error"));

	public static final ErrorInfo CONTACT_NOT_FOUND_ERROR = new ErrorInfo(1003, properties.getProperty("contact.not.found.error"));
}
