package br.com.adatech.moviesbattle.adapter.in.http.controller;

import javax.validation.UnexpectedTypeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.adatech.moviesbattle.adapter.in.http.controller.model.response.RetornoModelResponse;
import br.com.adatech.moviesbattle.adapter.out.database.exception.ExceptionDataProvider;
import br.com.adatech.moviesbattle.application.exception.ServiceException;

@RestControllerAdvice
public class QuizControllerAdvice extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler({ ExceptionDataProvider.class, ServiceException.class })
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public @ResponseBody RetornoModelResponse excecoes(Exception ex, WebRequest request) {

		return new RetornoModelResponse(ex.getMessage());
	}

	@ExceptionHandler({ MissingRequestHeaderException.class })
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public @ResponseBody RetornoModelResponse missingRequestHeaderException(Exception ex, WebRequest request) {

		return new RetornoModelResponse("Obrigatório o preenchimento do usuário e senha.");
	}

	@ExceptionHandler({ MethodArgumentTypeMismatchException.class, UnexpectedTypeException.class })
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public @ResponseBody RetornoModelResponse methodArgumentTypeMismatchException(Exception ex, WebRequest request) {
		
		return new RetornoModelResponse(ex.getMessage());
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		RetornoModelResponse retorno = ex.getBindingResult().getAllErrors().stream().map(objectError -> {
			String mensagem = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
			String campo = null;

			if (objectError instanceof FieldError) {
				FieldError fieldError = ((FieldError) objectError);
				campo = fieldError.getField();
			}

			return new RetornoModelResponse("Valor do campo ".concat(campo).concat(" inválido. ").concat(mensagem));
		}).findFirst().get();

		return handleExceptionInternal(ex, retorno, headers, HttpStatus.BAD_REQUEST, request);
	}
}
