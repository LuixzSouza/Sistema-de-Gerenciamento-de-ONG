package utils;

import model.Doador;
import model.ItemDoacao;
import model.Doacao;
import service.DoadorService;
import service.ItemDoacaoService;
import service.DoacaoService;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final DoadorService doadorService = new DoadorService();
    private static final ItemDoacaoService itemDoacaoService = new ItemDoacaoService();
    private static final DoacaoService doacaoService = new DoacaoService();

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Gerenciar Doador");
            System.out.println("2. Gerenciar Doações");
            System.out.println("3. Gerenciar Itens de Doação");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                	gerenciarDoador(scanner);
                    break;
                case 2:
                	gerenciarDoacoes(scanner);
                    break;
                case 3:
                	gerenciarItens(scanner);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } while (opcao != 0);

        scanner.close(); // Fechar o Scanner
    }
    // Gerenciar Doador
    private static void gerenciarDoador(Scanner scanner) {
    	int opcao;
    	do {
            System.out.println("\n=== Gerenciar Doadores ===");
            System.out.println("1. Adicionar Doador");
            System.out.println("2. Encontrar Doador por ID");
            System.out.println("3. Atualizar Doador por ID");
            System.out.println("4. Excluir Doador por ID");
            System.out.println("5. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1: inserirDoador(scanner); break;
                case 2: encontrarDoador(scanner); break;
                case 3: alterarDoador(scanner); break;
                case 4: excluirDoador(scanner); break;
                case 5: System.out.println("Voltando ao menu principal..."); break;
                default: System.out.println("Opção inválida."); break;
            }
        } while (opcao != 5);
    }

    private static void inserirDoador(Scanner scanner) {
        System.out.print("Digite o nome do doador: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o CPF do doador: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite o email do doador: ");
        String email = scanner.nextLine();
        System.out.print("Digite o telefone do doador: ");
        String telefone = scanner.nextLine();
        System.out.print("Digite o endereço do doador: ");
        String endereco = scanner.nextLine();

        if (!utils.Validador.validarCPF(cpf)) {
            System.out.println("CPF inválido. Operação cancelada.");
            return;
        }

        Doador doador = new Doador(nome, cpf, email, telefone, endereco);
        doadorService.salvarDoador(doador);
        System.out.println("Doador salvo com sucesso.");
    }

    private static void encontrarDoador(Scanner scanner) {
        System.out.print("Digite o ID do doador: ");
        Long id = scanner.nextLong();
        Doador doador = doadorService.buscarDoadorPorId(id);

        if (doador != null) {
            System.out.println(doador);
        } else {
            System.out.println("Doador não encontrado.");
        }
    }

    private static void excluirDoador(Scanner scanner) {
        System.out.print("Digite o ID do doador a ser excluído: ");
        Long id = scanner.nextLong();
        doadorService.removerDoador(id);
        System.out.println("Doador excluído com sucesso.");
    }

    private static void alterarDoador(Scanner scanner) {
        System.out.print("Digite o ID do doador a ser alterado: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Limpar o buffer
        Doador doador = doadorService.buscarDoadorPorId(id);

        if (doador != null) {
            System.out.print("Digite o novo nome do doador: ");
            doador.setNome(scanner.nextLine());
            System.out.print("Digite o novo email do doador: ");
            doador.setEmail(scanner.nextLine());
            System.out.print("Digite o novo telefone do doador: ");
            doador.setTelefone(scanner.nextLine());
            System.out.print("Digite o novo endereço do doador: ");
            doador.setEndereco(scanner.nextLine());

            doadorService.atualizarDoador(doador);
            System.out.println("Doador alterado com sucesso.");
        } else {
            System.out.println("Doador não encontrado.");
        }
    }

    // Gerenciar Doações
    private static void gerenciarDoacoes(Scanner scanner) {
    int opcao;
    do {
        System.out.println("\n=== Gerenciar Doações ===");
        System.out.println("1. Adicionar Doação");
        System.out.println("2. Encontrar Doação por ID");
        System.out.println("3. Atualizar Doação por ID");
        System.out.println("4. Excluir Doação por ID");
        System.out.println("5. Voltar ao Menu Principal");
        System.out.print("Escolha uma opção: ");
        opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        switch (opcao) {
            case 1: adicionarDoacao(scanner); break;
            case 2: encontrarDoacao(scanner); break;
            case 3: atualizarDoacao(scanner); break;
            case 4: excluirDoacao(scanner); break;
            case 5: System.out.println("Voltando ao menu principal..."); break;
            default: System.out.println("Opção inválida."); break;
        }
    } while (opcao != 5);
}

	// Adicionar uma doação
	private static void adicionarDoacao(Scanner scanner) {
	    // Perguntar pelo nome da doação
	    System.out.print("Digite o nome da doação: ");
	    String nome = scanner.nextLine();  // Captura o nome da doação
	    
	    System.out.print("Digite o ID do doador associado à doação: ");
	    Long doadorId = scanner.nextLong();
	    scanner.nextLine();  // Limpar o buffer do scanner
	
	    // Criar e salvar a doação (aqui mantendo o código sem alteração estrutural)
	    Doacao doacao = doacaoService.addDoacao(nome, doadorId);
	    System.out.println("Doação criada com sucesso: " + doacao);
	}
	
	// Encontrar uma doação
	private static void encontrarDoacao(Scanner scanner) {
	    System.out.print("Digite o ID da doação: ");
	    Long id = scanner.nextLong();
	    Doacao doacao = doacaoService.buscarDoacaoPorId(id);
	
	    if (doacao != null) {
	        System.out.println(doacao);
	    } else {
	        System.out.println("Doação não encontrada.");
	    }
	}
	
	// Atualizar uma doação
	private static void atualizarDoacao(Scanner scanner) {
	    System.out.print("Digite o ID da doação a ser atualizada: ");
	    Long id = scanner.nextLong();
	    scanner.nextLine(); // Limpar o buffer do scanner

	    Doacao doacao = doacaoService.buscarDoacaoPorId(id);
	    if (doacao == null) {
	        System.out.println("Doação não encontrada.");
	        return;
	    }

	    System.out.println("Doação atual encontrada: " + doacao);

	    System.out.print("Digite o novo nome da doação (ou pressione Enter para manter o atual): ");
	    String novoNome = scanner.nextLine();
	    if (!novoNome.isEmpty()) {
	        doacao.setNome(novoNome);
	    }

	    try {
	        doacaoService.atualizarDoacao(doacao);
	        System.out.println("Doação atualizada com sucesso.");
	    } catch (RuntimeException e) {
	        System.out.println("Erro ao atualizar a doação: " + e.getMessage());
	    }
	}
	
	// Excluir uma doação
	private static void excluirDoacao(Scanner scanner) {
	    System.out.print("Digite o ID da doação a ser excluída: ");
	    Long id = scanner.nextLong();
	    scanner.nextLine(); // Limpar o buffer do scanner

	    Doacao doacao = doacaoService.buscarDoacaoPorId(id);
	    if (doacao == null) {
	        System.out.println("Doação não encontrada.");
	        return;
	    }

	    System.out.println("Doação encontrada: " + doacao);

	    System.out.print("Tem certeza de que deseja excluir esta doação? (S/N): ");
	    String confirmacao = scanner.nextLine();

	    if (confirmacao.equalsIgnoreCase("S")) {
	        try {
	            doacaoService.removerDoacao(id);
	            System.out.println("Doação excluída com sucesso.");
	        } catch (RuntimeException e) {
	            System.out.println("Erro ao excluir a doação: " + e.getMessage());
	        }
	    } else {
	        System.out.println("A exclusão foi cancelada.");
	    }
	}


	

    // Gerenciar Itens
    private static void gerenciarItens(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\n=== Gerenciar Itens ===");
            System.out.println("1. Adicionar Item");
            System.out.println("2. Encontrar Item por ID");
            System.out.println("3. Excluir Item");
            System.out.println("4. Alterar Item");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    adicionarItem(scanner);
                    break;
                case 2:
                    encontrarItem(scanner);
                    break;
                case 3:
                    excluirItem(scanner);
                    break;
                case 4:
                    alterarItem(scanner);
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } while (opcao != 0);
    }

    public static void adicionarItem(Scanner scanner) {
        // Capturar os dados inseridos pelo usuário
        System.out.print("Digite o nome do item: ");
        String nome = scanner.nextLine();
        System.out.print("Digite a descrição do item: ");
        String descricao = scanner.nextLine();
        System.out.print("Digite a categoria do item: ");
        String categoria = scanner.nextLine();
        System.out.print("Digite o ID da doação associada: ");
        Long idDoacao = scanner.nextLong();
        scanner.nextLine();  // Limpar o buffer do scanner

        // Buscar a doação pelo ID fornecido
        Doacao doacao = doacaoService.buscarDoacaoPorId(idDoacao);
        if (doacao == null) {
            System.out.println("Doação não encontrada.");
            return;
        }

        // Criar e salvar o ItemDoacao
        ItemDoacao itemDoacao = new ItemDoacao(nome, descricao, categoria, doacao);
        itemDoacaoService.salvarItem(itemDoacao);
        System.out.println("Item de doação inserido com sucesso.");
    }

    private static void encontrarItem(Scanner scanner) {
        System.out.print("Digite o ID do item: ");
        long itemId = scanner.nextLong();
        ItemDoacao item = itemDoacaoService.encontrarItemPorId(itemId);

        if (item != null) {
            System.out.println(item);
        } else {
            System.out.println("Item não encontrado.");
        }
    }

    private static void excluirItem(Scanner scanner) {
        System.out.print("Digite o ID do item a ser excluído: ");
        long itemId = scanner.nextLong();
        itemDoacaoService.excluirItem(itemId);
        System.out.println("Item excluído com sucesso.");
    }

    private static void alterarItem(Scanner scanner) {
        System.out.print("Digite o ID do item a ser alterado: ");
        long itemId = scanner.nextLong();
        scanner.nextLine(); // Limpar o buffer
        ItemDoacao item = itemDoacaoService.encontrarItemPorId(itemId);

        if (item != null) {
            System.out.print("Digite o novo nome do item: ");
            item.setNome(scanner.nextLine());
            System.out.print("Digite a nova descrição do item: ");
            item.setDescricao(scanner.nextLine());
            System.out.print("Digite a nova categoria do item: ");
            item.setCategoria(scanner.nextLine());
            itemDoacaoService.salvarItem(item);
            System.out.println("Item alterado com sucesso.");
        } else {
            System.out.println("Item não encontrado.");
        }
    }
}
