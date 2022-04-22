package br.com.sulamerica.laboratorioapi.exceptionhandler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

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

import com.google.cloud.datastore.DatastoreException;

import br.com.sulamerica.laboratorioapi.exceptionhandler.exceptions.AgendamentoExameException;
import br.com.sulamerica.laboratorioapi.exceptionhandler.exceptions.BadRequestException;
import br.com.sulamerica.laboratorioapi.exceptionhandler.exceptions.ConsentimentoException;
import br.com.sulamerica.laboratorioapi.exceptionhandler.exceptions.ContratoException;
import br.com.sulamerica.laboratorioapi.exceptionhandler.exceptions.ConverterResponseException;
import br.com.sulamerica.laboratorioapi.exceptionhandler.exceptions.DataStoreEntityNotFoundException;
import br.com.sulamerica.laboratorioapi.exceptionhandler.exceptions.DataStoreException;
import br.com.sulamerica.laboratorioapi.exceptionhandler.exceptions.EncaminhamentoEmailException;
import br.com.sulamerica.laboratorioapi.exceptionhandler.exceptions.ExchangeException;
import br.com.sulamerica.laboratorioapi.exceptionhandler.exceptions.FhirException;
import br.com.sulamerica.laboratorioapi.exceptionhandler.exceptions.FileException;
import br.com.sulamerica.laboratorioapi.exceptionhandler.exceptions.FleuryAuthenticationException;
import br.com.sulamerica.laboratorioapi.exceptionhandler.exceptions.FleuryException;
import br.com.sulamerica.laboratorioapi.exceptionhandler.exceptions.InternalServerErrorException;
import br.com.sulamerica.laboratorioapi.exceptionhandler.exceptions.NotFoundException;
import br.com.sulamerica.laboratorioapi.exceptionhandler.exceptions.NotFoundVppException;
import br.com.sulamerica.laboratorioapi.exceptionhandler.exceptions.NotificationException;
import br.com.sulamerica.laboratorioapi.exceptionhandler.exceptions.PrestadorNotFoundException;
import br.com.sulamerica.laboratorioapi.exceptionhandler.exceptions.SalesforceErroIntegrationException;
import br.com.sulamerica.laboratorioapi.exceptionhandler.exceptions.SalesforceException;
import br.com.sulamerica.laboratorioapi.exceptionhandler.exceptions.TechnicalException;

@ControllerAdvice
@RestController
public class ExceptionHandler extends ResponseEntityExceptionHandler {
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ex.getBindingResult().getFieldErrors().get(0).getField();
		ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();

		String errorMessage = ex.getBindingResult().getFieldErrors().stream()
				.map(fieldError -> fieldError.getField() + ":" + fieldError.getDefaultMessage()).findFirst()
				.orElse(Strings.EMPTY);

