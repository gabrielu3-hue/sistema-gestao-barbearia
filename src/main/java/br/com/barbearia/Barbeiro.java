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
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome não pode ser vazio.");
        }
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        if (telefone == null || telefone.isBlank()) {
            throw new IllegalArgumentException("O telefone não pode ser vazio.");
        }
        this.telefone = telefone;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        if (especialidade == null || especialidade.isBlank()) {
            throw new IllegalArgumentException("A especialidade não pode ser vazia.");
        }
        this.especialidade = especialidade;
    }
}
