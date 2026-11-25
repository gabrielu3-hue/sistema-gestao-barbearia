package br.com.barbearia;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BarbeiroRepository {

    private final Map<Long, Barbeiro> barbeiros = new LinkedHashMap<>();

    // Valida o barbeiro informado
    private void validarBarbeiro(Barbeiro barbeiro) {
        if (barbeiro == null) {
            throw new IllegalArgumentException("O barbeiro não pode ser nulo.");
        }
    }

    // CREATE - Cadastrar barbeiro
    public void salvar(Barbeiro barbeiro) {
        validarBarbeiro(barbeiro);

        if (barbeiros.containsKey(barbeiro.getId())) {
            throw new IllegalArgumentException(
                    "Já existe um barbeiro com esse ID."
            );
        }

        barbeiros.put(barbeiro.getId(), barbeiro);
    }

    // READ - Listar todos os barbeiros
    public List<Barbeiro> listarTodos() {
        return new ArrayList<>(barbeiros.values());
    }

    // READ - Buscar barbeiro pelo ID
    public Barbeiro buscarPorId(Long id) {
        return barbeiros.get(id);
    }

    // UPDATE - Atualizar barbeiro
    public void atualizar(Long id, Barbeiro barbeiro) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("O ID deve ser positivo.");
        }

        validarBarbeiro(barbeiro);

        if (!barbeiros.containsKey(id)) {
            throw new IllegalArgumentException("Barbeiro não encontrado.");
        }

        if (!id.equals(barbeiro.getId())) {
            throw new IllegalArgumentException(
                    "O ID do barbeiro não corresponde ao ID informado."
            );
        }

        barbeiros.put(id, barbeiro);
    }

    // DELETE - Excluir barbeiro
    public void excluir(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("O ID deve ser positivo.");
        }

        if (!barbeiros.containsKey(id)) {
            throw new IllegalArgumentException("Barbeiro não encontrado.");
        }

        barbeiros.remove(id);
    }
}
