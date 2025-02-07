package modelos;// Faz parte do pacote modelos.

//A classe Carro herda da classe Veiculo.
public class Carro extends Veiculo {
    private int qtdPortas; // Atributo específico da classe Carro: quantidade de portas.

    // Construtor da Classe Carro.
    public Carro(int quantidade, String marca, String modelo, int ano, double preco, String cor,double km , int qtdPortas){
        super(quantidade, marca, modelo, ano, preco, cor, km); //Chama o construtor da Classe (Veiculo) para inicializar os atributos herdados.
        this.qtdPortas = qtdPortas; // Inicializa o atributo especifico classe Carro(quantidade de portas)
    }

}
