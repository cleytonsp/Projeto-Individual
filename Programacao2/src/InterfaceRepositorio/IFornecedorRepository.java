package InterfaceRepositorio;

import java.util.List;
import Entidades.Fornecedor;

public interface IFornecedorRepository {
    
    /**
     * Adiciona um novo fornecedor ao repositório.
     * 
     * @param fornecedor O fornecedor a ser adicionado.
     */
    void adicionar(Fornecedor fornecedor);
    
    /**
     * Busca um fornecedor pelo ID.
     * 
     * @param id O ID do fornecedor.
     * @return O fornecedor correspondente ao ID ou null se não encontrado.
     */
    Fornecedor buscarPorId(int id);
    
    /**
     * Busca um fornecedor pelo nome.
     * 
     * @param nome O nome do fornecedor.
     * @return O fornecedor correspondente ao nome ou null se não encontrado.
     */
    Fornecedor buscarPorNome(String nome);
    
    /**
     * Atualiza as informações de um fornecedor existente.
     * 
     * @param fornecedor O fornecedor com as informações atualizadas.
     */
    void atualizar(Fornecedor fornecedor);
    
    /**
     * Remove um fornecedor pelo ID.
     * 
     * @param id O ID do fornecedor a ser removido.
     */
    void removerPorId(int id);
    
    /**
     * Remove um fornecedor pelo nome.
     * 
     * @param nome O nome do fornecedor a ser removido.
     */
    void removerPorNome(String nome);
    
    /**
     * Lista todos os fornecedores.
     * 
     * @return Uma lista de todos os fornecedores.
     */
    List<Fornecedor> listarTodos();
}