package br.com.systemsgs.mercadolivre.exception;

public class RecursoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RecursoNaoEncontradoException() {
		super("Recurso (ID) não Encontrado!!!");
	}

}
