package controller;

import java.io.*;

public class ControleVendas{
    public static void exibirVeiculo(String arquivo){
        try(BufferedReader br = new BufferedReader(new FileReader(arquivo))){
            int i=1;
            while(br.ready()){
                System.out.println("(" + i + ")" + br.readLine());
                i++;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void relatorioDeVendas(int idCliente, int indiceVeiculo, String nomeArquivo){
        String info = "";
        try(BufferedReader br = new BufferedReader(new FileReader("data/db_clientes.txt"))){
            String linha;
            for(int i=1;(linha = br.readLine())!=null;i++){
                if(i == idCliente){
                    info = " " + linha;
                    break;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        try(BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))){
            String linha;
            for(int i=1;(linha = br.readLine())!=null;i++){
                if(i == indiceVeiculo){
                    info += " | CARRO: " + linha;
                    break;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("data/vendas.txt", true))){
            bw.write(info);
            bw.newLine();

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
