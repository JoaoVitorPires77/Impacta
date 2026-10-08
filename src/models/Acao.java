package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Acao {
// Classe abstrata que representa os dados e comportamentos comuns
// a todos os tipos de ações.

    private int id;
// Identifica cada ação.
    private String titulo;
// Armazena o título da ação.
    private String descricao;
// Armazena a descrição da ação.
    private LocalDateTime data;
// Armazena a data e o horário da ação.
    private int maxParticipantes;
// Define o número máximo de voluntários.
    private List<Voluntario> voluntarios;
// Lista que armazena os voluntários inscritos na ação.

    public Acao(int id, String titulo, String descricao, LocalDateTime data, int maxParticipantes) {
// Construtor que recebe os dados comuns de todas as ações.

        this.id = id;
        // Define o identificador da ação.
        this.titulo = titulo;
        // Define o título da ação.
        this.descricao = descricao;
        // Define a descrição da ação.
        this.data = data;
        // Define a data e o horário da ação.
        this.maxParticipantes = maxParticipantes;
        // Define a quantidade máxima de participantes.
        this.voluntarios = new ArrayList<>();
        // Cria uma lista vazia para armazenar os voluntários.
    }

    public int getId() {
        // Retorna o ID da ação.
        return id;
    }

    public String getTitulo() {
        // Retorna o título da ação.
        return titulo;
    }

    public String getDescricao() {
        // Retorna a descrição da ação.
        return descricao;
    }

    public LocalDateTime getData() {
        // Retorna a data e o horário da ação.
        return data;
    }

    public int getMaxParticipantes() {
        // Retorna o número máximo de participantes.
        return maxParticipantes;
    }

    public List<Voluntario> getVoluntarios() {
        // Retorna a lista de voluntários inscritos.
        return voluntarios;
    }

    public boolean possuiVoluntario(Voluntario voluntario) {
        // Verifica se o voluntário já está inscrito na ação.
        return voluntarios.contains(voluntario);
    }

    public boolean estaLotada() {
        // Verifica se a quantidade de inscritos atingiu o limite.
        return voluntarios.size() >= maxParticipantes;
    }

    public void adicionarVoluntario(Voluntario voluntario) {
        // Adiciona um voluntário à lista de inscritos.
        voluntarios.add(voluntario);
    }

    public abstract int calcularPontuacao();
// Metodo abstrato para calcular a pontuação de cada tipo de ação.

    public abstract String exibirDetalhesEspecificos();
// Metodo abstrato para exibir informações específicas de cada ação.

}
