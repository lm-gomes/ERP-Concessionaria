package modelos;
//Classe mae 
// Ela serve como base para outras classes, como Carro e Moto.
public abstract class Veiculo{
    // Atributos da classe Veiculo.
    public int quantidade;
    public String marca;
    public String modelo;
    public int ano;
    public double preco;
    public String cor;
    public double km;
   
    // Construtor da classe Veiculo
    public Veiculo(int quantidade, String marca, String modelo, int ano, double preco, String cor, double km){
        this.quantidade = quantidade; // Inicializa os atributos da classe com os valores passados com parâmetros.
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.preco = preco;
        this.cor = cor;
        this.km = km;
    }

    
    

}