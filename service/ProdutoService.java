package service;

import model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoService {

    private List<Produto> produtos = new ArrayList<>();

    public boolean cadastrar(Produto produto) {

        for (Produto p : produtos) {
            if (p.getId().equals(produto.getId())) {
                return false;
            }
        }

        produtos.add(produto);
        return true;
    }

    public List<Produto> listar() {
        return new ArrayList<>(produtos);
    }

    public Produto buscar(Integer id_busca) {
        for (Produto p : produtos) {
            if (p.getId().equals(id_busca)) {
                return p;
            }
        }

        return null;
    }

    public boolean alterar(Integer id, String nome, String categoria, Double preco, Double quantidade) {
        Produto produto = buscar(id);

        if (produto == null) {
            return false;
        }

        produto.setNome(nome);
        produto.setCategoria(categoria);
        produto.setPreco(preco);
        produto.setQuantidadeEstoque(quantidade);

        return true;
    }

    public boolean excluir(Integer id) {
        return produtos.removeIf(p -> p.getId().equals(id));
    }
}