package filipeProject.example.authenticationJwt.exceptions.handlers;


import filipeProject.example.authenticationJwt.dto.CustomErrorDTO;
import filipeProject.example.authenticationJwt.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<CustomErrorDTO> badCredentials(BadCredentialsException e, HttpServletRequest request) {
        var httpStatus = HttpStatus.UNAUTHORIZED;
        var customError = new CustomErrorDTO(Instant.now(),httpStatus.value(),e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(httpStatus).body(customError);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomErrorDTO> resourceNotFound(ResourceNotFoundException exception, HttpServletRequest request){
        var httpStatus = HttpStatus.NOT_FOUND;
        var customError = new CustomErrorDTO(Instant.now(),httpStatus.value(),
                exception.getMessage(),request.getRequestURI());
        return ResponseEntity.status(httpStatus).body(customError);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<CustomErrorDTO> conflictException(ConflictException exception, HttpServletRequest request){
        var httpStatus = HttpStatus.CONFLICT;
        var customError = new CustomErrorDTO(Instant.now(),httpStatus.value(),
                exception.getMessage(),request.getRequestURI());
        return ResponseEntity.status(httpStatus).body(customError);
    }



}
