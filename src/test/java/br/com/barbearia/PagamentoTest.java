package br.com.barbearia;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class PagamentoTest {

    private Agendamento criarAgendamento() {
        Cliente cliente = new Cliente(1L, "Ana", "31999999999", "ana@email.com");
        Barbeiro barbeiro = new Barbeiro(1L, "Carlos", "31988888888", "Corte");
        Servico servico = new Servico(1L, "Corte", "Corte masculino", 40.0);

        return new Agendamento(
                1L, cliente, barbeiro, servico,
                LocalDateTime.of(2026, 11, 27, 10, 0), "Agendado"
        );
    }

    private Pagamento criarPagamento() {
        return new Pagamento(
                1L, criarAgendamento(), 40.0,
                "Pix", "Pago",
                LocalDateTime.of(2026, 11, 27, 10, 30)
        );
    }

    @Test
    void deveCriarPagamentoValido() {
        Pagamento pagamento = criarPagamento();
        assertNotNull(pagamento);
        assertEquals(1L, pagamento.getId());
        assertEquals(40.0, pagamento.getValor());
        assertEquals("Pix", pagamento.getFormaPagamento());
        assertEquals("Pago", pagamento.getStatus());
    }

    @Test
    void naoDeveCriarPagamentoComIdNulo() {
        assertThrows(IllegalArgumentException.class, () ->
                new Pagamento(null, criarAgendamento(), 40.0, "Pix", "Pago", LocalDateTime.now()));
    }

    @Test
    void naoDeveCriarPagamentoComIdZero() {
        assertThrows(IllegalArgumentException.class, () ->
                new Pagamento(0L, criarAgendamento(), 40.0, "Pix", "Pago", LocalDateTime.now()));
    }

    @Test
    void naoDeveCriarPagamentoComIdNegativo() {
        assertThrows(IllegalArgumentException.class, () ->
                new Pagamento(-1L, criarAgendamento(), 40.0, "Pix", "Pago", LocalDateTime.now()));
    }

    @Test
    void naoDeveCriarPagamentoSemAgendamento() {
        assertThrows(IllegalArgumentException.class, () ->
                new Pagamento(1L, null, 40.0, "Pix", "Pago", LocalDateTime.now()));
    }

    @Test
    void naoDeveCriarPagamentoComValorZero() {
        assertThrows(IllegalArgumentException.class, () ->
                new Pagamento(1L, criarAgendamento(), 0.0, "Pix", "Pago", LocalDateTime.now()));
    }

    @Test
    void naoDeveCriarPagamentoComValorNegativo() {
        assertThrows(IllegalArgumentException.class, () ->
                new Pagamento(1L, criarAgendamento(), -10.0, "Pix", "Pago", LocalDateTime.now()));
    }

    @Test
    void naoDeveCriarPagamentoComValorInfinito() {
        assertThrows(IllegalArgumentException.class, () ->
                new Pagamento(1L, criarAgendamento(), Double.POSITIVE_INFINITY, "Pix", "Pago", LocalDateTime.now()));
    }

    @Test
    void naoDeveCriarPagamentoComValorNaN() {
        assertThrows(IllegalArgumentException.class, () ->
                new Pagamento(1L, criarAgendamento(), Double.NaN, "Pix", "Pago", LocalDateTime.now()));
    }

    @Test
    void naoDeveCriarPagamentoComFormaNula() {
        assertThrows(IllegalArgumentException.class, () ->
                new Pagamento(1L, criarAgendamento(), 40.0, null, "Pago", LocalDateTime.now()));
    }

    @Test
    void naoDeveCriarPagamentoComFormaVazia() {
        assertThrows(IllegalArgumentException.class, () ->
                new Pagamento(1L, criarAgendamento(), 40.0, " ", "Pago", LocalDateTime.now()));
    }

    @Test
    void naoDeveCriarPagamentoComStatusNulo() {
        assertThrows(IllegalArgumentException.class, () ->
                new Pagamento(1L, criarAgendamento(), 40.0, "Pix", null, LocalDateTime.now()));
    }

    @Test
    void naoDeveCriarPagamentoComStatusVazio() {
        assertThrows(IllegalArgumentException.class, () ->
                new Pagamento(1L, criarAgendamento(), 40.0, "Pix", " ", LocalDateTime.now()));
    }

    @Test
    void naoDeveCriarPagamentoComDataNula() {
        assertThrows(IllegalArgumentException.class, () ->
                new Pagamento(1L, criarAgendamento(), 40.0, "Pix", "Pago", null));
    }

    @Test
    void devePermitirAtualizarStatus() {
        Pagamento pagamento = criarPagamento();
        pagamento.setStatus("Cancelado");
        assertEquals("Cancelado", pagamento.getStatus());
    }

    @Test
    void devePermitirAtualizarFormaPagamento() {
        Pagamento pagamento = criarPagamento();
        pagamento.setFormaPagamento("Cartao");
        assertEquals("Cartao", pagamento.getFormaPagamento());
    }

    @Test
    void devePermitirAtualizarValor() {
        Pagamento pagamento = criarPagamento();
        pagamento.setValor(60.0);
        assertEquals(60.0, pagamento.getValor());
    }
}
