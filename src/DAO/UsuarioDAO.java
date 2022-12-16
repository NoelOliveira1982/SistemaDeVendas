package DAO;

import java.util.ArrayList;
import java.util.Arrays;

import Models.Administrador;
import Models.Cliente;
import Models.Usuario;

public class UsuarioDAO {
    
    public static ArrayList<Usuario> getUsuarios(){
        return new ArrayList<Usuario>(
            Arrays.asList(
                new Cliente("Jorge", "1234"),
                new Administrador("Pedro", "12345"),
                new Administrador("Caua", "12345"),
                new Cliente("Noel", "12345")
            )
        );
    }
}
