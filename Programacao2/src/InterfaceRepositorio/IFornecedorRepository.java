package InterfaceRepositorio;

import java.util.List;
import Entidades.Fornecedor;

public interface IFornecedorRepository {
    
   
    void adicionar(Fornecedor fornecedor);
    Fornecedor buscarPorId(int id);
    void atualizar(Fornecedor fornecedor);
    void removerPorId(int id);
    void removerPorNome(String nome);
    List<Fornecedor> listarTodos();
}