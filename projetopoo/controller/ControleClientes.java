package controller;
import modelos.Cliente;

import java.util.Scanner;
import java.io.*;

public class ControleClientes{
    public static void cadastrarClientes(Cliente c){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("data/clientes.txt", true))){
            bw.write("NOME:" + c.getNome() + ", ENDERECO: " + c.getEndereco() + ", CONTATO:" + c.getContato()); 
            bw.newLine();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}