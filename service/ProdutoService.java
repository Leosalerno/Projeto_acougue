package service;

import model.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public boolean baixarEstoques(Map<Integer, Double> quantidades) {
        // Confere todos os produtos antes de alterar qualquer estoque.
        for (Map.Entry<Integer, Double> entrada : quantidades.entrySet()) {
            Double quantidade = entrada.getValue();
            if (quantidade == null || !Double.isFinite(quantidade)
                    || !temEstoque(entrada.getKey(), quantidade)) {
                return false;
            }
        }

        for (Map.Entry<Integer, Double> entrada : quantidades.entrySet()) {
            Produto produto = buscar(entrada.getKey());
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - entrada.getValue());
        }
        return true;
    }

    public boolean temEstoque(Integer id, Double quantidade) {
        Produto produto = buscar(id);

        if (produto == null) {
            return false;
        }

        if (quantidade <= 0) {
            return false;
        }

        return produto.getQuantidadeEstoque() >= quantidade;
    }
}
