package br.com.barbearia;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class AgendamentoTest {

    private Cliente criarCliente() {
        return new Cliente(1L, "Joao", "31999999999", "joao@email.com");
    }

    private Barbeiro criarBarbeiro() {
        return new Barbeiro(1L, "Carlos", "31988888888", "Corte");
    }

    private Servico criarServico() {
        return new Servico(1L, "Corte", "Corte masculino", 40.0);
    }

    @Test
    void deveCriarAgendamentoComDadosValidos() {
        Agendamento a = new Agendamento(1L, criarCliente(), criarBarbeiro(),
                criarServico(), LocalDateTime.now(), "AGENDADO");
        assertEquals(1L, a.getId());
        assertEquals("AGENDADO", a.getStatus());
    }

    @Test
    void naoDeveCriarAgendamentoComIdNulo() {
        assertThrows(IllegalArgumentException.class, () ->
                new Agendamento(null, criarCliente(), criarBarbeiro(),
                        criarServico(), LocalDateTime.now(), "AGENDADO"));
    }

    @Test
    void naoDeveCriarAgendamentoComIdZero() {
        assertThrows(IllegalArgumentException.class, () ->
                new Agendamento(0L, criarCliente(), criarBarbeiro(),
                        criarServico(), LocalDateTime.now(), "AGENDADO"));
    }

    @Test
    void naoDeveCriarAgendamentoComClienteNulo() {
        assertThrows(IllegalArgumentException.class, () ->
                new Agendamento(1L, null, criarBarbeiro(), criarServico(),
                        LocalDateTime.now(), "AGENDADO"));
    }

    @Test
    void naoDeveCriarAgendamentoComBarbeiroNulo() {
        assertThrows(IllegalArgumentException.class, () ->
                new Agendamento(1L, criarCliente(), null, criarServico(),
                        LocalDateTime.now(), "AGENDADO"));
    }

    @Test
    void naoDeveCriarAgendamentoComServicoNulo() {
        assertThrows(IllegalArgumentException.class, () ->
                new Agendamento(1L, criarCliente(), criarBarbeiro(), null,
                        LocalDateTime.now(), "AGENDADO"));
    }

    @Test
    void naoDeveCriarAgendamentoComDataHoraNula() {
        assertThrows(IllegalArgumentException.class, () ->
                new Agendamento(1L, criarCliente(), criarBarbeiro(),
                        criarServico(), null, "AGENDADO"));
    }

    @Test
    void naoDeveCriarAgendamentoComStatusNulo() {
        assertThrows(IllegalArgumentException.class, () ->
                new Agendamento(1L, criarCliente(), criarBarbeiro(),
                        criarServico(), LocalDateTime.now(), null));
    }

    @Test
    void naoDeveCriarAgendamentoComStatusVazio() {
        assertThrows(IllegalArgumentException.class, () ->
                new Agendamento(1L, criarCliente(), criarBarbeiro(),
                        criarServico(), LocalDateTime.now(), " "));
    }

    @Test
    void deveAlterarClienteDoAgendamento() {
        Agendamento a = criarAgendamento();
        Cliente novoCliente = new Cliente(2L, "Maria", "31977777777", "maria@email.com");
        a.setCliente(novoCliente);
        assertEquals(novoCliente, a.getCliente());
    }

    @Test
    void naoDeveAlterarClienteParaNulo() {
        Agendamento a = criarAgendamento();
        assertThrows(IllegalArgumentException.class, () -> a.setCliente(null));
    }

    @Test
    void deveAlterarDataHoraDoAgendamento() {
        Agendamento a = criarAgendamento();
        LocalDateTime novaData = LocalDateTime.of(2026, 12, 10, 14, 30);
        a.setDataHora(novaData);
        assertEquals(novaData, a.getDataHora());
    }

    @Test
    void naoDeveAlterarDataHoraParaNula() {
        Agendamento a = criarAgendamento();
        assertThrows(IllegalArgumentException.class, () -> a.setDataHora(null));
    }

    @Test
    void deveAlterarStatusDoAgendamento() {
        Agendamento a = criarAgendamento();
        a.setStatus("CONFIRMADO");
        assertEquals("CONFIRMADO", a.getStatus());
    }

    @Test
    void naoDeveAlterarStatusParaVazio() {
        Agendamento a = criarAgendamento();
        assertThrows(IllegalArgumentException.class, () -> a.setStatus(" "));
    }

    private Agendamento criarAgendamento() {
        return new Agendamento(1L, criarCliente(), criarBarbeiro(),
                criarServico(), LocalDateTime.now(), "AGENDADO");
    }
}
