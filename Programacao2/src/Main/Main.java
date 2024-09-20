package Main;

import java.util.List;
import java.util.Scanner;

import Entidades.Cliente;
import Entidades.Fornecedor;
import Entidades.Produto;
import Entidades.EnumProdutoTipo;
import Repositorio.ClienteRepository;
import Repositorio.FornecedorRepository;
import Repositorio.ProdutoRepository;
import Servicos.ClienteService;
import Servicos.FornecedorService;
import Servicos.ProdutoService;
import Util.QueueUtil;
import Util.StackUtil;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static ClienteRepository clienteRepository = new ClienteRepository();
    private static FornecedorRepository fornecedorRepository = new FornecedorRepository();
    private static ProdutoRepository produtoRepository = new ProdutoRepository();

    private static StackUtil stackUtil = new StackUtil();
    private static QueueUtil queueUtil = new QueueUtil();

    private static ClienteService clienteService = new ClienteService(clienteRepository, stackUtil, queueUtil);
    private static FornecedorService fornecedorService = new FornecedorService(fornecedorRepository, stackUtil, queueUtil);
    private static ProdutoService produtoService = new ProdutoService(produtoRepository, stackUtil, queueUtil);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Bem-vindo ao Menu de gerenciamento de estoque:");
            System.out.println("1. Gerenciar Clientes");
            System.out.println("2. Gerenciar Fornecedores");
            System.out.println("3. Gerenciar Produtos");
            System.out.println("0. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    gerenciarClientes();
                    break;

                case 2:
                    gerenciarFornecedores();
                    break;

                case 3:
                    gerenciarProdutos();
                    break;

                case 0:
                    System.out.println("Saindo...");
                    System.exit(0);

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }

    private static void gerenciarProdutos() {
        System.out.println("1. Cadastrar Produto");
        System.out.println("2. Atualizar Produto");
        System.out.println("3. Listar Produtos");
        System.out.println("4. Remover Produto");
        System.out.println("5. Buscar Produto na Fila");
        System.out.println("6. Adicionar Produto na Fila");
        System.out.println("7. Remover Produto da Fila");
        System.out.println("8. Ver Próximo Produto na Fila");
        System.out.println("9. Exibir Todos os Produtos na Fila");
        System.out.println("0. Voltar ao menu principal");

        int opcao = scanner.nextInt();
        scanner.nextLine(); 

        switch (opcao) {
            case 1:
                System.out.println("Nome:");
                String nome = scanner.nextLine();
                System.out.println("Preço:");
                double preco = scanner.nextDouble();
                System.out.println("Quantidade de Estoque:");
                int estoque = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Fornecedor (nome):");
                String nomeFornecedor = scanner.nextLine();

                Fornecedor fornecedor = fornecedorService.buscar(nomeFornecedor);
                if (fornecedor == null) {
                    System.out.println("Fornecedor não encontrado. Cadastrar fornecedor primeiro.");
                    break;
                }

                System.out.println("Tipo do produto (1 para Físico, 2 para Digital):");
                int tipo = scanner.nextInt();
                scanner.nextLine(); 
                EnumProdutoTipo produtoTipo = (tipo == 1) ? EnumProdutoTipo.PRODUTO_FISICO : EnumProdutoTipo.PRODUTO_DIGITAL;

                Produto produto = new Produto(nome, preco, estoque, fornecedor, produtoTipo);
                produtoService.cadastrar(produto);
                System.out.println("Produto cadastrado com sucesso.");
                break;

            case 2:

                System.out.print("ID do produto para atualizar: ");
                int idAtualizar = scanner.nextInt();
                scanner.nextLine(); 

                Produto produtoExistente = produtoService.buscar(idAtualizar);
                if (produtoExistente != null) {
                    System.out.println("Nome: " + produtoExistente.getNome());
                    System.out.println("Preço: " + produtoExistente.getPreco());
                    System.out.println("Fornecedor: " + produtoExistente.getFornecedor().getNome());
                    System.out.println("Tipo: " + produtoExistente.getTipo());

                    System.out.print("Novo Preço: ");
                    double novoPreco = scanner.nextDouble();
                    scanner.nextLine(); 

                    produtoExistente.setPreco(novoPreco);
                    produtoService.atualizar(produtoExistente);
                    System.out.println("Produto atualizado com sucesso.");
                } else {
                    System.out.println("Produto não encontrado!");
                }
                break;

            case 3:
                System.out.println("Listando todos os produtos:");
                List<Produto> produtos = produtoService.listarProdutos();
                for (Produto p : produtos) {
                    System.out.println("ID: " + p.getId() + ", Nome: " + p.getNome() + ", Preço: " + p.getPreco() + ", Fornecedor: " + p.getFornecedor().getNome() + ", Tipo: " + p.getTipo());
                }
                break;

            case 4:
                System.out.print("ID do produto para remover: ");
                int idRemover = scanner.nextInt();
                scanner.nextLine(); 

                produtoService.remover(idRemover);
                System.out.println("Produto removido com sucesso.");
                break;

            case 5:
                System.out.print("ID do produto para buscar na fila: ");
                int idBuscarFila = scanner.nextInt();
                scanner.nextLine(); 

                Produto produtoNaFila = produtoRepository.buscarProdutoNaFila(idBuscarFila);
                if (produtoNaFila != null) {
                    System.out.println("Produto encontrado na fila: ID " + produtoNaFila.getId() + ", Nome: " + produtoNaFila.getNome() + ", Preço: " + produtoNaFila.getPreco() + ", Tipo: " + produtoNaFila.getTipo());
                } else {
                    System.out.println("Produto não encontrado na fila.");
                }
                break;

            case 6:
                System.out.print("ID do produto para adicionar na fila: ");
                int idAdicionarFila = scanner.nextInt();
                scanner.nextLine(); 

                Produto produtoParaFila = produtoService.buscar(idAdicionarFila);
                if (produtoParaFila != null) {
                    produtoRepository.adicionarProdutoNaFila(produtoParaFila);
                } else {
                    System.out.println("Produto não encontrado!");
                }
                break;

            case 7:
                Produto produtoRemovido = produtoRepository.removerProdutoDaFila();
                if (produtoRemovido != null) {
                    System.out.println("Produto removido da fila: " + produtoRemovido.getNome());
                }
                break;

            case 8:
                Produto proximoProduto = produtoRepository.verProximoProdutoNaFila();
                if (proximoProduto != null) {
                    System.out.println("Próximo produto na fila: " + proximoProduto.getNome());
                }
                break;

            case 9:
                produtoRepository.exibirFilaDeProdutos();
                break;

            case 0:
                return;

            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

        private static void gerenciarFornecedores() {
            System.out.println("1. Cadastrar Fornecedor");
            System.out.println("2. Atualizar Fornecedor");
            System.out.println("3. Buscar Fornecedor");
            System.out.println("4. Listar Fornecedores");
            System.out.println("5. Remover Fornecedor");
            System.out.println("0. Voltar ao menu principal");
            
            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                  
                    System.out.println("Nome:");
                    String nome = scanner.nextLine();
                    System.out.println("CNPJ: ");
                    String cnpj = scanner.nextLine();
                    System.out.println("Contato: ");
                    String contato = scanner.nextLine();
                    System.out.println("Endereço: ");
                    String endereco = scanner.nextLine();

                  
                    Fornecedor fornecedor = new Fornecedor(nome, cnpj, contato, endereco);
                    fornecedorService.cadastrar(fornecedor);
                    System.out.println("Fornecedor cadastrado com sucesso.");
                    break;

            case 2:
                
                System.out.println("ID do fornecedor que deseja atualizar: ");
                int idAtualizar = scanner.nextInt();
                scanner.nextLine(); 
                
                Fornecedor fornecedorExistente = fornecedorService.buscar(idAtualizar);
                if (fornecedorExistente != null) {
                    System.out.println("Nome atual:" + fornecedorExistente.getNome());
                    System.out.println("ID atual: " + fornecedorExistente.getId());
                    System.out.println("Contato atual: " + fornecedorExistente.getContato());
                    System.out.println("Endereço atual: " + fornecedorExistente.getEndereco());
                    
                    System.out.println("Insira novo nome: ");
                    String novoNome = scanner.nextLine();
                    System.out.println("Insira novo contato: ");
                    String novoContato = scanner.nextLine();
                    System.out.println("Insira novo endereço: ");
                    String novoEndereco = scanner.nextLine();
                    
                    fornecedorExistente.setNome(novoNome);
                    fornecedorExistente.setContato(novoContato);
                    fornecedorExistente.setEndereco(novoEndereco);
                    
                    fornecedorService.atualizar(fornecedorExistente);
                    System.out.println("Fornecedor atualizado com sucesso!");
                } else {
                    System.out.println("Fornecedor com ID " + idAtualizar + " não encontrado.");
                }
                break;

            case 3:
                
                System.out.println("ID do fornecedor que deseja buscar: ");
                int idBuscar = scanner.nextInt();
                scanner.nextLine(); 
                
                Fornecedor fornecedorBuscado = fornecedorService.buscar(idBuscar);
                if (fornecedorBuscado != null) {
                    System.out.println("ID " + fornecedorBuscado.getId() + ", Nome: " + fornecedorBuscado.getNome() + ", Contato: " + fornecedorBuscado.getContato() + ", Endereço: " + fornecedorBuscado.getEndereco());
                } else {
                    System.out.println("Fornecedor com ID " + idBuscar + " não encontrado.");
                }
                break;

            case 4:
                
                System.out.println("Listando todos os fornecedores:");
                List<Fornecedor> fornecedores = fornecedorService.listarFornecedores();
                for (Fornecedor f : fornecedores) {
                    System.out.println("ID: " + f.getId() + ", Nome: " + f.getNome() + ", CNPJ: " + f.getCnpj() + ", Contato: " + f.getContato() + ", Endereço: " + f.getEndereco());
                }
                break;

            case 5:
                
                System.out.print("ID do fornecedor para remover: ");
                int idRemover = scanner.nextInt();
                scanner.nextLine(); 

                fornecedorService.remover(idRemover);
                System.out.println("Fornecedor removido com sucesso.");
                break;
                
            case 0: 
                return;

            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

    private static void gerenciarClientes() {
        System.out.println("1. Cadastrar Cliente");
        System.out.println("2. Atualizar Cliente");
        System.out.println("3. Buscar Cliente");
        System.out.println("4. Listar Clientes");
        System.out.println("5. Remover Cliente");
        System.out.println("0. Voltar ao menu principal");
        
        int opcao = scanner.nextInt();
        scanner.nextLine(); 

        switch (opcao) {
            case 1:
               
                System.out.println("Nome:");
                String nome = scanner.nextLine();
                System.out.println("CNPJ: ");
                String cnpj = scanner.nextLine();
                System.out.println("Endereço: ");
                String endereco = scanner.nextLine();
                System.out.println("Contato: ");
                String contato = scanner.nextLine();

             
                Cliente cliente = new Cliente(nome, cnpj, contato, endereco);
                clienteService.cadastrar(cliente);
                System.out.println("Cliente cadastrado com sucesso.");
                break;

            case 2:
                
                System.out.println("ID do cliente que deseja atualizar: ");
                int idAtualizar = scanner.nextInt();
                scanner.nextLine(); 
                
                Cliente clienteExistente = clienteService.buscar(idAtualizar);
                if (clienteExistente != null) {
                    System.out.println("Nome atual: " + clienteExistente.getNome());
                    System.out.println("Endereço atual: " + clienteExistente.getEndereco());
                    
                    System.out.println("Insira novo nome: ");
                    String novoNome = scanner.nextLine();
                    System.out.println("Insira novo endereço: ");
                    String novoEndereco = scanner.nextLine();
                    
                    clienteExistente.setNome(novoNome);
                    clienteExistente.setEndereco(novoEndereco);
                    
                    clienteService.atualizar(clienteExistente);
                    System.out.println("Cliente atualizado com sucesso!");
                } else {
                    System.out.println("Cliente com ID " + idAtualizar + " não encontrado.");
                }
                break;

            case 3:
               
                System.out.println("ID do cliente que deseja buscar: ");
                int idBuscar = scanner.nextInt();
                scanner.nextLine(); 
                
                Cliente clienteBuscado = clienteService.buscar(idBuscar);
                if (clienteBuscado != null) {
                    System.out.println("ID " + clienteBuscado.getId() + ", Nome: " + clienteBuscado.getNome() + ", Endereço: " + clienteBuscado.getEndereco());
                } else {
                    System.out.println("Cliente com ID " + idBuscar + " não encontrado.");
                }
                break;

            case 4:
                
                System.out.println("Listando todos os clientes:");
                List<Cliente> clientes = clienteService.listar();
                for (Cliente c : clientes) {
                    System.out.println("ID: " + c.getId() + ", Nome: " + c.getNome() + ", CNPJ: " + c.getCnpj() + ", Endereço: " + c.getEndereco());
                }
                break;

            case 5:
                
                System.out.println("ID do cliente para remover:");
                int idRemover = scanner.nextInt();
                scanner.nextLine(); 
                
                clienteService.remover(idRemover);
                System.out.println("Cliente removido com sucesso.");
                break;
                
            case 0: 
                return; 

            default:
                System.out.println("Opção inválida!");
                break;
        }
    }
}