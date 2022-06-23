/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Arquivo.ControleArquivoProduto;
import Model.Produto;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Luan
 */
public class ControleProduto extends ControleArquivoProduto implements InterfaceControle{
    
    
    Produto produto = null;
    public ControleProduto(){
        String dir = System.getProperty("user.dir");
        String nome = dir+"/Dados/Produtos.txt";
        File arq =new File(nome);
        setArquivo(nome);
    }
    public int novoId() throws IOException{
        int novoid = contagemId();
        return novoid;
    }
    public boolean criarProduto(int id, String nome, double preco, String descricao) throws IOException{
        produto = new Produto(id, nome, preco, descricao);
        if(salvarProduto(produto) == true){
            return true;
        }else return false;
    }
    
    public boolean salvarProduto(Produto produto) throws IOException{
        
        if(gravarProdutoArquivo(produto) == true){
            return true;
        }else return false;
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
    public int selecionarId(JTable tabProds){
        int linha = tabProds.getSelectedRow();
        String id = (String) tabProds.getValueAt(linha,0);
        int ide = Integer.parseInt(id);
        return ide;
    }
    
    public Produto buscarProdArquivo(int id) throws IOException{
        return buscaProduto(id);
    }
   

   

}
