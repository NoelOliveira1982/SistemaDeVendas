package Models;
public class Item {
	
	private Produto produto;
	private int quantidade;

	public Item(Produto produto, int quantidade){
		this.produto = produto;
		this.quantidade = quantidade;
	}

	public Item(Produto produto){
		this.produto = produto;
		this.quantidade = 0;
	}
	
	public double getValorTotal(){
		return this.produto.getPrecoUnitario() * this.quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		if(quantidade < 0){
			this.quantidade = 0;
		}
		this.quantidade = quantidade;
	}
	
}
