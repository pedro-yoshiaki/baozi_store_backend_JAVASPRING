package com.uninter.baozi2.controller;

import com.uninter.baozi2.model.Produto;
import com.uninter.baozi2.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// @RestController → combina @Controller + @ResponseBody
// Significa: "esta classe é um controller e cada método retorna JSON automaticamente"
@RestController
// @RequestMapping → define a URL base para todos os endpoints desta classe
// Todos os endpoints de Produto começarão com /produtos
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // =============================================
    // POST /produtos → Criar produto
    // =============================================
    // @RequestBody → o Spring lê o JSON do corpo da requisição e converte para Produto
    // ResponseEntity<Produto> → nos permite controlar o status HTTP da resposta
    @PostMapping
    public ResponseEntity<Produto> criar(@RequestBody Produto produto) {
        Produto salvo = produtoService.salvar(produto);
        // 201 Created é o status correto para criação bem-sucedida
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    // =============================================
    // GET /produtos → Listar todos
    // =============================================
    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos() {
        List<Produto> produtos = produtoService.listarTodos();
        // 200 OK
        return ResponseEntity.ok(produtos);
    }

    // =============================================
    // GET /produtos/{id} → Buscar por ID
    // =============================================
    // @PathVariable → captura o {id} da URL (ex: /produtos/1 → id = 1)
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        Optional<Produto> produto = produtoService.buscarPorId(id);
        // Se o produto existe → 200 OK com o produto no body
        // Se não existe → 404 Not Found (sem body)
        return produto.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    // =============================================
    // DELETE /produtos/{id} → Apagar
    // =============================================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!produtoService.existe(id)) {
            // Se não existe, retorna 404
            return ResponseEntity.notFound().build();
        }
        produtoService.deletar(id);
        // 204 No Content → deletado com sucesso, sem body na resposta
        return ResponseEntity.noContent().build();
    }

    // =============================================
    // PUT /produtos/{id} → Atualizar (opcional)
    // =============================================
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        if (!produtoService.existe(id)) {
            return ResponseEntity.notFound().build();
        }
        produto.setId(id); // Garante que está atualizando o ID correto
        Produto atualizado = produtoService.salvar(produto);
        return ResponseEntity.ok(atualizado);
    }
}
