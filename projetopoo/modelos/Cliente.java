package modelos;

public class Cliente {
    private String nome;
    private long cpf;  
    protected String endereco;
    protected long contato;

    public Cliente(String nome, long cpf, String endereco, long contato){
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.contato = contato;
    }

    public void setCpf(long cpf){
        this.cpf = cpf;
    }
    public long getCpf(){
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
    public long getContato(){
        return contato;
    }
}
