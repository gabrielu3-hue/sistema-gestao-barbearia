package br.com.barbearia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AgendamentoRepositoryTest {

    private AgendamentoRepository repository;
    private Agendamento agendamento;

    @BeforeEach
    void configurar() {
        repository = new AgendamentoRepository();

        Cliente cliente = new Cliente(1L, "Ana Silva", "31999999999", "ana@email.com");
        Barbeiro barbeiro = new Barbeiro(1L, "Carlos", "31988888888", "Corte");
        Servico servico = new Servico(1L, "Corte", "Corte masculino", 35.0);

        agendamento = new Agendamento(
                1L, cliente, barbeiro, servico,
                LocalDateTime.of(2026, 11, 27, 14, 0),
                "AGENDADO"
        );
    }

    @Test
    void deveSalvarAgendamento() {
        repository.salvar(agendamento);
        assertEquals(Optional.of(agendamento), repository.buscarPorId(1L));
    }

    @Test
    void naoDeveSalvarAgendamentoNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> repository.salvar(null));
    }

    @Test
    void naoDeveSalvarAgendamentoComIdDuplicado() {
        repository.salvar(agendamento);
        Agendamento outro = criarAgendamento(1L);
        assertThrows(IllegalArgumentException.class,
                () -> repository.salvar(outro));
    }

    @Test
    void deveBuscarAgendamentoPorId() {
        repository.salvar(agendamento);
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
    void deveListarTodosAgendamentos() {
        repository.salvar(agendamento);
        repository.salvar(criarAgendamento(2L));
        assertEquals(2, repository.listarTodos().size());
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoHouverAgendamentos() {
        assertTrue(repository.listarTodos().isEmpty());
    }

    @Test
    void deveAtualizarAgendamento() {
        repository.salvar(agendamento);
        agendamento.setStatus("CONFIRMADO");
        repository.atualizar(agendamento);
        assertEquals("CONFIRMADO",
                repository.buscarPorId(1L).orElseThrow().getStatus());
    }

    @Test
    void naoDeveAtualizarAgendamentoNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> repository.atualizar(null));
    }

    @Test
    void naoDeveAtualizarAgendamentoInexistente() {
        assertThrows(IllegalArgumentException.class,
                () -> repository.atualizar(agendamento));
    }

    @Test
    void deveExcluirAgendamento() {
        repository.salvar(agendamento);
        repository.excluir(1L);
        assertTrue(repository.buscarPorId(1L).isEmpty());
    }

    @Test
    void naoDeveExcluirAgendamentoInexistente() {
        assertThrows(IllegalArgumentException.class,
                () -> repository.excluir(99L));
    }

    @Test
    void naoDeveExcluirComIdNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> repository.excluir(null));
    }

    @Test
    void deveManterOutrosAgendamentosAposExcluirUm() {
        repository.salvar(agendamento);
        repository.salvar(criarAgendamento(2L));
        repository.excluir(1L);
        assertEquals(1, repository.listarTodos().size());
    }

    private Agendamento criarAgendamento(Long id) {
        Cliente cliente = new Cliente(id, "Cliente " + id, "31999999999", "cliente" + id + "@email.com");
        Barbeiro barbeiro = new Barbeiro(id, "Barbeiro " + id, "31988888888", "Corte");
        Servico servico = new Servico(id, "Corte", "Corte masculino", 35.0);

        return new Agendamento(
                id, cliente, barbeiro, servico,
                LocalDateTime.of(2026, 11, 28, 15, 0),
                "AGENDADO"
        );
    }
}
