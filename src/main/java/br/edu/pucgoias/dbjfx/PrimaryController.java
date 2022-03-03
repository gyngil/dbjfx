package br.edu.pucgoias.dbjfx;

import java.io.IOException;
import java.util.List;

import br.edu.pucgoias.entidade.Aluno;
import br.edu.pucgoias.negocio.AlunoServiceImpl;
import br.edu.pucgoias.util.MinhaException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PrimaryController {

    @FXML
    private Button primaryButton;

    @FXML
    private Button btnIncluir;

    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnConsultar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnListar;

    /**
     * Executa a acao de alterar o aluno
     * @param event
     */
    @FXML
    void alterarAluno(ActionEvent event) {

        AlunoServiceImpl service = new AlunoServiceImpl();
        try {
            Aluno aluno = new Aluno();
            aluno.setNome("nome de teste " + (int) (Math.random()*100));
            aluno.setEmail("email@email.com");
            aluno.setIdade((int) (Math.random()*100));
            aluno.setId(1);

            service.alterar(aluno);
            System.out.println("Aluno alterado! id=" + aluno.getId());

        } catch (Exception e) {
            // TODO Auto-generated catch block
            if(e instanceof MinhaException) {
                System.out.println(((MinhaException)e).getMsg());
            }
            else {
                System.out.println(e.getMessage());
            }
            e.printStackTrace();
        }
    }

    /**
     * Executa a acao de consultar o aluno
     * @param event
     */
    @FXML
    void consultarAluno(ActionEvent event) {

        AlunoServiceImpl service = new AlunoServiceImpl();
        try {
            Aluno aluno = null;
            aluno = service.consultar(1);
            System.out.println(aluno.getNome());
            System.out.println(aluno.getId());
            System.out.println(aluno.getNome());
            System.out.println(aluno.getEmail());
            System.out.println(aluno.getIdade());

        } catch (Exception e) {
            // TODO Auto-generated catch block
            if(e instanceof MinhaException) {
                System.out.println(((MinhaException)e).getMsg());
            }
            else {
                System.out.println(e.getMessage());
            }
            e.printStackTrace();
        }
    }

    /**
     * Executa a acao de excluir o aluno
     * @param event
     */
    @FXML
    void excluirAluno(ActionEvent event) {

        AlunoServiceImpl service = new AlunoServiceImpl();
        try {
            Aluno aluno = null;
            aluno = service.consultar(4);
            System.out.println(aluno.getNome());
            System.out.println(aluno.getId());
            System.out.println(aluno.getNome());
            System.out.println(aluno.getEmail());

            service.excluir(4);
            System.out.println("Aluno excluido! id=" + aluno.getId());

        } catch (Exception e) {
            // TODO Auto-generated catch block
            if(e instanceof MinhaException) {
                System.out.println(((MinhaException)e).getMsg());
            }
            else {
                System.out.println(e.getMessage());
            }
            e.printStackTrace();
        }
    }

    /**
     * Executa a acao de incluir o aluno
     * @param event
     */
    @FXML
    void incluirAluno(ActionEvent event) {

        AlunoServiceImpl service = new AlunoServiceImpl();
        try {
            Aluno aluno = new Aluno();
            aluno.setNome("nome de teste " + (int) (Math.random()*100));
            aluno.setEmail("email@email.com");
            aluno.setIdade((int) (Math.random()*100));

            service.incluir(aluno);
            System.out.println("Aluno incluido! ");

        } catch (Exception e) {
            // TODO Auto-generated catch block
            if(e instanceof MinhaException) {
                System.out.println(((MinhaException)e).getMsg());
            }
            else {
                System.out.println(e.getMessage());
            }
            e.printStackTrace();
        }
    }

    /**
     * Executa a acao de listar o aluno
     * @param event
     */
    @FXML
    void listarAluno(ActionEvent event) {

        AlunoServiceImpl service = new AlunoServiceImpl();
        try {
            List<Aluno> lista = service.listar();
            for (Aluno aluno2 : lista) {
                System.out.println(aluno2.getNome());
                System.out.println(aluno2.getId());
                System.out.println(aluno2.getNome());
                System.out.println(aluno2.getEmail());
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            if(e instanceof MinhaException) {
                System.out.println(((MinhaException)e).getMsg());
            }
            else {
                System.out.println(e.getMessage());
            }
            e.printStackTrace();
        }
    }

    @FXML
    private void switchToSecondary() throws IOException {

        App.setRoot("secondary");

    }
}

