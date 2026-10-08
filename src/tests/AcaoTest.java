package tests;
//imports
import models.Acao;
import models.Mutirao;
import models.Oficina;
import models.Plantio;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AcaoTest {
    // Para todos os testes de pontuaçao é utilizado o email e id da açao para a indentificaçao
    @Test
    void testarPontuacaoPlantio() {

        // Teste para verificar se o sistema de pontuaçoes de plantio funciona corretamente e se retorna a pontuaçao correta
        Plantio plantio = new Plantio(1, "Plantio no campus", "Plantio de árvores", LocalDateTime.parse("2026-09-10T14:30"), 20, 10);

        // 5 + (2 * 10) = 25
        assertEquals(25, plantio.calcularPontuacao());
    }


    @Test
    void testarPontuacaoMutirao() {
        // Teste para verificar se o sistema de pontuaçoes do mutirao funciona corretamente e se retorna a pontuaçao correta

        Mutirao mutirao = new Mutirao(2, "Mutirão de limpeza", "Limpeza do parque", LocalDateTime.parse("2026-09-15T09:00"), 15, 5);

        // 4 * 5 = 20
        assertEquals(20, mutirao.calcularPontuacao());
    }


    @Test
    void testarPontuacaoOficinaComKit() {
        // Teste para verificar se o sistema de pontuaçoes de plantio funciona corretamente e se retorna a pontuaçao correta
        // Com kit --> +10 pontos
        // Sem kit --> nao tem adicional de 10 pontos

        Oficina oficina = new Oficina(3, "Oficina de reciclagem", "Reutilização de materiais", LocalDateTime.parse("2026-09-20T13:00"), 10, 4, true);

        // (3 * 4) + 10 = 22
        assertEquals(22, oficina.calcularPontuacao());
    }


    @Test
    void testarPontuacaoOficinaSemKit() {

        Oficina oficina = new Oficina(4, "Oficina de compostagem", "Compostagem doméstica", LocalDateTime.parse("2026-09-21T13:00"), 10, 4, false);

        // 3 * 4 = 12
        assertEquals(12, oficina.calcularPontuacao());
    }


    @Test
    void testarPolimorfismo() {
        //Criando novo plantio para o teste
        Acao plantio = new Plantio(5, "Plantio", "Descrição", LocalDateTime.parse("2026-09-10T14:30"), 20, 10);

        //Criando novo mutirao para o teste
        Acao mutirao = new Mutirao(6, "Mutirão", "Descrição", LocalDateTime.parse("2026-09-11T14:30"), 20, 5);

        //Criando nova oficina para o teste
        Acao oficina = new Oficina(7, "Oficina", "Descrição", LocalDateTime.parse("2026-09-12T14:30"), 20, 4, true);

        //Validando se o mesmo metodo caucula corretamente com diferentes comportamentos respondendo as dependencias das subclasses
        assertEquals(25, plantio.calcularPontuacao()
        );

        assertEquals(20, mutirao.calcularPontuacao()
        );

        assertEquals(22, oficina.calcularPontuacao());
    }

}
