package br.com.barbearia;

import java.time.LocalDateTime;

public class Agendamento {
    private Long id;
    private Cliente cliente;
    private Barbeiro barbeiro;
    private Servico servico;
    private LocalDateTime dataHora;
    private String status;

    public Agendamento(Long id, Cliente cliente, Barbeiro barbeiro,
                       Servico servico, LocalDateTime dataHora, String status) {
        setId(id);
        setCliente(cliente);
        setBarbeiro(barbeiro);
        setServico(servico);
        setDataHora(dataHora);
        setStatus(status);
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente nao pode ser nulo");
        }
        this.cliente = cliente;
    }

    public Barbeiro getBarbeiro() {
        return barbeiro;
    }

    public void setBarbeiro(Barbeiro barbeiro) {
        if (barbeiro == null) {
            throw new IllegalArgumentException("Barbeiro nao pode ser nulo");
        }
        this.barbeiro = barbeiro;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        if (servico == null) {
            throw new IllegalArgumentException("Servico nao pode ser nulo");
        }
        this.servico = servico;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        if (dataHora == null) {
            throw new IllegalArgumentException("Data e hora nao podem ser nulas");
        }
        this.dataHora = dataHora;
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
}
