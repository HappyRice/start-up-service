package startup.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import startup.dto.ErrorResponse;
import startup.exception.GameNotFoundException;
import startup.exception.HandNotFoundException;
import startup.exception.InvalidHandStateException;

@RestControllerAdvice
public class ErrorHandlingControllerAdvice {

	private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandlingControllerAdvice.class);

	@ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody public ErrorResponse onMethodArgumentNotValidException(final Exception e) {
		final BindingResult bindingResult;

		if (e instanceof MethodArgumentNotValidException) {
			bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
		} else {
			bindingResult = ((BindException) e).getBindingResult();
		}

		final StringBuilder stringBuilder = new StringBuilder();

		for (final FieldError fieldError : bindingResult.getFieldErrors()) {
			stringBuilder.append(fieldError.getDefaultMessage()).append(";");
		}

		return ErrorResponse.builder().withMessage(stringBuilder.toString()).build();
	}

	@ExceptionHandler(GameNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody public ErrorResponse onGameNotFoundException() {
		return ErrorResponse.builder().withMessage("Game was not found").build();
	}

	@ExceptionHandler(HandNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody public ErrorResponse onHandNotFoundException() {
		return ErrorResponse.builder().withMessage("Hand was not found").build();
	}

	@ExceptionHandler(InvalidHandStateException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody public ErrorResponse onInvalidHandStateTransitionException() {
		return ErrorResponse.builder().withMessage("Hand is not in the right state").build();
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody public ErrorResponse onMissingServletRequestParameterException(final MissingServletRequestParameterException e) {
		return ErrorResponse.builder().withMessage("Missing request parameter: " + e.getParameterName()).build();
	}

	@ExceptionHandler(HttpMediaTypeException.class)
	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	@ResponseBody public ErrorResponse onHttpMediaTypeException(final HttpMediaTypeException e) {
		return ErrorResponse.builder().withMessage("Unsupported media type").build();
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody public ErrorResponse onHttpMessageNotReadableException() {
		return ErrorResponse.builder().withMessage("Invalid payload").build();
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody public ErrorResponse onAllOtherException(final Exception e) {
		LOGGER.error(e.getMessage(), e);

		return ErrorResponse.builder().withMessage("Internal server error").build();
	}

}
