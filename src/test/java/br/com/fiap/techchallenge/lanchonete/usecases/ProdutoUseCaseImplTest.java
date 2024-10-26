package br.com.fiap.techchallenge.lanchonete.usecases;

import br.com.fiap.techchallenge.lanchonete.client.dto.ProdutoDTO;
import br.com.fiap.techchallenge.lanchonete.interfaces.gateways.ProdutoGateway;
import br.com.fiap.techchallenge.lanchonete.mocks.ProdutoDTOMock;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class ProdutoUseCaseImplTest {

    @Mock
    private ProdutoGateway produtoGateway;

    @InjectMocks
    private ProdutoUseCaseImpl produtoUseCase;

    private final ProdutoDTO produtoDTOMock = ProdutoDTOMock.getProdutoDto();

    @Test
    @Description("Deve validar se o produto existe")
    void deveRetornarExceptionAoTentarBuscarProdutoPorCategoria() throws Exception {
        when(produtoGateway.isProduto(anyInt())).thenReturn(produtoDTOMock);
        assertTrue(produtoUseCase.isProduto(1));
    }

    @Test
    @Description("Deve validar que o produto não existe")
    void deveValidarQueOProdutoNaoExiste() {
        when(produtoGateway.isProduto(anyInt())).thenReturn(null);
        assertFalse(produtoUseCase.isProduto(1));
    }
}
