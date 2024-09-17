package Servicos;

import Entidades.Produto;
import Repositorio.ProdutoRepository;
import Util.QueueUtil;
import Util.StackUtil;

import java.util.List;

public class ProdutoService {
    private ProdutoRepository produtoRepository;
    private StackUtil stackUtil;
    private QueueUtil queueUtil;

    public ProdutoService(ProdutoRepository produtoRepository, StackUtil stackUtil, QueueUtil queueUtil) {
        this.produtoRepository = produtoRepository;
        this.stackUtil = stackUtil;
        this.queueUtil = queueUtil;
    }

    public void cadastrar(Produto produto) {
        if (produto.getNome() == null || produto.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto não pode ser vazio.");
        }
        if (produto.getPreco() <= 0) {
            throw new IllegalArgumentException("O preço do produto deve ser maior que zero.");
        }
        
        produtoRepository.adicionar(produto);
        stackUtil.push("Produto cadastrado: " + produto.getNome());
        queueUtil.enqueue("Produto cadastrado: " + produto.getNome());
    }

    public Produto buscar(int id) {
        Produto produto = produtoRepository.buscar(id);
        if (produto == null) {
            throw new RuntimeException("Produto com ID " + id + " não encontrado.");
        }
        return produto;
    }

    public void atualizar(Produto produto) {
        if (produto.getNome() == null || produto.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto não pode ser vazio.");
        }
        if (produto.getPreco() <= 0) {
            throw new IllegalArgumentException("O preço do produto deve ser maior que zero.");
        }

        produtoRepository.atualizar(produto);
        stackUtil.push("Produto atualizado: " + produto.getNome());
        queueUtil.enqueue("Produto atualizado: " + produto.getNome());
    }

    public void remover(int id) {
        Produto produto = produtoRepository.buscar(id);
        if (produto != null) {
            produtoRepository.remover(id);
            stackUtil.push("Produto removido: " + produto.getNome());
            queueUtil.enqueue("Produto removido: " + produto.getNome());
        } else {
            throw new RuntimeException("Produto com ID " + id + " não encontrado.");
        }
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.listarTodos();
    }

    public Produto buscarProdutoNaFila(int id) {
        return produtoRepository.buscarProdutoNaFila(id);
    }
}