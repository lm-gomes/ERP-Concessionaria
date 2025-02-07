package modelos;// Faz parte do pacote modelos

// A classe Moto herda de classe Veiculo.
public class Moto extends Veiculo {
    // Construtor da classe Moto.
    public Moto(int quantidade, String marca, String modelo, int ano, double preco, String cor,double km){
        super(quantidade, marca, modelo, ano, preco, cor, km); // Chama o construtor da classe(Veiculo) para inicializar os atributos herdados.
    }
}