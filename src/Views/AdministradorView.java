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
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Models.Administrador;
import Models.Estoque;
import Models.Item;
import Models.Produto;

public class AdministradorView {

    private String[] itensEstoque;
    private String[] nomeItensEstoqueRemover;
    private String[] nomeItensEstoqueAdicionar;
    private int itemQuantidade;
    private Administrador adm;

    public AdministradorView(Administrador administrador) {
        this.adm = administrador;

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        frame.setSize(screenSize.width, screenSize.height);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setTitle("Administrador");

        JPanel panel = new JPanel();
        panel.setSize(screenSize.width, screenSize.height);
        panel.setLayout(null);
        frame.add(panel);

        JLabel labelTitulo = new JLabel("Bem vindo, " + this.adm.getUsuario());
        labelTitulo.setBounds(20, 50, 200, 50);
        labelTitulo.setFont(new Font("Calibri", Font.BOLD, 20));
        panel.add(labelTitulo);

        JLabel labelDesejo = new JLabel("Deseja cadastrar algo? ");
        labelDesejo.setBounds(20, 80, 150, 50);
        panel.add(labelDesejo);

        JTextField textFieldNomeProduto = new JTextField();
        textFieldNomeProduto.setBounds(170, 80, 100, 50);
        panel.add(textFieldNomeProduto);

        JLabel labelPrecoUnidade = new JLabel("Quanto custa a unidade?");
        labelPrecoUnidade.setBounds(20, 130, 200, 50);
        panel.add(labelPrecoUnidade);

        JSpinner spinnerPrecoUnidade = new JSpinner(new SpinnerNumberModel(1, 1, 9999, 1));
        spinnerPrecoUnidade.setBounds(170, 130, 100, 50);
        panel.add(spinnerPrecoUnidade);

        JLabel labelQuantidade = new JLabel("Quantas? ");
        labelQuantidade.setBounds(20, 170, 150, 50);
        panel.add(labelQuantidade);

        JSpinner spinnerQuantidade = new JSpinner(new SpinnerNumberModel(1, 1, 9999, 1));
        spinnerQuantidade.setBounds(170, 170, 100, 50);
        panel.add(spinnerQuantidade);

        JButton buttonSubmit = new JButton("Adicionar");
        buttonSubmit.setBounds(170, 250, 100, 30);
        panel.add(buttonSubmit);

        setItensEstoque(Estoque.getEstoque().getItems().stream().map(item -> "Produto: " + item.getProduto().getNome() + "    Preço unidade: " + item.getProduto().getPrecoUnitario() + "        Quantidade: " + item.getQuantidade()).toArray(String[]::new));
        JList<String> listEstoque = new JList<String>(itensEstoque);
        listEstoque.setBounds(screenSize.width/2, 20, screenSize.width/2, screenSize.height);
        panel.add(listEstoque);

        JLabel labelDesejoRemover = new JLabel("Deseja remover algo? ");
        labelDesejoRemover.setBounds(20, 300, 150, 50);
        panel.add(labelDesejoRemover);

        setnomeItensEstoqueRemover(Estoque.getEstoque().verItensDisponiveis().stream().map(item -> item.getProduto().getNome()).toArray(String[]::new));
        JComboBox<String> comboBoxEstoque = new JComboBox<String>(nomeItensEstoqueRemover);
        comboBoxEstoque.setBounds(170, 300, 100, 50);
        panel.add(comboBoxEstoque);

        JLabel labelQuantidadeRemover = new JLabel("Quantos? ");
        labelQuantidadeRemover.setBounds(20, 350, 150, 50);
        panel.add(labelQuantidadeRemover);

        Item itemRemover = Estoque.getEstoque().getItems().stream().filter(item -> item.getProduto().getNome().equals((String) comboBoxEstoque.getSelectedItem())).findFirst().get();
        JSpinner spinnerQuantidadeRemover = new JSpinner(new SpinnerNumberModel(1, 1, itemRemover.getQuantidade(), 1));
        spinnerQuantidadeRemover.setBounds(170, 350, 100, 50);
        panel.add(spinnerQuantidadeRemover);

        JButton buttonRemover = new JButton("Remover");
        buttonRemover.setBounds(170, 400, 100, 30);
        panel.add(buttonRemover);

        JLabel labelDesejoAdicionar = new JLabel("Deseja adicionar algo? ");
        labelDesejoAdicionar.setBounds(20, 450, 150, 50);
        panel.add(labelDesejoAdicionar);

        setNomeItensEstoqueAdicionar(Estoque.getEstoque().getItems().stream().map(item -> item.getProduto().getNome()).toArray(String[]::new));
        JComboBox<String> comboBoxEstoqueAdicionar = new JComboBox<String>(nomeItensEstoqueAdicionar);
        comboBoxEstoqueAdicionar.setBounds(170, 450, 100, 50);
        panel.add(comboBoxEstoqueAdicionar);

        JLabel labelQuantidadeAdicionar = new JLabel("Quantos? ");
        labelQuantidadeAdicionar.setBounds(20, 500, 150, 50);
        panel.add(labelQuantidadeAdicionar);

        JSpinner spinnerQuantidadeAdicionar = new JSpinner(new SpinnerNumberModel(1, 1, 9999, 1));
        spinnerQuantidadeAdicionar.setBounds(170,500, 100, 50);
        panel.add(spinnerQuantidadeAdicionar);

        JButton buttonAdicionar = new JButton("Adicionar");
        buttonAdicionar.setBounds(170, 550, 100, 30);
        panel.add(buttonAdicionar);

        JButton logout = new JButton("Desconectar");
        logout.setBounds(20, screenSize.height-300, 200, 30);
        panel.add(logout);

        logout.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                FrameLogin frameLogin = new FrameLogin();
                frame.dispose();
        }});

        comboBoxEstoque.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setItemQuantidade(Estoque.getEstoque().getItems().stream().filter(item -> item.getProduto().getNome().equals((String) comboBoxEstoque.getSelectedItem())).findFirst().get().getQuantidade());
                spinnerQuantidadeRemover.setModel(new SpinnerNumberModel(0, 0, itemQuantidade, 1));
            }
        });

        buttonAdicionar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {

                if((int) spinnerQuantidadeAdicionar.getValue() <= 0){
                    return;
                }
                Item itemAdicionar = Estoque.getEstoque().getItems().stream().filter(item -> item.getProduto().getNome().equals((String) comboBoxEstoqueAdicionar.getSelectedItem())).findFirst().get();
                adm.adicionarProduto(itemAdicionar.getProduto(), (int) spinnerQuantidadeAdicionar.getValue());
                setItensEstoque(Estoque.getEstoque().getItems().stream().map(item -> "Produto: " + item.getProduto().getNome() + "    Preço unidade: " + item.getProduto().getPrecoUnitario() + "        Quantidade: " + item.getQuantidade()).toArray(String[]::new));

                DefaultListModel<String> modelList = new DefaultListModel<String>();
                for (String string : itensEstoque) {
                    modelList.addElement(string);
                }
                listEstoque.setModel(modelList);

                setnomeItensEstoqueRemover(Estoque.getEstoque().verItensDisponiveis().stream().map(item -> item.getProduto().getNome()).toArray(String[]::new));
                DefaultComboBoxModel<String> modelComboBox = new DefaultComboBoxModel<String>();
                for (String string : nomeItensEstoqueRemover) {
                    modelComboBox.addElement(string);
                }
                comboBoxEstoque.setModel(modelComboBox);
            }
        });


        buttonRemover.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {

                if((int) spinnerQuantidadeRemover.getValue() <= 0){
                    return;
                }
                Item itemRemover = Estoque.getEstoque().getItems().stream().filter(item -> item.getProduto().getNome().equals((String) comboBoxEstoque.getSelectedItem())).findFirst().get();
                adm.removerProduto(itemRemover.getProduto(), (int) spinnerQuantidadeRemover.getValue());
                setItensEstoque(Estoque.getEstoque().getItems().stream().map(item -> "Produto: " + item.getProduto().getNome() + "    Preço unidade: " + item.getProduto().getPrecoUnitario() + "        Quantidade: " + item.getQuantidade()).toArray(String[]::new));

                DefaultListModel<String> modelList = new DefaultListModel<String>();
                for (String string : itensEstoque) {
                    modelList.addElement(string);
                }
                listEstoque.setModel(modelList);

                setnomeItensEstoqueRemover(Estoque.getEstoque().verItensDisponiveis().stream().map(item -> item.getProduto().getNome()).toArray(String[]::new));
                DefaultComboBoxModel<String> modelComboBox = new DefaultComboBoxModel<String>();
                for (String string : nomeItensEstoqueRemover) {
                    modelComboBox.addElement(string);
                }
                comboBoxEstoque.setModel(modelComboBox);
            }
        });

        buttonSubmit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if(textFieldNomeProduto.getText().length() <= 0){
                    return;
                }
                if((int) spinnerQuantidade.getValue() <= 0){
                    return;
                }
                adm.adicionarProduto(new Produto(textFieldNomeProduto.getText(), (int) spinnerPrecoUnidade.getValue()), (int) spinnerQuantidade.getValue());
                setItensEstoque(Estoque.getEstoque().getItems().stream().map(item -> "Produto: " + item.getProduto().getNome() + "    Preço unidade: " + item.getProduto().getPrecoUnitario() + "        Quantidade: " + item.getQuantidade()).toArray(String[]::new));

                setNomeItensEstoqueAdicionar(Estoque.getEstoque().getItems().stream().map(item -> item.getProduto().getNome()).toArray(String[]::new));
                DefaultComboBoxModel<String> modelComboBoxAdicionar = new DefaultComboBoxModel<String>();
                for (String string : nomeItensEstoqueAdicionar) {
                    modelComboBoxAdicionar.addElement(string);
                }
                comboBoxEstoqueAdicionar.setModel(modelComboBoxAdicionar);

                DefaultListModel<String> modelList = new DefaultListModel<String>();
                for (String string : itensEstoque) {
                    modelList.addElement(string);
                }
                listEstoque.setModel(modelList);

                setnomeItensEstoqueRemover(Estoque.getEstoque().verItensDisponiveis().stream().map(item -> item.getProduto().getNome()).toArray(String[]::new));
                DefaultComboBoxModel<String> modelComboBox = new DefaultComboBoxModel<String>();
                for (String string : nomeItensEstoqueRemover) {
                    modelComboBox.addElement(string);
                }
                comboBoxEstoque.setModel(modelComboBox);
            }
        });
    }

    public void setNomeItensEstoqueAdicionar(String[] nomeItensEstoqueAdicionar) {
        this.nomeItensEstoqueAdicionar = nomeItensEstoqueAdicionar;
    }

    public void setnomeItensEstoqueRemover(String[] nomeItensEstoqueRemover) {
        this.nomeItensEstoqueRemover = nomeItensEstoqueRemover;
    }

    public void setItemQuantidade(int itemQuantidade) {
        this.itemQuantidade = itemQuantidade;
    }

    public void setItensEstoque(String[] itensEstoque) {
        this.itensEstoque = itensEstoque;
    }
    
}
