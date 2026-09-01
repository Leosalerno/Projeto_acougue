package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Venda {
    private Integer id;
    private Cliente cliente;
    private List<ItemVenda> itemVendas = new ArrayList<>();
    private double valorTotal;
    private LocalDate data;

    public Venda(Integer id, Cliente cliente, LocalDate data) {
        this.id = id;
        this.cliente = cliente;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemVenda> getItemVendas() {
        return itemVendas;
    }

    public void setItemVendas(List<ItemVenda> itemVendas) {
        this.itemVendas = itemVendas;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void adicionarItem(ItemVenda item) {
        itemVendas.add(item);
    }

    public double calcularTotal() {
        double soma = 0;
        for(ItemVenda i: itemVendas) {
            soma += i.getPrecoUnitario() * i.getQuantidade();
        }
        return soma;
    }
}
