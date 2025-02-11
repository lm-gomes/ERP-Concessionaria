package view;

import modelos.Usuario;
import modelos.Veiculo;
import modelos.Vendedor;
import modelos.Carro;
import modelos.Estoquista;
import modelos.Gerente;
import modelos.Moto;
import modelos.Cliente;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import controller.ControleEstoque;
import controller.ControleUsuarios;
import controller.ControleClientes;
import controller.ControleVendas;

public class Screen {
    public static void menuPrincipal(){
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("Insira seu login: ");
            String login = sc.nextLine();
            System.out.println("Digite sua senha: ");
            int senha = sc.nextInt();

            if(ControleUsuarios.autenticarUsuario(login, senha)){
                ControleUsuarios.tipoUser(login);
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

    public static void menuCadastroUsuario(){
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("Insira o tipo de usuário:\n [1] Gerente\n [2] Estoquista\n [3] Vendedor\n>> ");
            int userInput = sc.nextInt();
            sc.nextLine();
            System.out.println("Digite o login do usuário: ");
            String login = sc.nextLine();
            login = login.toLowerCase();
            int senha = 0; 
            if(ControleUsuarios.validarLogin(login)){
                System.out.println("Digite a senha do usuário: ");
                senha = sc.nextInt();
                sc.nextLine();
            }
            
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


        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void menuRemoverUsuario(){
        System.out.println("Digite o login do usuário que você deseja remover: \n >> ");
        
        try{
           Scanner sc = new Scanner(System.in);
           String userInput = sc.nextLine();
           ControleUsuarios.removerUsuario(userInput);
        }
        catch(NoSuchElementException e){
            e.printStackTrace();
        }
    }

    public static void menuGerente(String login){
        System.out.println("Bem - vindo, " + login);
        System.out.print("Escolha uma operação:\n [1] Operar usuários.\n [2] Operar estoque.\n [3] Fazer venda.\n  >> ");
        Scanner sc = new Scanner(System.in);
        int userOp = sc.nextInt();
        
        switch (userOp) {
            case 1:
                System.out.print("O que deseja fazer:\n [1] Cadastrar novo usuário.\n [2] Remover usuário.");
                int input1 = sc.nextInt();
                switch (input1) {
                    case 1:
                        menuCadastroUsuario();
                        break;
                    case 2:
                        menuRemoverUsuario();
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
                break;
            case 2:
                System.out.print("O que deseja fazer:\n [1] Cadastrar novo veiculo.\n [2] Remover veíuculo."); // Realmente é necessário o remover veículo? A remoção não é feita automaticamente após uma venda? 
                int input2 = sc.nextInt();
                switch (input2) {
                    case 1:
                        menuCadastroVeiculo();
                        break;
                    case 2:
                        menuRemoveVeiculo();
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
                break;
            case 3:
                System.out.print("O que deseja fazer:\n [1] Vender veículo\n [2] Cadastrar cliente.\n [3] Alterar dados do cliente.\n [4] Remover cliente. \n [5] Exibir clientes.\n >>");
                int input3 = sc.nextInt();
                switch (input3) {
                    case 1:
                        menuVendas();
                        break;
                    case 2:
                        menuCadastrarCliente();
                        break;
                    case 3:
                        menuAlterarDadosCliente();
                        break;
                    case 4:
                        menuRemoverCliente();
                        break;
                    case 5:
                        ControleClientes.exibirClientes();
                        break;
                    default: 
                        System.out.println("Opção inválida.");
                        break;
                }
                break;
                default:
                System.out.println("Opção inválida.");
                break;
        }
        sc.close();
    }

    public static void menuEstoquista(String login){
        System.out.println("Bem - vindo, " + login);
        System.out.print("O que deseja fazer:\n [1] Cadastrar novo veiculo.\n [2] Remover veíuculo.");
        Scanner sc = new Scanner(System.in);
        int userOp = sc.nextInt();
        
        switch (userOp) {
            case 1:
                menuCadastroVeiculo();
                break;
            case 2:
                menuRemoveVeiculo();
                break;
                default:
                System.out.println("Opção inválida.");
                break;
        }
        sc.close();
    }

    public static void menuVendedor(String login){
        System.out.println("Bem - vindo, " + login);
        System.out.print("O que deseja fazer:\n [1] Vender veículo\n [2] Cadastrar cliente.\n [3] Alterar dados do cliente.\n [4] Remover cliente. \n [5] Exibir clientes.\n >>");
        Scanner sc = new Scanner(System.in);
        int userOp = sc.nextInt();
        
        switch (userOp) {
            case 1:
                        menuVendas();
                        break;
                    case 2:
                        menuCadastrarCliente();
                        break;
                    case 3:
                        menuAlterarDadosCliente();
                        break;
                    case 4:
                        menuRemoverCliente();
                        break;
                    case 5:
                        ControleClientes.exibirClientes();
                        break;
                    default: 
                        System.out.println("Opção inválida.");
                        break;
        }
        sc.close();
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
