package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

// Classe utilitária para gerenciar conexões JPA com o banco de dados
public class JPAUtil {

    // Fábrica de EntityManagers, configurada com a unidade de persistência "sistema_doacao"
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("sistema_doacao");

    // Retorna um EntityManager para realizar operações no banco
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Fecha a fábrica de EntityManagers ao encerrar a aplicação
    public static void close() {
        emf.close();
    }
}
