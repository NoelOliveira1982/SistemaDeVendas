package Views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import Controller.ChainUserController.AdministradorController;
import Controller.ChainUserController.ClienteController;
import Controller.ChainUserController.UsuarioController;
import DAO.UsuarioDAO;
import Models.Usuario;

public class FrameLogin {

    private UsuarioController controllerLogin = UsuarioController.login(new ClienteController(), new AdministradorController());
    
    public FrameLogin(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        frame.setSize(screenSize);
        frame.setLayout(null);
        frame.setTitle("Sistema de Vendas");

        JPanel panel = new JPanel();
        panel.setSize(screenSize);
        panel.setLayout(null);
        frame.add(panel);

        JLabel labelTitulo = new JLabel("Sistema de Vendas");
        labelTitulo.setBounds(20, 50, 200, 50);
        labelTitulo.setFont(new Font("Calibri", Font.BOLD, 20));
        panel.add(labelTitulo);

        JLabel labelUsuario = new JLabel("Usuário");
        labelUsuario.setBounds(20, 130, 80, 30);
        panel.add(labelUsuario);


        JTextField txtFieldUsuario = new JTextField(50);
        txtFieldUsuario.setBounds(100, 130, 150, 30);
        panel.add(txtFieldUsuario);


        JLabel labelSenha = new JLabel("Senha");
        labelSenha.setBounds(20, 160, 80, 30);
        panel.add(labelSenha);

        JPasswordField txtFieldSenha = new JPasswordField(50);
        txtFieldSenha.setBounds(100, 160, 150, 30);
        panel.add(txtFieldSenha);

        JButton buttonSubmit = new JButton("Entrar");
        buttonSubmit.setBounds(150, 200, 100, 30);
        
        buttonSubmit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Optional<Usuario> usuario = UsuarioDAO.getUsuarios().stream()
                .filter(usuarioFinder -> usuarioFinder.getUsuario().equals(txtFieldUsuario.getText()) && usuarioFinder.getSenha().equals(String.valueOf(txtFieldSenha.getPassword())))
                .findFirst();
                if(usuario.isPresent()){
                    if(controllerLogin.check(usuario.get())){
                        frame.dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário não cadastrado");
                }
            }
        });
        panel.add(buttonSubmit);

        frame.setVisible(true);
    }
}
