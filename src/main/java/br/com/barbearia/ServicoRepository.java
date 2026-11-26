package br.com.barbearia;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ServicoRepository {

    private final Map<Long, Servico> servicos = new LinkedHashMap<>();

    private void validarServico(Servico servico) {
        if (servico == null) {
            throw new IllegalArgumentException("O serviço não pode ser nulo.");
        }
    }

    private void validarId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("O ID deve ser positivo.");
        }
    }

    // CREATE
    public void salvar(Servico servico) {
        validarServico(servico);

        if (servicos.containsKey(servico.getId())) {
            throw new IllegalArgumentException(
                    "Já existe um serviço com esse ID."
            );
        }

        servicos.put(servico.getId(), servico);
    }

    // READ
    public List<Servico> listarTodos() {
        return new ArrayList<>(servicos.values());
    }

    public Servico buscarPorId(Long id) {
        validarId(id);
        return servicos.get(id);
    }

    // UPDATE
    public void atualizar(Long id, Servico servico) {
        validarId(id);
        validarServico(servico);

        if (!servicos.containsKey(id)) {
            throw new IllegalArgumentException("Serviço não encontrado.");
        }

        if (!id.equals(servico.getId())) {
            throw new IllegalArgumentException(
                    "O ID do serviço não corresponde ao ID informado."
            );
        }

        servicos.put(id, servico);
    }

    // DELETE
    public void excluir(Long id) {
        validarId(id);

        if (!servicos.containsKey(id)) {
            throw new IllegalArgumentException("Serviço não encontrado.");
        }

        servicos.remove(id);
    }
}
