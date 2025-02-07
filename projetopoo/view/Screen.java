package view;
import modelos.Veiculo;
import modelos.Carro;
import modelos.Moto;
import java.util.Scanner;

import controller.ControleEstoque;

public class Screen {
    public static void menuPrincipal(){
        try(Scanner scanner = new Scanner(System.in)){
            System.out.print("[1]Venda\n[2]Gerenciar Estoque\n[3]Gerenciar Usuarios\n>>");
            int userInput = scanner.nextInt();
            switch(userInput){
                case 1:{
                    ControleEstoque.logEstoque("Data:" );
                    break;
                }

                case 2:{
                    System.out.print("[1]Cadastrar veiculo\n[2]Consultar veiculos\n[3]Remover veiculo\n[4]Registros\n>>");
                    userInput = scanner.nextInt();
                    if(userInput == 1){
                        menuCadastroVeiculo();
                        
                    }
                    else if(userInput == 2){
                        menuConsultaVeiculo();

                    }
                    else if(userInput == 3){
                        menuRemoveVeiculo();

                    }
                    else if(userInput == 4){
                        ControleEstoque.consultarLog();
                    }
                    break;
                }

                case 3:{
                    
                    break;
                }

                case 4:{
                    
                }
            }
        }
        catch(Exception e){
            System.out.print("Oi");
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
            String nomeArquivo = userInput == 1 ? "data/carros.txt" : "data/motos.txt";
            String arquivoModelo = userInput == 1 ? "data/m_carros.txt" : "data/m_motos.txt";

            System.out.println("Digite o indice: ");
            int userIndex = scanner.nextInt();
            ControleEstoque.consultarEstoque(nomeArquivo);
            ControleEstoque.removerVeiculo(userIndex, nomeArquivo, arquivoModelo);
            
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
