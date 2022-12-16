package Models;
import java.util.ArrayList;

public class Administrador extends Usuario{

    public Administrador(String usuario, String senha) {
        super(usuario, senha);
    }

    public ArrayList<Item> adicionarProduto(Produto produto){
        Estoque estoque = Estoque.getEstoque();
        estoque.adicionarProduto(produto);

        return estoque.getItems();
    }

    public ArrayList<Item> adicionarProduto(Produto produto, int quantidade){
        Estoque estoque = Estoque.getEstoque();
        estoque.adicionarProduto(produto, quantidade);

        return estoque.getItems();
    }
    
    public ArrayList<Item> removerProduto(Produto produto){
        Estoque estoque = Estoque.getEstoque();
        estoque.removerProduto(produto);

        return estoque.getItems();
    }

    public ArrayList<Item> removerProduto(Produto produto, int quantidade){
        Estoque estoque = Estoque.getEstoque();
        estoque.removerProduto(produto, quantidade);

        return estoque.getItems();
    }
}
