package br.com.fiap.techchallenge.lanchonete.gateways;

import br.com.fiap.techchallenge.lanchonete.client.ProdutoClient;
import br.com.fiap.techchallenge.lanchonete.client.dto.ProdutoDTO;
import br.com.fiap.techchallenge.lanchonete.entities.exception.ProdutoException;
import br.com.fiap.techchallenge.lanchonete.mocks.ProdutoDTOMock;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class ProdutoGatewayImplTest {

    @Mock
    private ProdutoClient produtoClient;

    @InjectMocks
    private ProdutoGatewayImpl produtoGateway;

    private final ProdutoDTO produtoDtoMock = ProdutoDTOMock.getProdutoDto();

    @Test
    @Description("Deve verificar se é um produto")
    void deveVerificarProduto() {
        var responseEntityMock = produtoDtoMock;
        when(produtoClient.getProdutoById(anyInt())).thenReturn(responseEntityMock);
        assertNotNull(produtoGateway.isProduto(1));
    }

    @Test
    @Description("Deve verificar que não é um produto")
    void deveVerificarQueNaoEhProduto() {
        when(produtoClient.getProdutoById(anyInt())).thenReturn(null);
        assertNull(produtoGateway.isProduto(1));
//        assertThrows(ProdutoException.class, () -> produtoGateway.isProduto(1));
    }
}
