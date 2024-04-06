//package startup.utility;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.common.base.CaseFormat;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.validation.BindException;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.HttpMediaTypeException;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//@RestControllerAdvice
//public class ErrorHandlingControllerAdvice {
//
//	private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandlingControllerAdvice.class);
//
//	@Autowired
//	private ObjectMapper objectMapper;
//
//	@ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ResponseBody ValidationErrorResponse onMethodArgumentNotValidException(final Exception e) {
//		final ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse.Builder().withErrors(new ArrayList<>()).withMessage("Validation Failed").build();
//
//		final BindingResult bindingResult;
//
//		if (e instanceof MethodArgumentNotValidException) {
//			bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
//		} else {
//			bindingResult = ((BindException) e).getBindingResult();
//		}
//
//		for (final FieldError fieldError : bindingResult.getFieldErrors()) {
//			final String errorCode = fieldError.getCode();
//			ValidationErrorType validationErrorType = ValidationErrorType.UNKNOWN;
//			if (errorCode != null) {
//				try {
//					validationErrorType = ValidationErrorType.valueOf(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, errorCode));
//				} catch (final IllegalArgumentException illegalArgumentException) {
//					LOGGER.warn("No associated error code found for [{}]", errorCode);
//				}
//			}
//
//			final List<ValidationError> validationErrors = validationErrorResponse.getErrors();
//
//			validationErrors.add(
//					new ValidationError.Builder()
//							.withCode(validationErrorType.getCode())
//							.withFieldName(fieldError.getField())
//							.withMessage(fieldError.getDefaultMessage())
//							.build()
//			);
//
//			Collections.sort(validationErrors);
//		}
//
//		return validationErrorResponse;
//	}
//
//	@ExceptionHandler(JsonProcessingException.class)
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ResponseBody public ValidationErrorResponse onJsonProcessingException() {
//		// Could expose e.getOriginalMessage()
//		return new ValidationErrorResponse.Builder().withMessage("Invalid JSON").build();
//	}
//
//	@ExceptionHandler(HttpMediaTypeException.class)
//	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
//	@ResponseBody public ValidationErrorResponse onHttpMediaTypeException(final HttpMediaTypeException e) {
//		return new ValidationErrorResponse.Builder().withMessage(e.getMessage()).build();
//	}
//
//	@ExceptionHandler(UserException.class)
//	@ResponseBody public ValidationErrorResponse onUserException(final HttpServletResponse res, final UserException e) {
//		LOGGER.error("Caught User Exception", e);
//		return this.handleDownstreamServiceException(res, e.getResponseCode(), e);
//	}
//
//	private ValidationErrorResponse handleDownstreamServiceException(final HttpServletResponse res, final Integer code, final Exception e) {
//		// When down steam server error occurs we respond with a more appropriate error message to the client
//		res.setStatus(code);
//		final String message = code >= HttpStatus.INTERNAL_SERVER_ERROR.value() ? "An internal server error occurred." : e.getMessage();
//
//		try {
//			return this.objectMapper.readValue(message, ValidationErrorResponse.class);
//		} catch (final IOException ioException) {
//			return new ValidationErrorResponse.Builder().withMessage(message).build();
//		}
//	}
//}
