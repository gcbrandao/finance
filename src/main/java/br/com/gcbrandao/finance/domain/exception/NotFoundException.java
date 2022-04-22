package br.com.sulamerica.laboratorioapi.exceptionhandler.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 226174565240769599L;

    private String message;

}
