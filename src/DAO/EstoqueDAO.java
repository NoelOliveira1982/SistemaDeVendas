package DAO;

import Models.Estoque;
import Models.Produto;

public class EstoqueDAO {

    public static Estoque getEstoque(){
        Estoque estoque = Estoque.getEstoque();
        estoque.adicionarProduto(new Produto("Queijo 100g", 5.0), 120);
        estoque.adicionarProduto(new Produto("Pão", 0.75), 50);
        estoque.adicionarProduto(new Produto("Arroz 1kg", 10.0), 120);
        estoque.adicionarProduto(new Produto("Nescau 500g", 12.0), 50);
        estoque.adicionarProduto(new Produto("Presunto 100g", 4.0), 120);
        estoque.adicionarProduto(new Produto("Pastel", 10.0), 20);
        estoque.adicionarProduto(new Produto("Pizza", 30.0), 12);
        estoque.adicionarProduto(new Produto("Água mineral", 3.0), 122);
        estoque.adicionarProduto(new Produto("Esponja", 1.0), 1);
        estoque.adicionarProduto(new Produto("Tabela de Ovo 6 un.", 12.0), 12);
        estoque.adicionarProduto(new Produto("Sabão", 10.0), 12);
        estoque.adicionarProduto(new Produto("Sabonete", 10.0), 12);
        estoque.adicionarProduto(new Produto("Shampoo", 10.0), 12);
        estoque.adicionarProduto(new Produto("Condicionador", 10.0), 12);
        estoque.adicionarProduto(new Produto("Suco", 10.0), 12);

        return estoque;
    }
    
}
