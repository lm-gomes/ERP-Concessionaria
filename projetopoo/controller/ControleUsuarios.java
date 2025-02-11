package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import modelos.Usuario;
import view.Screen;


public class ControleUsuarios {
    public static void cadastrarUsuario(Usuario user){
        if(validarLogin(user.login)){
            try(BufferedWriter bw = new BufferedWriter(new FileWriter("data/users.txt" , true)); BufferedWriter bw2 = new BufferedWriter(new FileWriter("data/logins.txt" , true))){
                user.login = user.login.toLowerCase();
                bw2.write(user.login);
                bw2.newLine();
                user.login = user.login + " ";
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

        List<String> listaDeLogins = new ArrayList<>();
        List<String> listaFinalLogins = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader("data/logins.txt"))){
            while (br.ready()) {
                listaDeLogins.add(br.readLine());
            }
        } catch(Exception e){
            e.printStackTrace();
        }


        for(String user : listaDeLogins){
            if(!(user.contains(nomeUser))){
                listaFinalLogins.add(user);
            }
            
        }

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("data/logins.txt"))){
            for (String user : listaFinalLogins) {
                bw.write(user);
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean autenticarUsuario(String login , int senha){
        boolean foundLine = false;
        try(BufferedReader br = new BufferedReader(new FileReader("data/users.txt"))){
            login = login.toLowerCase();
            List<String> listaDeUsuarios = new ArrayList<>();
            String s = Integer.toString(senha);

            while(br.ready()){
                listaDeUsuarios.add(br.readLine());
            }
            //boolean foundLine = false;
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
        return foundLine;
    }

    public static boolean validarLogin(String login){
        login = login.toLowerCase();
        try(BufferedReader br = new BufferedReader((new FileReader("data/logins.txt")))){
            List <String> listaUsuarios = new ArrayList<>();
            while (br.ready()) {
                listaUsuarios.add(br.readLine());
            }
            for (String user : listaUsuarios) {
                if(login.contains(" ") || login.equals(user)){
                    return false;
                } 
            }
            

        } catch(IOException e){
            e.printStackTrace();
        }
        return true;
    }

    public static void tipoUser(String login){
        try(BufferedReader br = new BufferedReader(new FileReader("data/users.txt"))){
            List <String> listaUsuarios = new ArrayList<>();
            

            while (br.ready()) {
                listaUsuarios.add(br.readLine());
            }
            
            

            for (String user : listaUsuarios) {
                if(user.contains(login) && user.contains("G")){
                    Screen.menuGerente(login);
                    
                    break;
                } else if(user.contains(login) && user.contains("E")){
                    Screen.menuEstoquista(login);
                    
                    break;
                } else if(user.contains(login) && user.contains("V")){
                    Screen.menuVendedor(login);
                
                    break;
                } 
            }

            

        } catch (IOException e) {
            // TODO: handle exception
        }
    }

}
