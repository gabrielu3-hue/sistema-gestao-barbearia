package br.com.barbearia;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BarbeiroTest {

    @Test
    void deveCriarBarbeiroComDadosValidos() {
        Barbeiro barbeiro = new Barbeiro(
                1L, "Carlos", "31999999999", "Corte masculino"
        );

        assertEquals(1L, barbeiro.getId());
        assertEquals("Carlos", barbeiro.getNome());
        assertEquals("31999999999", barbeiro.getTelefone());
        assertEquals("Corte masculino", barbeiro.getEspecialidade());
    }

    @Test
    void naoDeveCriarBarbeiroComIdNulo() {
        assertThrows(IllegalArgumentException.class, () ->
                new Barbeiro(null, "Carlos", "31999999999", "Corte masculino")
        );
    }

    @Test
    void naoDeveCriarBarbeiroComIdInvalido() {
        assertThrows(IllegalArgumentException.class, () ->
                new Barbeiro(0L, "Carlos", "31999999999", "Corte masculino")
        );
    }

    @Test
    void naoDeveCriarBarbeiroComNomeVazio() {
        assertThrows(IllegalArgumentException.class, () ->
                new Barbeiro(1L, "", "31999999999", "Corte masculino")
        );
    }

    @Test
    void naoDeveCriarBarbeiroComTelefoneVazio() {
        assertThrows(IllegalArgumentException.class, () ->
                new Barbeiro(1L, "Carlos", "", "Corte masculino")
        );
    }

    @Test
    void naoDeveCriarBarbeiroComEspecialidadeVazia() {
        assertThrows(IllegalArgumentException.class, () ->
                new Barbeiro(1L, "Carlos", "31999999999", "")
        );
    }

    @Test
    void deveAlterarNomeDoBarbeiro() {
        Barbeiro barbeiro = new Barbeiro(
                1L, "Carlos", "31999999999", "Corte masculino"
        );

        barbeiro.setNome("Roberto");

        assertEquals("Roberto", barbeiro.getNome());
    }

    @Test
    void naoDeveAlterarNomeParaVazio() {
        Barbeiro barbeiro = new Barbeiro(
                1L, "Carlos", "31999999999", "Corte masculino"
        );

        assertThrows(IllegalArgumentException.class, () ->
                barbeiro.setNome("")
        );
    }

    @Test
    void deveAlterarTelefoneDoBarbeiro() {
        Barbeiro barbeiro = new Barbeiro(
                1L, "Carlos", "31999999999", "Corte masculino"
        );

        barbeiro.setTelefone("31888888888");

        assertEquals("31888888888", barbeiro.getTelefone());
    }

    @Test
    void deveAlterarEspecialidadeDoBarbeiro() {
        Barbeiro barbeiro = new Barbeiro(
                1L, "Carlos", "31999999999", "Corte masculino"
        );

        barbeiro.setEspecialidade("Barba");

        assertEquals("Barba", barbeiro.getEspecialidade());
    }
}
