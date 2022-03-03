package br.edu.pucgoias.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Anotacao criada para armazenar o nome da tabela 
 * do banco de dados relacionada a essa entidade
 * @author gilcimar
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Tabela {
	public String nomeTabela();
}
