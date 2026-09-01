package service;

import model.Cliente;
import model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ClienteService {

    private List<Cliente> clientes = new ArrayList<>();

    public boolean cadastrar(Cliente cliente) {

        for (Cliente c : clientes) {
            if (c.getId().equals(cliente.getId())) {
                return false;
            }
        }

        clientes.add(cliente);
        return true;
    }

    public List<Cliente> listar() {
        return new ArrayList<>(clientes);
    }

    public Cliente buscar(Integer id_busca) {
        for (Cliente c : clientes) {
            if (c.getId().equals(id_busca)) {
                return c;
            }
        }

        return null;
    }

    public boolean alterar(Integer id, String nome, String cpf, String telefone) {
        Cliente cliente = buscar(id);

        if (cliente == null) {
            return false;
        }

        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setTelefone(telefone);

        return true;
    }

    public boolean excluir(Integer id) {
        return clientes.removeIf(c -> c.getId().equals(id));
    }
}
