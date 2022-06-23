/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Arquivo;

import Model.Usuario;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author Luan
 */
public class ControleArquivoUsuario extends ControleArquivo{
    private BufferedReader leitor = null;
    private BufferedWriter escritor = null;
    protected Usuario user;
    ArrayList<String> listUsers ;
    String textolinha ;
    
    public void setTextolinha(String textolinha){
        this.textolinha = textolinha;
    }
    public String getTextolinha(){
        return textolinha;
    }
    
    
    public boolean gravarUserArquivo(Usuario user) throws IOException{
      
       try{
                escritor = new BufferedWriter(new FileWriter(getArquivo(),true));
                escritor.write("Nome: "+user.getNome()+"\n");
                escritor.write("Senha: "+user.getSenha()+"\n");
                escritor.flush();
                escritor.close();
                return true;
                
            }catch(IOException erro){
                return false;
            }
      
      
        
        
    }
    public boolean buscarUsuario(Usuario user) throws FileNotFoundException{
        String aux , aux2;
        
        try{
            leitor = new BufferedReader(new FileReader(getArquivo()));
            
            while(leitor.ready()){
                aux = leitor.readLine();
                StringTokenizer linha = new StringTokenizer(aux , ":");
                while(linha.hasMoreTokens()){
                                   
                    if(aux.contains(user.getNome())){
                        
                        aux2 = aux;
                        aux = leitor.readLine();
                        if(aux.contains(user.getSenha())){
                            return true;
                        }
                    }
                    aux = leitor.readLine();
                }
            }
            leitor.close();
        }catch(IOException erro){
            return false;
        }
        return false;
    }
    
        //"ID","TIPO ","NOME", "EMAIL", "TELEFONE", "DOCUMENTO1", "DOCUMENTO2"
    public boolean excluirUsuario(String nome, String senha) throws FileNotFoundException, IOException{
        listUsers = new ArrayList<>();
        
            leitor = new BufferedReader(new FileReader(getArquivo()));
            String linha = null;
            String linha2 = null;
            String aux ;
            aux = leitor.readLine();
            while(leitor.ready()){
                
                
                linha = aux;
                
                if(!aux.contains(nome)){
                    listUsers.add(aux);
                    aux = leitor.readLine();
                    System.out.println("nome "+ aux);
                    if(!aux.contains(senha)){
                        linha2 = aux;
                        System.out.println("senha "+ aux);
                        listUsers.add(aux);
                    }
                }
                
                aux = leitor.readLine();
                
                
            }
            return true;
        
    }
    public boolean novoArquivo(){
         try{
                escritor = new BufferedWriter(new FileWriter(getArquivo(),false));
                
                
                for(int i = 0 ; i < listUsers.size(); i++){
                    setTextolinha(listUsers.get(i));
                    escritor.write(getTextolinha());
                    escritor.write("\n");
                    
                }
                    escritor.flush();
                    escritor.close();
                
               
                
                return true;
                
            }catch(IOException erro){
                return false;
            }
    }
    
    
    
    public ArrayList listaUsuarios() throws FileNotFoundException, IOException{
        
        
        ArrayList listUsers = new ArrayList();
        String nome=null, senha= null, aux = null;
        String div[];
        
            
         //  System.out.println("teste dentro listausers " +getArquivo());
            
            leitor = new BufferedReader(new FileReader(getArquivo()));
            aux = leitor.readLine();
            while(leitor.ready()){
                
                if(aux.contains("Nome")){
                    div = aux.split(":");
                    nome = div[1];
                  //  System.out.println("nome "+nome);
                    aux = leitor.readLine();
                    if(aux.contains("Senha")){
                        div = aux.split(":");
                        senha = div[1];
                     //   System.out.println("senha "+senha);
                       // Usuario user = new Usuario(nome, senha);
                       
                        listUsers.add( new Object[]{nome, senha });
                        
                    }
                    
                }
                
                aux = leitor.readLine();
                
            }
            
            return listUsers;
            
    }
    
    
    @Override
    public boolean ler() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean escrever(boolean append) {
        if(arquivo != null){
            try{
                escritor = new BufferedWriter(new FileWriter(arquivo, append));
                escritor.write(getTextolinha());
                escritor.close();
                return true;
            }catch(IOException erro){
                System.err.println(erro.getMessage()+"Erro ao ler arquivo");
                return false;
            }
        }else return false;
    }
    
}
