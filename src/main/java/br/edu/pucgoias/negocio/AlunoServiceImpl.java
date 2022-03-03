package br.edu.pucgoias.negocio;

import java.util.List;

import br.edu.pucgoias.entidade.Aluno;
import br.edu.pucgoias.persistencia.AlunoDAO;
import br.edu.pucgoias.persistencia.AlunoDAOImpl;
import br.edu.pucgoias.persistencia.TransacaoBD;
import br.edu.pucgoias.util.BancoDadosException;
import br.edu.pucgoias.util.MinhaException;

/**
 * Classe que define as operacoes da camada de negocio de Aluno
 * @author Gilcimar
 */
public class AlunoServiceImpl implements AlunoService {

	//Interface da persistencia
	private AlunoDAO alunoDAO;
	
	public AlunoDAO getAlunoDAO() {
		return alunoDAO;
	}

	public void setAlunoDAO(AlunoDAO alunoDAO) {
		this.alunoDAO = alunoDAO;
	}
	
	/**
	 * Inclui uma aluno
	 * @param aluno
	 * @return
	 * @throws MinhaException
	 */
	public Aluno incluir(Aluno aluno) throws MinhaException {
		try {
			
			//instancia a persistencia e prepara a transacao de negocio
			alunoDAO = new AlunoDAOImpl();
			getAlunoDAO().setTransacao(new TransacaoBD());

			//abre a transacao
			getAlunoDAO().getTransacao().abrirTransacao(false);
			
			//inclui o aluno
			getAlunoDAO().incluir(aluno);

			//fecha a transacao
			getAlunoDAO().getTransacao().fecharTransacao();
			
			return aluno;
		
		} catch (BancoDadosException e) {
			throw new MinhaException(e,e.getMsg());
		} catch (Exception e) {
			throw new MinhaException(e,e.getMessage());
		}
	}

	/**
	 * Altera uma aluno
	 * @param aluno
	 * @return
	 * @throws MinhaException
	 */
	public Aluno alterar(Aluno aluno) throws MinhaException {

		try {
			
			//instancia a persistencia e prepara a transacao de negocio
			alunoDAO = new AlunoDAOImpl();
			getAlunoDAO().setTransacao(new TransacaoBD());

			//abre a transacao
			getAlunoDAO().getTransacao().abrirTransacao(false);
			
			//altera o aluno
			getAlunoDAO().alterar(aluno);

			//fecha a transacao
			getAlunoDAO().getTransacao().fecharTransacao();
			
			return aluno;
		
		} catch (BancoDadosException e) {
			throw new MinhaException(e,e.getMsg());
		} catch (Exception e) {
			throw new MinhaException(e,e.getMessage());
		}
	}

	/**
	 * Exclui uma aluno
	 * @param aluno
	 * @throws MinhaException
	 */
	public void excluir(Integer id) throws MinhaException {
		
		try {
			
			//instancia a persistencia e prepara a transacao de negocio
			alunoDAO = new AlunoDAOImpl();
			getAlunoDAO().setTransacao(new TransacaoBD());

			//abre a transacao
			getAlunoDAO().getTransacao().abrirTransacao(false);
			
			//exclui o aluno
			getAlunoDAO().excluir(id);

			//fecha a transacao
			getAlunoDAO().getTransacao().fecharTransacao();
			
			
		} catch (BancoDadosException e) {
			throw new MinhaException(e,e.getMsg());
		} catch (Exception e) {
			throw new MinhaException(e,e.getMessage());
		}
	}

	/**
	 * Consulta uma aluno pelo identificador
	 * @param id
	 * @return
	 * @throws MinhaException
	 */
	public Aluno consultar(Integer id) throws MinhaException {

		Aluno aluno = null;
		try {
			
			//instancia a persistencia e prepara a transacao de negocio
			alunoDAO = new AlunoDAOImpl();
			getAlunoDAO().setTransacao(new TransacaoBD());

			//abre a transacao
			getAlunoDAO().getTransacao().abrirTransacao(true);
			
			//consulta o aluno
			aluno = getAlunoDAO().consultar(id);

			//fecha a transacao
			getAlunoDAO().getTransacao().fecharTransacao();
			
			return aluno;
		
		} catch (BancoDadosException e) {
			throw new MinhaException(e,e.getMsg());
		} catch (Exception e) {
			throw new MinhaException(e,e.getMessage());
		}
	}

	/**
	 * Lista todas as alunos cadastradas
	 * @return
	 * @throws MinhaException
	 */
	public List<Aluno> listar() throws MinhaException {
		List<Aluno> lista = null;
		try {
			
			//instancia a persistencia e prepara a transacao de negocio
			alunoDAO = new AlunoDAOImpl();
			getAlunoDAO().setTransacao(new TransacaoBD());

			//abre a transacao
			getAlunoDAO().getTransacao().abrirTransacao(true);
			
			//executa a listagem de alunos
			lista = getAlunoDAO().listar();

			//fecha a transacao
			getAlunoDAO().getTransacao().fecharTransacao();
			
			return lista;
		
		} catch (BancoDadosException e) {
			throw new MinhaException(e,e.getMsg());
		} catch (Exception e) {
			throw new MinhaException(e,e.getMessage());
		}
	}

}
