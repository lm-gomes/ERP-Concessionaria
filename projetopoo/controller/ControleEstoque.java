package controller;


import java.io.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
            
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        catch(Exception e){
            System.out.println("Deu ruim felpinho desculpa");
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
        ordernarModelo(nomeArquivo);
        try(BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))){

            String s = br.readLine();
    
            while (br.ready()) {
                int cont = 1; 
                
                String proxLinha = "";
                while (br.ready() && (proxLinha = br.readLine()).equals(s)) {
                    cont++;
                }
    
                if (cont < 5) {
                    System.out.println(s + " em falta! (" + cont + " unidades)");
                }
                
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
                bw.write("Marca: " + car.marca + " Modelo: " + car.modelo.toLowerCase() + " Ano: " + car.ano + "  Preco: " + car.preco + " Cor:" + car.cor + " KM: " + car.km);
                bw.newLine();
                bwm.write(car.modelo);
                bwm.newLine();
            }
            logEstoque("[LOG-SISTEMA]Veiculo cadastrado: Carro - Modelo: " + car.modelo);
            

        } catch (IOException e){
            e.printStackTrace();
        }
        ordernarModelo("data/m_carros.txt");
        
    }

    public static void cadastrarVeiculo(Moto moto){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/motos.txt", true)); BufferedWriter bwm = new BufferedWriter(new FileWriter("data/m_motos.txt", true))) {
            for(int i = 0; i < moto.quantidade; i++){
                bw.write("Marca: " + moto.marca + " Modelo: " + moto.modelo.toLowerCase() + " Ano: " + moto.ano + " Preco: " + moto.preco + " Cor:" + moto.cor + " KM: " + moto.km);
                bw.newLine();
                bwm.write(moto.modelo);
                bwm.newLine();
            }
            logEstoque("[LOG-SISTEMA]Veiculo cadastrado: Moto - Modelo: " + moto.modelo);
            
            ordernarModelo("data/m_motos.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void consultarEstoque(String nomeArquivo, String modeloArquivo){
        System.out.println("-------------------[!]ALERTA[!]--------------------------");
        verificarEstoque(modeloArquivo);
        System.out.println("---------------------------------------------------------");

        try(BufferedReader br = new BufferedReader(new FileReader(nomeArquivo));){
            int i = 1;
            while(br.ready()){
                System.out.println("(" + i + ")" + br.readLine());
                i++;
            }
        }
        catch(FileNotFoundException e){
            System.out.println("caiu aqui!!!");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void removerVeiculo(int index, String nomeArquivo, String arquivoModelo){
        List<String> listaDeVeiculos = new ArrayList<>();
        List<String> listaDeModelos = new ArrayList<>();

        String logVeiculoRemovido = "";

        try(BufferedReader br = new BufferedReader(new FileReader(nomeArquivo)); BufferedReader brm = new BufferedReader(new FileReader(arquivoModelo))){    
            for(int i = 1; br.ready(); i++){
                String linha =  br.readLine();
                if(i != index){
                    listaDeVeiculos.add(linha);
                }
                else{
                    logVeiculoRemovido = linha;
                }
            }  

            boolean modeloEncontrado = false;
            String linha = "";
            String logLinha = "";
            while(brm.ready()){
                linha = brm.readLine();
                if(!modeloEncontrado){
                    if(!logVeiculoRemovido.contains(linha)){
                       listaDeModelos.add(linha);
                    }
                    else{
                        modeloEncontrado = true;
                        logLinha = linha;
                    }
                }
                else{
                    listaDeModelos.add(linha);
                }
            }
            if(!modeloEncontrado){
                System.out.println("Nao foi possivel encontrar um veiculo no indice especificado...");
            }
            else{
                System.out.println("- - Veiculo removido com sucesso! - -");
                logEstoque("[LOG-SISTEMA]Veiculo removido: Modelo - " + logLinha);
            }
            
        }
        catch(Exception e){
            e.printStackTrace();

        }

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo)); BufferedWriter bwm = new BufferedWriter(new FileWriter(arquivoModelo))){
            for(String veiculo: listaDeVeiculos){
                bw.write(veiculo);
                bw.newLine();
            }
            String logModelo;
            for(String modelo: listaDeModelos){
                bwm.write(modelo);
                bwm.newLine();
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void logEstoque(String mensagem){
        LocalDateTime data = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String logData = data.format(formato);
        
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("data/logEstoque.txt", true))){
                bw.write(mensagem + " " + logData);
                bw.newLine();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void consultarLog(){
        try(BufferedReader br = new BufferedReader(new FileReader("data/logEstoque.txt"));){
            System.out.println("---------------------------------------------");
            while(br.ready()){
                System.out.println(br.readLine());
            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}