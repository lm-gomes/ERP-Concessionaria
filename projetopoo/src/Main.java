package src;

//import modelos.Carro;
//import modelos.Moto;
import modelos.Usuario;
import view.Screen;
import java.util.Scanner;
import controller.ControleEstoque;
import controller.ControleUsuarios;

public class Main{
    public static void main(String[] args){
        //Screen.menuPrincipal();
        /*Usuario u = new Usuario("juju", 1234);
        Usuario u2 = new Usuario("aninha", 4321);
        Usuario u3 = new Usuario("clara", 2468);
        ControleUsuarios.cadastrarUsuario(u);
        ControleUsuarios.cadastrarUsuario(u2);
        ControleUsuarios.cadastrarUsuario(u3);*/
        //ControleUsuarios.autenticarUsuario("cururua", 4321);
        //ControleUsuarios.exibirUsuarios();
        //ControleUsuarios.removerUsuario("juju");
        //ControleUsuarios.exibirUsuarios();
        //Screen.menuCadastroUsuario();
        //Screen.menuRemoverUsuario();
        Screen.menuGerente("julia");
        
    }
}