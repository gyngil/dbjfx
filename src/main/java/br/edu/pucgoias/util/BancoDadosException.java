package br.edu.pucgoias.util;

/**
 * Classe que encapsula as excecoes da aplicacao de Banco de Dados com JavaFX
 * @author Gilcimar
 *
 */
public class BancoDadosException extends Exception {
	
	private static final long serialVersionUID = 1189188521388183949L;
	private Exception ex;
	private String msg;

	public BancoDadosException(Exception e){
		ex = e;
		msg = e.getMessage();
	}

	public BancoDadosException(Exception e, String mensagem){
		e.printStackTrace();
		ex = e;
		msg = mensagem;
	}

	public Exception getEx() {
		return ex;
	}

	public String getMsg() {
		return msg;
	}
	
}
