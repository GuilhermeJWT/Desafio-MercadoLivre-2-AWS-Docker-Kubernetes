package br.com.systemsgs.mercadolivre.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionHandlerControllerAdvice {
	
	@ExceptionHandler(GenerationExceptionClass.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public RestApiErrorsException tratamentoException(GenerationExceptionClass generationExceptionClass) {
		String mensagemErro = generationExceptionClass.getLocalizedMessage();
		return new RestApiErrorsException(mensagemErro);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public RestApiErrorsException exibeErrosParaCliente(MethodArgumentNotValidException methodArgumentNotValidException) {
		List<String> errors = methodArgumentNotValidException.getBindingResult().getAllErrors().stream()
				.map(erro -> erro.getDefaultMessage()).collect(Collectors.toList());

		return new RestApiErrorsException(errors);

	}
	
	@ExceptionHandler(RecursoNaoEncontradoException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public RestApiErrorsException recursoNaoEncontradoException(RecursoNaoEncontradoException recursoNaoEncontrado) {
		return new RestApiErrorsException(recursoNaoEncontrado.getMessage());
	}
	
	@ExceptionHandler(InvalidJwtAuthenticationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public RestApiErrorsException invalidAuthenticationException(InvalidJwtAuthenticationException invalidJwtAuthenticationException) {
		return new RestApiErrorsException(invalidJwtAuthenticationException.getMessage());
	}
	
	/*
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<RestApiErrorsException> lancaExceptionAll(Exception ex, WebRequest request){
		RestApiErrorsException restApiErrosException = new RestApiErrorsException(ex.getMessage());
		return new ResponseEntity<>(restApiErrosException, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	*/
	
}
