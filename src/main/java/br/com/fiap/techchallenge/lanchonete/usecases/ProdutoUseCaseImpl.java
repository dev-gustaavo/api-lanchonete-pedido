package br.com.fiap.techchallenge.lanchonete.usecases;

import br.com.fiap.techchallenge.lanchonete.entities.MensagemErroPadrao;
import br.com.fiap.techchallenge.lanchonete.entities.exception.ProdutoException;
import br.com.fiap.techchallenge.lanchonete.interfaces.gateways.ProdutoGateway;
import br.com.fiap.techchallenge.lanchonete.interfaces.usecases.ProdutoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
