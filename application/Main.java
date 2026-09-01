package application;

import model.Cliente;
import model.Produto;
import java.util.Scanner;

import service.ClienteService;
import service.ProdutoService;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProdutoService produtoService = new ProdutoService();
        ClienteService clienteService = new ClienteService();

        while(true) {
            System.out.println("" +
                    "====================================\n" +
                    "       SISTEMA DO AÇOUGUE\n" +
                    "====================================");

            System.out.print("1 - Produtos\n" +
                    "2 - Clientes\n" +
                    "3 - Realizar venda\n" +
                    "4 - Estoque\n" +
                    "5 - Histórico de vendas\n" +
                    "0 - Sair\n" +
                    "\n" +
                    "Escolha uma opção: ");

            int opcao_inicio = sc.nextInt();

            if (opcao_inicio == 0) {
                System.out.println("Encerrando o programa...");
                System.exit(0);
            }

            else if (opcao_inicio == 1) {
                System.out.println("========= PRODUTOS =========");
                System.out.print("1 - Cadastrar produto\n" +
                        "2 - Listar produtos\n" +
                        "3 - Buscar produto\n" +
                        "4 - Alterar produto\n" +
                        "5 - Excluir produto\n" +
                        "0 - Voltar\n" +
                        "\n" +
                        "Escolha: ");
                int op_produtos = sc.nextInt();

                if (op_produtos == 0) {
                    continue;
                }

                else if (op_produtos == 1) {
                    System.out.println("== CADASTRO ==");
                    System.out.println("id: ");
                    Integer id = sc.nextInt();
                    System.out.println("Nome: ");
                    sc.nextLine();
                    String nome = sc.nextLine();
                    System.out.println("Categoria: ");
                    String categoria = sc.nextLine();
                    System.out.println("Preço: ");
                    Double preco = sc.nextDouble();
                    System.out.println("Quantidade: ");
                    Double quantidade = sc.nextDouble();

                    Produto produto = new Produto(id, nome, categoria, preco, quantidade);

                    if (produtoService.cadastrar(produto)) {
                        System.out.println("Produto cadastrado com sucesso!");
                    } else {
                        System.out.println("Já existe um produto com esse ID.");
                    }

                }

                else if (op_produtos == 2) {
                    System.out.println("== ESTOQUE ==");
                    for(Produto p: produtoService.listar()) {
                        System.out.println(p);
                        System.out.println("--------------------");
                    }
                    sc.next();
                }

                else if (op_produtos == 3) {
                    System.out.println("== BUSCAR PRODUTO ==");
                    System.out.println("Digite o ID do produto: ");
                    Integer id_busca = sc.nextInt();
                    Produto produto = produtoService.buscar(id_busca);

                    if(produto == null) {
                        System.out.println("Produto não encontrado.");
                    }
                    else {
                        System.out.println(produto);
                    }

                    sc.next();
                }

                else if (op_produtos == 4) {
                    System.out.println("== ALTERAR PRODUTO ==");
                    System.out.println("Digite o ID do produto: ");
                    Integer id_produto_alterar = sc.nextInt();
                    Produto produto = produtoService.buscar(id_produto_alterar);
                    if (produto == null) {
                        System.out.println("Produto não encontrado.");
                    }
                    else {
                        System.out.println("Novo nome: ");
                        sc.nextLine();
                        String nome_novo = sc.nextLine();
                        System.out.println("Nova categoria: ");
                        String categoria_nova = sc.nextLine();
                        System.out.println("Novo preço: ");
                        double preco_novo = sc.nextDouble();
                        System.out.println("Nova quantidade: ");
                        double quantidade_nova = sc.nextDouble();
                        produtoService.alterar(id_produto_alterar, nome_novo, categoria_nova, preco_novo, quantidade_nova);
                        System.out.println("Produto alterado com sucesso!");
                    }
                }

                else if (op_produtos == 5) {
                    System.out.println("== EXCLUIR PRODUTO ==");
                    System.out.println("Digite o ID do produto: ");
                    Integer id_excluir = sc.nextInt();

                    if (produtoService.excluir(id_excluir)) {
                        System.out.println("Produto excluído com sucesso.");
                    } else {
                        System.out.println("Produto não encontrado.");
                    }
                }

            }

            else if(opcao_inicio == 2) {
                System.out.println("========= CLIENTES =========");
                System.out.print("1 - Cadastrar cliente\n" +
                        "2 - Listar clientes\n" +
                        "3 - Buscar cliente\n" +
                        "4 - Alterar cliente\n" +
                        "5 - Excluir cliente\n" +
                        "0 - Voltar\n" +
                        "\n" +
                        "Escolha: ");
                int op_clientes = sc.nextInt();

                if (op_clientes == 0) {
                    continue;
                }
                else if(op_clientes == 1) {
                    System.out.println("== CADASTRO ==");
                    System.out.println("id: ");
                    Integer id = sc.nextInt();
                    System.out.println("Nome: ");
                    sc.nextLine();
                    String nome = sc.nextLine();
                    System.out.println("CPF: ");
                    String cpf = sc.nextLine();
                    System.out.println("Telefone: ");
                    String telefone = sc.nextLine();

                    Cliente cliente = new Cliente(id, nome, cpf, telefone);

                    if (clienteService.cadastrar(cliente)) {
                        System.out.println("Cliente cadastrado com sucesso!");
                    } else {
                        System.out.println("Já existe um cliente com esse ID.");
                    }
                }

                else if(op_clientes == 2) {
                    System.out.println("== LISTA DE CLIENTES ==");
                    for(Cliente c: clienteService.listar()) {
                        System.out.println(c);
                        System.out.println("--------------------");
                    }
                    sc.next();
                }

                else if(op_clientes == 3) {
                    System.out.println("== BUSCAR CLIENTE ==");
                    System.out.println("Digite o ID do cliente: ");
                    Integer id_busca = sc.nextInt();
                    Cliente cliente = clienteService.buscar(id_busca);

                    if(cliente == null) {
                        System.out.println("Cliente não encontrado.");
                    }
                    else {
                        System.out.println(cliente);
                    }

                    sc.next();
                }

                else if(op_clientes == 4) {
                    System.out.println("== ALTERAR CLIENTE ==");
                    System.out.println("Digite o ID do cliente: ");
                    Integer id_cliente_alterar = sc.nextInt();
                    Cliente cliente = clienteService.buscar(id_cliente_alterar);

                    if (cliente == null) {
                        System.out.println("Cliente não encontrado.");
                    }
                    else {
                        System.out.println("Novo nome: ");
                        sc.nextLine();
                        String nome_novo = sc.nextLine();
                        System.out.println("Novo CPF: ");
                        String cpf_novo = sc.nextLine();
                        System.out.println("Novo telefone: ");
                        String telefone_novo = sc.nextLine();

                        clienteService.alterar(id_cliente_alterar, nome_novo, cpf_novo, telefone_novo);
                        System.out.println("Cliente alterado com sucesso!");
                    }
                }

                else if(op_clientes == 5) {
                    System.out.println("== EXCLUIR CLIENTE ==");
                    System.out.println("Digite o ID do cliente: ");
                    Integer id_excluir = sc.nextInt();

                    if (clienteService.excluir(id_excluir)) {
                        System.out.println("Cliente excluído com sucesso.");
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                }
            }
        }


    }
}
