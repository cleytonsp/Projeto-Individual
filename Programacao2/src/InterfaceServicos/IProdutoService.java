package InterfaceServicos;

import java.util.List;
import Entidades.Produto;

public interface IProdutoService {
    /**
     * Cadastra um novo produto.
     * @param produto O produto a ser cadastrado.
     */
    void cadastrar(Produto produto);

    /**
     * Busca um produto pelo seu ID.
     * @param id O ID do produto a ser buscado.
     * @return O produto encontrado, ou null se não encontrado.
     */
    Produto buscar(int id);

    /**
     * Atualiza as informações de um produto existente.
     * @param produto O produto com as informações atualizadas.
     */
    void atualizar(Produto produto);

    /**
     * Remove um produto pelo seu ID.
     * @param id O ID do produto a ser removido.
     */
    void remover(int id);

    /**
     * Lista todos os produtos cadastrados.
     * @return Uma lista de produtos.
     */
    List<Produto> listarProdutos();
}