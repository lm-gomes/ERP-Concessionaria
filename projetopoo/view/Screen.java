package view;
import modelos.Veiculo;
import modelos.Vendedor;
import modelos.Carro;
import modelos.Estoquista;
import modelos.Gerente;
import modelos.Moto;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import controller.ControleEstoque;
import controller.ControleUsuarios;

public class Screen {
    public static void menuPrincipal(){
        try(Scanner scanner = new Scanner(System.in)){
            System.out.print("[1]Cadastrar veiculo\n[2]Consultar veiculos\n[3]Remover veiculo\n>>");
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
            System.out.println("Digite o indice: ");
            int userIndex = scanner.nextInt();
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void menuCadastroUsuario(){
        try(Scanner sc = new Scanner(System.in)){
            System.out.println("Insira o tipo de usuário:\n [1] Gerente\n [2] Estoquista\n [3] Vendedor\n>> ");
            int userInput = sc.nextInt();
            sc.nextLine();
            System.out.println("Digite o login do usuário: ");
            String login = sc.nextLine();
            System.out.println("Digite a senha do usuário: ");
            int senha = sc.nextInt();
            
            login = login + " ";
            switch (userInput){
                case 1:
                    Gerente gerente = new Gerente(login, senha, 'G');
                    ControleUsuarios.cadastrarUsuario(gerente);
                    break;
                case 2:
                    Estoquista estoquista = new Estoquista(login , senha , 'E');
                    ControleUsuarios.cadastrarUsuario(estoquista);
                    break;
                case 3:
                    Vendedor vendedor = new Vendedor(login , senha, 'V');
                    ControleUsuarios.cadastrarUsuario(vendedor);
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }

            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void menuRemoverUsuario(){
        System.out.println("Digite o login do usuário que você deseja remover: \n >> ");
        
        try(Scanner sc = new Scanner(System.in)){
           String userInput = sc.nextLine();
           ControleUsuarios.removerUsuario(userInput);
        } catch(NoSuchElementException e){
            e.printStackTrace();
        }

    }
}
