package br.edu.pucgoias.negocio;

import java.util.List;

import br.edu.pucgoias.entidade.Aluno;
import br.edu.pucgoias.util.MinhaException;

/**
 * Interface que define as operacoes da camada de negocio de Aluno
 * @author Gilcimar
 *
 */
public interface AlunoService {
	
	/**
	 * Inclui uma aluno
	 * @param aluno
	 * @return
	 * @throws MinhaException
	 */
	public Aluno incluir(Aluno aluno) throws MinhaException;
	
	/**
	 * Altera uma aluno
	 * @param aluno
	 * @return
	 * @throws MinhaException
	 */
	public Aluno alterar(Aluno aluno) throws MinhaException;
	
	/**
	 * Exclui uma aluno
	 * @param id
	 * @throws MinhaException
	 */
	public void excluir(Integer id) throws MinhaException;
	
	/**
	 * Consulta uma aluno pelo identificador
	 * @param id
	 * @return
	 * @throws MinhaException
	 */
	public Aluno consultar(Integer id) throws MinhaException;
	
	/**
	 * Lista todas as alunos cadastradas
	 * @return
	 * @throws MinhaException
	 */
	public List<Aluno> listar() throws MinhaException;

}
