package br.com.barbearia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PagamentoRepositoryTest {

    private PagamentoRepository repository;
    private Pagamento pagamento;

    @BeforeEach
    void configurar() {
        repository = new PagamentoRepository();
        pagamento = criarPagamento(1L);
    }

    @Test
    void deveSalvarPagamento() {
        repository.salvar(pagamento);
        assertEquals(Optional.of(pagamento), repository.buscarPorId(1L));
    }

    @Test
    void naoDeveSalvarPagamentoNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> repository.salvar(null));
    }

    @Test
    void naoDeveSalvarPagamentoComIdDuplicado() {
        repository.salvar(pagamento);
        assertThrows(IllegalArgumentException.class,
                () -> repository.salvar(criarPagamento(1L)));
    }

    @Test
    void deveBuscarPagamentoPorId() {
        repository.salvar(pagamento);
        assertTrue(repository.buscarPorId(1L).isPresent());
    }

    @Test
    void deveRetornarVazioAoBuscarIdInexistente() {
        assertTrue(repository.buscarPorId(99L).isEmpty());
    }

    @Test
    void naoDeveBuscarComIdNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> repository.buscarPorId(null));
    }

    @Test
    void deveListarTodosPagamentos() {
        repository.salvar(pagamento);
        repository.salvar(criarPagamento(2L));
        assertEquals(2, repository.listarTodos().size());
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoHouverPagamentos() {
        assertTrue(repository.listarTodos().isEmpty());
    }

    @Test
    void deveAtualizarPagamento() {
        repository.salvar(pagamento);
        pagamento.setStatus("Cancelado");
        repository.atualizar(pagamento);
        assertEquals("Cancelado",
                repository.buscarPorId(1L).orElseThrow().getStatus());
    }

    @Test
    void naoDeveAtualizarPagamentoNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> repository.atualizar(null));
    }

    @Test
    void naoDeveAtualizarPagamentoInexistente() {
        assertThrows(IllegalArgumentException.class,
                () -> repository.atualizar(pagamento));
    }

    @Test
    void deveExcluirPagamento() {
        repository.salvar(pagamento);
        repository.excluir(1L);
        assertTrue(repository.buscarPorId(1L).isEmpty());
    }

    @Test
    void naoDeveExcluirPagamentoInexistente() {
        assertThrows(IllegalArgumentException.class,
                () -> repository.excluir(99L));
    }

    @Test
    void naoDeveExcluirComIdNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> repository.excluir(null));
    }

    @Test
    void deveManterOutrosPagamentosAposExcluirUm() {
        repository.salvar(pagamento);
        repository.salvar(criarPagamento(2L));
        repository.excluir(1L);
        assertEquals(1, repository.listarTodos().size());
    }

    private Pagamento criarPagamento(Long id) {
        Cliente cliente = new Cliente(id, "Ana", "31999999999", "ana@email.com");
        Barbeiro barbeiro = new Barbeiro(id, "Carlos", "31988888888", "Corte");
        Servico servico = new Servico(id, "Corte", "Corte masculino", 40.0);

        Agendamento agendamento = new Agendamento(
                id, cliente, barbeiro, servico,
                LocalDateTime.of(2026, 11, 27, 10, 0),
                "AGENDADO"
        );

        return new Pagamento(
                id, agendamento, 40.0, "Pix", "Pago",
                LocalDateTime.of(2026, 11, 27, 10, 30)
        );
    }
}
