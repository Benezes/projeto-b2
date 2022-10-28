package br.com.gabriel.projetob2.controller.exception;

import br.com.gabriel.projetob2.infra.exception.CallFailedException;
import br.com.gabriel.projetob2.infra.exception.JsonFormatException;
import br.com.gabriel.projetob2.service.exception.InstallmentsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class HandlerController {

    @ExceptionHandler(InstallmentsException.class)
    public ResponseEntity<StandardError> parcelamentoError(InstallmentsException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError();

        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Parcelamento error");
        err.setPath(request.getRequestURI());
        err.setMessage(e.getMessage());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(CallFailedException.class)
    public ResponseEntity<StandardError> apiError(CallFailedException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError();

        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("API - gov error");
        err.setPath(request.getRequestURI());
        err.setMessage(e.getMessage());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(JsonFormatException.class)
    public ResponseEntity<StandardError> jsonParserError(JsonFormatException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError();

        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Json parse error");
        err.setPath(request.getRequestURI());
        err.setMessage(e.getMessage());

        return ResponseEntity.status(status).body(err);
    }

}

