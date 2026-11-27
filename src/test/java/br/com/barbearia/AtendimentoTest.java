package br.com.barbearia;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AtendimentoTest {

    private Agendamento criarAgendamento() {
        Cliente cliente = new Cliente(1L, "Ana", "31999999999", "ana@email.com");
        Barbeiro barbeiro = new Barbeiro(1L, "Carlos", "31988888888", "Corte");
        Servico servico = new Servico(1L, "Corte", "Corte masculino", 40.0);

        return new Agendamento(
                1L, cliente, barbeiro, servico,
                LocalDateTime.of(2026, 11, 27, 10, 0), "Agendado"
        );
    }

    private Atendimento criarAtendimento() {
        return new Atendimento(
                1L,
                criarAgendamento(),
                LocalDateTime.of(2026, 11, 27, 10, 0),
                LocalDateTime.of(2026, 11, 27, 10, 30),
                "Concluido",
                "Corte realizado com sucesso"
        );
    }

    @Test
    void deveCriarAtendimentoValido() {
        Atendimento atendimento = criarAtendimento();

        assertNotNull(atendimento);
        assertEquals(1L, atendimento.getId());
        assertEquals("Concluido", atendimento.getStatus());
        assertEquals("Corte realizado com sucesso", atendimento.getObservacoes());
    }

    @Test
    void naoDeveCriarAtendimentoComIdNulo() {
        assertThrows(IllegalArgumentException.class, () ->
                new Atendimento(null, criarAgendamento(),
                        LocalDateTime.now(), LocalDateTime.now().plusMinutes(30),
                        "Concluido", "Observacao"));
    }

    @Test
    void naoDeveCriarAtendimentoComIdZero() {
        assertThrows(IllegalArgumentException.class, () ->
                new Atendimento(0L, criarAgendamento(),
                        LocalDateTime.now(), LocalDateTime.now().plusMinutes(30),
                        "Concluido", "Observacao"));
    }

    @Test
    void naoDeveCriarAtendimentoComIdNegativo() {
        assertThrows(IllegalArgumentException.class, () ->
                new Atendimento(-1L, criarAgendamento(),
                        LocalDateTime.now(), LocalDateTime.now().plusMinutes(30),
                        "Concluido", "Observacao"));
    }

    @Test
    void naoDeveCriarAtendimentoSemAgendamento() {
        assertThrows(IllegalArgumentException.class, () ->
                new Atendimento(1L, null,
                        LocalDateTime.now(), LocalDateTime.now().plusMinutes(30),
                        "Concluido", "Observacao"));
    }

    @Test
    void naoDeveCriarAtendimentoComDataInicioNula() {
        assertThrows(IllegalArgumentException.class, () ->
                new Atendimento(1L, criarAgendamento(),
                        null, LocalDateTime.now().plusMinutes(30),
                        "Concluido", "Observacao"));
    }

    @Test
    void naoDeveCriarAtendimentoComDataFimNula() {
        assertThrows(IllegalArgumentException.class, () ->
                new Atendimento(1L, criarAgendamento(),
                        LocalDateTime.now(), null,
                        "Concluido", "Observacao"));
    }

    @Test
    void naoDeveCriarAtendimentoComDataFimIgualAoInicio() {
        LocalDateTime inicio = LocalDateTime.of(2026, 11, 27, 10, 0);

        assertThrows(IllegalArgumentException.class, () ->
                new Atendimento(1L, criarAgendamento(),
                        inicio, inicio, "Concluido", "Observacao"));
    }

    @Test
    void naoDeveCriarAtendimentoComDataFimAnteriorAoInicio() {
        LocalDateTime inicio = LocalDateTime.of(2026, 11, 27, 10, 30);
        LocalDateTime fim = LocalDateTime.of(2026, 11, 27, 10, 0);

        assertThrows(IllegalArgumentException.class, () ->
                new Atendimento(1L, criarAgendamento(),
                        inicio, fim, "Concluido", "Observacao"));
    }

    @Test
    void naoDeveCriarAtendimentoComStatusNulo() {
        assertThrows(IllegalArgumentException.class, () ->
                new Atendimento(1L, criarAgendamento(),
                        LocalDateTime.now(), LocalDateTime.now().plusMinutes(30),
                        null, "Observacao"));
    }

    @Test
    void naoDeveCriarAtendimentoComStatusVazio() {
        assertThrows(IllegalArgumentException.class, () ->
                new Atendimento(1L, criarAgendamento(),
                        LocalDateTime.now(), LocalDateTime.now().plusMinutes(30),
                        " ", "Observacao"));
    }

    @Test
    void devePermitirAtualizarStatus() {
        Atendimento atendimento = criarAtendimento();

        atendimento.setStatus("Em andamento");

        assertEquals("Em andamento", atendimento.getStatus());
    }

    @Test
    void naoDeveAtualizarStatusComValorNulo() {
        Atendimento atendimento = criarAtendimento();

        assertThrows(IllegalArgumentException.class, () ->
                atendimento.setStatus(null));
    }

    @Test
    void naoDeveAtualizarStatusComValorVazio() {
        Atendimento atendimento = criarAtendimento();

        assertThrows(IllegalArgumentException.class, () ->
                atendimento.setStatus(" "));
    }

    @Test
    void devePermitirAtualizarObservacoes() {
        Atendimento atendimento = criarAtendimento();

        atendimento.setObservacoes("Cliente solicitou acabamento");

        assertEquals("Cliente solicitou acabamento", atendimento.getObservacoes());
    }

    @Test
    void devePermitirAtualizarDataInicio() {
        Atendimento atendimento = criarAtendimento();
        LocalDateTime novaData = LocalDateTime.of(2026, 11, 27, 10, 5);

        atendimento.setDataInicio(novaData);

        assertEquals(novaData, atendimento.getDataInicio());
    }

    @Test
    void naoDeveAtualizarDataInicioComValorNulo() {
        Atendimento atendimento = criarAtendimento();

        assertThrows(IllegalArgumentException.class, () ->
                atendimento.setDataInicio(null));
    }

    @Test
    void naoDeveAtualizarDataInicioPosteriorAoFim() {
        Atendimento atendimento = criarAtendimento();
        LocalDateTime novaData = LocalDateTime.of(2026, 11, 27, 11, 0);

        assertThrows(IllegalArgumentException.class, () ->
                atendimento.setDataInicio(novaData));
    }

    @Test
    void devePermitirAtualizarDataFim() {
        Atendimento atendimento = criarAtendimento();
        LocalDateTime novaData = LocalDateTime.of(2026, 11, 27, 11, 0);

        atendimento.setDataFim(novaData);

        assertEquals(novaData, atendimento.getDataFim());
    }

    @Test
    void naoDeveAtualizarDataFimComValorNulo() {
        Atendimento atendimento = criarAtendimento();

        assertThrows(IllegalArgumentException.class, () ->
                atendimento.setDataFim(null));
    }

    @Test
    void naoDeveAtualizarDataFimAnteriorAoInicio() {
        Atendimento atendimento = criarAtendimento();
        LocalDateTime novaData = LocalDateTime.of(2026, 11, 27, 9, 0);

        assertThrows(IllegalArgumentException.class, () ->
                atendimento.setDataFim(novaData));
    }
}
