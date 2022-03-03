package br.edu.pucgoias.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Anotacao criada para armazenar o nome da 
 * coluna que esta registrada no banco de dados 
 * para um objeto de entidade
 * @author gilcimar
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Coluna {
	public String nomeColuna();
}
