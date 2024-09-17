package InterfaceRepositorio;

import java.util.List;
import Entidades.Produto;

public interface IProdutoRepository {
    /**
     * Adiciona um novo produto ao repositório.
     * @param produto O produto a ser adicionado.
     */
    void adicionar(Produto produto);

    /**
     * Atualiza as informações de um produto existente no repositório.
     * @param produto O produto com as informações atualizadas.
     */
    void atualizar(Produto produto);

    /**
     * Remove um produto do repositório pelo seu ID.
     * @param id O ID do produto a ser removido.
     */
    void remover(int id);

    /**
     * Lista todos os produtos presentes no repositório.
     * @return Uma lista contendo todos os produtos.
     */
    List<Produto> listarTodos();
}