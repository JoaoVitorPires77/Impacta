package models;

import java.time.LocalDateTime;

public class Oficina extends Acao {
// Classe Oficina, que herda os atributos e métodos de Acao;

    private int duracaoHoras;
// Armazena a duração de horas da oficina;
    private boolean kitMaterial;
// Informa se a oficina possui kit de material;

    public Oficina(int id, String titulo, String descricao, LocalDateTime data, int maxParticipantes, int duracaoHoras, boolean kitMaterial) {

        super(id, titulo, descricao, data, maxParticipantes);
        // Envia os atributos comuns para a classe Acao;

        this.duracaoHoras = duracaoHoras;
        // Define a duração da oficina;
        this.kitMaterial = kitMaterial;
        // Define se possui kit de material;
    }

    public int getDuracaoHoras() {
        // Retorna a duração da oficina;
        return duracaoHoras;
    }

    public boolean isKitMaterial() {
        // Retorna se possui kit de material;
        return kitMaterial;
    }

    @Override
// Sobrescreve o metodo de cálculo da classe Acao;

    public int calcularPontuacao() {

        int pontuacao = 3 * duracaoHoras;
        // Calcula 3 pontos para cada hora;

        if (kitMaterial) {
            pontuacao += 10;
            // Adiciona 10 pontos caso possua kit;
        }

        return pontuacao;
        // Retorna a pontuação final;
    }

    @Override
// Sobrescreve o metodo para exibir detalhes específicos;

    public String exibirDetalhesEspecificos() {

        return "Tipo: Oficina Ecológica\n" +
                "Duração em horas: " + duracaoHoras +
                "\nPossui kit de material educativo: " +
                (kitMaterial ? "Sim" : "Não");
        // Retorna as informações específicas da oficina;
    }

}
