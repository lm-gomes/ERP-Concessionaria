package modelos;

public class Carro extends Veiculo {
    private int qtdPortas;
    public Carro(int quantidade, String marca, String modelo, int ano, double preco, String cor,double km , int qtdPortas){
        super(quantidade, marca, modelo, ano, preco, cor, km);
        this.qtdPortas = qtdPortas;
    }

}
