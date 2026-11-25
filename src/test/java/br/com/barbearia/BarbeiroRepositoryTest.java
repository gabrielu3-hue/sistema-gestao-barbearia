package br.com.barbearia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BarbeiroRepositoryTest {

    private BarbeiroRepository repository;
    private Barbeiro barbeiro;

    @BeforeEach
    void configurar() {
        repository = new BarbeiroRepository();
        barbeiro = new Barbeiro(
                1L, "Carlos", "31999999999", "Corte masculino"
        );
    }

    @Test
    void deveSalvarBarbeiro() {
        repository.salvar(barbeiro);

        assertEquals(barbeiro, repository.buscarPorId(1L));
    }

    @Test
    void naoDeveSalvarBarbeiroNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> repository.salvar(null));
    }

    @Test
    void naoDeveSalvarBarbeiroComIdDuplicado() {
        repository.salvar(barbeiro);

        Barbeiro outro = new Barbeiro(
                1L, "Roberto", "31888888888", "Barba"
        );

        assertThrows(IllegalArgumentException.class,
                () -> repository.salvar(outro));
    }

    @Test
    void deveListarTodosOsBarbeiros() {
        repository.salvar(barbeiro);

        assertEquals(1, repository.listarTodos().size());
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoHouverBarbeiros() {
        assertTrue(repository.listarTodos().isEmpty());
    }

    @Test
    void deveBuscarBarbeiroPorId() {
        repository.salvar(barbeiro);

        assertEquals(barbeiro, repository.buscarPorId(1L));
    }

    @Test
    void deveRetornarNuloAoBuscarIdInexistente() {
        assertNull(repository.buscarPorId(99L));
    }

    @Test
    void deveAtualizarBarbeiro() {
        repository.salvar(barbeiro);

        Barbeiro atualizado = new Barbeiro(
                1L, "Roberto", "31888888888", "Barba"
        );

        repository.atualizar(1L, atualizado);

        assertEquals("Roberto", repository.buscarPorId(1L).getNome());
    }

    @Test
    void naoDeveAtualizarBarbeiroInexistente() {
        assertThrows(IllegalArgumentException.class,
                () -> repository.atualizar(99L, barbeiro));
    }

    @Test
    void naoDeveAtualizarComBarbeiroNulo() {
        repository.salvar(barbeiro);

        assertThrows(IllegalArgumentException.class,
                () -> repository.atualizar(1L, null));
    }

    @Test
    void deveExcluirBarbeiro() {
        repository.salvar(barbeiro);

        repository.excluir(1L);

        assertNull(repository.buscarPorId(1L));
    }

    @Test
    void naoDeveExcluirBarbeiroInexistente() {
        assertThrows(IllegalArgumentException.class,
                () -> repository.excluir(99L));
    }

    @Test
    void deveManterOutrosBarbeirosAoExcluirUm() {
        repository.salvar(barbeiro);
        Barbeiro outro = new Barbeiro(
                2L, "Roberto", "31888888888", "Barba"
        );
        repository.salvar(outro);

        repository.excluir(1L);

        assertEquals(1, repository.listarTodos().size());
        assertEquals(outro, repository.buscarPorId(2L));
    }

    @Test
    void deveListarVariosBarbeiros() {
        repository.salvar(barbeiro);
        Barbeiro outro = new Barbeiro(
                2L, "Roberto", "31888888888", "Barba"
        );
        repository.salvar(outro);

        assertEquals(2, repository.listarTodos().size());
    }

    @Test
    void deveBuscarSegundoBarbeiroPorId() {
        repository.salvar(barbeiro);
        Barbeiro outro = new Barbeiro(
                2L, "Roberto", "31888888888", "Barba"
        );
        repository.salvar(outro);

        assertEquals(outro, repository.buscarPorId(2L));
    }
}
