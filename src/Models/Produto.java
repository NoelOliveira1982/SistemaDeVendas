package Models;
public class Produto {

	private String nome;
	private double precoUnitario;
	
	public Produto(String nome, double precoUnitario){
		this.nome = nome;
		this.precoUnitario = precoUnitario;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getPrecoUnitario() {
		return precoUnitario;
	}
	public void setPrecoUnitario(double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}
}
