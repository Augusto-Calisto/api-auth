package br.com.unip.validacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErroValidacaoHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public List<ErroFormulario> handle(MethodArgumentNotValidException erro) {
		List<ErroFormulario> erros = new ArrayList<ErroFormulario>();
		
		List<FieldError> fieldErrors = erro.getBindingResult().getFieldErrors();
		
		// e = objeto da lista de FieldError
		fieldErrors.forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			
			ErroFormulario err = new ErroFormulario(e.getField(), mensagem);
			
			erros.add(err);
		});
		
		return erros;
	}
}
