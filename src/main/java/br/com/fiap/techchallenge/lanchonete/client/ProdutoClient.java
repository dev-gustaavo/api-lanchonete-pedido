package br.com.fiap.techchallenge.lanchonete.client;

import br.com.fiap.techchallenge.lanchonete.client.dto.ProdutoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "produtos", url = "${feign.produto.url}")
public interface ProdutoClient {

    @GetMapping(value = "/busca/{id}", consumes = "application/json")
    ProdutoDTO getProdutoById(@RequestParam("id") Integer id);
}