		return response(ex, request, headers, HttpStatus.BAD_REQUEST, errorMessage);
	}

	private ResponseEntity<Object> response(Exception ex, WebRequest request, HttpHeaders headers, HttpStatus status,
			String message) {
		return handleExceptionInternal(ex, message, headers, status, request);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@org.springframework.web.bind.annotation.ExceptionHandler(IOException.class)
	@ResponseBody
	ApiError handleBadRequest(HttpServletRequest req, Exception ex) {
		return new ApiError(ex.getMessage());
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@org.springframework.web.bind.annotation.ExceptionHandler(IllegalAccessException.class)
	@ResponseBody
	ApiError handleAcessError(HttpServletRequest req, Exception ex) {
		return new ApiError(ex.getMessage());
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@org.springframework.web.bind.annotation.ExceptionHandler(ExchangeException.class)
	@ResponseBody
	ApiError handleErrorField(HttpServletRequest req, Exception ex) {
		return new ApiError(ex.getMessage());
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@org.springframework.web.bind.annotation.ExceptionHandler(TechnicalException.class)
	@ResponseBody
	ApiError handleErrorTechnicalException(HttpServletRequest req, Exception ex) {
		return new ApiError(ex.getMessage().concat(". Connection refused"));
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@org.springframework.web.bind.annotation.ExceptionHandler(SalesforceException.class)
	@ResponseBody
	ApiError handleErrorSalesforce(HttpServletRequest req, Exception ex) {
		return new ApiError(ex.getMessage());
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@org.springframework.web.bind.annotation.ExceptionHandler(DatastoreException.class)
	@ResponseBody
	ApiError handleDatastoreException(HttpServletRequest req, Exception ex) {
		return new ApiError(ex.getMessage());
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@org.springframework.web.bind.annotation.ExceptionHandler(DataStoreEntityNotFoundException.class)
	@ResponseBody
	ApiError handleDataStoreEntityNotFoundException(HttpServletRequest req, Exception ex) {
		return new ApiError(ex.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
	@ResponseBody
	ApiError handleConstraintViolationException(HttpServletRequest req, Exception ex) {
		return new ApiError(ex.getMessage());
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@org.springframework.web.bind.annotation.ExceptionHandler(FleuryAuthenticationException.class)
	@ResponseBody
	ApiError handleFleuryAuthenticationException(HttpServletRequest req, Exception ex) {
		return new ApiError(ex.getMessage());
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@org.springframework.web.bind.annotation.ExceptionHandler(FleuryException.class)
	@ResponseBody
	ApiError handleFleuryException(HttpServletRequest req, Exception ex) {
		return new ApiError(ex.getMessage());
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@org.springframework.web.bind.annotation.ExceptionHandler(ContratoException.class)
	@ResponseBody
	ApiError handleErrorContrato(HttpServletRequest req, Exception ex) {
		return new ApiError(ex.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@org.springframework.web.bind.annotation.ExceptionHandler(ConsentimentoException.class)
	@ResponseBody
	ApiError handleErrorConsentimento(HttpServletRequest req, Exception ex) {
		return new ApiError(ex.getMessage());
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@org.springframework.web.bind.annotation.ExceptionHandler(FhirException.class)
	@ResponseBody
	ApiError handleErrorFhirRequesterError(HttpServletRequest req, FhirException ex) {
		return new ApiError(ex.getMessage());
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@org.springframework.web.bind.annotation.ExceptionHandler(NotificationException.class)
	@ResponseBody
	ApiError handleErrorNotification(HttpServletRequest req, NotificationException ex) {
		return new ApiError(ex.getMessage());
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@org.springframework.web.bind.annotation.ExceptionHandler(DataStoreException.class)
	@ResponseBody
	ApiError handleErrorDatastore(HttpServletRequest req, DataStoreException ex) {
		return new ApiError(ex.getMessage());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@org.springframework.web.bind.annotation.ExceptionHandler(SalesforceErroIntegrationException.class)
	@ResponseBody
	ApiError handleErrorSalesforceError(HttpServletRequest req, SalesforceErroIntegrationException ex) {
		return new ApiError(ex.getMessage());
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@org.springframework.web.bind.annotation.ExceptionHandler(PrestadorNotFoundException.class)
	@ResponseBody
	ApiError handlePrestadorNotFound(HttpServletRequest req, Exception ex) {
		return new ApiError(ex.getMessage());
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@org.springframework.web.bind.annotation.ExceptionHandler(NotFoundVppException.class)
	@ResponseBody
	ApiError handlePrestadorNotFoundVpp(HttpServletRequest req, NotFoundVppException ex) {
		return new ApiError(ex.getMessage(), ex.getErros());
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
	@ResponseBody
	ApiError handlePrestadorNotFound(HttpServletRequest req, NotFoundException ex) {
		return new ApiError(ex.getMessage());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@org.springframework.web.bind.annotation.ExceptionHandler(BadRequestException.class)
	@ResponseBody
	ApiError handleBadRequestException(HttpServletRequest req, BadRequestException ex) {
		return new ApiError(ex.getMessage());
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@org.springframework.web.bind.annotation.ExceptionHandler(ConverterResponseException.class)
	@ResponseBody
	ApiError handleConverterResponse(HttpServletRequest req, Exception ex) {
		return new ApiError(ex.getMessage());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@org.springframework.web.bind.annotation.ExceptionHandler(EncaminhamentoEmailException.class)
	@ResponseBody
	ApiError handleEncaminhamentoEmailResponse(HttpServletRequest req, Exception ex) {
		return new ApiError(ex.getMessage());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@org.springframework.web.bind.annotation.ExceptionHandler(InternalServerErrorException.class)
	@ResponseBody
	ApiError handleInternalError(HttpServletRequest req, Exception ex) {
		return new ApiError(ex.getMessage());
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@org.springframework.web.bind.annotation.ExceptionHandler(FileException.class)
	@ResponseBody
	ApiError handleErrorFileExceptio(HttpServletRequest req, FileException ex) {
		return new ApiError(ex.getMessage());
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@org.springframework.web.bind.annotation.ExceptionHandler(AgendamentoExameException.class)
	@ResponseBody
	ApiError handleErrorAgendamentoExame(HttpServletRequest req, Exception ex) {
		return new ApiError(ex.getMessage());
	}

}
