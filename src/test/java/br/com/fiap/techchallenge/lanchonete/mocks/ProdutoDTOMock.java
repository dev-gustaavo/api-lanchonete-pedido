package br.com.fiap.techchallenge.lanchonete.mocks;

import br.com.fiap.techchallenge.lanchonete.client.dto.ProdutoDTO;

public class ProdutoDTOMock {

    public static ProdutoDTO getProdutoDto() {
        return new ProdutoDTO()
                .setId(1);
    }
}
