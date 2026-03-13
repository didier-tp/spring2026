package tp.appliSpring.generic.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.validation.FieldError;
import tp.appliSpring.generic.exception.EntityNotFoundException;
import tp.appliSpring.generic.dto.ApiError;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestResponseEntityExceptionHandler 
   extends ResponseEntityExceptionHandler {
	
	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	@Override //if @Valid error
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		//return super.handleMethodArgumentNotValid(ex, headers, status, request);
		List<String> errors = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map( (FieldError fe) ->  fe.getDefaultMessage())
				.collect(Collectors.toList());
		String errorMsg = "not @Valid argument : " + errors.toString();
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, errorMsg));
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		String error = "Malformed JSON request";
		return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error));
	}



	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
		return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, ex.getMessage()));
	}
	
	/*
	@ExceptionHandler(ConflictException.class)
	protected ResponseEntity<Object> handleConflict(ConflictException ex) {
		return buildResponseEntity(new ApiError(HttpStatus.CONFLICT, ex.getMessage()));
	}*/

	//AuthorizationDeniedException (since spring 6.3) is a subclass of AccessDeniedException
    //need specific exception handler in security config


}
