package service;

import model.Doador;
import utils.JPAUtil;

import javax.persistence.EntityManager;

public class DoadorService {

    public void inserirDoador(Doador doador) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(doador);
        em.getTransaction().commit();
        em.close();
    }

    public Doador encontrarDoadorPorCpf(String cpf) {
        EntityManager em = JPAUtil.getEntityManager();
        Doador doador = em.createQuery("SELECT d FROM Doador d WHERE d.cpf = :cpf", Doador.class)
                .setParameter("cpf", cpf)
                .getSingleResult();
        em.close();
        return doador;
    }

    public void excluirDoador(String cpf) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        Doador doador = em.createQuery("SELECT d FROM Doador d WHERE d.cpf = :cpf", Doador.class)
                .setParameter("cpf", cpf)
                .getSingleResult();
        if (doador != null) {
            em.remove(doador);
        }

        em.getTransaction().commit();
        em.close();
    }
}
