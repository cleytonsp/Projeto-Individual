package Servicos;

import java.util.List;
import Entidades.Cliente;
import InterfaceServicos.IClienteService;
import Repositorio.ClienteRepository;
import Util.QueueUtil;
import Util.StackUtil;

public class ClienteService implements IClienteService {

    private ClienteRepository clienteRepository;
    private StackUtil stackUtil;
    private QueueUtil queueUtil;

    // Construtor atualizado
    public ClienteService(ClienteRepository clienteRepository, StackUtil stackUtil, QueueUtil queueUtil) {
        this.clienteRepository = clienteRepository;
        this.stackUtil = stackUtil;
        this.queueUtil = queueUtil;
    }

    @Override
    public void cadastrar(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("O cliente não pode ser nulo.");
        }
        if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome do cliente é obrigatório.");
        }
        if (cliente.getCnpj() == null || !isValidCnpj(cliente.getCnpj())) {
            throw new IllegalArgumentException("O CNPJ do cliente deve ser válido.");
        }
        if (cliente.getEndereco() == null || cliente.getEndereco().isEmpty()) {
            throw new IllegalArgumentException("O endereço do cliente é obrigatório.");
        }
        clienteRepository.adicionar(cliente);
        stackUtil.push("Cliente cadastrado: " + cliente.getNome());
        queueUtil.enqueue("Cliente cadastrado: " + cliente.getNome());
    }

    @Override
    public Cliente buscar(int id) {
        Cliente cliente = clienteRepository.buscar(id);
        if (cliente == null) {
            throw new RuntimeException("Cliente com ID " + id + " não encontrado.");
        }
        return cliente;
    }

    @Override
    public Cliente buscar(String nome) {
        Cliente cliente = clienteRepository.buscar(nome);
        if (cliente == null) {
            throw new RuntimeException("Cliente com nome " + nome + " não encontrado.");
        }
        return cliente;
    }

    @Override
    public void atualizar(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("O cliente não pode ser nulo.");
        }
        if (cliente.getEndereco() == null || cliente.getEndereco().isEmpty()) {
            throw new IllegalArgumentException("O endereço do cliente é obrigatório.");
        }
        clienteRepository.atualizar(cliente);
        stackUtil.push("Cliente atualizado: " + cliente.getNome());
        queueUtil.enqueue("Cliente atualizado: " + cliente.getNome());
    }

    @Override
    public void remover(int id) {
        Cliente cliente = clienteRepository.buscar(id);
        if (cliente == null) {
            throw new RuntimeException("Cliente com ID " + id + " não encontrado.");
        }
        clienteRepository.remover(id);
        stackUtil.push("Cliente removido: " + cliente.getNome());
        queueUtil.enqueue("Cliente removido: " + cliente.getNome());
    }

    @Override
    public void remover(String nome) {
        Cliente cliente = clienteRepository.buscar(nome);
        if (cliente == null) {
            throw new RuntimeException("Cliente com nome " + nome + " não encontrado.");
        }
        clienteRepository.remover(nome);
        stackUtil.push("Cliente removido: " + cliente.getNome());
        queueUtil.enqueue("Cliente removido: " + cliente.getNome());
    }

    @Override
    public List<Cliente> listar() {
        return clienteRepository.listarTodos();
    }

    private boolean isValidCnpj(String cnpj) {
        // Implementação de validação do CNPJ
        if (cnpj == null || cnpj.isEmpty()) {
            return false;
        }
        // Adicione aqui a lógica real de validação do CNPJ
        return true; // Placeholder para validação real
    }

    // Métodos para acessar a pilha e fila (opcional)
    public String verUltimaOperacao() {
        return stackUtil.peek();
    }

    public String processarProximoPedido() {
        return queueUtil.peek();
    }
}