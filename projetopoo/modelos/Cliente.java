package modelos;

public class Cliente {
    public String nome;  
    protected String endereco;
    protected int contato;
    private int senha;

    public Cliente(String nome, String endereco, int contato, int senha){
        this.nome = nome;
        this.endereco = endereco;
        this.contato = contato;
        this.senha = senha;
    }

    public void setSenha(int senha){
        this.senha = senha;
    }
    public int getSenha(){
        return senha;
    }

    public void alterarInformacoes(String endereco, int contato){
        System.out.println("Nao e possivel alterar nome e senha!");
        this.endereco = endereco;
        this.contato = contato;
    }
}
