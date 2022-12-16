package Models;
import java.util.ArrayList;
import java.util.Optional;

public class Carrinho {
	
	private ArrayList<Item> carrinho;

	public Carrinho(){
		carrinho = new ArrayList<Item>();
	}

	public ArrayList<Item> getItems(){
		return this.carrinho;
	}
	
	public ArrayList<Item> adicionarProduto(Produto produto) {
		return this.adicionarProduto(produto, 1);
	}

	public ArrayList<Item> adicionarProduto(Produto produto, int quantidade) {
		
		if(!Estoque.getEstoque().booItemDisponivel(produto)){
			return carrinho;
		}

		Optional<Item> filteredItem = this.carrinho.stream().filter(item -> item.getProduto().equals(produto)).findAny();
		if(filteredItem.isPresent()){
			Item finalItem = filteredItem.get();
			System.out.println("Quantidade: " + (int) (finalItem.getQuantidade()+quantidade));
			finalItem.setQuantidade((int) finalItem.getQuantidade()+(int) quantidade);
			carrinho.removeIf(itemFinal -> itemFinal.getProduto().equals(produto));
			carrinho.add(finalItem);

			Estoque.getEstoque().removerProduto(finalItem.getProduto(), quantidade);
			return carrinho;
		}

		if(Estoque.getEstoque().booItemDisponivel(produto)){
			Estoque.getEstoque().removerProduto(produto, quantidade);
			Item item = new Item(produto, quantidade);
			this.carrinho.add(item);
			return carrinho;
		}
		return carrinho;
	}
	
	public ArrayList<Item> removerProduto(Produto produto, int quantidade) {
		
		Item filteredItem = this.carrinho.stream().filter(item -> item.getProduto().equals(produto)).findFirst().get();
		filteredItem.setQuantidade(filteredItem.getQuantidade()-quantidade);
		this.carrinho.removeIf(item -> item.getProduto().equals(produto));
		this.carrinho.add(filteredItem);

		Estoque.getEstoque().adicionarProduto(filteredItem.getProduto(), quantidade);

		return carrinho;
	}

	public ArrayList<Item> removerProduto(Produto produto) {
		
		carrinho.removeIf(item -> item.getProduto().equals(produto));
		Estoque.getEstoque().adicionarProduto(produto);
		return carrinho;
	}



	public double fecharCarrinho() {
		double valorTotal = this.carrinho.stream().reduce(0.0, (total, item) -> item.getValorTotal() + total, Double::sum);
		this.carrinho.clear();
		return valorTotal;
	}

	public void limparCarrinho() {
		
		for (Item item: carrinho) {
			
			carrinho.remove(item);
			Estoque.getEstoque().adicionarProduto(item.getProduto(), item.getQuantidade());
		}
	}
}

