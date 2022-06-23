/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Arquivo.ControleArquivoUsuario;
import Model.Usuario;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 *
 * @author Luan
 */
public class ControleUsuario extends ControleArquivoUsuario{
        
    ArrayList listUser = new ArrayList<>();
   // protected Usuario user;
    
    public ControleUsuario(){
        String dir = System.getProperty("user.dir");
        String nome = dir+"/Dados/Usuarios.txt";
       // setArquivo(nome);
        File arq = new File(nome);
        System.out.println("nome "+ nome);
        System.out.println("get arquivo "+ getArquivo());
        
        if(!arq.exists()){
            System.out.println("teste dentro do if" );
            setArquivo(nome);
            criarUsuario("admin", "admin");
            gravarUsuario();
        }else{
            setArquivo(nome);
        }
    }
    
    public void criarUsuario(String nome, String senha) {
        user = new Usuario(nome, senha);
    }
    
    public boolean gravarUsuario(){
        try{
            
            if(gravarUserArquivo(user) == true){
                JOptionPane.showMessageDialog(null, "Usuario gravado no arquivo com sucesso");
            }
            return true;
        }catch(IOException erro){
            
            JOptionPane.showMessageDialog(null, "erro ao gravar usuario no arquivo");
            return false;
        }
    }
    
    public boolean verificarUsuario(String nome, String senha) throws FileNotFoundException{
        user = new Usuario(nome, senha);
        if(buscarUsuario(user) == true){
            return true;
        }else return false;
    }
    
    
    
    
   /* public void listarUsuarios(ArrayList <Usuario> listUsers){
        
    } */
    /*
    public ArrayList<Usuario> listaUsuarios(){
        ArrayList<Usuario> listUsers = new ArrayList<>();
        try{
            Usuario c =null;
            ObjectInputStream leitor = new ObjectInputStream(new FileInputStream(res));
            while(p != null){
                p = (Produto) leitor.readObject();
                if(p != null){
                    listaprodutos.add(p);
                }
            }
            leitor.close();
        }catch (IOException erro){
            System.err.println(erro.getMessage());
        } catch (ClassNotFoundException erro) {
            System.err.println(erro.getMessage());
        }
        return listaprodutos;
    }
    */
}
