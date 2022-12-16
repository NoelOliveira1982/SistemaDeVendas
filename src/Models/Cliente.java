package Models;
import java.util.ArrayList;

public class Cliente extends Usuario{
    
    private Carrinho carrinho;

    public Cliente(String usuario, String senha){
        super(usuario, senha);
        this.carrinho = new Carrinho();
    }

    public ArrayList<Item> adicionarProdutoAoCarrinho(Produto produto){
        this.carrinho.adicionarProduto(produto);

        return this.carrinho.getItems();
    }

    public ArrayList<Item> adicionarProdutoAoCarrinho(Produto produto, int quantidade){
        this.carrinho.adicionarProduto(produto, quantidade);

        return this.carrinho.getItems();
    }

    public ArrayList<Item> removerProdutoDoCarrinho(Produto produto){
        this.carrinho.removerProduto(produto);

        return this.carrinho.getItems();
    }

    public ArrayList<Item> removerProdutoDoCarrinho(Produto produto, int quantidade){
        this.carrinho.removerProduto(produto, quantidade);

        return this.carrinho.getItems();
    }

    public double fecharCarrinho(){
        return this.carrinho.fecharCarrinho();
    }

    public int getQuantidadeProdutosCarrinho(){
		return this.carrinho.getItems().size();
	}

    public Carrinho getCarrinho() {
        return carrinho;
    }
}
