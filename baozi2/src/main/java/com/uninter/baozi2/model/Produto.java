package com.uninter.baozi2.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

// @Entity → diz ao JPA que esta classe representa uma tabela no banco de dados
@Entity
// @Table → define o nome da tabela no banco (opcional)
@Table(name = "produto")
// @Data (Lombok) → gera automaticamente getters, setters, toString, equals e hashCode

public class Produto {

    // @Id → marca este campo como chave primária da tabela
    @Id
    // @GeneratedValue → o banco gera o ID automaticamente (auto_increment no MySQL)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column(nullable = false) → este campo não pode ser nulo no banco
    @Column(nullable = false)
    private String nome;

    // BigDecimal é o tipo correto para valores monetários (evita erros de arredondamento)
    // precision=10, scale=2 → até 10 dígitos, sendo 2 decimais (ex: 99999999.99)
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    // Boolean → true = tem em estoque, false = sem estoque
    @Column(nullable = false)
    private Boolean estoque;
    
    // Construtor vazio — obrigatório para o JPA funcionar
    public Produto () {}

    // Getters e Setters
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

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Boolean getEstoque() {
		return estoque;
	}

	public void setEstoque(Boolean estoque) {
		this.estoque = estoque;
	}
}
