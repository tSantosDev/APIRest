package handlers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dtos.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ControllerExceptionHandler {

	/**
	 * Manipula a exceção {@link DataIntegrityViolationException} e retorna uma
	 * resposta HTTP 400 Bad Request com um objeto {@link ExceptionDTO} contendo uma
	 * mensagem de erro personalizada.
	 *
	 * @param exception A exceção {@link DataIntegrityViolationException} lançada.
	 * @return Um ResponseEntity contendo um objeto {@link ExceptionDTO} e um código
	 *         de status HTTP 400.
	 */
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ExceptionDTO> dataIntegrityViolationException(DataIntegrityViolationException exception) {
		ExceptionDTO exceptionDTO = new ExceptionDTO("Usuário já cadastrado!", "400");
		return ResponseEntity.badRequest().body(exceptionDTO);
	}

	/**
	 * Manipula a exceção {@link EntityNotFoundException} e retorna uma resposta
	 * HTTP 404 Not Found com um objeto {@link ExceptionDTO} contendo uma mensagem
	 * de erro personalizada.
	 *
	 * @param exception A exceção {@link EntityNotFoundException} lançada.
	 * @return Um ResponseEntity contendo um objeto {@link ExceptionDTO} e um código
	 *         de status HTTP 404.
	 */
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ExceptionDTO> entityNotFoundException(EntityNotFoundException exception) {
		ExceptionDTO exceptionDTO = new ExceptionDTO("Usuário não encontrado!", "404");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTO);
	}

	/**
	 * Manipula a exceção {@link EmptyResultDataAccessException} e retorna uma
	 * resposta HTTP 404 Not Found com um objeto {@link ExceptionDTO} contendo uma
	 * mensagem de erro personalizada.
	 *
	 * @param exception A exceção {@link EmptyResultDataAccessException} lançada.
	 * @return Um ResponseEntity contendo um objeto {@link ExceptionDTO} e um código
	 *         de status HTTP 404.
	 */
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<ExceptionDTO> emptyResultDataAccessException(EmptyResultDataAccessException exception) {
		ExceptionDTO exceptionDTO = new ExceptionDTO("Usuário não encontrado!", "404");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTO);
	}

	/**
	 * Manipula exceções genéricas {@link Exception} e retorna uma resposta HTTP 500
	 * Internal Server Error com um objeto {@link ExceptionDTO} contendo a mensagem
	 * de erro da exceção.
	 *
	 * @param exception A exceção genérica {@link Exception} lançada.
	 * @return Um ResponseEntity contendo um objeto {@link ExceptionDTO} e um código
	 *         de status HTTP 500.
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionDTO> generalException(Exception exception) {
		ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), "500");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionDTO);
	}

}
