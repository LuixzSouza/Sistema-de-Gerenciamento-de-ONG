package utils;

import model.Doador;
import service.DoadorService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DoadorService doadorService = new DoadorService();

        while (true) {
            System.out.println("1 - Inserir Doador");
            System.out.println("2 - Encontrar Doador por CPF");
            System.out.println("3 - Excluir Doador");
            System.out.println("0 - Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    System.out.println("Digite o nome do doador:");
                    String nome = scanner.nextLine();
                    System.out.println("Digite o CPF:");
                    String cpf = scanner.nextLine();
                    System.out.println("Digite o email:");
                    String email = scanner.nextLine();
                    System.out.println("Digite o telefone:");
                    String telefone = scanner.nextLine();
                    System.out.println("Digite o endereço:");
                    String endereco = scanner.nextLine();

                    Doador novoDoador = new Doador(nome, cpf, email, telefone, endereco);
                    doadorService.inserirDoador(novoDoador);
                    break;
                case 2:
                    System.out.println("Digite o CPF:");
                    cpf = scanner.nextLine();
                    Doador doador = doadorService.encontrarDoadorPorCpf(cpf);
                    System.out.println(doador);
                    break;
                case 3:
                    System.out.println("Digite o CPF:");
                    cpf = scanner.nextLine();
                    doadorService.excluirDoador(cpf);
                    break;
                case 0:
                    JPAUtil.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
