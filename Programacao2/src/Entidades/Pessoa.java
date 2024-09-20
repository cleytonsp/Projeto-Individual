package Entidades;

public abstract class Pessoa {
    private static int ultimoId = 0; 
    
    protected int id;
    protected String nome;
    protected String cnpj;
    protected String contato;
    protected String endereco;

    public Pessoa(String nome, String cnpj, String contato, String endereco) {
        this.id = gerarId();
        this.nome = nome;
        this.cnpj = cnpj;
        this.contato = contato;
        this.endereco = endereco;
    }

    private synchronized static int gerarId() {
        ultimoId++; 
        return ultimoId;
    }

 
    public int getId() {
        return id;
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
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}