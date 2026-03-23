package com.uninter.baozi2.service;

import com.uninter.baozi2.model.Cliente;
import com.uninter.baozi2.model.Pedido;
import com.uninter.baozi2.model.Produto;
import com.uninter.baozi2.repository.ClienteRepository;
import com.uninter.baozi2.repository.PedidoRepository;
import com.uninter.baozi2.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    // O service de Pedido precisa dos repositórios de Cliente e Produto
    // para buscar os objetos completos antes de salvar o Pedido
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    // Este método recebe os IDs e monta o objeto Pedido completo antes de salvar
    // Retorna null se o cliente ou produto não forem encontrados
    public Pedido salvar(Long clienteId, Long produtoId, Integer quantidade) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        Optional<Produto> produto = produtoRepository.findById(produtoId);

        if (cliente.isEmpty() || produto.isEmpty()) {
            return null; // O controller vai tratar isso como 404
        }

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente.get());
        pedido.setProduto(produto.get());
        pedido.setQuantidade(quantidade);

        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public void deletar(Long id) {
        pedidoRepository.deleteById(id);
    }

    public boolean existe(Long id) {
        return pedidoRepository.existsById(id);
    }
}
