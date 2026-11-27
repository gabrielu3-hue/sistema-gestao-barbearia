package br.com.barbearia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AtendimentoRepositoryTest {

    private AtendimentoRepository repository;
    private Atendimento atendimento;

    @BeforeEach
    void configurar() {
        repository = new AtendimentoRepository();
        atendimento = criarAtendimento(1L);
    }

    @Test
    void deveSalvarAtendimento() {
        repository.salvar(atendimento);
        assertEquals(Optional.of(atendimento), repository.buscarPorId(1L));
    }

    @Test
    void naoDeveSalvarAtendimentoNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> repository.salvar(null));
    }

    @Test
    void naoDeveSalvarAtendimentoComIdDuplicado() {
        repository.salvar(atendimento);
        Atendimento outro = criarAtendimento(1L);
        assertThrows(IllegalArgumentException.class,
                () -> repository.salvar(outro));
    }

    @Test
    void deveBuscarAtendimentoPorId() {
        repository.salvar(atendimento);
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
    void deveListarTodosAtendimentos() {
        repository.salvar(atendimento);
        repository.salvar(criarAtendimento(2L));
        assertEquals(2, repository.listarTodos().size());
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoHouverAtendimentos() {
        assertTrue(repository.listarTodos().isEmpty());
    }

    @Test
    void deveAtualizarAtendimento() {
        repository.salvar(atendimento);
        atendimento.setStatus("FINALIZADO");
        repository.atualizar(atendimento);
        assertEquals("FINALIZADO",
                repository.buscarPorId(1L).orElseThrow().getStatus());
    }

    @Test
    void naoDeveAtualizarAtendimentoNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> repository.atualizar(null));
    }

    @Test
    void naoDeveAtualizarAtendimentoInexistente() {
        assertThrows(IllegalArgumentException.class,
                () -> repository.atualizar(atendimento));
    }

    @Test
    void deveExcluirAtendimento() {
        repository.salvar(atendimento);
        repository.excluir(1L);
        assertTrue(repository.buscarPorId(1L).isEmpty());
    }

    @Test
    void naoDeveExcluirAtendimentoInexistente() {
        assertThrows(IllegalArgumentException.class,
                () -> repository.excluir(99L));
    }

    @Test
    void naoDeveExcluirComIdNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> repository.excluir(null));
    }

    @Test
    void deveManterOutrosAtendimentosAposExcluirUm() {
        repository.salvar(atendimento);
        repository.salvar(criarAtendimento(2L));
        repository.excluir(1L);
        assertEquals(1, repository.listarTodos().size());
    }

    private Atendimento criarAtendimento(Long id) {
        Cliente cliente = new Cliente(
                id, "Cliente " + id, "31999999999", "cliente" + id + "@email.com"
        );

        Barbeiro barbeiro = new Barbeiro(
                id, "Barbeiro " + id, "31988888888", "Corte"
        );

        Servico servico = new Servico(
                id, "Corte", "Corte masculino", 35.0
        );

        Agendamento agendamento = new Agendamento(
                id, cliente, barbeiro, servico,
                LocalDateTime.of(2026, 11, 27, 14, 0),
                "AGENDADO"
        );

        return new Atendimento(
                id,
                agendamento,
                LocalDateTime.of(2026, 11, 27, 14, 0),
                LocalDateTime.of(2026, 11, 27, 14, 30),
                "EM_ANDAMENTO",
                "Atendimento de teste"
        );
    }
}
