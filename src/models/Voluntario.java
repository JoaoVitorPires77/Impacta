package models;

import java.util.ArrayList;
import java.util.List;

public class Voluntario {
// Classe que representa um aluno voluntário do sistema.

    private String nome;
// Armazena o nome do voluntário.
    private String email;
// Armazena o e-mail, utilizado como identificação única.
    private String matricula;
// Armazena a matrícula do voluntário.
    private List<Acao> acoes;
// Lista que armazena as ações das quais o voluntário participa.

    public Voluntario(String nome, String email, String matricula) {
// Construtor que recebe os dados do voluntário.

        this.nome = nome;
        // Define o nome.
        this.email = email;
        // Define o e-mail.
        this.matricula = matricula;
        // Define a matrícula.
        this.acoes = new ArrayList<>();
        // Cria uma lista vazia para armazenar as ações do voluntário.
    }

    public String getNome() {
        // Retorna o nome do voluntário.
        return nome;
    }

    public String getEmail() {
        // Retorna o e-mail do voluntário.
        return email;
    }

    public String getMatricula() {
        // Retorna a matrícula do voluntário.
        return matricula;
    }

    public List<Acao> getAcoes() {
        // Retorna a lista de ações do voluntário.
        return acoes;
    }

    public void adicionarAcao(Acao acao) {
        // Adiciona uma ação à lista de participações do voluntário.
        acoes.add(acao);
    }

}
