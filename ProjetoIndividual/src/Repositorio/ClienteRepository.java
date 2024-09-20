package Repositorio;

import java.util.ArrayList;
import java.util.List;
import Entidades.Cliente;

public class ClienteRepository {
    private List<Cliente> clientes = new ArrayList<>();

    public void adicionar(Cliente cliente) {
        clientes.add(cliente);
    }

    public Cliente buscar(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }

    public Cliente buscar(String nome) {
        for (Cliente cliente : clientes) {
            if (cliente.getNome().equalsIgnoreCase(nome)) {
                return cliente;
            }
        }
        return null;
    }

    public void atualizar(Cliente cliente) {
        int index = encontrarIndiceClientePorId(cliente.getId());
        if (index >= 0) {
            clientes.set(index, cliente);
        }
    }

    public void remover(int id) {
        int index = encontrarIndiceClientePorId(id);
        if (index >= 0) {
            clientes.remove(index);
        }
    }

    public void remover(String nome) {
        Cliente cliente = buscar(nome);
        if (cliente != null) {
            clientes.remove(cliente);
        }
    }

    public List<Cliente> listarTodos() {
        return new ArrayList<>(clientes); 
    }

    private int encontrarIndiceClientePorId(int id) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
}