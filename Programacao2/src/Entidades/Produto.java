package Entidades;

public class Produto {
    private static int ultimoId = 0; 
    
    protected int id;
    private String nome;
    private double preco;
    private int estoque;
    private Fornecedor fornecedor;
    private EnumProdutoTipo tipo;

    public Produto(String nome, double preco, int estoque, Fornecedor fornecedor, EnumProdutoTipo tipo) {
        this.id = gerarId();
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
        this.fornecedor = fornecedor;
        this.tipo = tipo;
    }

    private static synchronized int gerarId() {
        return ++ultimoId; 
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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public EnumProdutoTipo getTipo() {
        return tipo;
    }

    public void setTipo(EnumProdutoTipo tipo) {
        this.tipo = tipo;
    }
}