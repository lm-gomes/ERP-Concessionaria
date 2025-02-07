package controller;


import java.io.*;
import java.util.*;
import modelos.Carro;
import modelos.Moto;

public class ControleEstoque{

     // Método para ordenar os modelos de veículos em um arquivo.
    public static void ordernarModelo(String nomeArquivo){ 
        List<String> listaDeModelos = new ArrayList<>(); // Lista para armazenar os modelos.
        try(BufferedReader br = new BufferedReader(new FileReader(nomeArquivo));){ 
            // Lê todas as linhas de arquivo e adiciona a lista.
            while(br.ready()){
                listaDeModelos.add(br.readLine());
            }
            // Ordena a lista de modelos em ordem alfabética.
            Collections.sort(listaDeModelos);
            
            // Exibe os modelos ordenados no terminal
            for(String modelo : listaDeModelos){
                System.out.println(modelo);
            }
            
        }
         // Trata o erro se o arquivo não for encontrado.
        catch(FileNotFoundException e){
            System.out.println(e.getMessage()); 
        }
        
        // Trata os outros erros genéricos
        catch(Exception e){ 
            System.out.println("Deu ruim paizão"); // Imprime uma mensagem de erro na tela.
        }
        
        //Reescreve o arquivo com os modelos ordenados.
        try(BufferedWriter bwm = new BufferedWriter(new FileWriter(nomeArquivo))){
            for(String modelo : listaDeModelos){
                bwm.write(modelo); // Escreve cada modelo no arquivo.
                bwm.newLine(); // Adiciona uma nova linha após cada modelo.
            }
        }
        // Trata erros ao escrever no arquivo.
        catch(Exception e){
            e.printStackTrace();

        }
    }
     // Método para verificar se há estoque suficiente de cada modelo.
    public static void verificarEstoque(String nomeArquivo){
        try(BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))){

            String s = br.readLine();  // Lê a primeira linha

            while (br.ready()) {
                int cont = 1;  // Inicia o contador com 1 (pois já lemos uma ocorrência)
            
                // Enquanto houver mais linhas e a próxima linha for igual à atual
                String proxLinha = "";
                while (br.ready() && (proxLinha = br.readLine()).equals(s)) {
                    cont++;
                }
            
                // Se a string apareceu menos de 5 vezes, exibe alerta
                if (cont < 5) {
                    System.out.println("Alerta! " + s);
                }
            
                // Aqui, proxLinha é uma nova string, então precisamos processá-la na próxima iteração
                s = proxLinha;
            }
            
        }
        // Trata erros de leitura do arquivo.
        catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
    
    // Método para cadastrar um carro no estoque.
    public static void cadastrarVeiculo(Carro car){
        
        // Abre o arquivo de carros e permite adicionar novos carros ao final da lista sem apagar os outros antigos.
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/carros.txt", true)); BufferedWriter bwm = new BufferedWriter(new FileWriter("data/m_carros.txt", true))) {
           
            // Adiciona o carro ao estoque de acordo com a quantidade informada.
            for(int i = 0; i < car.quantidade; i++){
                bw.write("Marca: " + car.marca + " Modelo: " + car.modelo + " Ano: " + car.ano + "  Preco: " + car.preco + " Cor:" + car.cor + " KM: " + car.km);
                bw.newLine();// Adiciona uma nova linha após cada carro.
                bwm.write(car.modelo);// Adiciona o modelo ao arquivo de modelo
                bwm.newLine(); // Adiciona uma nova linha após cada modelo.
                
            }

        } catch (IOException e) { //Trata erros ao escrever nos arquivos.
            e.printStackTrace();
        }

        // Ordens os modelos de carros após o cadastro.
        ordernarModelo("data/m_carros.txt");
        
    }
    
    // Metódo para cadastrar uma moto no estoque.
    public static void cadastrarVeiculo(Moto moto){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/motos.txt", true)); BufferedWriter bwm = new BufferedWriter(new FileWriter("data/m_motos.txt", true))) {
         // Abre o arquivo de modelos de motos em modo de append.

            for(int i = 0; i < moto.quantidade; i++){ // Adiciona a moto ao estoque de acordo com a quantidade informada.
                bw.write("Marca: " + moto.marca + " Modelo: " + moto.modelo + " Ano: " + moto.ano + " Preco: " + moto.preco + " Cor:" + moto.cor + " KM: " + moto.km);
                bw.newLine(); // Adiciona uma nova linha após cada moto.
                bwm.write(moto.modelo); // Adiciona o modelo ao arquivo de modelos
                bwm.newLine(); // Adiciona uma nova linha após cada modelo.
            }

        } catch (IOException e) {
            e.printStackTrace(); // Trata erros ao escrever nos arquivos
        }
    }


    // Método para consultar o estoque de veículos.
    public static void consultarEstoque(String nomeArquivo){
        verificarEstoque("data/m_carros.txt"); // Verifica se há alertas de estoque baixo.

        try(BufferedReader br = new BufferedReader(new FileReader(nomeArquivo));){
            int i = 1;
            // Exibe cada veículo de estoque com número de índice.
            while(br.ready()){
                System.out.println("(" + i + ")" + br.readLine());
                i++;
            }
        }
        // Trata erro se o arquivo não for encontrado.
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        // Trata erros de leitura do arquivo
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    //Método para remover um veículo no estoque.
    public static void removerVeiculo(int index){
        
    }

}