package Repositorio;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import Entidades.Produto;

public class ProdutoRepository {
    private List<Produto> produtos = new ArrayList<>();
    private Queue<Produto> filaDeProdutos = new LinkedList<>();

    public void adicionar(Produto produto) {
        produtos.add(produto);
        filaDeProdutos.add(produto);
        System.out.println("Produto " + produto.getNome() + " adicionado ao repositório e à fila.");
    }

    public Produto buscar(int id) {
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                return produto;
            }
        }
        System.out.println("Produto com ID " + id + " não encontrado.");
        return null;
    }
    
    public void atualizar(Produto produto) {
        int index = encontrarIndiceProdutoPorId(produto.getId());
        if (index >= 0) {
            produtos.set(index, produto);
            atualizarProdutoNaFila(produto); 
            System.out.println("Produto " + produto.getNome() + " atualizado no repositório e na fila.");
        } else {
            System.out.println("Produto com ID " + produto.getId() + " não encontrado para atualização.");
        }
    }

    public void remover(int id) {
        int index = encontrarIndiceProdutoPorId(id);
        if (index >= 0) {
            Produto produtoRemovido = produtos.remove(index);
            filaDeProdutos.remove(produtoRemovido); 
            System.out.println("Produto " + produtoRemovido.getNome() + " removido do repositório e da fila.");
        } else {
            System.out.println("Produto com ID " + id + " não encontrado para remoção.");
        }
    }

    public List<Produto> listarTodos() {
        return new ArrayList<>(produtos); 
    }

    public void adicionarProdutoNaFila(Produto produto) {
        filaDeProdutos.add(produto);
        System.out.println("Produto " + produto.getNome() + " adicionado à fila.");
    }

    public Produto removerProdutoDaFila() {
        Produto produtoRemovido = filaDeProdutos.poll(); 
        if (produtoRemovido != null) {
            System.out.println("Produto " + produtoRemovido.getNome() + " removido da fila.");
        } else {
            System.out.println("A fila está vazia.");
        }
        return produtoRemovido;
    }

    public Produto verProximoProdutoNaFila() {
        Produto proximoProduto = filaDeProdutos.peek(); 
        if (proximoProduto != null) {
            System.out.println("Próximo produto na fila: " + proximoProduto.getNome());
        } else {
            System.out.println("A fila está vazia.");
        }
        return proximoProduto;
    }

    public void exibirFilaDeProdutos() {
        if (filaDeProdutos.isEmpty()) {
            System.out.println("A fila está vazia.");
        } else {
            System.out.println("Produtos na fila:");
            for (Produto produto : filaDeProdutos) {
                System.out.println(" - " + produto.getNome());
            }
        }
    }

    public Produto buscarProdutoNaFila(int id) {
        for (Produto produto : filaDeProdutos) {
            if (produto.getId() == id) {
                System.out.println("Produto " + produto.getNome() + " encontrado na fila.");
                return produto;
            }
        }
        System.out.println("Produto com ID " + id + " não encontrado na fila.");
        return null;
    }

    private int encontrarIndiceProdutoPorId(int id) {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    private void atualizarProdutoNaFila(Produto produtoAtualizado) {
        Queue<Produto> novaFila = new LinkedList<>();
        for (Produto produto : filaDeProdutos) {
            if (produto.getId() == produtoAtualizado.getId()) {
                novaFila.add(produtoAtualizado); 
            } else {
                novaFila.add(produto); 
            }
        }
        filaDeProdutos = novaFila;
    }
}