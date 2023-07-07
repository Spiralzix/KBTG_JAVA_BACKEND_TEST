package tech.kbtg.springboothomework.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tech.kbtg.springboothomework.Entity.Exception.UserNotFound;
import tech.kbtg.springboothomework.Entity.Response.SuccessfullyResponse;

@ControllerAdvice
public class ExceptionalHandler {
    @ExceptionHandler
    public ResponseEntity<SuccessfullyResponse> handleException(UserNotFound exc) {
        System.out.println(exc);
        SuccessfullyResponse error = new SuccessfullyResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
