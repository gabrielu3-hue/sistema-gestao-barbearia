package br.com.barbearia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServicoRepositoryTest {

    private ServicoRepository repository;
    private Servico servico;

    @BeforeEach
    void configurar() {
        repository = new ServicoRepository();
        servico = new Servico(1L, "Corte masculino", "Corte tradicional", 40.00);
    }

    @Test
    void deveSalvarServico() {
        repository.salvar(servico);
        assertEquals(servico, repository.buscarPorId(1L));
    }

    @Test
    void naoDeveSalvarServicoNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> repository.salvar(null));
    }

    @Test
    void naoDeveSalvarServicoComIdDuplicado() {
        repository.salvar(servico);
        Servico outro = new Servico(1L, "Barba", "Barba completa", 25.00);
        assertThrows(IllegalArgumentException.class,
                () -> repository.salvar(outro));
    }

    @Test
    void deveListarTodosOsServicos() {
        repository.salvar(servico);
        assertEquals(1, repository.listarTodos().size());
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoHouverServicos() {
        assertTrue(repository.listarTodos().isEmpty());
    }

    @Test
    void deveBuscarServicoPorId() {
        repository.salvar(servico);
        assertEquals(servico, repository.buscarPorId(1L));
    }

    @Test
    void deveRetornarNuloAoBuscarIdInexistente() {
        assertNull(repository.buscarPorId(99L));
    }

    @Test
    void deveAtualizarServico() {
        repository.salvar(servico);
        Servico atualizado = new Servico(
                1L, "Corte e barba", "Combo completo", 60.00
        );
        repository.atualizar(1L, atualizado);
        assertEquals("Corte e barba", repository.buscarPorId(1L).getNome());
    }

    @Test
    void naoDeveAtualizarServicoInexistente() {
        assertThrows(IllegalArgumentException.class,
                () -> repository.atualizar(99L, servico));
    }

    @Test
    void naoDeveAtualizarComServicoNulo() {
        repository.salvar(servico);
        assertThrows(IllegalArgumentException.class,
                () -> repository.atualizar(1L, null));
    }

    @Test
    void deveExcluirServico() {
        repository.salvar(servico);
        repository.excluir(1L);
        assertNull(repository.buscarPorId(1L));
    }

    @Test
    void naoDeveExcluirServicoInexistente() {
        assertThrows(IllegalArgumentException.class,
                () -> repository.excluir(99L));
    }

    @Test
    void deveManterOutrosServicosAoExcluirUm() {
        repository.salvar(servico);
        Servico outro = new Servico(2L, "Barba", "Barba completa", 25.00);
        repository.salvar(outro);
        repository.excluir(1L);
        assertEquals(1, repository.listarTodos().size());
        assertEquals(outro, repository.buscarPorId(2L));
    }

    @Test
    void deveListarVariosServicos() {
        repository.salvar(servico);
        Servico outro = new Servico(2L, "Barba", "Barba completa", 25.00);
        repository.salvar(outro);
        assertEquals(2, repository.listarTodos().size());
    }

    @Test
    void deveBuscarSegundoServicoPorId() {
        repository.salvar(servico);
        Servico outro = new Servico(2L, "Barba", "Barba completa", 25.00);
        repository.salvar(outro);
        assertEquals(outro, repository.buscarPorId(2L));
    }
}
