package Models;
import java.util.ArrayList;
import java.util.Optional;

import DAO.EstoqueDAO;

public class Estoque {

	private ArrayList<Item> estoque;
	private static Estoque instance;

	private Estoque(){
		this.estoque = new ArrayList<Item>();
	}

	public ArrayList<Item> getItems(){
		return this.estoque;
	}

	public static Estoque getEstoque(){
		if(instance == null){
			instance = new Estoque();
			instance = EstoqueDAO.getEstoque();
		}
		return instance;
	}
	
	
	public ArrayList<Item> adicionarProduto(Produto produto) {
		Optional<Item> item = this.estoque.stream().filter(valor -> valor.getProduto().equals(produto)).findFirst();
		if(item.isPresent()) {
			this.adicionarProduto(item.get().getProduto(), 1);
			return estoque;
		}

		estoque.add(new Item(produto));
		return estoque;
	}

	public ArrayList<Item> adicionarProduto(Produto produto, int quantidade) {
		Optional<Item> filteredItem = this.estoque.stream().filter(item -> item.getProduto().equals(produto)).findFirst();
		if(filteredItem.isPresent()){
			Item item = filteredItem.get();
			this.estoque.remove(item);
			item.setQuantidade(item.getQuantidade()+quantidade);
			this.estoque.add(item);

			return estoque;
		}
		this.estoque.add(new Item(produto, quantidade));
		
		return estoque;
	}

	
	public ArrayList<Item> removerProduto(Produto produto, int quantidade) {
		Item filteredItem = this.estoque.stream().filter(item -> item.getProduto().equals(produto)).findFirst().get();
		this.estoque.remove(filteredItem);
		System.out.println("Estoque  antes: " + filteredItem.getQuantidade());
		filteredItem.setQuantidade(filteredItem.getQuantidade()-quantidade);
		System.out.println("Estoque depois: " + filteredItem.getQuantidade());
		this.estoque.add(filteredItem);
		return estoque;
	}
	
	public ArrayList<Item> removerProduto(Produto produto) {
		this.removerProduto(produto, this.estoque.stream().filter(item -> item.getProduto().equals(produto)).findFirst().get().getQuantidade());
		return estoque;
	}
	
	public ArrayList<Item> verItensDisponiveis() {
		ArrayList<Item> itensDisponiveis = new ArrayList<Item>();
		for (Item item: estoque) {
			if (item.getQuantidade() > 0) {
				itensDisponiveis.add(item);
			}
		}
		return itensDisponiveis;
	}

	public boolean booItemDisponivel(Produto produto) {
		Optional<Item> finalItem = this.estoque.stream().filter(UniqueItem -> UniqueItem.getProduto().equals(produto)).findFirst();
		return finalItem.isPresent() && finalItem.get().getQuantidade() >= 1;
	}
	
	public ArrayList<Item> verItensIndisponiveis() {
		ArrayList<Item> itensIndisponiveis = new ArrayList<Item>();
		for (Item item: estoque) {
			if (item.getQuantidade() == 0) {
				itensIndisponiveis.add(item);
			}
		}
		return itensIndisponiveis;
	}

	public Produto returnProduto(String nome){
		return Estoque.getEstoque().getItems().stream().filter(item -> item.getProduto().getNome().equals(nome)).findFirst().get().getProduto();
	}
}
