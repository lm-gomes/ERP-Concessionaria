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

            //nao fui eu q fiz isso
            


            /*for(int i = 0; br.ready(); i++){
                String s = br.readLine();
                int cont = 0;
                for(j = i; br.ready() && br.readLine().equals(s); j++){
                    cont++;
                }
                    if(cont < 5){
                        System.out.println("Alerta!" + s);
                    }
                    i = j;
            }*/
            while(br.ready()){
                String s = br.readLine();
                int cont = 1;
                while(br.ready() && br.readLine().equals(s)){
                    cont++;
                }
                br.reset();
                if(cont<5){
                    System.out.println("Alerta!" + s);
                }
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