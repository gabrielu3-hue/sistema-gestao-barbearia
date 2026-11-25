package br.com.barbearia;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void deveCriarClienteComDadosValidos() {
        Cliente cliente = new Cliente(1L, "Gabriel Silva", "31999999999", "gabriel@email.com");
        assertEquals(1L, cliente.getId());
        assertEquals("Gabriel Silva", cliente.getNome());
        assertEquals("31999999999", cliente.getTelefone());
        assertEquals("gabriel@email.com", cliente.getEmail());
    }

    @Test
    void naoDeveAceitarIdZero() {
        assertThrows(IllegalArgumentException.class, () ->
            new Cliente(0L, "Gabriel Silva", "31999999999", "gabriel@email.com"));
    }

    @Test
    void naoDeveAceitarNomeVazio() {
        assertThrows(IllegalArgumentException.class, () ->
            new Cliente(1L, "", "31999999999", "gabriel@email.com"));
    }

    @Test
    void naoDeveAceitarTelefoneVazio() {
        assertThrows(IllegalArgumentException.class, () ->
            new Cliente(1L, "Gabriel Silva", "", "gabriel@email.com"));
    }

    @Test
    void naoDeveAceitarEmailVazio() {
        assertThrows(IllegalArgumentException.class, () ->
            new Cliente(1L, "Gabriel Silva", "31999999999", ""));
    }

    @Test
    void deveAlterarIdComValorValido() {
        Cliente cliente = new Cliente(1L, "Gabriel Silva", "31999999999", "gabriel@email.com");
        cliente.setId(2L);
        assertEquals(2L, cliente.getId());
    }

    @Test
    void naoDeveAceitarIdNuloNoSetter() {
        Cliente cliente = new Cliente(1L, "Gabriel Silva", "31999999999", "gabriel@email.com");
        assertThrows(IllegalArgumentException.class, () -> cliente.setId(null));
    }

    @Test
    void naoDeveAceitarIdNegativoNoSetter() {
        Cliente cliente = new Cliente(1L, "Gabriel Silva", "31999999999", "gabriel@email.com");
        assertThrows(IllegalArgumentException.class, () -> cliente.setId(-1L));
    }

    @Test
    void deveAlterarNomeComValorValido() {
        Cliente cliente = new Cliente(1L, "Gabriel Silva", "31999999999", "gabriel@email.com");
        cliente.setNome("Carlos Souza");
        assertEquals("Carlos Souza", cliente.getNome());
    }

    @Test
    void naoDeveAceitarNomeNuloNoSetter() {
        Cliente cliente = new Cliente(1L, "Gabriel Silva", "31999999999", "gabriel@email.com");
        assertThrows(IllegalArgumentException.class, () -> cliente.setNome(null));
    }

    @Test
    void naoDeveAceitarNomeComEspacosNoSetter() {
        Cliente cliente = new Cliente(1L, "Gabriel Silva", "31999999999", "gabriel@email.com");
        assertThrows(IllegalArgumentException.class, () -> cliente.setNome("   "));
    }

    @Test
    void deveAlterarTelefoneComValorValido() {
        Cliente cliente = new Cliente(1L, "Gabriel Silva", "31999999999", "gabriel@email.com");
        cliente.setTelefone("31988888888");
        assertEquals("31988888888", cliente.getTelefone());
    }

    @Test
    void naoDeveAceitarTelefoneNuloNoSetter() {
        Cliente cliente = new Cliente(1L, "Gabriel Silva", "31999999999", "gabriel@email.com");
        assertThrows(IllegalArgumentException.class, () -> cliente.setTelefone(null));
    }

    @Test
    void naoDeveAceitarTelefoneComEspacosNoSetter() {
        Cliente cliente = new Cliente(1L, "Gabriel Silva", "31999999999", "gabriel@email.com");
        assertThrows(IllegalArgumentException.class, () -> cliente.setTelefone("   "));
    }

    @Test
    void deveAlterarEmailComValorValido() {
        Cliente cliente = new Cliente(1L, "Gabriel Silva", "31999999999", "gabriel@email.com");
        cliente.setEmail("novo@email.com");
        assertEquals("novo@email.com", cliente.getEmail());
    }

    @Test
    void naoDeveAceitarEmailNuloNoSetter() {
        Cliente cliente = new Cliente(1L, "Gabriel Silva", "31999999999", "gabriel@email.com");
        assertThrows(IllegalArgumentException.class, () -> cliente.setEmail(null));
    }

    @Test
    void naoDeveAceitarEmailComEspacosNoSetter() {
        Cliente cliente = new Cliente(1L, "Gabriel Silva", "31999999999", "gabriel@email.com");
        assertThrows(IllegalArgumentException.class, () -> cliente.setEmail("   "));
    }
}
