/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Arquivo;

import Model.Produto;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luan
 */
public class ControleArquivoProduto extends ControleArquivo{
    
    private BufferedReader leitor = null;
    private BufferedWriter escritor = null;
    protected Produto produto;
    ArrayList<String> listProdutos ;
    ArrayList<Produto> produtos;
    ArrayList<Object> dados ;
    String textolinha ;
    int id ;
    public String getTextolinha() {
        return textolinha;
    }

    public void setTextolinha(String textolinha) {
        this.textolinha = textolinha;
    }
    public int contagemId() throws FileNotFoundException, IOException{
        String aux, numero;
        int valor , conta =0 ;
        String []div;
        leitor = new BufferedReader(new FileReader(getArquivo()));
        aux = leitor.readLine();
        while(leitor.ready()){
            if(aux.contains("ID")){
                div = aux.split(":");
                numero = div[1];
                System.out.println("numero em string "+div[1]);
                System.out.println("numero "+numero);
                //valor = Integer.valueOf(numero);
                valor = Integer.parseInt(numero);
                System.out.println("numero em valor "+valor );
                conta = valor +1;
            }
            aux = leitor.readLine();
        }
        return conta;
    }
    
    public boolean gravarProdutoArquivo(Produto produto) throws IOException{
       
       try{
                escritor = new BufferedWriter(new FileWriter(getArquivo(),true));
                escritor.write("ID:"+produto.getId()+"\n");
                escritor.write("Nome: "+produto.getNome()+"\n");
                escritor.write("Preço: "+produto.getPreco()+"\n");
                escritor.write("Descrição: "+produto.getDescricao()+"\n");
                escritor.flush();
                escritor.close();
                return true;
                
            }catch(IOException erro){
                return false;
            }
      
    }
    
     /*
    public ArrayList<Produto> newlistProd() throws FileNotFoundException, IOException{
        Produto p = null;
        produtos = new ArrayList<>();
        String aux, id, nome, preco, descricao;
        String [] div;
        leitor = new BufferedReader(new FileReader(getArquivo()));
        aux = leitor.readLine();
        while(leitor.ready()){
            
            if(aux.contains("ID")){
                div = aux.split(":");
                id = div[1];
                aux = leitor.readLine();
                if(aux.contains("Nome")){
                    div = aux.split(":");
                    nome = div[1];
                    aux = leitor.readLine();
                    
                    if(aux.contains("Preço")){
                        div = aux.split(":");
                        preco = div[1];
                        aux = leitor.readLine();
                        
                        if(aux.contains("Descrição")){
                            div = aux.split(":");
                            descricao = div[1];
                           // aux = leitor.readLine();
                            
                           // System.out.println("id "+ id + "\nnome" +nome+ "\npreco"+preco+ "\n descricao "+  descricao);
                            int ide = Integer.parseInt(id);
                            double dpreco = Double.parseDouble(preco);
                            p = new Produto(ide, nome, dpreco, descricao);
                            produtos.add(p);
                        }
                    }
                }
            }
            aux = leitor.readLine();
        }
        return produtos;
        
    }*/
    
