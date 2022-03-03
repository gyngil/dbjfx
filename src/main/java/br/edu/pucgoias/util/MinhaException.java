package br.edu.pucgoias.util;

/**
 * Classe que encapsula as excecoes da aplicacao do Projeto de Banco de Dados com JavaFX
 * @author Gilcimar
 *
 */
public class MinhaException extends Exception {

	private static final long serialVersionUID = -6748900454102863658L;
	private Exception ex;
	private String msg;

	public MinhaException(Exception e){
		ex = e;
		msg = e.getMessage();
	}

	public MinhaException(Exception e, String mensagem){
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
