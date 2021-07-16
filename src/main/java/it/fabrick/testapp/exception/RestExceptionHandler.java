package it.fabrick.testapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler 
{
	@ExceptionHandler(BonificoNotFoundException.class)
	public ResponseEntity<ErrorResponse> PromoNotFoundHandler(Exception ex)
	{
		ErrorResponse errore = new ErrorResponse();
		
		errore.setCodice(HttpStatus.NOT_FOUND.value());
		errore.setMessaggio(ex.getMessage());
		
		return new ResponseEntity<ErrorResponse>(errore, HttpStatus.NOT_FOUND);
	}
}
