package Controller.ChainUserController;

import Models.Administrador;
import Models.Usuario;
import Views.AdministradorView;

public class AdministradorController extends UsuarioController {

    @Override
    public boolean check(Usuario usuario) {
        if(usuario.getClass().getSimpleName().equals("Administrador")){
            AdministradorView admView = new AdministradorView((Administrador) usuario);
            return true;
        }
        return checkNext(usuario);
    }
    
}
