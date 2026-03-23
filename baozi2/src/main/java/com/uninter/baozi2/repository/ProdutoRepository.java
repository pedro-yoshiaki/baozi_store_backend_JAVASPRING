package com.uninter.baozi2.repository;

import com.uninter.baozi2.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @Repository → marca esta interface como componente de acesso a dados
// JpaRepository<Produto, Long> → primeiro parâmetro é a Entidade, segundo é o tipo do ID
// Ao estender JpaRepository, você herda os métodos:
//   save(entity)       → INSERT ou UPDATE
//   findById(id)       → SELECT por ID
//   findAll()          → SELECT todos
//   deleteById(id)     → DELETE por ID
//   existsById(id)     → verifica se existe
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
