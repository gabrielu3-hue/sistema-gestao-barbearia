package br.com.barbearia;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AgendamentoRepository {

    private final Map<Long, Agendamento> agendamentos = new LinkedHashMap<>();

    public void salvar(Agendamento agendamento) {
        validarAgendamento(agendamento);

        if (agendamentos.containsKey(agendamento.getId())) {
            throw new IllegalArgumentException("Ja existe agendamento com esse ID");
        }

        agendamentos.put(agendamento.getId(), agendamento);
    }

    public Optional<Agendamento> buscarPorId(Long id) {
        validarId(id);
        return Optional.ofNullable(agendamentos.get(id));
    }

    public List<Agendamento> listarTodos() {
        return new ArrayList<>(agendamentos.values());
    }

    public void atualizar(Agendamento agendamento) {
        validarAgendamento(agendamento);
        validarExistencia(agendamento.getId());

        agendamentos.put(agendamento.getId(), agendamento);
    }

    public void excluir(Long id) {
        validarId(id);
        validarExistencia(id);
        agendamentos.remove(id);
    }

    private void validarAgendamento(Agendamento agendamento) {
        if (agendamento == null) {
            throw new IllegalArgumentException("Agendamento nao pode ser nulo");
        }
    }

    private void validarId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID nao pode ser nulo");
        }
    }

    private void validarExistencia(Long id) {
        if (!agendamentos.containsKey(id)) {
            throw new IllegalArgumentException("Agendamento nao encontrado");
        }
    }
}
