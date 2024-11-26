package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ItemDoacao {

	@Override
	public String toString() {
	    return "ItemDoacao{" +
	           "id=" + id +
	           ", nome='" + nome + '\'' +
	           ", doacaoId=" + (doacao != null ? doacao.getId() : "null") +
	           '}';
	}


	// Define o campo "id" como chave primária, com valores gerados automaticamente pelo banco de dados.
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private String categoria;

	 // Define o relacionamento de "muitos para um" com a entidade `Doacao`.
	 // O atributo `@JoinColumn` mapeia a coluna "id_doacao" como chave estrangeira, que vincula o item a uma doação específica.
	 // A propriedade `nullable = false` indica que todo item deve estar obrigatoriamente vinculado a uma doação.
    @ManyToOne
    @JoinColumn(name = "id_doacao", nullable = false)
    private Doacao doacao;

    // Construtores, getters e setters
    public ItemDoacao() {}

    public ItemDoacao(String nome, String descricao, String categoria, Doacao doacao) {
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.doacao = doacao;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Doacao getDoacao() {
        return doacao;
    }

    public void setDoacao(Doacao doacao) {
        this.doacao = doacao;
    }
}
