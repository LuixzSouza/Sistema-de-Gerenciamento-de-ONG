package utils;

public class Validador {

    // Validação de CPF
	public static boolean validarCPF(String cpf) {
        return cpf != null && cpf.length() == 11 && cpf.matches("[0-9]+");
    }

}
