package Repositorio;

import java.util.ArrayList;
import java.util.List;
import Entidades.Fornecedor;

public class FornecedorRepository {
    private List<Fornecedor> fornecedores = new ArrayList<>();

    public void adicionar(Fornecedor fornecedor) {
        fornecedores.add(fornecedor);
    }

    public Fornecedor buscar(int id) {
        for (Fornecedor fornecedor : fornecedores) {
            if (fornecedor.getId() == id) {
                return fornecedor;
            }
        }
        return null;
    }

    public Fornecedor buscar(String nome) {
        for (Fornecedor fornecedor : fornecedores) {
            if (fornecedor.getNome().equalsIgnoreCase(nome)) {
                return fornecedor;
            }
        }
        return null;
    }

    public void atualizar(Fornecedor fornecedor) {
        int index = encontrarIndiceFornecedorPorId(fornecedor.getId());
        if (index >= 0) {
            fornecedores.set(index, fornecedor);
        }
    }

    public boolean remover(int id) {
        int index = encontrarIndiceFornecedorPorId(id);
        if (index >= 0) {
            fornecedores.remove(index);
            return true;
        }
        return false;
    }

    public void remover(String nome) {
        Fornecedor fornecedor = buscar(nome);
        if (fornecedor != null) {
            fornecedores.remove(fornecedor);
        }
    }

    public List<Fornecedor> listarTodos() {
        return fornecedores;
    }

    private int encontrarIndiceFornecedorPorId(int id) {
        for (int i = 0; i < fornecedores.size(); i++) {
            if (fornecedores.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
}