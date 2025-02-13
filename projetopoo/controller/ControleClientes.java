package controller;
import modelos.Cliente;

import java.util.*;
import java.io.*;

public class ControleClientes{
    public static void cadastrarClientes(Cliente c){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("data/clientes.txt", true))){
            bw.write("CLIENTE: " + c.getNome() + " CPF: " + c.getCpf() + " ENDERECO: " + c.getEndereco() + " CONTATO: " + c.getContato());
            bw.newLine();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("data/db_clientes.txt", true))){
            bw.write("CLIENTE: " + c.getNome() + " CPF: " + c.getCpf()); 
            bw.newLine();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void exibirClientes(){
        try(BufferedReader br = new BufferedReader(new FileReader("data/clientes.txt"))){
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

    public static void alterarDadosCliente(int index, String nome, int cpf, String end, int num){
        String dados = "CLIENTE: " + nome + " CPF: " + cpf + " ENDERECO: " + end + " CONTATO: " + num;
        String db_dados = "CLIENTE: " + nome + " CPF: " + cpf;

        List<String> linhas = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader("data/clientes.txt"))){
            String linha;
            while((linha=br.readLine())!=null){
                linhas.add(linha);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("data/clientes.txt"))){
            int i=1;
            for(String linha : linhas){
                if(i != index){
                    bw.write(linha);
                    bw.newLine();
                }
                else{
                    bw.write(dados);
                    bw.newLine();
                }
                i++;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        List<String> lin = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader("data/db_clientes.txt"))){
            String linha;
            while((linha=br.readLine())!=null){
                lin.add(linha);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("data/db_clientes.txt"))){
            int i=1;
            for(String linha : lin){
                if(i != index){
                    bw.write(linha);
                    bw.newLine();
                }
                else{
                    bw.write(db_dados);
                    bw.newLine();
                }
                i++;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }

    public static void removerCliente(int index){
        List<String> linhas = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader("data/clientes.txt"))){
            String linha;
            while((linha=br.readLine())!=null){
                linhas.add(linha);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("data/clientes.txt"))){
            int i=1;
            for(String linha : linhas){
                if(i != index){
                    bw.write(linha);
                    bw.newLine();
                }
                i++;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        List<String> lin = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader("data/db_clientes.txt"))){
        String linha;
        while((linha=br.readLine())!=null){
            lin.add(linha);
        }
    }
    catch(Exception e){
        e.printStackTrace();
    }

    try(BufferedWriter bw = new BufferedWriter(new FileWriter("data/db_clientes.txt"))){
        int i=1;
        for(String linha : lin){
            if(i != index){
                bw.write(linha);
                bw.newLine();
            }
            i++;
        }
    }
    catch(Exception e){
        e.printStackTrace();
    }
    }
    
}