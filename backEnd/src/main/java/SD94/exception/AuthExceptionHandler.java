package SD94.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AuthExceptionHandler {
//    @ExceptionHandler(UsernameNotFoundException.class)
//    public ResponseEntity<Object> handleUsernameNotFoundException(
//            UsernameNotFoundException ex, WebRequest request) {
//
//        Map<String, Object> responseBody = new HashMap<>();
//        responseBody.put("timestamp", new Date());
//        responseBody.put("status", HttpStatus.UNAUTHORIZED.value());
//        responseBody.put("error", "Unauthorized");
//        responseBody.put("message", "Invalid username or password");
//        responseBody.put("path", request.getDescription(false));
//
//        return new ResponseEntity<>(responseBody, HttpStatus.UNAUTHORIZED);
//    }


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(
            BadCredentialsException ex) {

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("timestamp", new Date());
        responseBody.put("status", HttpStatus.UNAUTHORIZED.value());
        responseBody.put("error", "Unauthorized");
        responseBody.put("message", "Invalid username or password");

        return new ResponseEntity<>(responseBody, HttpStatus.UNAUTHORIZED);
    }

}
