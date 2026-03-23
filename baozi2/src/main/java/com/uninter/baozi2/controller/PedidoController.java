package com.uninter.baozi2.controller;

import com.uninter.baozi2.model.Pedido;
import com.uninter.baozi2.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // =============================================
    // Classe auxiliar para receber o JSON de criação
    // =============================================
    // Ao invés de receber um objeto Pedido (que tem Cliente e Produto completos),
    // recebemos apenas os IDs no corpo da requisição. Isso é mais simples para o cliente da API.
    // Exemplo de JSON esperado:
    // {
    //   "clienteId": 1,
    //   "produtoId": 1,
    //   "quantidade": 5
    // }
    static class PedidoRequest {
        public Long clienteId;
        public Long produtoId;
        public Integer quantidade;
    }

    // POST /pedidos
    @PostMapping
    public ResponseEntity<Pedido> criar(@RequestBody PedidoRequest request) {
        Pedido salvo = pedidoService.salvar(request.clienteId, request.produtoId, request.quantidade);
        if (salvo == null) {
            // Cliente ou Produto não encontrado → 404
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    // GET /pedidos
    @GetMapping
    public ResponseEntity<List<Pedido>> listarTodos() {
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    // GET /pedidos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoService.buscarPorId(id);
        return pedido.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /pedidos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!pedidoService.existe(id)) {
            return ResponseEntity.notFound().build();
        }
        pedidoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
