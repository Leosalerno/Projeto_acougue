package service;

import model.Produto;
import model.Venda;

import java.util.ArrayList;
import java.util.List;

public class VendaService {
    private List<Venda> vendas = new ArrayList<>();

    public boolean cadastrar(Venda venda) {
        for (Venda v : vendas) {
            if (v.getId().equals(venda.getId())) {
                return false;
            }
        }

        vendas.add(venda);
        return true;
    }

    public boolean existe(Integer id) {
        for (Venda v : vendas) {
            if (v.getId().equals(id)) {
                return true;
            }
        }

        return false;
    }
}
