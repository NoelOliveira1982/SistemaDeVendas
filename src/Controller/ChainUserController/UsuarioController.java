package Controller.ChainUserController;

import Models.Usuario;

public abstract class UsuarioController {
    private UsuarioController next;
    
    public static UsuarioController login(UsuarioController first, UsuarioController... chain){
        UsuarioController head = first;
        for(UsuarioController nextChain: chain) {
            head.next = nextChain;
            head = nextChain;
        }
        return first;
    }

    public abstract boolean check(Usuario usuario);

    protected boolean checkNext(Usuario usuario){
        if(next == null){
            return true;
        }
        return next.check(usuario);
    }
}
