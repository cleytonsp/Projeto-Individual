package InterfaceServicos;

import java.util.List;
import Entidades.Fornecedor;

public interface IFornecedorService {
    void cadastrar(Fornecedor fornecedor);
    Fornecedor buscar(int id);
    Fornecedor buscar(String nome);
    void atualizar(Fornecedor fornecedor);
    boolean remover(int id);
    void remover(String nome);
    List<Fornecedor> listarFornecedores(); // Método correto
}