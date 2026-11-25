package br.com.barbearia;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BarbeiroRepository {

    private final Map<Long, Barbeiro> barbeiros = new LinkedHashMap<>();

    private void validarBarbeiro(Barbeiro barbeiro) {
        if (barbeiro == null) {
            throw new IllegalArgumentException("O barbeiro não pode ser nulo.");
        }
    }

    private void validarId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("O ID deve ser positivo.");
        }
    }

    // CREATE
    public void salvar(Barbeiro barbeiro) {
        validarBarbeiro(barbeiro);

        if (barbeiros.containsKey(barbeiro.getId())) {
            throw new IllegalArgumentException(
                    "Já existe um barbeiro com esse ID."
            );
        }

        barbeiros.put(barbeiro.getId(), barbeiro);
    }

    // READ
    public List<Barbeiro> listarTodos() {
        return new ArrayList<>(barbeiros.values());
    }

    public Barbeiro buscarPorId(Long id) {
        validarId(id);
        return barbeiros.get(id);
    }

    // UPDATE
    public void atualizar(Long id, Barbeiro barbeiro) {
        validarId(id);
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

    // DELETE
    public void excluir(Long id) {
        validarId(id);

        if (!barbeiros.containsKey(id)) {
            throw new IllegalArgumentException("Barbeiro não encontrado.");
        }

        barbeiros.remove(id);
    }
}
