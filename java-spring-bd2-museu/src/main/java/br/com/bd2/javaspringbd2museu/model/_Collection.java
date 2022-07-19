package br.com.bd2.javaspringbd2museu.model;

public class _Collection {
    private String nome;
    private String telefone;
    private String tipo;
    private String descricao;
    private String endereco;
    private String pessoa_contato;

    public _Collection() {
    }

    public _Collection(
        String nome,
        String telefone,
        String tipo,
        String descricao,
        String endereco,
        String pessoa_contato
    ) {
        this.nome = nome;
        this.telefone = telefone;
        this.tipo = tipo;
        this.descricao = descricao;
        this.endereco = endereco;
        this.pessoa_contato = pessoa_contato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getPessoa_contato() {
        return pessoa_contato;
    }

    public void setPessoa_contato(String pessoa_contato) {
        this.pessoa_contato = pessoa_contato;
    }
}
