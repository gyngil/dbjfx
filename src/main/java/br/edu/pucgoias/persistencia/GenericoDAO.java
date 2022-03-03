package br.edu.pucgoias.persistencia;

import java.io.Serializable;
import java.util.List;

import br.edu.pucgoias.util.BancoDadosException;

/**
 * Interface que define as operacoes da camada de persistencia generica
 * @author Gilcimar
 *
 */
public interface GenericoDAO<T, ID extends Serializable> {
	
	/**
	 * Retorna a classe a ser persistida
	 * @return
	 */
	public Class<T> getObjectClass();
	
	/**
	 * Obtem a transacao corrente, caso exista
	 * @return
	 */
	public TransacaoBD getTransacao();

	/**
	 * Seta a transacao corrente
	 * @return
	 */
	public void setTransacao(TransacaoBD transacao);

	/**
	 * Inclui um objeto T na base de dados
	 * @param object
	 * @return
	 * @throws BancoDadosException
	 */
	public T incluir(T object) throws BancoDadosException;
	
	/**
	 * Altera um objeto T na base de dados
	 * @param object
	 * @return
	 * @throws BancoDadosException
	 */
	public T alterar(T object) throws BancoDadosException;
	
	/**
	 * Consulta um objeto T da base de dados
	 * @param id
	 * @return
	 * @throws BancoDadosException
	 */
	public T consultar(Integer id) throws BancoDadosException;
	
	/**
	 * Exclui um objeto T  da base de dados
	 * @param id
	 * @throws BancoDadosException
	 */
	public void excluir(Integer id) throws BancoDadosException;
	
	/**
	 * Lista os objetos T da base de dados
	 * @return
	 * @throws BancoDadosException
	 */
	public List<T> listar() throws BancoDadosException;
}
