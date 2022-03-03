package br.edu.pucgoias.dbjfx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
 
/**
 * Classe de entidade que representa o formulario do Aluno
 * @author gilcimar
 *
 */
public class AlunoBean {
    //Propriedades com Observer para o modelo de Table Columns
    private IntegerProperty id;
    private StringProperty nome;
    private StringProperty email;
    private IntegerProperty idade;
 
    //Constructor
    public AlunoBean() {
        this.id = new SimpleIntegerProperty();
        this.nome = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.idade = new SimpleIntegerProperty();
    }
 
    public int getId() {
        return id.get();
    }
 
    public void setId(int id){
        this.id.set(id);
    }
 
    public IntegerProperty idProperty() {
        return id;
    }
 
    public String getNome () {
        return nome.get();
    }
 
    public void setNome(String nome){
        this.nome.set(nome);
    }
 
    public StringProperty nomeProperty() {
        return nome;
    }
 
    public String getEmail () {
        return email.get();
    }
 
    public void setEmail (String email){
        this.email.set(email);
    }
 
    public StringProperty emailProperty() {
        return email;
    }
 
    public int getIdade() {
        return idade.get();
    }
 
    public void setIdade(int idade){
        this.idade.set(idade);
    }
 
    public IntegerProperty idadeProperty(){
        return idade;
    }
 
 
}
