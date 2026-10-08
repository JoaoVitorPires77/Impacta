package system;

import exceptions.AcaoLotadaException;
import exceptions.EmailDuplicadoException;
import exceptions.InscricaoDuplicadaException;

import models.Acao;
import models.Mutirao;
import models.Oficina;
import models.Plantio;
import models.Voluntario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Impacta {

    // Cria para guardar os voluntários.
    private Map<String, Voluntario> voluntarios;
    // Cria para guardar as ações;
    private Map<Integer, Acao> acoes;
    // Guarda o próximo ID que será usado;
    private int proximoId;

    // Construtor da classe Impacta;
    public Impacta() {
        // Cria o Map dos voluntários;
        voluntarios = new HashMap<>();
        // Cria o Map das ações;
        acoes = new HashMap<>();
        // Define o primeiro ID como 1;
        proximoId = 1;
    }

// VOLUNTÁRIOS:

    // Cadastra um novo voluntário;
    public boolean cadastrarVoluntario(String nome, String email, String matricula) {

        // Verifica se o e-mail já está cadastrado;
        if (voluntarios.containsKey(email)) {
            // Se já existir, lança uma exceção;
            throw new EmailDuplicadoException("Já existe um voluntário cadastrado com este e-mail.");
        }

        // Cria um novo voluntário com os dados recebidos;
        Voluntario voluntario = new Voluntario(nome, email, matricula);

        // Guarda o voluntário usando o e-mail como chave;
        voluntarios.put(email, voluntario);

        // Retorna TRUE, indicando que deu certo;
        return true;
    }


    // Mostra os dados de um voluntário;
    public String exibirVoluntario(String email) {

        // Procura o voluntário pelo e-mail;
        Voluntario voluntario = voluntarios.get(email);

        // Verifica se o voluntário não foi encontrado;
        if (voluntario == null) {
            // Retorna uma mensagem informando que não existe;
            return "Voluntário não encontrado.";
        }

        // Pega a quantidade de ações do voluntário;
        int quantidadeAcoes = voluntario.getAcoes().size();

        // Calcula a pontuação total do voluntário;
        int pontuacao = calcularPontuacaoVoluntario(voluntario);

        return "Nome: " + voluntario.getNome() +
                "\nQuantidade de ações: " + quantidadeAcoes +
                "\nPontuação de impacto acumulada: " + pontuacao;
    }

    // Lista os voluntários cadastrados;
    public String[] listarVoluntarios() {

        // Cria uma lista com os voluntários do Map;
        List<Voluntario> lista = new ArrayList<>(voluntarios.values());

        // Ordena pela maior pontuação e depois pelo nome;
        lista.sort(Comparator.comparingInt(this::calcularPontuacaoVoluntario).reversed().thenComparing(Voluntario::getNome));

        // Cria para guardar os nomes;
        String[] resultado = new String[lista.size()];

        // Percorre a lista de voluntários;
        for (int i = 0; i < lista.size(); i++) {
            // Pega o voluntário da posição atual;
            Voluntario voluntario = lista.get(i);
            // Coloca o nome no resultado;
            resultado[i] = voluntario.getNome();
        }

        return resultado;
    }

// CADASTRO DE AÇÕES:

    // Cadastra uma ação do tipo Plantio;
    public int cadastrarPlantio(String titulo, String descricao, String data, int maxParticipantes, int qtdMudas) {

        // Converte a data de String para LocalDateTime;
        LocalDateTime dataConvertida = LocalDateTime.parse(data);

        // Pega o ID atual e depois aumenta para o próximo;
        int id = proximoId++;

        // Cria um novo Plantio com os dados recebidos;
        Plantio plantio = new Plantio(id, titulo, descricao, dataConvertida, maxParticipantes, qtdMudas);

        // Guarda o plantio usando o ID;
        acoes.put(id, plantio);

        return id;
    }

    // Cadastra uma ação do tipo Mutirão;
    public int cadastrarMutirao(String titulo, String descricao, String data, int maxParticipantes, int duracaoHoras) {

        LocalDateTime dataConvertida = LocalDateTime.parse(data);

        int id = proximoId++;

        // Cria um novo Mutirão com os dados recebidos;
        Mutirao mutirao = new Mutirao(id, titulo, descricao, dataConvertida, maxParticipantes, duracaoHoras);

        // Guarda o mutirão usando o ID como chave;
        acoes.put(id, mutirao);

        return id;
    }


    // Cadastra uma ação do tipo Oficina;
    public int cadastrarOficina(String titulo, String descricao, String data, int maxParticipantes, int duracaoHoras, boolean kitMaterial) {

        LocalDateTime dataConvertida = LocalDateTime.parse(data);

        int id = proximoId++;

        // Cria uma nova Oficina com os dados recebidos;
        Oficina oficina = new Oficina(id, titulo, descricao, dataConvertida, maxParticipantes, duracaoHoras, kitMaterial);

        acoes.put(id, oficina);

        // Retorna o ID da oficina;
        return id;
    }


// INSCRIÇÃO:


    // Inscreve um voluntário em uma ação;
    public boolean inscreverVoluntario(String emailVoluntario, int idAcao) {

        // Procura o voluntário pelo e-mail;
        Voluntario voluntario = voluntarios.get(emailVoluntario);

        // Procura a ação pelo ID dele;
        Acao acao = acoes.get(idAcao);

        // Verifica se o voluntário OU a ação não existem;
        if (voluntario == null || acao == null) {
            return false;
        }

        // Verifica se o voluntário já está inscrito;
        if (acao.possuiVoluntario(voluntario)) {
            // Se já estiver, vai lançar uma exceção;
            throw new InscricaoDuplicadaException("O voluntário já está inscrito nesta ação.");
        }

        // Verifica se a ação está lotada;
        if (acao.estaLotada()) {
            // Se estiver lotada, lança uma exceção novamente;
            throw new AcaoLotadaException("A ação já atingiu o número máximo de participantes.");
        }

        // Adiciona o voluntário na ação;
        acao.adicionarVoluntario(voluntario);

        // Adiciona a ação na lista do voluntário;
        voluntario.adicionarAcao(acao);

        return true;
    }


// EXIBIÇÃO DE AÇÕES:


    // Mostra os detalhes de uma ação;
    public String exibirDetalhesAcao(int idAcao) {

        // Procura a ação usando o ID;
        Acao acao = acoes.get(idAcao);

        // Verifica se a ação não foi encontrada;
        if (acao == null) {
            // Retorna uma mensagem informando que não existe;
            return "Ação não encontrada.";
        }

        // Cria um texto para juntar os detalhes da ação;
        StringBuilder detalhes = new StringBuilder();

        // Adiciona o título;
        detalhes.append("Título: ").append(acao.getTitulo()).append("\n");

        // Adiciona a descrição;
        detalhes.append("Descrição: ").append(acao.getDescricao()).append("\n");

        // Adiciona a data;
        detalhes.append("Data: ").append(acao.getData()).append("\n");

        // Calcula e adiciona a pontuação;
        detalhes.append("Pontuação calculada: ").append(acao.calcularPontuacao()).append("\n");

        // Adiciona a capacidade máxima;
        detalhes.append("Capacidade máxima: ").append(acao.getMaxParticipantes()).append("\n\n");


        // Adiciona os detalhes específicos do tipo da ação;
        detalhes.append(acao.exibirDetalhesEspecificos());

        // Adiciona o título da lista de voluntários;
        detalhes.append("\n\nVoluntários inscritos:\n");


        // Verifica se a lista de voluntários está vazia;
        if (acao.getVoluntarios().isEmpty()) {
            // Mostra que não existem voluntários inscritos;
            detalhes.append("Nenhum voluntário inscrito.");

        } else {

            // Percorre os voluntários inscritos;
            for (Voluntario voluntario : acao.getVoluntarios()) {

                // vai adicionar o nome do voluntário na lista;
                detalhes.append("- ").append(voluntario.getNome()).append("\n");
            }
        }

        // Retorna todos os detalhes montados;
        return detalhes.toString();
    }


// MÉTODOS AUXILIARES:

    private int calcularPontuacaoVoluntario(Voluntario voluntario) {

        int pontuacaoTotal = 0;

        // Percorre as ações do voluntário e soma a potuação de cada ação;
        for (Acao acao : voluntario.getAcoes()) {
            pontuacaoTotal += acao.calcularPontuacao();
        }

        return pontuacaoTotal;
    }

}