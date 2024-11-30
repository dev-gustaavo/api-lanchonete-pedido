package br.com.fiap.techchallenge.lanchonete.client;

import br.com.fiap.techchallenge.lanchonete.client.dto.ClienteDTO;
import br.com.fiap.techchallenge.lanchonete.client.dto.ProdutoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "clientes", url = "${feign.cliente.url}")
public interface ClienteClient {

    @GetMapping(value = "/{cpf}", consumes = "application/json")
    ClienteDTO getClienteByCpf(@RequestParam("cpf") String cpf);
}
