package Controller.ChainUserController;

import Models.Cliente;
import Models.Usuario;
import Views.ClienteView;

public class ClienteController extends UsuarioController{

    @Override
    public boolean check(Usuario usuario) {
        if(usuario.getClass().getSimpleName().equals("Cliente")){
            ClienteView clienteView = new ClienteView((Cliente) usuario);
            return true;
        }
        return checkNext(usuario);
    }
    
}
