package model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Doacao {

	@Override
	public String toString() {
	    // Define como o objeto será exibido como string
	    return "Doacao{" +
	           "id=" + id + // Exibe o ID da doação
	           ", nome='" + nome + '\'' + // Exibe o nome da doação
	           ", itens=" + (itens != null ? itens.size() + " itens" : "null") + // Mostra o número de itens associados ou "null" se não houver
	           '}';
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// ID único da doação, gerado automaticamente pelo banco de dados
	private Long id;

	private String nome; 
	// Nome da doação, usado para identificação

	@ManyToOne
	@JoinColumn(name = "doador_id", nullable = false) 
	// Relacionamento com a entidade "Doador"
	// Cada doação está vinculada a um único doador
	private Doador doador;

	@OneToMany(mappedBy = "doacao", fetch = FetchType.EAGER) 
	// Relacionamento com a entidade "ItemDoacao"
	// Uma doação pode conter vários itens
	private List<ItemDoacao> itens; 
	// Lista de itens associados a esta doação


    // Construtores, getters e setters
    public Doacao() {}

    public Doacao(String nome) {
        this.nome = nome;
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


    public List<ItemDoacao> getItens() {
        return itens;
    }

    public void setItens(List<ItemDoacao> itens) {
        this.itens = itens;
    }

    // Método para associar o doador à doação
    public void setDoador(Doador doador) {
        this.doador = doador;
    }

    public Doador getDoador() {
        return doador;
    }
}

