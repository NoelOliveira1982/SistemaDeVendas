package Views;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import Models.Cliente;
import Models.Estoque;
import Models.Item;
import Models.Produto;


public class ClienteView {

    private int itemQuantidade;
    private String[] itensCarrinho;
    private String[] nomeItensCarrinho;
    private String[] itensEstoque;
    private double totalCarrinho = 0;

    private Cliente cliente;
    
    public ClienteView(Cliente cliente){
        this.cliente = cliente;

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        frame.setSize(screenSize.width, screenSize.height);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setTitle("Cliente");

        JPanel panel = new JPanel();
        panel.setSize(screenSize.width, screenSize.height);
        panel.setLayout(null);
        frame.add(panel);

        JLabel labelTitulo = new JLabel("Bem vindo, " + this.cliente.getUsuario());
        labelTitulo.setBounds(20, 50, 200, 50);
        labelTitulo.setFont(new Font("Calibri", Font.BOLD, 20));
        panel.add(labelTitulo);

        JLabel labelDesejo = new JLabel("Deseja alguma coisa? ");
        labelDesejo.setBounds(20, 80, 150, 50);
        panel.add(labelDesejo);

        setItensEstoque(Estoque.getEstoque().verItensDisponiveis().stream().map(item -> item.getProduto().getNome()).toArray(String[]::new));
        JComboBox<String> comboBoxEstoque = new JComboBox<String>(itensEstoque);
        comboBoxEstoque.setBounds(170, 80, 100, 50);
        panel.add(comboBoxEstoque);

        JLabel labelQuantidade = new JLabel("Quantas? ");
        labelQuantidade.setBounds(20, 130, 150, 50);
        panel.add(labelQuantidade);

        itemQuantidade = Estoque.getEstoque().getItems().get(comboBoxEstoque.getSelectedIndex()).getQuantidade();
        JSpinner spinnerQuantidade = new JSpinner(new SpinnerNumberModel(1, 1, itemQuantidade, 1));
        spinnerQuantidade.setBounds(170, 130, 100, 50);
        panel.add(spinnerQuantidade);


        JLabel labelDesejoRemover = new JLabel("Deseja remover algo? ");
        labelDesejoRemover.setBounds(20, 300, 150, 50);
        panel.add(labelDesejoRemover);

        setNomeItensCarrinho(cliente.getCarrinho().getItems().stream().map(item -> item.getProduto().getNome()).toArray(String[]::new));
        JComboBox<String> comboBoxEstoqueRemover = new JComboBox<String>(nomeItensCarrinho);
        comboBoxEstoqueRemover.setBounds(170, 300, 100, 50);
        panel.add(comboBoxEstoqueRemover);

        JLabel labelQuantidadeRemover = new JLabel("Quantos? ");
        labelQuantidadeRemover.setBounds(20, 350, 150, 50);
        panel.add(labelQuantidadeRemover);

        int itemRemoverQuantidade = cliente.getCarrinho().getItems().stream().filter(item -> item.getProduto().getNome().equals((String) comboBoxEstoque.getSelectedItem())).findFirst().orElse(new Item(null, 0)).getQuantidade();
        JSpinner spinnerQuantidadeRemover = new JSpinner(new SpinnerNumberModel(0, 0, itemRemoverQuantidade, 1));
        spinnerQuantidadeRemover.setBounds(170, 350, 100, 50);
        panel.add(spinnerQuantidadeRemover);

        JButton buttonRemover = new JButton("Remover");
        buttonRemover.setBounds(170, 400, 100, 30);
        panel.add(buttonRemover);


        comboBoxEstoqueRemover.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Item itemRemover = cliente.getCarrinho().getItems().stream().filter(item -> item.getProduto().getNome().equals((String) comboBoxEstoqueRemover.getSelectedItem())).findFirst().get();
                spinnerQuantidadeRemover.setModel(new SpinnerNumberModel(0, 0, itemRemover.getQuantidade(), 1));
            }
        });

        comboBoxEstoque.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setItemQuantidade(Estoque.getEstoque().getItems().stream().filter(item -> item.getProduto().getNome().equals((String) comboBoxEstoque.getSelectedItem())).findFirst().get().getQuantidade());
                spinnerQuantidade.setModel(new SpinnerNumberModel(0, 0, itemQuantidade, 1));
            }
        });

        JButton buttonSubmit = new JButton("Adicionar");
        buttonSubmit.setBounds(170, 200, 100, 30);
        panel.add(buttonSubmit);

        JButton logout = new JButton("Desconectar");
        logout.setBounds(20, screenSize.height-300, 200, 30);
        panel.add(logout);

        logout.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                FrameLogin frameLogin = new FrameLogin();
                frame.dispose();
        }});
        

        setItensCarrinho(cliente.getCarrinho().getItems().stream().map(item -> "Item: " + item.getProduto().getNome() + "\nQuantidade: " + item.getQuantidade()).toArray(String[]::new));
        JList<String> listCarrinho = new JList<String>(itensCarrinho);
        listCarrinho.setBounds(screenSize.width/2, 20, screenSize.width/2, screenSize.height/2);
        panel.add(listCarrinho);

        JButton buttonFecharCarrinho = new JButton("Fechar Carrinho.");
        buttonFecharCarrinho.setBounds(screenSize.width/2, screenSize.height/2+50, 150, 50);
        panel.add(buttonFecharCarrinho);

        JLabel labelTotal = new JLabel("Total: " + totalCarrinho);
        labelTotal.setBounds(screenSize.width/2+200, screenSize.height/2+50, 150, 50);
        panel.add(labelTotal);


        buttonRemover.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Produto produto = Estoque.getEstoque().returnProduto((String) comboBoxEstoqueRemover.getSelectedItem());
                cliente.removerProdutoDoCarrinho(produto, (int) spinnerQuantidadeRemover.getValue());

                setItemQuantidade(Estoque.getEstoque().getItems().stream().filter(item -> item.getProduto().getNome().equals((String) comboBoxEstoque.getSelectedItem())).findFirst().get().getQuantidade());
                spinnerQuantidade.setModel(new SpinnerNumberModel(0, 0, itemQuantidade, 1));
                setItensCarrinho(cliente.getCarrinho().getItems().stream().map(item -> "Item: " + item.getProduto().getNome() + "     Quantidade: " + item.getQuantidade() + "        Preço unidade: " + item.getProduto().getPrecoUnitario()).toArray(String[]::new));
                setItensEstoque(Estoque.getEstoque().verItensDisponiveis().stream().map(item -> item.getProduto().getNome()).toArray(String[]::new));
                setNomeItensCarrinho(cliente.getCarrinho().getItems().stream().map(item -> item.getProduto().getNome()).toArray(String[]::new));

                
                DefaultComboBoxModel<String> modelComboBox = new DefaultComboBoxModel<String>();
                for (String string : itensEstoque) {
                    modelComboBox.addElement(string);
                }
                comboBoxEstoque.setModel(modelComboBox);

                DefaultComboBoxModel<String> modelComboBoxAdicionar = new DefaultComboBoxModel<String>();
                for (String string : nomeItensCarrinho) {
                    modelComboBoxAdicionar.addElement(string);
                }
                comboBoxEstoqueRemover.setModel(modelComboBoxAdicionar);

                DefaultListModel<String> modelList = new DefaultListModel<String>();
                for (String string : itensCarrinho) {
                    modelList.addElement(string);
                }
                listCarrinho.setModel(modelList);
            }
        });

        buttonSubmit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Produto produto = Estoque.getEstoque().returnProduto((String) comboBoxEstoque.getSelectedItem());
                cliente.adicionarProdutoAoCarrinho(produto, (Integer) spinnerQuantidade.getValue());

                setItemQuantidade(Estoque.getEstoque().getItems().stream().filter(item -> item.getProduto().getNome().equals((String) comboBoxEstoque.getSelectedItem())).findFirst().get().getQuantidade());
                spinnerQuantidade.setModel(new SpinnerNumberModel(0, 0, itemQuantidade, 1));
                setItensCarrinho(cliente.getCarrinho().getItems().stream().map(item -> "Item: " + item.getProduto().getNome() + "     Quantidade: " + item.getQuantidade() + "        Preço unidade: " + item.getProduto().getPrecoUnitario()).toArray(String[]::new));
                setItensEstoque(Estoque.getEstoque().verItensDisponiveis().stream().map(item -> item.getProduto().getNome()).toArray(String[]::new));
                setNomeItensCarrinho(cliente.getCarrinho().getItems().stream().map(item -> item.getProduto().getNome()).toArray(String[]::new));

                
                DefaultComboBoxModel<String> modelComboBox = new DefaultComboBoxModel<String>();
                for (String string : itensEstoque) {
                    modelComboBox.addElement(string);
                }
                comboBoxEstoque.setModel(modelComboBox);

                DefaultComboBoxModel<String> modelComboBoxAdicionar = new DefaultComboBoxModel<String>();
                for (String string : nomeItensCarrinho) {
                    modelComboBoxAdicionar.addElement(string);
                }
                comboBoxEstoqueRemover.setModel(modelComboBoxAdicionar);

                DefaultListModel<String> modelList = new DefaultListModel<String>();
                for (String string : itensCarrinho) {
                    modelList.addElement(string);
                }
                listCarrinho.setModel(modelList);
            }
        });

        buttonFecharCarrinho.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setTotalCarrinho(cliente.fecharCarrinho());
                labelTotal.setText("Total: " + totalCarrinho);
            }
        });

        frame.setVisible(true);
    }

    public void setItensEstoque(String[] itensEstoque) {
        this.itensEstoque = itensEstoque;
    }

    public void setItensCarrinho(String[] itensCarrinho) {
        this.itensCarrinho = itensCarrinho;
    }

    public void setNomeItensCarrinho(String[] nomeItensCarrinho) {
        this.nomeItensCarrinho = nomeItensCarrinho;
    }

    public void setTotalCarrinho(double totalCarrinho) {
        this.totalCarrinho = totalCarrinho;
    }

    public void setItemQuantidade(int itemQuantidade) {
        this.itemQuantidade = itemQuantidade;
    }
}
