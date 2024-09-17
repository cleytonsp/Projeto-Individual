package Servicos;

import Entidades.Fornecedor;
import Repositorio.FornecedorRepository;
import Util.QueueUtil;
import Util.StackUtil;

import java.util.List;

public class FornecedorService {
    private FornecedorRepository fornecedorRepository;
    private StackUtil stackUtil;
    private QueueUtil queueUtil;

    public FornecedorService(FornecedorRepository fornecedorRepository, StackUtil stackUtil, QueueUtil queueUtil) {
        this.fornecedorRepository = fornecedorRepository;
        this.stackUtil = stackUtil;
        this.queueUtil = queueUtil;
    }

    public void cadastrar(Fornecedor fornecedor) {
        if (fornecedor == null) {
            throw new IllegalArgumentException("O fornecedor não pode ser nulo.");
        }
        if (fornecedor.getNome() == null || fornecedor.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome do fornecedor é obrigatório.");
        }
        if (fornecedor.getCnpj() == null || !isValidCnpj(fornecedor.getCnpj())) {
            throw new IllegalArgumentException("O CNPJ do fornecedor deve ser válido.");
        }
        fornecedorRepository.adicionar(fornecedor);
        stackUtil.push("Fornecedor cadastrado: " + fornecedor.getNome());
        queueUtil.enqueue("Fornecedor cadastrado: " + fornecedor.getNome());
    }

    public Fornecedor buscar(int id) {
        Fornecedor fornecedor = fornecedorRepository.buscar(id);
        if (fornecedor == null) {
            throw new RuntimeException("Fornecedor com ID " + id + " não encontrado.");
        }
        return fornecedor;
    }

    public Fornecedor buscar(String nome) {
        Fornecedor fornecedor = fornecedorRepository.buscar(nome);
        if (fornecedor == null) {
            throw new RuntimeException("Fornecedor com nome " + nome + " não encontrado.");
        }
        return fornecedor;
    }

    public void atualizar(Fornecedor fornecedor) {
        if (fornecedor == null) {
            throw new IllegalArgumentException("O fornecedor não pode ser nulo.");
        }
        fornecedorRepository.atualizar(fornecedor);
        stackUtil.push("Fornecedor atualizado: " + fornecedor.getNome());
        queueUtil.enqueue("Fornecedor atualizado: " + fornecedor.getNome());
    }

    public boolean remover(int id) {
        Fornecedor fornecedor = fornecedorRepository.buscar(id);
        if (fornecedor != null) {
            fornecedorRepository.remover(id);
            stackUtil.push("Fornecedor removido: " + fornecedor.getNome());
            queueUtil.enqueue("Fornecedor removido: " + fornecedor.getNome());
            return true;
        }
        return false;
    }

    public List<Fornecedor> listarFornecedores() {
        return fornecedorRepository.listarTodos();
    }

    // Métodos para acessar a pilha e fila (opcional)
    public String verUltimaOperacao() {
        return stackUtil.peek();
    }

    public String processarProximoPedido() {
        return queueUtil.peek();
    }

    private boolean isValidCnpj(String cnpj) {
        // Implementação de validação do CNPJ
        if (cnpj == null || cnpj.isEmpty()) {
            return false;
        }
        // Adicione aqui a lógica real de validação do CNPJ
        return true; // Placeholder para validação real
    }
}