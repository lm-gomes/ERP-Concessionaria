package controller;

import java.io.*;

public class ControleVendas{
    public static void exibirVeiculo(String arquivo){
        try(BufferedReader br = new BufferedReader(new FileReader(arquivo))){
            while(br.ready()){
                System.out.println(br.readLine());
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
