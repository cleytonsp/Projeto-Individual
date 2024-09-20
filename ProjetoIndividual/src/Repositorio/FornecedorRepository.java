package Repositorio;

import java.util.ArrayList;
import java.util.List;
import Entidades.Fornecedor;

public class FornecedorRepository {
    private List<Fornecedor> fornecedores = new ArrayList<>();

    // Adiciona um novo fornecedor
    public void adicionar(Fornecedor fornecedor) {
        fornecedores.add(fornecedor);
    }

    // Busca um fornecedor pelo ID
    public Fornecedor buscar(int id) {
        for (Fornecedor fornecedor : fornecedores) {
            if (fornecedor.getId() == id) {
                return fornecedor;
            }
        }
        return null; // Ou lance uma exceção
    }

    // Busca um fornecedor pelo nome
    public Fornecedor buscar(String nome) {
        for (Fornecedor fornecedor : fornecedores) {
            if (fornecedor.getNome().equalsIgnoreCase(nome)) {
                return fornecedor;
            }
        }
        return null; 
    }

    // Atualiza um fornecedor existente
    public void atualizar(Fornecedor fornecedor) {
        int index = encontrarIndiceFornecedorPorId(fornecedor.getId());
        if (index >= 0) {
            fornecedores.set(index, fornecedor);
        } else {
            throw new RuntimeException("Fornecedor não encontrado para atualização.");
        }
    }

    // Remove um fornecedor pelo ID
    public boolean remover(int id) {
        int index = encontrarIndiceFornecedorPorId(id);
        if (index >= 0) {
            fornecedores.remove(index);
            return true;
        }
        return false; 
    }

    // Remove um fornecedor pelo nome
    public boolean remover(String nome) {
        Fornecedor fornecedor = buscar(nome);
        if (fornecedor != null) {
            fornecedores.remove(fornecedor);
            return true; 
        }
        return false; 
    }

    // Lista todos os fornecedores
    public List<Fornecedor> listarTodos() {
        return fornecedores;
    }

    // Encontra o índice de um fornecedor pelo ID
    private int encontrarIndiceFornecedorPorId(int id) {
        for (int i = 0; i < fornecedores.size(); i++) {
            if (fornecedores.get(i).getId() == id) {
                return i;
            }
        }
        return -1; 
    }
}