package br.com.gcbrandao.finance.domain.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Setter
@Getter
public class ErrorDetails implements Serializable {

    private static final long serialVersionUID = 1681677048470934868L;

    HttpStatus status;
    String message;
    private List<String> errors;

    public ErrorDetails(final HttpStatus status, final String message, final List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ErrorDetails(final HttpStatus status, final String message, final String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }
}