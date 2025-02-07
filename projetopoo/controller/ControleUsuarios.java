package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import modelos.Usuario;


public class ControleUsuarios {
    public static void cadastrarUsuario(Usuario user){
        if(validarLogin(user.login)){
            user.login = user.login + " ";
            try(BufferedWriter bw = new BufferedWriter(new FileWriter("data/users.txt" , true))){
                user.login = user.login.toLowerCase();
                bw.write("Tipo: " + user.tipo +" Login: " + user.login + " Senha: " + user.senha);
                bw.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }  
            System.out.println("Cadastro sucedido."); 
        } else{
            System.out.println("Login inválido. Tente novamente.");
        }
        
    }

    public static void exibirUsuarios(){
        try(BufferedReader br = new BufferedReader(new FileReader("data/users.txt"))){
            while (br.ready()) {
                System.out.println(br.readLine());
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void removerUsuario(String nomeUser){
        List<String> listaDeUsuarios = new ArrayList<>();
        List<String> listaFinal = new ArrayList<>();
        nomeUser = nomeUser + " ";
        try(BufferedReader br = new BufferedReader(new FileReader("data/users.txt"))){
            while (br.ready()) {
                listaDeUsuarios.add(br.readLine());
            }
        } catch(Exception e){
            e.printStackTrace();
        }


        for(String user : listaDeUsuarios){
            if(!(user.contains(nomeUser))){
                listaFinal.add(user);
            }
            
        }

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("data/users.txt"))){
            for (String user : listaFinal) {
                bw.write(user);
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void autenticarUsuario(String login , int senha){
        try(BufferedReader br = new BufferedReader(new FileReader("data/users.txt"))){
            login = login.toLowerCase();
            List<String> listaDeUsuarios = new ArrayList<>();
            String s = Integer.toString(senha);

            while(br.ready()){
                listaDeUsuarios.add(br.readLine());
            }
            boolean foundLine = false;
            for (String user : listaDeUsuarios) {
                if(user.contains(login) && user.contains(s)){
                    System.out.println("Usuário autorizado.");
                    foundLine = true;
                    break;
                }else{
                    foundLine = false;
                }
            }

            if(!foundLine){
                System.out.println("Usuário ou senha incorretos.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean validarLogin(String login){
        boolean foundSpace = false;
        login = login.toLowerCase();
        try(BufferedReader br = new BufferedReader((new FileReader("data/users.txt")))){
            List <String> listaUsuarios = new ArrayList<>();
            while (br.ready()) {
                listaUsuarios.add(br.readLine());
            }
            for (String user : listaUsuarios) {
                if(login.contains(" ") || login.contains(user)){
                    foundSpace = true;;
                    break;
                    //System.out.println("Login inválido.");
                }  
            }
            

        } catch(IOException e){
            e.printStackTrace();
        }
        return foundSpace;
    }

}
