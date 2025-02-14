package modelos;

public abstract class Veiculo{
    public int quantidade;
    public String marca;
    public String modelo;
    public int ano;
    public double preco;
    public String cor;
    public double km;

    public Veiculo(int quantidade, String marca, String modelo, int ano, double preco, String cor, double km){
        this.quantidade = quantidade;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.preco = preco;
        this.cor = cor;
        this.km = km;
    }

    
    

}