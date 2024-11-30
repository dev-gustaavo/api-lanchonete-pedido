package br.com.fiap.techchallenge.lanchonete.interfaces.gateways;

import br.com.fiap.techchallenge.lanchonete.client.dto.ClienteDTO;

public interface ClienteGateway {

    ClienteDTO isCliente(String cpf);
}
