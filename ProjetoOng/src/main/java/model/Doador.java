package model;

import java.util.List;
import javax.persistence.*;

@Entity
public class Doador {

	// Define o campo "id" como chave primária na tabela, com valores gerados automaticamente pelo banco de dados.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String endereco;

	 // Define o relacionamento de "um para muitos" com a entidade Doacao.
	 // Um doador pode estar associado a várias doações.
	 // O atributo "mappedBy = 'doador'" indica que o relacionamento é mapeado pelo campo "doador" na classe `Doacao`.
    @OneToMany(mappedBy = "doador")
    private List<Doacao> doacoes;  // Lista de doações feitas pelo doador

    // Construtor sem parâmetros (necessário para o Hibernate)
    public Doador() {}

    // Construtor com parâmetros para inicializar os campos
    public Doador(String nome, String cpf, String email, String telefone, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    // Getters e setters
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Doacao> getDoacoes() {
        return doacoes;
    }

    public void setDoacoes(List<Doacao> doacoes) {
        this.doacoes = doacoes;
    }

    @Override
    public String toString() {
        return "Doador{" +
               "id=" + id +
               ", nome='" + nome + '\'' +
               ", cpf='" + cpf + '\'' +
               ", email='" + email + '\'' +
               ", telefone='" + telefone + '\'' +
               ", endereco='" + endereco + '\'' +
               '}'; // Omitindo as doações para evitar lista grande no log
    }
}
