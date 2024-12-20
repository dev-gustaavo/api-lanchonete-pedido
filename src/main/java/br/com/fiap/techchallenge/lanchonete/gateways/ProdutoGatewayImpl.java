package br.com.fiap.techchallenge.lanchonete.gateways;

import br.com.fiap.techchallenge.lanchonete.client.ProdutoClient;
import br.com.fiap.techchallenge.lanchonete.client.dto.ProdutoDTO;
import br.com.fiap.techchallenge.lanchonete.interfaces.gateways.ProdutoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
            return produto;
        } catch (Exception e) {
            if (developmentEnv)
                return new ProdutoDTO().setId(id);

            return null;
        }
    }
}

