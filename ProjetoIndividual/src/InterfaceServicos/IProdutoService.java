package InterfaceServicos;

import java.util.List;
import Entidades.Produto;

public interface IProdutoService {

    void cadastrar(Produto produto);
    Produto buscar(int id);
    void atualizar(Produto produto);
    void remover(int id);
    List<Produto> listarProdutos();
}