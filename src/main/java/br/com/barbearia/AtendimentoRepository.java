package br.com.barbearia;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AtendimentoRepository {

    private final Map<Long, Atendimento> atendimentos = new LinkedHashMap<>();

    public void salvar(Atendimento atendimento) {
        validarAtendimento(atendimento);

        if (atendimentos.putIfAbsent(atendimento.getId(), atendimento) != null) {
            throw new IllegalArgumentException("Ja existe atendimento com esse ID");
        }
    }

    public Optional<Atendimento> buscarPorId(Long id) {
        validarId(id);
        return Optional.ofNullable(atendimentos.get(id));
    }

    public List<Atendimento> listarTodos() {
        return new ArrayList<>(atendimentos.values());
    }

    public void atualizar(Atendimento atendimento) {
        validarAtendimento(atendimento);
        validarExistencia(atendimento.getId());

        atendimentos.put(atendimento.getId(), atendimento);
    }

    public void excluir(Long id) {
        validarId(id);
        validarExistencia(id);
        atendimentos.remove(id);
    }

    private void validarAtendimento(Atendimento atendimento) {
        if (atendimento == null) {
            throw new IllegalArgumentException("Atendimento nao pode ser nulo");
        }
    }

    private void validarId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID nao pode ser nulo");
        }
    }

    private void validarExistencia(Long id) {
        if (!atendimentos.containsKey(id)) {
            throw new IllegalArgumentException("Atendimento nao encontrado");
        }
    }
}
