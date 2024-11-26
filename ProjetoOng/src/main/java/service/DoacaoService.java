package service;

import model.Doacao;
import model.Doador;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DoacaoService {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("sistema_doacao");
    private static final EntityManager em = emf.createEntityManager();

    public Doacao salvarDoacao(Doacao doacao) {
        try {
            em.getTransaction().begin();
            em.persist(doacao);
            em.getTransaction().commit();  // Confirma a transação
            return doacao;
        } catch (Exception e) {
            em.getTransaction().rollback();  // Desfaz a transação em caso de erro
            throw new RuntimeException("Erro ao salvar doação: " + e.getMessage(), e);
        }
    }

    public Doacao buscarDoacaoPorId(Long id) {
        return em.find(Doacao.class, id);
    }
    
    public void atualizarDoacao(Doacao doacao) {
        try {
            em.getTransaction().begin();
            em.merge(doacao);
            em.getTransaction().commit();  // Confirma a transação
        } catch (Exception e) {
            em.getTransaction().rollback();  // Desfaz a transação em caso de erro
            throw new RuntimeException("Erro ao atualizar doador: " + e.getMessage(), e);
        }
    }

    public void removerDoacao(Long id) {
        try {
            em.getTransaction().begin();
            Doacao doacao = em.find(Doacao.class, id);
            if (doacao != null) {
                em.remove(doacao);
            }
            em.getTransaction().commit();  // Confirma a transação
        } catch (Exception e) {
            em.getTransaction().rollback();  // Desfaz a transação em caso de erro
            throw new RuntimeException("Erro ao remover doador: " + e.getMessage(), e);
        }
    }


    // Método para adicionar uma doação associando-a a um doador
    public Doacao addDoacao(String nome, Long doadorId) {
        try {
            em.getTransaction().begin();

            // Buscar o doador pelo ID
            Doador doador = em.find(Doador.class, doadorId);
            if (doador == null) {
                throw new RuntimeException("Doador não encontrado.");
            }

            Doacao doacao = new Doacao();
            doacao.setNome(nome);
            doacao.setDoador(doador);  // Associando o doador à doação

            em.persist(doacao);
            em.getTransaction().commit();  // Confirma a transação
            return doacao;
        } catch (Exception e) {
            em.getTransaction().rollback();  // Desfaz a transação em caso de erro
            throw new RuntimeException("Erro ao adicionar doação: " + e.getMessage(), e);
        }
    }

    public void fecharConexao() {
        em.close();
        emf.close();
    }
}
