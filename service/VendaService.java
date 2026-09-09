package service;

import model.ItemVenda;
import model.Venda;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;

public class VendaService {
    private List<Venda> vendas = new ArrayList<>();
    private final ProdutoService produtoService;

    public VendaService(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    public enum ResultadoVenda {
        SUCESSO, ID_DUPLICADO, SEM_ITENS, QUANTIDADE_INVALIDA, ESTOQUE_INSUFICIENTE
    }

    public ResultadoVenda finalizar(Venda venda) {
        if (existe(venda.getId())) {
            return ResultadoVenda.ID_DUPLICADO;
        }

        if (venda.getItemVendas().isEmpty()) {
            return ResultadoVenda.SEM_ITENS;
        }

        Map<Integer, Double> quantidades = new LinkedHashMap<>();
        for (ItemVenda item : venda.getItemVendas()) {
            if (!Double.isFinite(item.getQuantidade()) || item.getQuantidade() <= 0) {
                return ResultadoVenda.QUANTIDADE_INVALIDA;
            }
            Integer id = item.getProduto().getId();
            double total = quantidades.getOrDefault(id, 0.0) + item.getQuantidade();
            if (!Double.isFinite(total)) {
                return ResultadoVenda.QUANTIDADE_INVALIDA;
            }
            quantidades.put(id, total);
        }

        if (!produtoService.baixarEstoques(quantidades)) {
            return ResultadoVenda.ESTOQUE_INSUFICIENTE;
        }

        venda.setValorTotal(venda.calcularTotal());
        vendas.add(venda);
        return ResultadoVenda.SUCESSO;
    }

    public boolean existe(Integer id) {
        for (Venda v : vendas) {
            if (v.getId().equals(id)) {
                return true;
            }
        }

        return false;
    }

    public List<Venda> listar() {
        return new ArrayList<>(vendas);
    }
}
