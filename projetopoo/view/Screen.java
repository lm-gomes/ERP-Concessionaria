<<<<<<< HEAD
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
    static Scanner sc = new Scanner(System.in);
    public static void menuPrincipal(){
        
        try{
            printMenu(2);
            System.out.print("Usuario: ");
            String login = sc.nextLine();
            System.out.print("Senha: ");
            int senha = sc.nextInt();
            sc.nextLine();
            limparTela();
            if(ControleUsuarios.autenticarUsuario(login, senha)){
                ControleUsuarios.tipoUser(login);
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        
    }

    public static void menuCadastroVeiculo(){
        try{
            System.out.println("[1]Carro\n[2]Moto\n>> ");
            int tipoVeiculo = sc.nextInt();
            sc.nextLine();
            limparTela();
            
            if(tipoVeiculo <= 0 || tipoVeiculo > 2){
                throw new IllegalArgumentException("[ERRO] Indice invalido!");
            }
            System.out.print("Marca:");
            String marcaVeiculo = sc.nextLine();
            System.out.print("Modelo: ");
            String modeloVeiculo = sc.nextLine();

            System.out.print("Ano: ");
            int anoVeiculo = sc.nextInt();

            if(anoVeiculo < 1886 || anoVeiculo > 2025){
                throw new IllegalArgumentException("[ERRO] Ano inválido!");
            }
            System.out.print("Preco: ");
            double precoVeiculo = sc.nextDouble();
            sc.nextLine();
            if(precoVeiculo < 1000){
                throw new IllegalArgumentException("[ERRO] Quer dar logo não? (Preço abaixo do padrão)");
            }
            System.out.print("Cor: ");
            String corVeiculo = sc.nextLine();

            System.out.print("KM: ");
            double kmVeiculo = sc.nextDouble();
            if(kmVeiculo < 0){
                throw new IllegalArgumentException("[ERRO] Quilometragem invalida!");
            }
            
            if(tipoVeiculo == 1){
                System.out.println("Portas: ");
                int portasVeiculo = sc.nextInt();
                if(portasVeiculo <=0 || portasVeiculo == 1 || portasVeiculo > 4){
                    throw new IllegalArgumentException("[ERRO] Quantidade de portas invalida!");
                }
                System.out.println("Quantidade: ");
                int qtdVeiculo = sc.nextInt();
                if(qtdVeiculo <= 0){
                    throw new IllegalArgumentException("[ERRO] Quantidade de veiculos invalida!");
                }
                Carro carro = new Carro(qtdVeiculo, marcaVeiculo, modeloVeiculo, anoVeiculo, precoVeiculo, corVeiculo, kmVeiculo, portasVeiculo);
                ControleEstoque.cadastrarVeiculo(carro);
            }
            else{
                System.out.println("Quantidade: ");
                int qtdVeiculo = sc.nextInt();
                if(qtdVeiculo <= 0){
                    throw new IllegalArgumentException("[ERRO] Quantidade de veiculos invalida!");
                }
                Moto moto = new Moto(qtdVeiculo, marcaVeiculo, modeloVeiculo, anoVeiculo, precoVeiculo, corVeiculo, kmVeiculo);
                ControleEstoque.cadastrarVeiculo(moto);
            }
            
            limparTela();
            System.out.println("- - Veiculo cadastrado com sucesso! - -");
            esperar(2500);

        }

        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        catch(Exception e){
            System.out.println("Erro: Entrada invalida!");
        }

        finally{
            esperar(2500);
        }
    }

    public static void menuConsultaVeiculo(){
        try{
            System.out.print("[1]Carro\n[2]Moto\nDigite o tipo de veículo: ");
            int userInput = sc.nextInt();
            sc.nextLine();
            if(userInput <= 0 || userInput > 2){
                throw new IllegalArgumentException("[ERRO] Indice invalido!");
            }
            if(userInput == 1){
                ControleEstoque.consultarEstoque("data/carros.txt", "data/m_carros.txt");
            }
            else{
                ControleEstoque.consultarEstoque("data/motos.txt", "data/m_motos.txt");
            }
            System.out.println("\n\nPressione [ENTER] para continuar.");
            sc.nextLine();

        }

        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        catch(Exception e){
            e.printStackTrace();
        }

    
    }

    public static void menuRemoveVeiculo(){
        try{
            limparTela();
            System.out.print("[1]Carro\n[2]Moto\n>>");
            int userInput = sc.nextInt();
            sc.nextLine();
            if(userInput <= 0 || userInput > 2){
                throw new IllegalArgumentException("[ERRO] Indice invalido!");
            }
            String nomeArquivo = userInput == 1 ? "data/carros.txt" : "data/motos.txt";
            String arquivoModelo = userInput == 1 ? "data/m_carros.txt" : "data/m_motos.txt";

            ControleEstoque.consultarEstoque(nomeArquivo, arquivoModelo);
            System.out.print("\nDigite o indice: ");
            int userIndex = sc.nextInt();
            sc.nextLine();

            limparTela();
            ControleEstoque.removerVeiculo(userIndex, nomeArquivo, arquivoModelo);
            
            esperar(2500);
            
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        catch(Exception e){
            e.printStackTrace();
        }

        finally{
            esperar(2500);
        }
    }

    public static void menuCadastroUsuario(){
        try{
            System.out.println("Insira o tipo de usuário:\n [1] Gerente\n [2] Estoquista\n [3] Vendedor\n>> ");
            int userInput = sc.nextInt();
            sc.nextLine();
            System.out.print("\nDigite o login do usuário: ");
            String login = sc.nextLine();
            login = login.toLowerCase();
            int senha = 0; 
            if(ControleUsuarios.validarLogin(login)){
                System.out.print("\nDigite a senha do usuário: ");
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

            limparTela();
            System.out.println("- - Usuario cadastrado com sucesso! - -");
            esperar(2500);


        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void menuRemoverUsuario(){
        System.out.print("Digite o login do usuário que você deseja remover: \n>> ");
        
        try{
           String userInput = sc.nextLine();
       
           limparTela();
           ControleUsuarios.removerUsuario(userInput);
           esperar(2500);
        }
        catch(NoSuchElementException e){
            e.printStackTrace();
        }
    }

    public static void menuGerente(String login){
        System.out.println("Pressione [ENTER] para entrar no sistema.");
        sc.nextLine();

        boolean isRunning = true;
        while(isRunning){
        limparTela();

        printMenu(1);
        System.out.print("Escolha uma operação:\n [1] Operar usuários.\n [2] Operar estoque.\n [3] Fazer venda.\n [4] Sair\n>> ");
        int userOp = sc.nextInt();
        sc.nextLine();
        limparTela();
        switch (userOp) {
            case 1:
                System.out.print("O que deseja fazer:\n [1] Cadastrar novo usuário.\n [2] Remover usuário.\n [3] Exibir usuários.\n>> ");
                int input1 = sc.nextInt();
                sc.nextLine();
                limparTela();
                switch (input1) {
                    case 1:
                        menuCadastroUsuario();
                        break;
                    case 2:
                        menuRemoverUsuario();
                        break;
                    case 3:
                        ControleUsuarios.exibirUsuarios();
                        System.out.println("Pressione [ENTER] para continuar.");
                        sc.nextLine();
                        break;
                        
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
                break;
            case 2:
                System.out.print("O que deseja fazer:\n [1] Cadastrar novo veiculo.\n [2] Remover veíuculo.\n [3] Consultar veículos.\n [4] Acessar registros\n>> ");
                int input2 = sc.nextInt();
                sc.nextLine();
                switch (input2) {
                    case 1:
                        menuCadastroVeiculo();
                        break;
                    case 2:
                        menuRemoveVeiculo();
                        break;
                    case 3:
                        menuConsultaVeiculo();
                        break;
                    case 4:
                        ControleEstoque.consultarLog();
                        System.out.println("\n(!) Pressione [ENTER] para continuar\n");
                        sc.nextLine();
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
                break;
            case 3:
                System.out.print("O que deseja fazer:\n [1] Vender veículo\n [2] Cadastrar cliente.\n [3] Alterar dados do cliente.\n [4] Remover cliente. \n [5] Exibir clientes.\n [6] sair.\n>>");
                int input3 = sc.nextInt();
                sc.nextLine();
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
                        menuExibirClientes();
                        break;
                    case 6:
                        isRunning = false;
                        System.out.println("Programa encerrado.");
                        break;
                    default: 
                        System.out.println("Opção inválida.");
                        break;
                }
                break;

                case 4:
                    isRunning = false;
                    System.out.println("\nPrograma encerrado.");
                    break;
                default:
                System.out.println("Opção inválida.");
                break;
        }

    }
    }

    public static void menuEstoquista(String login){
        System.out.println("Pressione [ENTER] para entrar no sistema.");
        sc.nextLine();
        boolean isRunning = true;
        while(isRunning){
            limparTela();
            printMenu(1);
            System.out.print("O que deseja fazer:\n [1] Cadastrar novo veiculo.\n [2] Remover veíuculo.\n [3] Consultar veículos.\n [4] Acessar registros\n [5] Sair\n>> ");
            int userOp = sc.nextInt();
            sc.nextLine();
            limparTela();
            switch (userOp) {
                case 1:
                    menuCadastroVeiculo();
                    break;
                case 2:
                    menuRemoveVeiculo();
                    break;
                case 3:
                    menuConsultaVeiculo();
                    break;
                case 4:
                    ControleEstoque.consultarLog();
                    System.out.println("\n(!) Pressione [ENTER] para continuar\n");
                    sc.nextLine();
                case 5:
                    isRunning = false;
                    System.out.println("Programa encerrado.");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    public static void menuVendedor(String login){
        System.out.println("Pressione [ENTER] para entrar no sistema.");
        sc.nextLine();
        boolean isRunning = true;
        while(isRunning){
            limparTela();
            printMenu(1);
            System.out.print("O que deseja fazer:\n [1] Vender veículo\n [2] Cadastrar cliente.\n [3] Alterar dados do cliente.\n [4] Remover cliente. \n [5] Exibir clientes.\n[6]Sair\n>> ");
            int userOp = sc.nextInt();
            sc.nextLine();
            
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
                    menuExibirClientes();
                    break;
                case 6:
                    isRunning = false;
                    System.out.println("\nPrograma encerrado");
                    break;
                default: 
                    System.out.println("Opção inválida.");
                    break;
            }
        }
        
    }

    public static void menuVendas(){
        try{
            ControleClientes.exibirClientes();
            int totalClientes = ControleClientes.contarLinhasArquivos("data/clientes.txt");

            System.out.print("Indice do cliente que fara a compra\n>>");
            int id = sc.nextInt();
            sc.nextLine();
            if(id <= 0 || id > totalClientes){
                throw new IllegalArgumentException("[ERRO] Indice invalido!");

            }
            limparTela();

            System.out.print("[1]Carro\n[2]Moto\n>>");
            int userInput = sc.nextInt();
            sc.nextLine();
            if(userInput <= 0 || userInput > 2){
                throw new IllegalArgumentException("[ERRO] Indice invalido!");

            }
            limparTela();

            if(userInput == 1){
                ControleVendas.exibirVeiculo("data/carros.txt");
                int totalCarros = ControleClientes.contarLinhasArquivos("data/carros.txt");

                System.out.print("Digite o indice do carro para a venda\n>>");
                int indice = sc.nextInt();
                sc.nextLine();
                if(indice <= 0 || indice > totalCarros)
                {
                    throw new IllegalArgumentException("[ERRO] Indice invalido!");
                }

                ControleVendas.relatorioDeVendas(id, indice, "data/carros.txt", "data/m_carros.txt");

                ControleEstoque.removerVeiculo(indice, "data/carros.txt", "data/m_carros.txt");

                limparTela();
                System.out.println("- - Carro vendido com sucesso! - -");
            }
            else{
                ControleVendas.exibirVeiculo("data/motos.txt");
                int totalMotos = ControleClientes.contarLinhasArquivos("data/carros.txt");

                System.out.print("Digite o indice da moto para a venda\n>>");
                int indice = sc.nextInt();
                sc.nextLine();
                if(indice <= 0 || indice > totalMotos)
                {
                    throw new IllegalArgumentException("[ERRO] Indice invalido!");
                }

                ControleVendas.relatorioDeVendas(id, indice, "data/motos.txt", "data/m_motos.txt");

                ControleEstoque.removerVeiculo(indice, "data/motos.txt", "data/m_motos.txt");

                limparTela();
                System.out.println("- - Moto vendida com sucesso! - - ");
            }
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            esperar(2500);
        }


    }

    public static void menuCadastrarCliente(){
        try{
            System.out.println("NOME: ");
            String clienteNome = sc.nextLine();

            System.out.println("CPF: ");
            long clienteCpf = sc.nextLong();
            sc.nextLine();
            if(clienteCpf <= 0){
                throw new IllegalArgumentException("[ERRO] CPF invalido!!");
            }

            System.out.println("ENDERECO: ");
            String clienteEndereco = sc.nextLine();

            System.out.println("CONTATO: ");
            long clienteContato = sc.nextLong();
            sc.nextLine();
            if(clienteContato <= 0){
                throw new IllegalArgumentException("[ERRO] contato invalido!!");
            }

            Cliente cliente = new Cliente(clienteNome, clienteCpf, clienteEndereco, clienteContato);
            ControleClientes.cadastrarClientes(cliente);

            limparTela();
            System.out.println("Cliente cadastrado com sucesso!");
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            esperar(2500);
        }
    }

    public static void menuAlterarDadosCliente(){
        try{
            ControleClientes.exibirClientes();
            int totalClientes = ControleClientes.contarLinhasArquivos("data/clientes.txt");

            System.out.print("Digite o indice do cliente para trocar suas informacoes\n>>");
            int index = sc.nextInt();
            sc.nextLine();
            esperar(1500);
            limparTela();
            if(index <= 0 || index > totalClientes){
                throw new IllegalArgumentException("[ERRO] Indice invalido!");

            }

            System.out.println("\t*NAO E RECOMENDADO ALTERAR NOME OU CPF*\n");

            System.out.print("Digite o nome do cliente\n>>");
            String nome = sc.nextLine();

            System.out.print("Digite o cpf do cliente\n>>");
            long cpf = sc.nextLong();
            sc.nextLine();
            if(cpf <= 0){
                throw new IllegalArgumentException("[ERRO] CPF invalido!!");
            }

            System.out.print("Digite o novo endereco\n>>");
            String end = sc.nextLine();

            System.out.print("Digite o novo numero de telefone\n>>");
            long tel = sc.nextLong();
            sc.nextLine();
            if(tel <= 0){
                throw new IllegalArgumentException("[ERRO] contato invalido!!");
            }

            ControleClientes.alterarDadosCliente(index, nome, cpf, end, tel);

            limparTela();
            System.out.println("Informacoes atualizadas com sucesso!");
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            esperar(2500);
        }
    }

    public static void menuExibirClientes(){
        try {
            ControleClientes.exibirClientes();
            System.out.println("\n\nPressione [ENTER] para continuar.");
            sc.nextLine();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void menuRemoverCliente(){
        try{
            ControleClientes.exibirClientes();
            int totalClientes = ControleClientes.contarLinhasArquivos("data/clientes.txt");

            System.out.print("Digite o indice do cliente a remover\n>>");
            int index = sc.nextInt();
            sc.nextLine();
            if(index <= 0 || index > totalClientes){
                throw new IllegalArgumentException("[ERRO] Indice invalido!");

            }

            ControleClientes.removerCliente(index);

            limparTela();
            System.out.println("- - Cliente removido com sucesso! - -");
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            esperar(2500);
        }
    }

    public static void limparTela(){
        for(int i = 0; i < 50; i++){
            System.out.println();
        }
    }

    public static void esperar(int milissegundos) {
        try {
            Thread.sleep(milissegundos);
        } catch (InterruptedException e) {
            e.getMessage();
        }
    }

    public static void printMenu(int indice){
        switch(indice){
            case 1:
                System.out.print(" ----------------------\n");
                System.out.print("|  ERP CONCESSIONARIA  |\n");
                System.out.print(" ----------------------\n");
                break;

            case 2:
                System.out.print(" ----------------------\n");
                System.out.print("|        LOGIN         |\n");
                System.out.print(" ----------------------\n");

        }
        
    }

}
=======
package view;
import modelos.Veiculo;
import modelos.Carro;
import modelos.Moto;
import modelos.Cliente;
import java.util.Scanner;

import controller.ControleEstoque;

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
}
>>>>>>> parent of 36eb50d (delete Main)
