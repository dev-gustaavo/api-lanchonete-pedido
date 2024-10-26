package br.com.fiap.techchallenge.lanchonete.client;

import br.com.fiap.techchallenge.lanchonete.client.dto.ProdutoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "produtos", url = "${client.produto.url}")
public interface ProdutoClient {

    @GetMapping(value = "/{id}", consumes = "application/json")
    ResponseEntity<ProdutoDTO> getProdutoById(@RequestParam("id") Integer id);
}
