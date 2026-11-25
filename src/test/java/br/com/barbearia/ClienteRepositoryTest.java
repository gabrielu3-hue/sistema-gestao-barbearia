package br.com.barbearia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ClienteRepositoryTest {

    private ClienteRepository repository;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        repository = new ClienteRepository();
        cliente = new Cliente(1L, "Ana Silva", "31999999999", "ana@email.com");
    }

    @Test
    void deveCadastrarCliente() {
        repository.salvar(cliente);

        assertEquals(cliente, repository.buscarPorId(1L).orElseThrow());
    }

    @Test
    void naoDeveCadastrarClienteNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> repository.salvar(null));
    }

    @Test
    void naoDeveCadastrarClientesComMesmoId() {
        repository.salvar(cliente);

        Cliente duplicado = new Cliente(1L, "Carlos", "31888888888", "carlos@email.com");

        assertThrows(IllegalArgumentException.class,
                () -> repository.salvar(duplicado));
    }

    @Test
    void deveListarTodosOsClientes() {
        repository.salvar(cliente);
        repository.salvar(new Cliente(2L, "Carlos", "31888888888", "carlos@email.com"));

        List<Cliente> clientes = repository.listarTodos();

        assertEquals(2, clientes.size());
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoHouverClientes() {
        assertTrue(repository.listarTodos().isEmpty());
    }

    @Test
    void deveBuscarClientePorId() {
        repository.salvar(cliente);

        Optional<Cliente> resultado = repository.buscarPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Ana Silva", resultado.get().getNome());
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
    void deveAtualizarCliente() {
        repository.salvar(cliente);

        Cliente atualizado = new Cliente(1L, "Ana Souza", "31777777777", "ana.souza@email.com");

        repository.atualizar(atualizado);

        assertEquals("Ana Souza",
                repository.buscarPorId(1L).orElseThrow().getNome());
    }

    @Test
    void naoDeveAtualizarClienteInexistente() {
        assertThrows(IllegalArgumentException.class,
                () -> repository.atualizar(cliente));
    }

    @Test
    void naoDeveAtualizarClienteNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> repository.atualizar(null));
    }

    @Test
    void deveExcluirCliente() {
        repository.salvar(cliente);

        repository.excluir(1L);

        assertTrue(repository.buscarPorId(1L).isEmpty());
    }

    @Test
    void naoDeveExcluirClienteInexistente() {
        assertThrows(IllegalArgumentException.class,
                () -> repository.excluir(99L));
    }

    @Test
    void naoDeveExcluirComIdNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> repository.excluir(null));
    }
}