    public Produto buscaProduto(int id) throws FileNotFoundException, IOException{
        Produto prod = null;
        String aux, ids, nome, preco, descricao;
        String [] div;
        int cod;
        double dpreco;
        leitor = new BufferedReader(new FileReader(getArquivo()));
        aux = leitor.readLine();
        while(leitor.ready()){
             if(aux.contains("ID")){
                div = aux.split(":");
                ids = div[1];
                
                cod = Integer.parseInt(ids);
                if(cod == id){
                    aux = leitor.readLine();
                    if(aux.contains("Nome")){
                        div = aux.split(":");
                        nome = div[1];
                        aux = leitor.readLine();
                        if(aux.contains("Preço")){
                            div = aux.split(":");
                            preco = div[1];
                            dpreco = Double.parseDouble(preco);
                            aux = leitor.readLine();

                            if(aux.contains("Descrição")){
                                div = aux.split(":");
                                descricao = div[1];
                               // aux = leitor.readLine();


                                prod = new Produto(cod, nome, dpreco, descricao);

                            }
                        
                        }
                    }    
                }
                aux = leitor.readLine();
             }
            aux = leitor.readLine();
        }
        return prod;
        
    }
    
    
 
    
    public boolean novoarquivo() throws IOException{
        escritor = new BufferedWriter(new FileWriter(getArquivo(),false));
        for(Produto p:produtos){
            escritor.write("ID:"+Integer.toString(p.getId()));
            escritor.write("\n");
            escritor.write("Nome: "+p.getNome());
            escritor.write("\n");
            escritor.write("Preço:"+Double.toString( p.getPreco()));
            escritor.write("\n");
            escritor.write("Descrição: "+p.getDescricao());
            escritor.write("\n");
        }
        escritor.flush();
        escritor.close();
        
       
        return true;
    }
    //para remoção de dados
    public void listaProdutos() throws FileNotFoundException, IOException{
        //dados = new ArrayList<Object>();
        produtos = new ArrayList<>();
        String aux, id, nome, preco, descricao;
        int cod;
        double dpreco;
        String [] div;
        leitor = new BufferedReader(new FileReader(getArquivo()));
        aux = leitor.readLine();
        while(leitor.ready()){
            
            if(aux.contains("ID")){
                div = aux.split(":");
                id = div[1];
                cod = Integer.parseInt(id);
                aux = leitor.readLine();
                if(aux.contains("Nome")){
                    div = aux.split(":");
                    nome = div[1];
                    aux = leitor.readLine();
                    
                    if(aux.contains("Preço")){
                        div = aux.split(":");
                        preco = div[1];
                        dpreco = Double.parseDouble(preco);
                        aux = leitor.readLine();
                        
                        if(aux.contains("Descrição")){
                            div = aux.split(":");
                            descricao = div[1];
                           // aux = leitor.readLine();
                            
                            System.out.println("id "+ id + "\nnome" +nome+ "\npreco"+preco+ "\n descricao "+  descricao);
                            Produto p = new Produto(cod, nome, dpreco, descricao);
                            produtos.add(p);
                            //dados.add(new Object[]{cod, nome, dpreco, descricao});
                        }
                    }
                }
            }
            aux = leitor.readLine();
        }
        
    }
   
    
    public boolean removeProduto(int id){
       
        
        
        for(Produto p:produtos){
            if(p.getId() == id){
                produtos.remove(p);
                return true;
            }
        }
        return false;
        
    }
    public boolean gravarOrdenado(int id, String nome, double preco, String descricao) throws IOException{
        int menor, atual;
        Produto novo = new Produto(id, nome, preco, descricao);
        
        escritor = new BufferedWriter(new FileWriter(getArquivo(),false));
        for(Produto p:produtos){
            
            if(p.getId()+1 <= id || p.getId() > id+1){
                
                
                escritor.write("ID:"+Integer.toString(p.getId()));
                escritor.write("\n");
                escritor.write("Nome: "+p.getNome());
                escritor.write("\n");
                escritor.write("Preço:"+Double.toString( p.getPreco()));
                escritor.write("\n");
                escritor.write("Descrição: "+p.getDescricao());
                escritor.write("\n");
            }
            else if(id == p.getId()-1){
                escritor.write("ID:"+Integer.toString(novo.getId()));
                escritor.write("\n");
                escritor.write("Nome: "+novo.getNome());
                escritor.write("\n");
                escritor.write("Preço:"+Double.toString( novo.getPreco()));
                escritor.write("\n");
                escritor.write("Descrição: "+novo.getDescricao());
                escritor.write("\n");
                
            }
            
        }
        escritor.flush();
        escritor.close();
        
       
        return true;
        
    }
    //para enviar dados para a tabela
    public ArrayList impressaoTabela() throws IOException{
       ArrayList listaProdutos = new ArrayList();
       
        String aux, id, nome, preco, descricao;
        String [] div;
        leitor = new BufferedReader(new FileReader(getArquivo()));
        aux = leitor.readLine();
        while(leitor.ready()){
            
            if(aux.contains("ID")){
                div = aux.split(":");
                id = div[1];
                aux = leitor.readLine();
                if(aux.contains("Nome")){
                    div = aux.split(":");
                    nome = div[1];
                    aux = leitor.readLine();
                    
                    if(aux.contains("Preço")){
                        div = aux.split(":");
                        preco = div[1];
                        aux = leitor.readLine();
                        
                        if(aux.contains("Descrição")){
                            div = aux.split(":");
                            descricao = div[1];
                           // aux = leitor.readLine();
                            
                            System.out.println("id "+ id + "\nnome" +nome+ "\npreco"+preco+ "\n descricao "+  descricao);
                            listaProdutos.add(new Object[]{id, nome, preco, descricao});
                        }
                    }
                }
            }
            aux = leitor.readLine();
        }
        return listaProdutos;
     }
    
    @Override
    public boolean ler() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean escrever(boolean append) {
        if(getArquivo() != null){
            try{
                escritor = new BufferedWriter(new FileWriter(getArquivo(), append));
                escritor.write(getTextolinha());
                escritor.write("\n");
                escritor.close();
                return true;
            }catch(IOException erro){
                System.err.println(erro.getMessage()+"Erro ao ler arquivo");
                return false;
            }
        }else return false;
    }
    
    
    
    
}
