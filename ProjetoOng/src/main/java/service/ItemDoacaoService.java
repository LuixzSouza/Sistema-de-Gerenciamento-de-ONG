package service;

import model.ItemDoacao;
import model.Doacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ItemDoacaoService {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("sistema_doacao");
    private static final EntityManager em = emf.createEntityManager();

    public void salvarItem(ItemDoacao itemDoacao) {
        try {
            em.getTransaction().begin();
            em.persist(itemDoacao);
            em.getTransaction().commit();  // Confirma a transação
        } catch (Exception e) {
            em.getTransaction().rollback();  // Desfaz a transação em caso de erro
            throw new RuntimeException("Erro ao salvar item de doação: " + e.getMessage(), e);
        }
    }

    public ItemDoacao encontrarItemPorId(long id) {
        return em.find(ItemDoacao.class, id);
    }

    public void excluirItem(long id) {
        ItemDoacao item = encontrarItemPorId(id);
        if (item != null) {
            try {
                em.getTransaction().begin();
                em.remove(item);
                em.getTransaction().commit();  // Confirma a transação
            } catch (Exception e) {
                em.getTransaction().rollback();  // Desfaz a transação em caso de erro
                throw new RuntimeException("Erro ao excluir item de doação: " + e.getMessage(), e);
            }
        }
    }

    // Método para adicionar um item à doação
    public void addItem(String nome, String descricao, String categoria, Long doacaoId) {
        try {
            em.getTransaction().begin();

            // Buscar a doação pelo ID
            Doacao doacao = em.find(Doacao.class, doacaoId);
            if (doacao == null) {
                throw new RuntimeException("Doação não encontrada.");
            }

            ItemDoacao itemDoacao = new ItemDoacao();
            itemDoacao.setNome(nome);
            itemDoacao.setDescricao(descricao);
            itemDoacao.setCategoria(categoria);
            itemDoacao.setDoacao(doacao);  // Associando o item à doação

            em.persist(itemDoacao);
            em.getTransaction().commit();  // Confirma a transação
        } catch (Exception e) {
            em.getTransaction().rollback();  // Desfaz a transação em caso de erro
            throw new RuntimeException("Erro ao adicionar item de doação: " + e.getMessage(), e);
        }
    }

    public void fecharConexao() {
        em.close();
        emf.close();
    }
}
