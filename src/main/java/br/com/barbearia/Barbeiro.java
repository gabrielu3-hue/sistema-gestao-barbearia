package br.com.barbearia;

public class Barbeiro {

    private Long id;
    private String nome;
    private String telefone;
    private String especialidade;

    public Barbeiro(Long id, String nome, String telefone, String especialidade) {
        setId(id);
        setNome(nome);
        setTelefone(telefone);
        setEspecialidade(especialidade);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("O ID deve ser maior que zero.");
        }
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        validarTexto(nome, "O nome");
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        validarTexto(telefone, "O telefone");
        this.telefone = telefone;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        validarTexto(especialidade, "A especialidade");
        this.especialidade = especialidade;
    }

    private void validarTexto(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(campo + " não pode ser vazio.");
        }
    }
}
