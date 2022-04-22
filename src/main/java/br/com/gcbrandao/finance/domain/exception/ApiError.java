package br.com.gcbrandao.finance.domain.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiError implements Serializable {

    private static final long serialVersionUID = 6430733760654466807L;

    @JsonProperty("message")
    private String message;

    @JsonInclude(Include.NON_NULL)
    private List<String> errors;

    public ApiError(final String message) {
        this.message = message;
    }
}
