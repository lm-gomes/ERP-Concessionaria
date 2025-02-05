package view;
import modelos.Veiculo;
import modelos.Carro;
import modelos.Moto;
import modelos.Cliente;
import java.util.Scanner;

import controller.ControleEstoque;
import controller.ControleClientes;
import controller.ControleVendas;

public class Screen {
    public static void menuPrincipal(){
        try(Scanner scanner = new Scanner(System.in)){
            System.out.print("[1]Cadastrar veiculo\n[2]Consultar veiculos\n[3]Remover veiculo\n[4]Cadastrar Cliente\n[5]Vender Veiculo\n>>");
            int userInput = scanner.nextInt();
            switch(userInput){
                case 1:{
                    menuCadastroVeiculo();
                    break;
                }

                case 2:{
                    menuConsultaVeiculo();
                    break;
                }

                case 3:{
                    break;
                    
                }
                case 4:{
                    menuCadastrarCliente();
                }
                case 5:{
                    menuVendas();
                }
            }
        }
        catch(Exception e){
            System.out.print(e.getMessage());
        }
        
        
    }

    public static void menuCadastroVeiculo(){
        try(Scanner scanner = new Scanner(System.in)){
            System.out.println("[1]Carro\n[2]Moto\n>> ");
            int tipoVeiculo = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Marca:");
            String marcaVeiculo = scanner.nextLine();
            System.out.println("Modelo: ");
            String modeloVeiculo = scanner.nextLine();
            /*System.out.println("Ano: ");
            int anoVeiculo = scanner.nextInt();
            System.out.println("Preco: ");
            double precoVeiculo = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("Cor: ");
            String corVeiculo = scanner.nextLine();
            System.out.println("KM: ");
            double kmVeiculo = scanner.nextDouble();
            */
            if(tipoVeiculo == 1){
                /*System.out.println("Portas: ");
                int portasVeiculo = scanner.nextInt();
                System.out.println("Quantidade: ");
                int qtdVeiculo = scanner.nextInt();
                */
                Carro carro = new Carro(2, marcaVeiculo, modeloVeiculo, 1, 1, "red", 0, 4);
                ControleEstoque.cadastrarVeiculo(carro);
            }
            else{
                //System.out.println("Quantidade: ");
                //int qtdVeiculo = scanner.nextInt();
                Moto moto = new Moto(2, marcaVeiculo, modeloVeiculo, 1, 1, "cor", 1);
                ControleEstoque.cadastrarVeiculo(moto);
            }
            

        }
        catch(Exception e){
            System.out.println("Erro: Entrada invalida!");
        }

    }

    public static void menuConsultaVeiculo(){
        try(Scanner scanner = new Scanner(System.in)){
            System.out.print("[1]Carro\n[2]Moto\nDigite o tipo de veículo: ");
            int userInput = scanner.nextInt();
            if(userInput == 1){
                ControleEstoque.consultarEstoque("data/carros.txt");
            }
            else{
                ControleEstoque.consultarEstoque("data/motos.txt");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void menuRemoveVeiculo(){
        try(Scanner scanner = new Scanner(System.in)){
            System.out.print("[1]Carro\n[2]Moto\n>>");
            int userInput = scanner.nextInt();
            System.out.println("Digite o indice: ");
            int userIndex = scanner.nextInt();
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void menuCadastrarCliente(){
        try(Scanner scan = new Scanner(System.in)){
            System.out.println("NOME: ");
            String userNome = scan.nextLine();
            System.out.println("ENDERECO: ");
            String userEndereco = scan.nextLine();
            System.out.println("CONTATO: ");
            int userContato = scan.nextInt();

            Cliente cliente = new Cliente(userNome, userEndereco, userContato);
            ControleClientes.cadastrarClientes(cliente);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void menuVendas(){
        try(Scanner scan = new Scanner(System.in)){
            System.out.print("[1]Carro\n[2]Moto\n>>");
            int userInput = scan.nextInt();
            if(userInput == 1){
                ControleVendas.exibirVeiculo("data/carros.txt");
            }
            else{
                ControleVendas.exibirVeiculo("data/motos.txt");
            }

            System.out.println("Digite o modelo\n>>");
            String modelo = scan.nextLine();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
}
