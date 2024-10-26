package br.com.fiap.techchallenge.lanchonete.interfaces.gateways;

import br.com.fiap.techchallenge.lanchonete.client.dto.ProdutoDTO;

import java.util.List;

public interface ProdutoGateway {

    ProdutoDTO isProduto(Integer id);
}
