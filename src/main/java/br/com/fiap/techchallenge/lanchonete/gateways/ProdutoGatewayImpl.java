package br.com.fiap.techchallenge.lanchonete.gateways;

import br.com.fiap.techchallenge.lanchonete.client.ProdutoClient;
import br.com.fiap.techchallenge.lanchonete.client.dto.ProdutoDTO;
import br.com.fiap.techchallenge.lanchonete.entities.MensagemErroPadrao;
import br.com.fiap.techchallenge.lanchonete.entities.exception.ProdutoException;
import br.com.fiap.techchallenge.lanchonete.interfaces.gateways.ProdutoGateway;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

@RequiredArgsConstructor
@Component
public class ProdutoGatewayImpl implements ProdutoGateway {

    private final ProdutoClient produtoClient;

    @Value("${env.development}")
    private boolean developmentEnv;

    @Override
    public ProdutoDTO isProduto(Integer id) {
        try {
            var produto = produtoClient.getProdutoById(id);
            return produto.getBody();
        } catch (Exception e) {
            if (developmentEnv)
                return new ProdutoDTO().setId(id);

            throw new ProdutoException(MensagemErroPadrao.PRODUTO_NAO_ENCONTRADO);
        }
    }
}

