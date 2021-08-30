package telran.java38.person.dto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UnknownPersonTypeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
