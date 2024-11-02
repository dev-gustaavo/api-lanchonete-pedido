package br.com.fiap.techchallenge.lanchonete.usecases;

import br.com.fiap.techchallenge.lanchonete.interfaces.gateways.ProdutoGateway;
import br.com.fiap.techchallenge.lanchonete.interfaces.usecases.ProdutoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProdutoUseCaseImpl implements ProdutoUseCase {

    private final ProdutoGateway produtoGateway;

    @Override
    public Boolean isProduto(Integer id) {
        var produto = produtoGateway.isProduto(id);

        return produto != null;
    }
}
