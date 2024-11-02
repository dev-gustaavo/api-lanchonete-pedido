package br.com.fiap.techchallenge.lanchonete.usecases;

import br.com.fiap.techchallenge.lanchonete.entities.MensagemErroPadrao;
import br.com.fiap.techchallenge.lanchonete.entities.Pedido;
import br.com.fiap.techchallenge.lanchonete.entities.QrCode;
import br.com.fiap.techchallenge.lanchonete.entities.StatusPagamento;
import br.com.fiap.techchallenge.lanchonete.entities.exception.ClienteException;
import br.com.fiap.techchallenge.lanchonete.entities.exception.ProdutoException;
import br.com.fiap.techchallenge.lanchonete.interfaces.gateways.ClienteGateway;
import br.com.fiap.techchallenge.lanchonete.interfaces.gateways.MercadoPagoGateway;
import br.com.fiap.techchallenge.lanchonete.interfaces.gateways.PedidoGateway;
import br.com.fiap.techchallenge.lanchonete.interfaces.usecases.ClienteUseCase;
import br.com.fiap.techchallenge.lanchonete.interfaces.usecases.PedidoUseCase;
import br.com.fiap.techchallenge.lanchonete.interfaces.usecases.ProdutoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PedidoUseCaseImpl implements PedidoUseCase {

    private final PedidoGateway pedidoGateway;
    private final ProdutoUseCase produtoUseCase;
    private final MercadoPagoGateway mercadoPagoGateway;
    private final ClienteUseCase clienteUseCase;

    @Override
    public Pedido store(Pedido pedido) throws Exception {
        validaProdutosInformadosNoPedido(pedido);
        validaClienteInformado(pedido);

        try {
            return pedidoGateway.store(pedido);
        } catch (Exception exception) {
            throw new Exception(MensagemErroPadrao.ERRO_GENERICO, exception);
        }
    }

    private void validaClienteInformado(Pedido pedido) {
        var erros = isClienteExistente(pedido.getIdentificacaoCliente());

        if (!erros.isEmpty())
            throw new ClienteException(MensagemErroPadrao.CLIENTE_NAO_ENCONTRADO);
    }

    private Map<String, Boolean> isClienteExistente(String cpf) {
        Map<String, Boolean> erro = new HashMap<>();

        var existe = clienteUseCase.isCliente(cpf);

        if (!existe)
            erro.put(cpf, true);

        return erro;
    }

    private void validaProdutosInformadosNoPedido(Pedido pedido) {
        if (pedido.getProdutoId().isEmpty())
            throw new ProdutoException(MensagemErroPadrao.PRODUTO_PEDIDO_NAO_ENCONTRADO);

        var erros = isProdutoExistente(pedido.getProdutoId());

        if (!erros.isEmpty())
            throw new ProdutoException(MensagemErroPadrao.PRODUTO_PEDIDO_NAO_ENCONTRADO, erros);
    }

    private Map<Integer, Boolean> isProdutoExistente(List<Integer> idProdutos) {
        Map<Integer, Boolean> erro = new HashMap<>();

        idProdutos.forEach(id -> {
            var produto = produtoUseCase.isProduto(id);

            if (!produto)
                erro.put(id, true);
        });

        return erro;
    }

    @Override
    public QrCode gerarQrCode(Integer numeroPedido) {
        return mercadoPagoGateway.gerarQrCode(numeroPedido);
    }

    @Override
    public List<Pedido> listarPedidos() throws Exception {
        return pedidoGateway.listarPedidos();
    }

    @Override
    public Pedido notificacaoPagamento(Pedido pedido) throws Exception {
        try {
            if (isPedidoPago(pedido)) {
                return pedidoGateway.atualizarStatusPagamentoParaPago(pedido);
            }

            return pedido;
        } catch (Exception exception) {
            throw new Exception(MensagemErroPadrao.ERRO_GENERICO, exception);
        }
    }

    private boolean isPedidoPago(Pedido pedido) {
        return pedido.getStatusPagamento().equals(StatusPagamento.PAGO);
    }

    @Override
    public Pedido consultarStatusPagamento(Integer numeroPedido) throws Exception {
        return pedidoGateway.consultarStatusPagamento(numeroPedido);
    }
}
