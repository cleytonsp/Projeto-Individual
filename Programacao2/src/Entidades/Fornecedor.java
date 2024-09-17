package Entidades;

public class Fornecedor {
    private int id;
    private String nome;
    private String cnpj;
    private String contato;
    private String endereco; // Novo campo

    // Construtor atualizado
    public Fornecedor(int id, String nome, String cnpj, String contato, String endereco) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.contato = contato;
        this.endereco = endereco; // Inicialização do novo campo
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getEndereco() {
        return endereco; // Getter para o novo campo
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco; // Setter para o novo campo
    }
}