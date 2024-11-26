package service;

import model.Doador;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import java.util.List;

public class DoadorService {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("sistema_doacao");
    private static final EntityManager em = emf.createEntityManager();

    public void salvarDoador(Doador doador) {
        try {
            em.getTransaction().begin();
            em.persist(doador);
            em.getTransaction().commit();  // Confirma a transação
        } catch (Exception e) {
            em.getTransaction().rollback();  // Desfaz a transação em caso de erro
            throw new RuntimeException("Erro ao salvar doador: " + e.getMessage(), e);
        }
    }

    public Doador buscarDoadorPorId(Long id) {
        return em.find(Doador.class, id);
    }

    public List<Doador> listarDoadores() {
        return em.createQuery("SELECT d FROM Doador d", Doador.class).getResultList();
    }

    public void atualizarDoador(Doador doador) {
        try {
            em.getTransaction().begin();
            em.merge(doador);
            em.getTransaction().commit();  // Confirma a transação
        } catch (Exception e) {
            em.getTransaction().rollback();  // Desfaz a transação em caso de erro
            throw new RuntimeException("Erro ao atualizar doador: " + e.getMessage(), e);
        }
    }

    public void removerDoador(Long id) {
        try {
            em.getTransaction().begin();
            Doador doador = em.find(Doador.class, id);
            if (doador != null) {
                em.remove(doador);
            }
            em.getTransaction().commit();  // Confirma a transação
        } catch (Exception e) {
            em.getTransaction().rollback();  // Desfaz a transação em caso de erro
            throw new RuntimeException("Erro ao remover doador: " + e.getMessage(), e);
        }
    }

    // Método para buscar um doador pelo CPF
    public Doador getDoadorPorCpf(String cpf) {
        TypedQuery<Doador> query = em.createQuery("SELECT d FROM Doador d WHERE d.cpf = :cpf", Doador.class);
        query.setParameter("cpf", cpf);
        Doador doador = query.getResultList().stream().findFirst().orElse(null);
        return doador;
    }

    public void fecharConexao() {
        em.close();
        emf.close();
    }
}

