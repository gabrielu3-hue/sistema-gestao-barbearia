package br.com.barbearia;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PagamentoRepository {

    private final Map<Long, Pagamento> pagamentos = new LinkedHashMap<>();

    public void salvar(Pagamento pagamento) {
        validarPagamento(pagamento);

        if (pagamentos.containsKey(pagamento.getId())) {
            throw new IllegalArgumentException("Ja existe pagamento com esse ID");
        }

        pagamentos.put(pagamento.getId(), pagamento);
    }

    public Optional<Pagamento> buscarPorId(Long id) {
        validarId(id);
        return Optional.ofNullable(pagamentos.get(id));
    }

    public List<Pagamento> listarTodos() {
        return new ArrayList<>(pagamentos.values());
    }

    public void atualizar(Pagamento pagamento) {
        validarPagamento(pagamento);
        validarExistencia(pagamento.getId());
        pagamentos.put(pagamento.getId(), pagamento);
    }

    public void excluir(Long id) {
        validarId(id);
        validarExistencia(id);
        pagamentos.remove(id);
    }

    private void validarPagamento(Pagamento pagamento) {
        if (pagamento == null) {
            throw new IllegalArgumentException("Pagamento nao pode ser nulo");
        }
    }

    private void validarId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID nao pode ser nulo");
        }
    }

    private void validarExistencia(Long id) {
        if (!pagamentos.containsKey(id)) {
            throw new IllegalArgumentException("Pagamento nao encontrado");
        }
    }
}
