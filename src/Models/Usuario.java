package Models;
public abstract class Usuario {
    private String usuario;
    private String senha;

    public Usuario(String usuario, String senha){
        this.usuario = usuario;
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void logout(){}
}
