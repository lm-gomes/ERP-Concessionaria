package controller;


import java.io.*;
import java.util.*;
import modelos.Carro;
import modelos.Moto;

public class ControleEstoque{

    public static void ordernarModelo(String nomeArquivo){
        List<String> listaDeModelos = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(nomeArquivo));){
            while(br.ready()){
                listaDeModelos.add(br.readLine());
            }
            Collections.sort(listaDeModelos);
            for(String modelo : listaDeModelos){
                System.out.println(modelo);
            }
            
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        catch(Exception e){
            System.out.println("Deu ruim paizão");
        }

        try(BufferedWriter bwm = new BufferedWriter(new FileWriter(nomeArquivo))){
            for(String modelo : listaDeModelos){
                bwm.write(modelo);
                bwm.newLine();
            }
        }
        catch(Exception e){
            e.printStackTrace();

        }
    }

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
        catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    public static void cadastrarVeiculo(Carro car){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/carros.txt", true)); BufferedWriter bwm = new BufferedWriter(new FileWriter("data/m_carros.txt", true))) {

            for(int i = 0; i < car.quantidade; i++){
                bw.write("Marca: " + car.marca + " Modelo: " + car.modelo + " Ano: " + car.ano + "  Preco: " + car.preco + " Cor:" + car.cor + " KM: " + car.km);
                bw.newLine();
                bwm.write(car.modelo);
                bwm.newLine();
                
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        ordernarModelo("data/m_carros.txt");
        
    }

    public static void cadastrarVeiculo(Moto moto){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/motos.txt", true)); BufferedWriter bwm = new BufferedWriter(new FileWriter("data/m_motos.txt", true))) {
            for(int i = 0; i < moto.quantidade; i++){
                bw.write("Marca: " + moto.marca + " Modelo: " + moto.modelo + " Ano: " + moto.ano + " Preco: " + moto.preco + " Cor:" + moto.cor + " KM: " + moto.km);
                bw.newLine();
                bwm.write(moto.modelo);
                bwm.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void consultarEstoque(String nomeArquivo){
        verificarEstoque("data/m_carros.txt");

        try(BufferedReader br = new BufferedReader(new FileReader(nomeArquivo));){
            int i = 1;
            while(br.ready()){
                System.out.println("(" + i + ")" + br.readLine());
                i++;
            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void removerVeiculo(int index){
        
    }

}