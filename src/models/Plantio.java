package models;

import java.time.LocalDateTime;

public class Plantio extends Acao {
// Classe Plantio, que herda os atributos e métodos de Acao.

    private int qtdMudas;
// Armazena a quantidade estimada de mudas.

    public Plantio(int id, String titulo, String descricao, LocalDateTime data, int maxParticipantes, int qtdMudas) {

        super(id, titulo, descricao, data, maxParticipantes);
        // Envia os atributos comuns para a classe Acao.
        this.qtdMudas = qtdMudas;
        // Define a quantidade de mudas a serem plantadas.
    }

    public int getQtdMudas() {
        // Retorna a quantidade de mudas.
        return qtdMudas;
    }

    @Override
// Sobrescreve o metodo de cálculo da classe Acao.

    public int calcularPontuacao() {
        // Calcula 5 pontos base mais 2 pontos por muda.
        return 5 + (2 * qtdMudas);
    }

    @Override
// Sobrescreve o metodo para exibir os detalhes específicos.

    public String exibirDetalhesEspecificos() {
        // Retorna as informações específicas do plantio.
        return "Tipo: Plantio de Mudas\n" +
                "Quantidade estimada de mudas: " + qtdMudas;
    }

}

