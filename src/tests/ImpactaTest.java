package tests;
//imports
import exceptions.AcaoLotadaException;
import exceptions.EmailDuplicadoException;
import exceptions.InscricaoDuplicadaException;

import system.Impacta;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ImpactaTest {

    private Impacta sistema;

    @BeforeEach
    void configurar() {
        //Cria um novo impacta antes de um novo teste
        sistema = new Impacta();
    }

    @Test
    void testarCadastroVoluntario() {
        //Cadastra o voluntario e verifica se foi efetuado
        boolean resultado = sistema.cadastrarVoluntario(
                        "João",
                        "joao@email.com",
                        "12345"
                );

        assertEquals(true, resultado);
    }


    @Test
    void testarEmailDuplicado() {
        //Cadastra dois voluntarios diferentes, porem com emais iguais, e testa se da erro
        sistema.cadastrarVoluntario(
                "João",
                "joao@email.com",
                "12345"
        );

        assertThrows(EmailDuplicadoException.class, () -> {
            sistema.cadastrarVoluntario(
                    "Outro João",
                    "joao@email.com",
                    "54321"
            ); // Email duplicado--> sinaliza erro de emailduplicadoException
                }
        );
    }


    @Test
    void testarRankingPorPontuacao() {
        //Teste para o sistema de ranking pela pontuaçao
        sistema.cadastrarVoluntario(
                "Ana",
                "ana@email.com",
                "1"
        );

        sistema.cadastrarVoluntario(
                "Bruno",
                "bruno@email.com",
                "2"
        );

        sistema.cadastrarVoluntario(
                "Carlos",
                "carlos@email.com",
                "3"
        );


        // 5 + (2 * 8) = 21
        int acaoAna =
                sistema.cadastrarPlantio(
                        "Plantio da Ana",
                        "Descrição",
                        "2026-09-10T10:00",
                        5,
                        8
                );


        // 4 * 10 = 40
        int acaoBruno =
                sistema.cadastrarMutirao(
                        "Mutirão do Bruno",
                        "Descrição",
                        "2026-09-11T10:00",
                        5,
                        10
                );


        // 4 * 7 = 28
        int acaoCarlos = sistema.cadastrarMutirao(
                        "Mutirão do Carlos",
                        "Descrição",
                        "2026-09-12T10:00",
                        5,
                        7
                );
        //Ordem esperada de ranking Bruno(0), Carlos (1), Ana(2), ordem de ranking segue o index


        //Inscriçao dos voluntarios
        sistema.inscreverVoluntario("ana@email.com", acaoAna);

        sistema.inscreverVoluntario("bruno@email.com", acaoBruno);

        sistema.inscreverVoluntario("carlos@email.com", acaoCarlos);

        //Cria a lista de ranking
        String[] ranking = sistema.listarVoluntarios();
        //Testa se a lista de ranking realmente esta correta
        assertArrayEquals(new String[]{"Bruno", "Carlos", "Ana"}, ranking);
    }


    //Quando 2 ou mais voluntarios possuem a mesma pontuaçao o desempate é feito pela ordem alfabetica, ordenando eles conforme o nome
    @Test
    void testarDesempateAlfabetico() {
        //testa se o desempate pela ordem alfabetica funciona
        sistema.cadastrarVoluntario(
                "Carlos",
                "carlos@email.com",
                "1"
        );

        sistema.cadastrarVoluntario(
                "Ana",
                "ana@email.com",
                "2"
        );

        sistema.cadastrarVoluntario(
                "Bruno",
                "bruno@email.com",
                "3"
        );


        // Todos possuem 20 pontos

        int acaoCarlos = sistema.cadastrarMutirao(
                        "Mutirão Carlos",
                        "Descrição",
                        "2026-09-10T10:00",
                        5,
                        5
                );

        int acaoAna = sistema.cadastrarMutirao(
                        "Mutirão Ana",
                        "Descrição",
                        "2026-09-11T10:00",
                        5,
                        5
                );

        int acaoBruno = sistema.cadastrarMutirao(
                        "Mutirão Bruno",
                        "Descrição",
                        "2026-09-12T10:00",
                        5,
                        5
                );

        sistema.inscreverVoluntario("carlos@email.com", acaoCarlos);

        sistema.inscreverVoluntario("ana@email.com", acaoAna);

        sistema.inscreverVoluntario("bruno@email.com", acaoBruno);

        //Cria o ranking pelo index da lista
        String[] ranking = sistema.listarVoluntarios();

        //Verifica se a ordem do ranking esta correta pelo index
        assertArrayEquals(new String[]{"Ana", "Bruno", "Carlos"}, ranking);
    }

    @Test
    void testarAcaoLotada() {
        //Testa a restriçao de capacidade maxima de açoes
        sistema.cadastrarVoluntario(
                "João",
                "joao@email.com",
                "1"
        );

        sistema.cadastrarVoluntario(
                "Maria",
                "maria@email.com",
                "2"
        );

        int idAcao = sistema.cadastrarPlantio(
                        "Plantio",
                        "Descrição",
                        "2026-09-10T10:00",
                        1,
                        5
                );
        // há apenas 1 vaga (max 1 participante)

        //Joao ocupa a vaga
        sistema.inscreverVoluntario("joao@email.com", idAcao);

        //Verifica se ao tentar adicionar mais uma pessoa, é retornado o erro de Açaolotada
        assertThrows(AcaoLotadaException.class, () -> {
                    sistema.inscreverVoluntario("maria@email.com", idAcao);
                }
        );
    }

    @Test
    void testarInscricaoDuplicada() {
        //Testa se o sistema impede de o usuario se cadastrar 2 vezes na mesma açao
        sistema.cadastrarVoluntario(
                "João",
                "joao@email.com",
                "1"
        );


        int idAcao = sistema.cadastrarPlantio("Plantio", "Descrição", "2026-09-10T10:00", 5, 5);

        //Joao é cadastrado no plantio
        sistema.inscreverVoluntario("joao@email.com", idAcao);

        //Verifica se o sistema apresenta erro/impede joao de cadastrar novamente na açao plantio
        assertThrows(InscricaoDuplicadaException.class, () -> {
                    sistema.inscreverVoluntario("joao@email.com", idAcao);
                }
        );
    }

}