package InterfaceRepositorio;

import java.util.List;
import Entidades.Cliente;

public interface IClienteRepository {
	
    void adicionar(Cliente cliente);
    Cliente buscar(int id);
    Cliente buscar(String nome);
    void atualizar(Cliente cliente);
    void remover(int id);
    void remover(String nome);
    List<Cliente> listarTodos();
}