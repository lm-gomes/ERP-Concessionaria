package modelos;

public class Usuario{
    public String login;
    public int senha;
    public char tipo;

    public Usuario(String login , int senha , char tipo){
        this.login = login;
        this.senha = senha;
        this.tipo = tipo;
    }
}