package br.com.barbearia;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ClienteRepository {

    private final Map<Long, Cliente> clientes = new LinkedHashMap<>();

    // CREATE - Cadastrar cliente
    public void salvar(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("O cliente não pode ser nulo.");
        }

        if (clientes.containsKey(cliente.getId())) {
            throw new IllegalArgumentException("Já existe um cliente com esse ID.");
        }

        clientes.put(cliente.getId(), cliente);
    }

    // READ - Listar todos os clientes
    public List<Cliente> listarTodos() {
        return new ArrayList<>(clientes.values());
    }

    // READ - Buscar cliente pelo ID
    public Optional<Cliente> buscarPorId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("O ID deve ser positivo.");
        }

        return Optional.ofNullable(clientes.get(id));
    }

    // UPDATE - Atualizar cliente
    public void atualizar(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("O cliente não pode ser nulo.");
        }

        if (!clientes.containsKey(cliente.getId())) {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }

        clientes.put(cliente.getId(), cliente);
    }

    // DELETE - Excluir cliente
    public void excluir(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("O ID deve ser positivo.");
        }

        if (!clientes.containsKey(id)) {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }

        clientes.remove(id);
    }
}