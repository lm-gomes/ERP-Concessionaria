package modelos;

public class Cliente {
<<<<<<< HEAD
    private String nome;
    private long cpf;  
    protected String endereco;
    protected long contato;

    public Cliente(String nome, long cpf, String endereco, long contato){
        this.nome = nome;
        this.cpf = cpf;
=======
    private String nome;  
    protected String endereco;
    protected int contato;

    public Cliente(String nome, String endereco, int contato){
        this.nome = nome;
>>>>>>> parent of 36eb50d (delete Main)
        this.endereco = endereco;
        this.contato = contato;
    }

<<<<<<< HEAD
    public void setCpf(long cpf){
        this.cpf = cpf;
    }
    public long getCpf(){
        return cpf;
    }

=======
>>>>>>> parent of 36eb50d (delete Main)
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
<<<<<<< HEAD
    public long getContato(){
=======
    public int getContato(){
>>>>>>> parent of 36eb50d (delete Main)
        return contato;
    }
}
