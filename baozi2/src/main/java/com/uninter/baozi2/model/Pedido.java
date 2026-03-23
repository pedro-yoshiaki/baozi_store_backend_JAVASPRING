package com.uninter.baozi2.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pedido")

public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // =============================================
    // RELACIONAMENTOS
    // =============================================

    // @ManyToOne → muitos pedidos podem pertencer a um único cliente
    // @JoinColumn → cria a coluna FK "cliente_id" na tabela "pedido"
    // Em vez de guardar apenas o ID (Long clienteId), guardamos o OBJETO Cliente.
    // O JPA cuida de salvar/buscar o relacionamento automaticamente.
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    // @ManyToOne → muitos pedidos podem ter um único produto
    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    // Quantidade de unidades compradas neste pedido
    @Column(nullable = false)
    private Integer quantidade;
    
    // Construtor vazio — obrigatório para o JPA funcionar
    public Pedido() {}

    // Getters e Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}   
}
