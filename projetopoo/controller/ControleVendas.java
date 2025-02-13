package controller;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public static void relatorioDeVendas(int idCliente, int indiceVeiculo, String nomeArquivo, String arquivoModelo){
        LocalDateTime data = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String horaFormatada = data.format(formato);
        
        String padrao = "[LOG-SISTEMA] ";
        String info = "";
        try(BufferedReader br = new BufferedReader(new FileReader("data/db_clientes.txt"))){
            String linha;
            for(int i=1;(linha = br.readLine())!=null;i++){
                if(i == idCliente){
                    info = linha;
                    break;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        try(BufferedReader br = new BufferedReader(new FileReader(nomeArquivo)); BufferedReader brm =  new BufferedReader(new FileReader(arquivoModelo))){
            String veiculo = "";
            String linha;
            for(int i=1;(linha = br.readLine())!=null;i++){
                if(i == indiceVeiculo){
                    veiculo = linha;
                    break;
                }
            }
            while((linha = brm.readLine())!=null){
                if(veiculo.contains(linha)){
                    info += " | VEICULO: " + linha;
                    break;
                }
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("data/vendas.txt", true))){
            bw.write(padrao + info + " (" + horaFormatada + ")");
            bw.newLine();

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
