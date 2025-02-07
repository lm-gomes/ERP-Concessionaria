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
            System.out.print("[1]Cadastrar veiculo\n[2]Consultar veiculos\n[3]Remover veiculo\n[4]Cadastrar Cliente\n[5]Remover cliente\n[6]Vender veiculo\n[7]Alterar Dados do Cliente\n[8]Exibir clientes\n>>");
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
                    break;
                }
                case 5:{
                    menuRemoverCliente();
                    break;
                }
                case 6:{
                    menuVendas();
                    break;
                }
                case 7:{
                    menuAlterarDadosCliente();
                    break;
                }
                case 8:{
                    ControleClientes.exibirClientes();
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
            String clienteNome = scan.nextLine();

            System.out.println("CPF: ");
            int clienteCpf = scan.nextInt();
            scan.nextLine();

            System.out.println("ENDERECO: ");
            String clienteEndereco = scan.nextLine();

            System.out.println("CONTATO: ");
            int clienteContato = scan.nextInt();

            Cliente cliente = new Cliente(clienteNome, clienteCpf, clienteEndereco, clienteContato);
            ControleClientes.cadastrarClientes(cliente);

            System.out.println("Cliente cadastrado com sucesso!");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void menuVendas(){
        try(Scanner scan = new Scanner(System.in)){
            ControleClientes.exibirClientes();

            System.out.print("Indice do cliente que fara a compra\n>>");
            int id = scan.nextInt();

            System.out.print("[1]Carro\n[2]Moto\n>>");
            int userInput = scan.nextInt();
            if(userInput == 1){
                ControleVendas.exibirVeiculo("data/carros.txt");

                System.out.print("Digite o indice do carro para a venda\n>>");
                int indice = scan.nextInt();

                // ControleEstoque.removerVeiculo(indice, "data/carros.txt");

                ControleVendas.relatorioDeVendas(id, indice, "data/carros.txt");

                System.out.println("Carro vendido!");
            }
            else{
                ControleVendas.exibirVeiculo("data/motos.txt");
                System.out.print("Digite o indice da moto para a venda\n>>");
                int indice = scan.nextInt();
                
                // ControleEstoque.removerVeiculo(indice, "data/moto.txt");

                ControleVendas.relatorioDeVendas(id, indice, "data/moto.txt");

                System.out.println("Moto vendida!");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    public static void menuAlterarDadosCliente(){
        try(Scanner scan = new Scanner(System.in)){
            ControleClientes.exibirClientes();

            System.out.print("Digite o indice do cliente para trocar suas informacoes\n>>");
            int index = scan.nextInt();
            scan.nextLine();

            System.out.println("Nao e recomendado alterar nome e cpf");

            System.out.print("Digite o nome do cliente\n>>");
            String nome = scan.nextLine();

            System.out.print("Digite o cpf do cliente\n>>");
            int cpf = scan.nextInt();
            scan.nextLine();

            System.out.print("Digite o novo endereco\n>>");
            String end = scan.nextLine();

            System.out.print("Digite o novo numero de telefone\n>>");
            int tel = scan.nextInt();

            ControleClientes.alterarDadosCliente(index, nome, cpf, end, tel);

            System.out.println("Informacoes atualizadas!");
        }
    }

    public static void menuRemoverCliente(){
        try(Scanner scan = new Scanner(System.in)){
            ControleClientes.exibirClientes();

            System.out.print("Digite o indice do cliente a remover\n>>");
            int index = scan.nextInt();

            ControleClientes.removerCliente(index);

            System.out.println("Cliente removido!");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
