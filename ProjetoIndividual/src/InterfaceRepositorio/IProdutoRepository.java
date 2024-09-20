package InterfaceRepositorio;

import java.util.List;
import Entidades.Produto;

public interface IProdutoRepository {
	
    void adicionar(Produto produto);
    void atualizar(Produto produto);
    void remover(int id);
    List<Produto> listarTodos();
}