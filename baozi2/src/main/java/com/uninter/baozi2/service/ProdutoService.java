package com.uninter.baozi2.service;

import com.uninter.baozi2.model.Produto;
import com.uninter.baozi2.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// @Service → marca esta classe como componente de lógica de negócio
// É aqui que ficaria regras como: "não pode cadastrar produto com preço negativo"
// Separar a lógica do Controller é uma boa prática (princípio da responsabilidade única)
@Service
public class ProdutoService {

    // @Autowired → injeta automaticamente o repositório (Injeção de Dependência do Spring)
    // Você não precisa fazer "new ProdutoRepository()" — o Spring faz isso por você
    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    // Optional<Produto> → pode conter um Produto ou estar vazio (quando ID não existe)
    // Isso evita NullPointerException!
    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public void deletar(Long id) {
        produtoRepository.deleteById(id);
    }

    public boolean existe(Long id) {
        return produtoRepository.existsById(id);
    }
}
