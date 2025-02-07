package modelos;

public class Cliente {
    private String nome;
    private int cpf;  
    protected String endereco;
    protected int contato;

    public Cliente(String nome, int cpf, String endereco, int contato){
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.contato = contato;
    }

    public void setCpf(int cpf){
        this.cpf = cpf;
    }
    public int getCpf(){
        return cpf;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    public String getNome(){
        return nome;
    }

    public void setEndereco(String endereco){
        this.endereco = endereco;
    }
    public String getEndereco(){
        return endereco;
    }

    public void setContato(int contato){
        this.contato = contato;
    }
    public int getContato(){
        return contato;
    }
}
