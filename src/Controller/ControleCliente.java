/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Arquivo.ControleArquivoCliente;
import Model.Cliente;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Luan
 */
public class ControleCliente extends ControleArquivoCliente implements InterfaceControle{
    ArrayList<Cliente> listaclientes ;
    
    Cliente cliente = null;
    public ControleCliente(){
        String dir = System.getProperty("user.dir");
        String nome = dir+"/Dados/Clientes.txt";
        setArquivo(nome);
    }
    
    public int novoId() throws IOException{
        int novoid = contagemId();
        return novoid;
    }
    
    String [] dados ;
    
   /* public void pegarDados(String no1, String no2, String no3, String no4, String no5, String no6, String no7, String no8, String no9, String no10, String no11){
        dados = new ArrayList<>();
        dados.add(no1);
        dados.add(no2);
        dados.add(no3);
        dados.add(no4);
        dados.add(no5);
        dados.add(no5);
        dados.add(no6);
        dados.add(no7);
        dados.add(no8);
        dados.add(no9);
        dados.add(no10);
        dados.add(no11);
        
    } */
    //pessoa fisica
    /* nome, email, telefone, rg, cpf, estado, cidade, bairro, rua, numero, complemento */
    //pessoa juridica
    /*nome, email telefone, nomefantasia, cnpj, estado, cidade, bairro, rua, numero, complemento*/
    public boolean pegarDados(boolean alteracao ,int id ,int tipo, String no1, String no2, String no3, String no4, String no5, String no6, String no7, String no8, String no9, String no10, String no11) throws IOException{
        dados = new String[12] ;
        if(alteracao == true){
            dados[0] = Integer.toString(id);
            dados[1] = no1;
            dados[2] = no2;
            dados[3] = no3;
            dados[4] = no4;
            dados[5] = no5;
            dados[6] = no6;
            dados[7] = no7;
            dados[8] = no8;
            dados[9] = no9;
            dados[10] = no10;
            dados[11] = no11;
            return true;
        }
        if(alteracao == false){
            dados[0] = Integer.toString(novoId());
            dados[1] = no1;
            dados[2] = no2;
            dados[3] = no3;
            dados[4] = no4;
            dados[5] = no5;
            dados[6] = no6;
            dados[7] = no7;
            dados[8] = no8;
            dados[9] = no9;
            dados[10] = no10;
            dados[11] = no11;
            return true;
        }
     
        return false;
    }
    
    public void criarCliente(int tipo){
        this.cliente = new Cliente(tipo, dados);
        System.out.println("testando novo cliente \n ");
        if(cliente.getPessoaFisica() != null){
            System.out.println("id "+this.cliente.getPessoaFisica().getId());
        }
        if(cliente.getPessoaJuridica() != null){
            System.out.println("id "+this.cliente.getPessoaJuridica().getId());
        }
        
       // salvarCliente(cliente);
           
    }
     
    public boolean salvarCliente() throws IOException{
        
        if(gravarClienteArquivo(cliente, true) == true){
            return true;
        }else return false;
        
    }
    
    
    public Cliente buscaCliente(String nomeBusca) throws IOException{
        cliente = buscaClient(nomeBusca);
       
        return cliente;
    } 
    
    public Cliente getCliente(){
        
        System.out.println(" dentro de get cliente +++++ \n");
        if(cliente.getPessoaFisica() != null){
            System.out.println(" id pf "+cliente.getPessoaFisica().getId());
        }
        if(cliente.getPessoaJuridica() != null){
            System.out.println(" id  pj "+cliente.getPessoaJuridica().getId());
        }
        
        return cliente;
    }

    @Override
    public JTable dadostabela(JTable table, ArrayList listProds, String[] colunas) {
        ModeloTabela modelo = new ModeloTabela(listProds, colunas);
            
            table.setModel(modelo);
            for(int i = 0 ; i <colunas.length;i++){
                table.getColumnModel().getColumn(i).setPreferredWidth(20);
                table.getColumnModel().getColumn(i).setResizable(true);
            }
            
            
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            return table;
    
    }
    
   

    
    
     public int selecionarId(JTable tabClientes){
        int linha = tabClientes.getSelectedRow();
        String id = (String) tabClientes.getValueAt(linha,0);
        int ide = Integer.parseInt(id);
        return ide;
    }
    public int selecionarTipo(JTable tabClientes){
        int linha = tabClientes.getSelectedRow();
        int type =-1;
        String tipo = (String) tabClientes.getValueAt(linha,1);
        if(tipo.contains("FÍSICA")){
            type =0;
            return type;
        }
        if(tipo.contains("JURÍDICA")){
            type = 1;
            return type;
        }
        //int ttipo = Integer.parseInt(tipo);
        return type;
       
        
    }
    
    
    
   
    
}
