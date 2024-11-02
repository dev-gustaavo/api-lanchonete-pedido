package br.com.fiap.techchallenge.lanchonete.gateways;

import br.com.fiap.techchallenge.lanchonete.client.ClienteClient;
import br.com.fiap.techchallenge.lanchonete.client.ProdutoClient;
import br.com.fiap.techchallenge.lanchonete.client.dto.ClienteDTO;
import br.com.fiap.techchallenge.lanchonete.client.dto.ProdutoDTO;
import br.com.fiap.techchallenge.lanchonete.interfaces.gateways.ClienteGateway;
import br.com.fiap.techchallenge.lanchonete.interfaces.gateways.ProdutoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ClienteGatewayImpl implements ClienteGateway {

    private final ClienteClient clienteClient;

    @Value("${env.development}")
    private boolean developmentEnv;

    @Override
    public ClienteDTO isCliente(String cpf) {
        try {
            var cliente = clienteClient.getClienteByCpf(cpf);
            return cliente;
        } catch (Exception e) {
            if (developmentEnv)
                return new ClienteDTO().setCpf(cpf);

            return null;
        }
    }
}

