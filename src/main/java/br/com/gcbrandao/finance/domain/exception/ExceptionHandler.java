package br.com.gcbrandao.finance.domain.exception;

import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.io.IOException;

@ControllerAdvice
@RestController
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                                                  final HttpHeaders headers, final HttpStatus status,
                                                                  final WebRequest request) {

        ex.getBindingResult().getFieldErrors().get(0).getField();
        ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();

        final String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ":" + fieldError.getDefaultMessage()).findFirst()
                .orElse(Strings.EMPTY);

        return response(ex, request, headers, HttpStatus.BAD_REQUEST, errorMessage);
    }

    private ResponseEntity<Object> response(final Exception ex, final WebRequest request, final HttpHeaders headers,
                                            final HttpStatus status,
                                            final String message) {
        return handleExceptionInternal(ex, message, headers, status, request);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(IOException.class)
    @ResponseBody
    ApiError handleBadRequest(final HttpServletRequest req, final Exception ex) {
        return new ApiError(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalAccessException.class)
    @ResponseBody
    ApiError handleAcessError(final HttpServletRequest req, final Exception ex) {
        return new ApiError(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    ApiError handleConstraintViolationException(final HttpServletRequest req, final Exception ex) {
        return new ApiError(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    @ResponseBody
    ApiError handleNotFoundException(final HttpServletRequest req, final Exception ex) {
        return new ApiError(ex.getMessage());
    }


}
