package br.edu.pucgoias.persistencia;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.pucgoias.util.BancoDadosException;
import br.edu.pucgoias.util.Coluna;
import br.edu.pucgoias.util.Tabela;

/**
 * Classe que define as operacoes da camada de persistencia generica
 * @author Gilcimar
 *
 */
public class GenericoDAOImpl<T, ID extends Serializable> implements GenericoDAO<T, ID> {

	private final Class<T> oClass;
	protected TransacaoBD transacao;


	public TransacaoBD getTransacao() {
		return transacao;
	}

	public void setTransacao(TransacaoBD transacao) {
		this.transacao = transacao;
	}

	//Classe a ser persistida
	public Class<T> getObjectClass() {
		return this.oClass;
	}
	
	//Obtem uma instancia da classe
	@SuppressWarnings({ "unused", "deprecation" })
	private T newInstance() throws InstantiationException, IllegalAccessException {
		return oClass.newInstance();
	}

	//Construtor da classe
	@SuppressWarnings("unchecked")
	public GenericoDAOImpl(){
		this.oClass = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * Inclui um objeto T na base de dados
	 * @param object
	 * @return
	 * @throws BancoDadosException
	 */
	public T incluir(T object) throws BancoDadosException {
		Statement stmt = null;

		try{

			//Cria a instrucao SQL do INSERT
			StringBuilder sql = new StringBuilder();
			
			sql.append("INSERT INTO ");
			sql.append(getNomeTabela());
			sql.append(montarInstrucaoInsert(object));

			System.out.println(sql.toString());
			stmt = transacao.createStatement(sql.toString());

			stmt.execute(sql.toString());

			//melhorar isso... 
			return null;
			
		} catch (Exception e) {
			throw new BancoDadosException(e, "Não foi possível executar a inclusao no banco de dados! " + e.getMessage());
		}
		finally {
			try {
				//fecha statement
				if (stmt != null) {
					stmt.close();
				}
			}
			catch (Exception e) {}
		}
	}

	/**
	 * Altera um objeto T na base de dados
	 * @param object
	 * @return
	 * @throws BancoDadosException
	 */
	public T alterar(T object) throws BancoDadosException {
		Statement stmt = null;

		try{

			//Cria a instrucao SQL do SELECT
			StringBuilder sql = new StringBuilder();
			
			sql.append("UPDATE ");
			sql.append(getNomeTabela());
			sql.append(" SET ");
			sql.append(montarInstrucaoUpdate(object));
			sql.append(" WHERE ID = ");
			sql.append(obterValorId(object));

			System.out.println(sql.toString());
			stmt = transacao.createStatement(sql.toString());

			stmt.execute(sql.toString());

			//melhorar isso... 
			return null;
			
		} catch (Exception e) {
			throw new BancoDadosException(e, "Não foi possível executar a alteracao no banco de dados! " + e.getMessage());
		}
		finally {
			try {
				//fecha statement
				if (stmt != null) {
					stmt.close();
				}
			}
			catch (Exception e) {}
		}
		
	}

	/**
	 * Consulta um objeto T da base de dados
	 * @param id
	 * @return
	 * @throws BancoDadosException
	 */
	public T consultar(Integer id) throws BancoDadosException {
		Statement stmt = null;
		ResultSet rs = null;
		T obj = null;

		try{

			//Cria a instrucao SQL do SELECT
			StringBuilder sql = new StringBuilder();
			
			sql.append("SELECT * FROM ");
			sql.append(getNomeTabela());
			sql.append(" WHERE id = ");
			sql.append(id);

			System.out.println(sql.toString());
			stmt = transacao.createStatement(sql.toString());

			rs = stmt.executeQuery(sql.toString());

			if(rs!=null && rs.next()) {
				obj = mapearResultSetEmObjeto(rs);
			}
			else {
				throw new BancoDadosException(new Exception(), "Nenhum registro encontrado com a chave informada! " + sql.toString());
			}
			
		} catch (Exception e) {
			throw new BancoDadosException(e, "Não foi possível consultar no banco de dados! " + e.getMessage());
		}
		finally {
			try {
				//fecha resultset
				if (rs != null) {
					rs.close();
				}
				//fecha statement
				if (stmt != null) {
					stmt.close();
				}
			}
			catch (Exception e) {}
		}
		return obj;
	}

	/**
	 * metodo que retorna o nome da tabela que esta registrado na anotacao da entidade
	 * @return
	 */
	private String getNomeTabela() {
	    return oClass.getAnnotation(Tabela.class).nomeTabela();
	}
	
	/**
	 * Exclui um objeto T  da base de dados
	 * @param id
	 * @throws BancoDadosException
	 */
	public void excluir(Integer id) throws BancoDadosException {
		Statement stmt = null;

		try{

			//Cria a instrucao SQL do SELECT
			StringBuilder sql = new StringBuilder();
			
			sql.append("DELETE FROM ");
			sql.append(getNomeTabela());
			sql.append(" WHERE ID = ");
			sql.append(id);

			System.out.println(sql.toString());
			stmt = transacao.createStatement(sql.toString());

			stmt.execute(sql.toString());

			
		} catch (Exception e) {
			throw new BancoDadosException(e, "Não foi possível executar a exclusao no banco de dados! " + e.getMessage());
		}
		finally {
			try {
				//fecha statement
				if (stmt != null) {
					stmt.close();
				}
			}
			catch (Exception e) {}
		}
		
	}

	/**
	 * Lista os objetos T da base de dados
	 * @return
	 * @throws BancoDadosException
	 */
	public List<T> listar() throws BancoDadosException {
		List<T> lista = new ArrayList<T>();  
		Statement stmt = null;
		ResultSet rs = null;
		T obj = null;

		try{

			//Cria a instrucao SQL do SELECT
			StringBuilder sql = new StringBuilder();
			
			sql.append("SELECT * FROM ");
			sql.append(getNomeTabela());
			sql.append(" ORDER BY 1");

			System.out.println(sql.toString());
			stmt = transacao.createStatement(sql.toString());

			rs = stmt.executeQuery(sql.toString());

			while(rs!=null && rs.next()) {

				obj = mapearResultSetEmObjeto(rs);
				lista.add(obj);
				
			}
			
		} catch (Exception e) {
			throw new BancoDadosException(e, "Não foi possível listar no banco de dados! " + e.getMessage());
		}
		finally {
			try {
				//fecha resultset
				if (rs != null) {
					rs.close();
				}
				//fecha statement
				if (stmt != null) {
					stmt.close();
				}
			}
			catch (Exception e) {}
		}
		return lista;

     }

	
	/**
	 * Mapeia os campos de um ResultSet nas respecitvas propriedades do objeto T 
	 * as colunas do ResultSet devem ter o mesmo nome dos atributos do objeto T
	 * @param rst
	 * @return objeto T
	 */
	@SuppressWarnings({ "deprecation" })
	private T mapearResultSetEmObjeto(ResultSet rst) {

		T objeto=null;
		try {
			//instancia o objeto dinamicamente
			objeto = (T) oClass.newInstance();

			//mapeia os valores do resultset no objeto da entidade T
			for (Field field : oClass.getDeclaredFields()) {
				field.setAccessible(true);
				Coluna column = field.getAnnotation(Coluna.class);
				Object value = rst.getObject(column.nomeColuna());
				Class<?> type = field.getType();
				if (isPrimitiva(type)) {//verifica se o tipo é primitivo
					Class<?> boxed = mapearClassePrimitiva(type);//mapeia o tipo equivalente
					value = boxed.cast(value);
				}
				field.set(objeto, value);
			}
		} catch (InstantiationException | IllegalAccessException | SQLException e) {
			e.printStackTrace();
		}
		return objeto;
	}	

	/**
	 * Monta a instrucao SET do UPDATE com os valores do objeto
	 * @param objeto
	 * @return
	 */
	private String montarInstrucaoUpdate(T objeto) {
		StringBuilder sql = new StringBuilder();
		try {
			
			//mapeia os valores do resultset no objeto da entidade T
			for (Field field : objeto.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				Coluna column = field.getAnnotation(Coluna.class);

				if(sql.length()>1) {
					sql.append(", ");
				}
				sql.append(column.nomeColuna());
				sql.append(" = "); 
				if (isUsarAspas(field.getType())) {
					sql.append("'");
					sql.append(field.get(objeto));
					sql.append("'");
				}
				else {
					sql.append(field.get(objeto));
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sql.toString();
	}	

	/**
	 * Monta a instrucao VALUES do INSERT com os valores do objeto
	 * @param objeto
	 * @return
	 */
	private String montarInstrucaoInsert(T objeto) {
		StringBuilder sql = new StringBuilder();
		StringBuilder campos = new StringBuilder();
		StringBuilder values = new StringBuilder();
		
		try {
			campos.append("(");
			values.append("(");
			
			//cria a estrutura do insert passando por cada um dos atributos da tabela/objeto
			for (Field field : objeto.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				Coluna column = field.getAnnotation(Coluna.class);
				
				//nao monta para o atributo id
				if (!"id".equalsIgnoreCase(column.nomeColuna())) {
					
					if(campos.length()>1) {
						campos.append(", ");
					}
					if(values.length()>1) {
						values.append(", ");
					}
					
					campos.append(column.nomeColuna());

					if (isUsarAspas(field.getType())) {
						values.append("'");
						values.append(field.get(objeto));
						values.append("'");
					}
					else {
						values.append(field.get(objeto));
					}

				}
				
			}
			campos.append(")");
			values.append(")");
			
			sql.append(campos);
			sql.append(" VALUES ");
			sql.append(values);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sql.toString();
	}	

	/**
	 * Obtem o valor do campo id do objeto
	 * @param objeto
	 * @return
	 */
	private String obterValorId(T objeto) {
		StringBuilder sql = new StringBuilder();
		try {

			//mapeia os valores do resultset no objeto da entidade T
			Field field = objeto.getClass().getDeclaredField("id");
			field.setAccessible(true);
			sql.append(field.get(objeto));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return sql.toString();
	}	
	
	/**
	 * Verifica se a classe é do tipo primitiva
	 * @param type
	 * @return
	 */
	private boolean isPrimitiva(Class<?> type) {
	    return (type == int.class || type == long.class || type == double.class || type == float.class
	            || type == boolean.class || type == byte.class || type == char.class || type == short.class);
	}
	
	/**
	 * Mapeia qual é o tipo da classe primitiva
	 * @param type
	 * @return
	 */
	private Class<?> mapearClassePrimitiva(Class<?> type) {
	    if (type == int.class) {
	        return Integer.class;
	    } else if (type == long.class) {
	        return Long.class;
	    } else if (type == double.class) {
	        return Double.class;
	    } else if (type == float.class) {
	        return Float.class;
	    } else if (type == boolean.class) {
	        return Boolean.class;
	    } else if (type == byte.class) {
	        return Byte.class;
	    } else if (type == char.class) {
	        return Character.class;
	    } else if (type == short.class) {
	        return Short.class;
	    } else {
	        String string = "class '" + type.getName() + "' is not a primitive";
	        throw new IllegalArgumentException(string);
	    }
	}
	
	/**
	 * Retorna se o tipo necessita do uso de aspas
	 * @param type
	 * @return
	 */
	private boolean isUsarAspas(Class<?> type) {
		
	    if (type == int.class || type == long.class || type == double.class || type == float.class ||
	    		type == Integer.class || type == Long.class || type == Double.class || type == Float.class) {
	        return false;
	    } else{
	    	return true;
	    }
	}
	

	
}
