package models;

import java.time.LocalDateTime;

public class Mutirao extends Acao {
// Classe Mutirao, que herda os atributos e métodos de Acao.

    private int duracaoHoras;
// Armazena a duração do mutirão em horas.

    public Mutirao(int id, String titulo, String descricao, LocalDateTime data, int maxParticipantes, int duracaoHoras) {

        super(id, titulo, descricao, data, maxParticipantes);
        // Envia os atributos comuns para a classe Acao.
        this.duracaoHoras = duracaoHoras;
        // Define a duração do mutirão.
    }

    public int getDuracaoHoras() {
        // Retorna a duração do mutirão.
        return duracaoHoras;
    }

    @Override
// Sobrescreve o metodo de cálculo da classe Acao.

    public int calcularPontuacao() {
        // Calcula 4 pontos para cada hora de duração.
        return 4 * duracaoHoras;
    }

    @Override
// Sobrescreve o metodo para exibir os detalhes específicos.

    public String exibirDetalhesEspecificos() {
        // Retorna as informações específicas do mutirão.
        return "Tipo: Mutirão de Reciclagem\n" +
                "Duração em horas: " + duracaoHoras;
    }

}
