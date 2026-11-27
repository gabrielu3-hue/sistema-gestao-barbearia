package br.com.barbearia;

import java.time.LocalDateTime;

public class Pagamento {

    private Long id;
    private Agendamento agendamento;
    private double valor;
    private String formaPagamento;
    private String status;
    private LocalDateTime dataPagamento;

    public Pagamento(Long id, Agendamento agendamento, double valor,
                     String formaPagamento, String status, LocalDateTime dataPagamento) {
        setId(id);
        setAgendamento(agendamento);
        setValor(valor);
        setFormaPagamento(formaPagamento);
        setStatus(status);
        setDataPagamento(dataPagamento);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID deve ser positivo");
        }
        this.id = id;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        if (agendamento == null) {
            throw new IllegalArgumentException("Agendamento nao pode ser nulo");
        }
        this.agendamento = agendamento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        if (!Double.isFinite(valor) || valor <= 0) {
            throw new IllegalArgumentException("Valor deve ser positivo e finito");
        }
        this.valor = valor;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        if (formaPagamento == null || formaPagamento.isBlank()) {
            throw new IllegalArgumentException("Forma de pagamento nao pode ser vazia");
        }
        this.formaPagamento = formaPagamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status nao pode ser vazio");
        }
        this.status = status;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        if (dataPagamento == null) {
            throw new IllegalArgumentException("Data do pagamento nao pode ser nula");
        }
        this.dataPagamento = dataPagamento;
    }
}
