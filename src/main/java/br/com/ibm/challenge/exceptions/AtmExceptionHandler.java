package br.com.ibm.challenge.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import br.com.ibm.challenge.model.response.ErrorMessage;

@ControllerAdvice
public class AtmExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = AtmException.class)
    public ResponseEntity<Object> handleSaqueException(Exception exception, WebRequest request) {

        String errorMessageDescription = exception.getLocalizedMessage();

        if(errorMessageDescription == null) errorMessageDescription = exception.toString();

        ErrorMessage errorMessage = new ErrorMessage(errorMessageDescription);

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
