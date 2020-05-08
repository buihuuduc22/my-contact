package duc.bui.mycontact.db.dto;

import duc.bui.mycontact.business.common.ErrorInfo;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

@Data
public class ResponseObject<T> {
	private static final Logger logger = LoggerFactory.getLogger(ResponseObject.class);

	private static Properties properties;

	private ErrorInfo error;
	private T responseData;
	private String successMessage;
	private String warningMessage;
}
