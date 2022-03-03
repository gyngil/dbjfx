package br.edu.pucgoias.entidade;

import br.edu.pucgoias.util.Coluna;
import br.edu.pucgoias.util.Tabela;

/**
 * Classe de entidade que representa o Aluno
 * @author gilcimar
 * Obs..: necessário que o atributo chave seja nomeado de id
 *
 */
@Tabela(nomeTabela="ALUNOS")
public class Aluno {

	@Coluna(nomeColuna="id")
	private Integer id;
	@Coluna(nomeColuna="nome")
    private String nome;
	@Coluna(nomeColuna="email")
    private String email;
	@Coluna(nomeColuna="idade")
    private Integer idade;
 
    public int getId() {
        return id;
    }
 
    public void setId(int id){
        this.id = id;
    }
 
    public String getNome () {
        return nome;
    }
 
    public void setNome(String nome){
        this.nome = nome;
    }
 
    public String getEmail () {
        return email;
    }
 
    public void setEmail (String email){
        this.email = email;
    }
 
    public int getIdade() {
        return idade;
    }
 
    public void setIdade(int idade){
        this.idade = idade;
    }
 
}
