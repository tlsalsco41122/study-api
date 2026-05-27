package hs.dgsw.study_api.exception;

import hs.dgsw.study_api.dto.res.ErrorRes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorRes> handleIllegalArgument(IllegalArgumentException e) {
        return toResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity<ErrorRes> handleIllegalAccess(IllegalAccessException e) {
        return toResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorRes> handleUsernameNotFound(UsernameNotFoundException e) {
        return toResponse(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorRes> handleAuthentication(AuthenticationException e) {
        return toResponse(HttpStatus.UNAUTHORIZED, e.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorRes> handleAccessDenied(AccessDeniedException e) {
        return toResponse(HttpStatus.FORBIDDEN, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorRes> handleException(Exception e) {
        return toResponse(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류가 발생했습니다.");
    }

    private ResponseEntity<ErrorRes> toResponse(HttpStatus status, String message) {
        ErrorRes res = new ErrorRes(status.value(), message);
        return ResponseEntity.status(status).body(res);
    }
}

