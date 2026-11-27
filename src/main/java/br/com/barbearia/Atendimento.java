package br.com.barbearia;

import java.time.LocalDateTime;

public class Atendimento {

    private Long id;
    private Agendamento agendamento;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private String status;
    private String observacoes;

    public Atendimento(Long id, Agendamento agendamento,
                       LocalDateTime dataInicio, LocalDateTime dataFim,
                       String status, String observacoes) {
        setId(id);
        setAgendamento(agendamento);
        setDataInicio(dataInicio);
        setDataFim(dataFim);
        setStatus(status);
        setObservacoes(observacoes);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("O ID deve ser positivo.");
        }
        this.id = id;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        if (agendamento == null) {
            throw new IllegalArgumentException("O agendamento é obrigatório.");
        }
        this.agendamento = agendamento;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        if (dataInicio == null) {
            throw new IllegalArgumentException("A data de início é obrigatória.");
        }
        if (dataFim != null && !dataFim.isAfter(dataInicio)) {
            throw new IllegalArgumentException("A data de fim deve ser posterior ao início.");
        }
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        if (dataFim == null) {
            throw new IllegalArgumentException("A data de fim é obrigatória.");
        }
        if (dataInicio != null && !dataFim.isAfter(dataInicio)) {
            throw new IllegalArgumentException("A data de fim deve ser posterior ao início.");
        }
        this.dataFim = dataFim;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("O status é obrigatório.");
        }
        this.status = status;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
