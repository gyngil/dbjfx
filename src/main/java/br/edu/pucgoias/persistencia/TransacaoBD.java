package br.edu.pucgoias.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe que implementa as operacoes basicas com banco de dados
 * @author gilcimar
 *
 */
public class TransacaoBD {
    //JDBC Driver Utilizado
    private static final String JDBC_DRIVER = "org.hsqldb.jdbc.JDBCDriver";
 
    //Objeto de Connection
    private Connection conn = null;
 
    private static final String connStr = "jdbc:hsqldb:file:/Users/gilcimar/eclipse-workspace/dbjfx/bd/bdjfxBD/dbjfxBD";
    private static final String username = "SA";
    private static final String password = "";
    
    /**
     * Abre uma conexao ao banco de dados HSQLDB
     * @throws Exception
     */
    private void abrirConexao() throws Exception {
        //Setting Oracle JDBC Driver
        try {
            
        	Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(connStr,username, password);
            
            
        } catch (SQLException e) {
            System.out.println("Nao foi possível obter a conexao com o banco de dados! " + e);
            throw e;
        } catch (ClassNotFoundException e) {
            System.out.println("Não foi possível instanciar a classe do banco de dados! " + e);
			throw e;
		}
    }
 
    /**
     * Fecha a conexao do banco de dados de forma segura
     * @throws SQLException
     */
    private void fecharConexao() throws SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e){
           throw e;
        }
    }
    
    /**
     * Abre uma transacao no banco de dados que pode ser somente leitura ou para escrita de dados
     * @param leitura
     * @throws Exception
     */
    public void abrirTransacao(boolean leitura) throws Exception {
    	
        if (conn == null) {
           	abrirConexao();
        }
        
        conn.setReadOnly(leitura);
        
        if(conn !=null && !conn.isClosed()) {
        	conn.setAutoCommit(false);
        }
        else{
        	throw new Exception("Não foi possível abrir a transacao!");
        }
    }
    
    /**
     * Fecha uma transacao de forma segura (com commit)
     * @throws Exception
     */
    public void fecharTransacao() throws Exception {
    	
        try {
            if (conn != null && !conn.isClosed()) {
            	conn.commit();
            	
            	conn.setAutoCommit(true);
            	
            	fecharConexao();
                    }
        } catch (Exception e){
           throw e;
        }
    	
    }

    /**
     * Cria um statement na conexao para execucao de instrucoes SQL
     * @param sql
     * @return
     * @throws Exception
     */
    public Statement createStatement(String sql) throws Exception {
        //Create statement
    	if(conn!=null && !conn.isClosed()) {
    		return conn.createStatement();
    	}
    	else {
        	throw new Exception("Não foi possível abrir a transacao!");
    	}

    }
        
 
}
