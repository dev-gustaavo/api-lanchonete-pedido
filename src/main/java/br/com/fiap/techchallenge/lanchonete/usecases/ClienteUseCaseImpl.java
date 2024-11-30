package br.com.fiap.techchallenge.lanchonete.usecases;

import br.com.fiap.techchallenge.lanchonete.interfaces.gateways.ClienteGateway;
import br.com.fiap.techchallenge.lanchonete.interfaces.gateways.ProdutoGateway;
import br.com.fiap.techchallenge.lanchonete.interfaces.usecases.ClienteUseCase;
import br.com.fiap.techchallenge.lanchonete.interfaces.usecases.ProdutoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteUseCaseImpl implements ClienteUseCase {

    private final ClienteGateway clienteGateway;

    @Override
    public Boolean isCliente(String cpf) {
        var cliente = clienteGateway.isCliente(cpf);

        return cliente != null;
    }
}
