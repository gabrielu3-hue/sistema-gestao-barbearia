package br.com.barbearia;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void deveCriarClienteComDadosValidos() {
        Cliente cliente = new Cliente(
            1L,
            "Gabriel Silva",
            "31999999999",
            "gabriel@email.com"
        );

        assertEquals(1L, cliente.getId());
        assertEquals("Gabriel Silva", cliente.getNome());
        assertEquals("31999999999", cliente.getTelefone());
        assertEquals("gabriel@email.com", cliente.getEmail());
    }

    @Test
    void naoDeveAceitarIdZero() {
        assertThrows(IllegalArgumentException.class, () ->
            new Cliente(0L, "Gabriel Silva", "31999999999", "gabriel@email.com")
        );
    }

    @Test
    void naoDeveAceitarNomeVazio() {
        assertThrows(IllegalArgumentException.class, () ->
            new Cliente(1L, "", "31999999999", "gabriel@email.com")
        );
    }

    @Test
    void naoDeveAceitarTelefoneVazio() {
        assertThrows(IllegalArgumentException.class, () ->
            new Cliente(1L, "Gabriel Silva", "", "gabriel@email.com")
        );
    }

    @Test
    void naoDeveAceitarEmailVazio() {
        assertThrows(IllegalArgumentException.class, () ->
            new Cliente(1L, "Gabriel Silva", "31999999999", "")
        );
    }
}
