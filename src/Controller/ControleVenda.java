/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Arquivo.ControleArquivoVenda;
import Model.Cliente;
import Model.ItemVenda;
import Model.Produto;
import Model.Venda;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Luan
 */
public class ControleVenda extends ControleArquivoVenda implements InterfaceControle{
    ArrayList<ItemVenda> itensVenda = new ArrayList<>();
    Venda venda = null;
    Cliente cliente;
    ItemVenda item = null;
    Produto produto;
    ControleProduto controleproduto = new ControleProduto();
    ArrayList dados = null;
    ArrayList<Produto> listaProdutos = new ArrayList<Produto>();
  
    
    double precototal=0;
    int linha;
    
    public ControleVenda(){
        String dir = System.getProperty("user.dir");
        String nome = dir+"/Dados/Vendas.txt";
       // File arq = new File(nome);
        System.out.println("nome "+ nome);
        System.out.println("get arquivo "+ getArquivo());
        setArquivo(nome);
    }
    
    public int novoId() throws IOException{
        int novoid = contagemId();
        return novoid;
    }
    
    
    
    public boolean novaVenda(int idClient, ArrayList<ItemVenda> itensVenda){
        double valor = 0;
        int id = 0;
        try{
            id = novoId();
        }catch(IOException erro){
            System.out.println("erro "+erro);
        }
        
        for(int i = 0 ; i< itensVenda.size() ; i++){
          valor = itensVenda.get(i).getValorItem() + valor;
            
        }
        venda = new Venda(id, idClient, itensVenda, valor);
      
        return true;
        
    }
    public Venda getVenda(){
        return venda;
    }
    
    
    
    @Override
     public JTable dadostabela(JTable table, ArrayList lista, String [] colunas){
               
         
            ModeloTabela modelo = new ModeloTabela(lista, colunas);
            
            table.setModel(modelo);
            for(int i = 0 ; i <colunas.length;i++){
                table.getColumnModel().getColumn(i).setPreferredWidth(20);
                table.getColumnModel().getColumn(i).setResizable(true);
            }
            
            
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            return table;
     }
     
     
     
    public int selecionarId(JTable tab){
        int linha = tab.getSelectedRow();
        String id = (String) tab.getValueAt(linha,0);
        int ide = Integer.parseInt(id);
        return ide;
    }
    
    public void setLinha(int linha){
        this.linha = linha;
    }
    public int selecionarID(JTable tab, int campo){
        int linha = tab.getSelectedRow();
        String id = (String) tab.getValueAt(linha,campo);
        int ide = Integer.parseInt(id);
        return ide;
    }
    /*
    public void addProdList(JTable tab, int qtd){
        int linha = tab.getSelectedRow();
        
        String id  = (String) tab.getValueAt(linha,0);
        int ide = Integer.parseInt(id);
        String nome = (String) tab.getValueAt(linha,1);
        String preco = (String) tab.getValueAt(linha,2);
        double dpreco = Double.parseDouble(preco);
        String descricao = (String) tab.getValueAt(linha,3);
        precototal = (dpreco * qtd)+precototal;
        
    }
    */
    public void selecionarProduto(JTable tab, int qtd) throws IOException{
        int linha = tab.getSelectedRow();
        
        String id  = (String) tab.getValueAt(linha,0);
        int ide = Integer.parseInt(id);
        
        Produto p =  controleproduto.buscarProdArquivo(ide);
        
        precototal = (p.getPreco() * qtd); 
        
        
        item = new ItemVenda(p,qtd,precototal);
        itensVenda.add(item);
        convItemObject();
       
    }
    
    public ArrayList convItemObject( ){
        dados = new ArrayList();
        for(ItemVenda v : itensVenda){
            System.out.println("dentro conv item obj "+v.getItemProduto().getQuantidade());
            System.out.println("dentro conv item obj 2 ---> "+v.getQuantidade());
            dados.add(new Object[]{v.getItemProduto().getId(),
                v.getItemProduto().getNome(),v.getItemProduto().getDescricao(),
                v.getItemProduto().getPreco(),v.getQuantidade(),
                v.getValorItem()
                
            });
        }
        return dados;
    }
    
    public ArrayList getDadosTabelas(){
        return dados;
    }
    public ArrayList<ItemVenda> getItensVenda(){
        return itensVenda;
    }
      
    public double valorTotal(JTable tab){
        double value =0 ;
        for(int i =0 ; i <tab.getRowCount() ; i++){
            value = (double)tab.getValueAt(i, 5) + value;
        }
        return value;
    }
   
    public boolean removerProdCarrinho(JTable tab, int id){
    
        for(ItemVenda v : itensVenda){
            if(v.getItemProduto().getId() == id){
               itensVenda.remove(v);
               return true;
            }
        }
        return false;
       
    }
    
    public  JTable atualizaTabela(JTable tab, String [] colunas){
        
       // String [] colunas = new String[]{"ID ","NOME DO PRODUTO","DESCRIÇÃO","PREÇO", "QUANTIDADE","PREÇO TOTAL"};
        //Carrinho.tabCarrinho = controlVenda.dadostabela(Carrinho.tabCarrinho, controlVenda.getProdPreco(),colunas );
        tab = dadostabela(tab, convItemObject(),colunas );
        //idClient = controlVenda.selecionarId(selCli.tabClientes);
        return tab;
    }
    
    
    
}
