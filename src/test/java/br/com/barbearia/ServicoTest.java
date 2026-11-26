package br.com.barbearia;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ServicoTest {

    @Test
    void deveCriarServicoComDadosValidos() {
        Servico servico = new Servico(1L, "Corte de cabelo", "Corte masculino", 40.0);
        assertEquals(1L, servico.getId());
        assertEquals("Corte de cabelo", servico.getNome());
        assertEquals("Corte masculino", servico.getDescricao());
        assertEquals(40.0, servico.getPreco());
    }

    @Test
    void naoDeveCriarServicoComIdNulo() {
        assertThrows(IllegalArgumentException.class, () -> new Servico(null, "Corte", "Corte masculino", 40.0));
    }

    @Test
    void naoDeveCriarServicoComIdZero() {
        assertThrows(IllegalArgumentException.class, () -> new Servico(0L, "Corte", "Corte masculino", 40.0));
    }

    @Test
    void naoDeveCriarServicoComNomeNulo() {
        assertThrows(IllegalArgumentException.class, () -> new Servico(1L, null, "Corte masculino", 40.0));
    }

    @Test
    void naoDeveCriarServicoComNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> new Servico(1L, " ", "Corte masculino", 40.0));
    }

    @Test
    void naoDeveCriarServicoComDescricaoNula() {
        assertThrows(IllegalArgumentException.class, () -> new Servico(1L, "Corte", null, 40.0));
    }

    @Test
    void naoDeveCriarServicoComDescricaoVazia() {
        assertThrows(IllegalArgumentException.class, () -> new Servico(1L, "Corte", " ", 40.0));
    }

    @Test
    void naoDeveCriarServicoComPrecoZero() {
        assertThrows(IllegalArgumentException.class, () -> new Servico(1L, "Corte", "Corte masculino", 0.0));
    }

    @Test
    void naoDeveCriarServicoComPrecoNegativo() {
        assertThrows(IllegalArgumentException.class, () -> new Servico(1L, "Corte", "Corte masculino", -10.0));
    }

    @Test
    void deveAlterarNomeDoServico() {
        Servico servico = new Servico(1L, "Corte", "Corte masculino", 40.0);
        servico.setNome("Corte degradê");
        assertEquals("Corte degradê", servico.getNome());
    }

    @Test
    void naoDeveAlterarNomeParaVazio() {
        Servico servico = new Servico(1L, "Corte", "Corte masculino", 40.0);
        assertThrows(IllegalArgumentException.class, () -> servico.setNome(" "));
    }

    @Test
    void deveAlterarDescricaoDoServico() {
        Servico servico = new Servico(1L, "Corte", "Corte masculino", 40.0);
        servico.setDescricao("Corte com máquina e tesoura");
        assertEquals("Corte com máquina e tesoura", servico.getDescricao());
    }

    @Test
    void naoDeveAlterarDescricaoParaNula() {
        Servico servico = new Servico(1L, "Corte", "Corte masculino", 40.0);
        assertThrows(IllegalArgumentException.class, () -> servico.setDescricao(null));
    }

    @Test
    void deveAlterarPrecoDoServico() {
        Servico servico = new Servico(1L, "Corte", "Corte masculino", 40.0);
        servico.setPreco(45.0);
        assertEquals(45.0, servico.getPreco());
    }

    @Test
    void naoDeveAlterarPrecoParaNegativo() {
        Servico servico = new Servico(1L, "Corte", "Corte masculino", 40.0);
        assertThrows(IllegalArgumentException.class, () -> servico.setPreco(-5.0));
    }
}
