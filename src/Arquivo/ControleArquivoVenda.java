/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Arquivo;

import Controller.ControleCliente;
import Model.Cliente;
import Model.ItemVenda;
import Model.Produto;
import Model.Venda;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Luan
 */
public class ControleArquivoVenda extends ControleArquivo {
    private BufferedReader leitor = null;
    private BufferedWriter escritor = null;
    String textolinha;
    protected Venda venda;
    protected ItemVenda itemVenda;
    ArrayList lista = null;
    ArrayList<Venda> listVendas = new ArrayList<Venda>();

    public String getTextolinha() {
        return textolinha;
    }

    public void setTextolinha(String textolinha) {
        this.textolinha = textolinha;
    }
    
    
    
    
    public int contagemId() throws IOException{
        String aux , numero;
        int valor = 0, conta=0;
        String [] div;
        leitor = new BufferedReader(new FileReader(getArquivo()));
        
        aux = leitor.readLine();
        while(leitor.ready()){
            if(aux.contains("ID")){
                div = aux.split(":");
                numero = div[1];
                valor = Integer.parseInt(numero);
                conta  = valor+ 1;
            }
            aux = leitor.readLine();
        }
        return valor;
        
    }
    
    
    public boolean gravarVendaArquivo(Venda venda){
        try{
            escritor = new BufferedWriter(new FileWriter(getArquivo(), true));
            escritor.write("ID de Venda :"+venda.getIdVenda()+"\n");
            escritor.write("ID do Cliente :"+venda.getIdcliente()+"\n");
            for(ItemVenda v : venda.getItens()){
                escritor.write("ID do Produto :"+v.getItemProduto().getId()+"\n");
                escritor.write("Nome do Produto :"+v.getItemProduto().getNome()+"\n");
                escritor.write("Descrição :"+v.getItemProduto().getDescricao()+"\n");
                escritor.write("Preço do Produto :"+v.getItemProduto().getPreco()+"\n");
                System.out.println("teste gravando qtd "+v.getQuantidade());
                escritor.write("Quantidade :"+v.getQuantidade()+"\n");
                escritor.write("Valor por Item :"+v.getValorItem()+"\n");
            }
            
            escritor.write("Valor Total da Venda :"+venda.getValortotal()+"\n");  
            escritor.flush();
            escritor.close();
            return true;
            
        }catch(IOException erro){
            System.out.println("erro "+erro);
            return false;
        }
        
    }
    
    
    //IMPRESSÃO NA TABELA ARRAY PRIMITIVO
    //VENDA TEM MUITA INFORMAÇÃO PASSAR TUDO PARA ARRAY DE VENDA A PARTIR DISSO SELECIONAR O QUE IMPRIMIR
    //UM MESMO CLIENTE PODE TER VARIAS COMPRAS ENTAO NÃO TERÁ LISTA DE TODAS AS VENDAS, 
    //SOMENTE REFERENTE A BUSCA 
    public ArrayList impressaoTabela(String nome) throws IOException{
        ControleCliente controlCli = new ControleCliente();
        Cliente cliente = controlCli.buscaCliente(nome);
        int idCliente = cliente.getPessoaJuridica().getId();
        System.out.println("getidcliente de cliente "+idCliente);
        lista = new ArrayList();
        
        /*if(cliente.getPessoaFisica()!= null){
            System.out.println("cliente busca "+cliente.getPessoaFisica().getNome());
             lista.add(new Object[]{cliente.getPessoaFisica().getNome(),
             cliente.getPessoaFisica().getCpf(), cliente.getPessoaFisica().getRg()});
        }if(cliente.getPessoaJuridica()!= null){
            System.out.println("cliente busca "+cliente.getPessoaJuridica().getNome());
             lista.add(new Object[]{cliente.getPessoaJuridica().getNome(),cliente.getPessoaJuridica().getCNPJ(),
                     cliente.getPessoaJuridica().getNomeFantasia(),
                     });
             
        }
        */
        leitor = new BufferedReader(new FileReader(getArquivo()));
        String aux = leitor.readLine();
        String idvenda=null,idcli, idproduto, nomeprod,desc, preco, qtd, valoritem, valortotal;
        String []div;
        double dpreco, dvaloritem;
        int idclitest, idprod, qtt;
        Produto p = null;
        
        ItemVenda item = null;
        ArrayList<ItemVenda> listItens = new ArrayList<>();
       
        while(aux != null){
            while(leitor.ready()){
                
                    if(aux.contains("ID de Venda")){
                        div = aux.split(":");
                        idvenda = div[1];
                        aux = leitor.readLine();
                    }
                        if(aux.contains("ID do Cliente")){
                            div = aux.split(":");
                            idclitest = Integer.parseInt(div[1]);
                        
                            if(idclitest == idCliente ){
                                aux = leitor.readLine();
                                while(!aux.contains("Valor Total da Venda")){ 
                                    if(aux.contains("ID do Produto")){
                                        div = aux.split(":");
                                        idprod = Integer.parseInt(div[1]);
                                        aux = leitor.readLine();
                                        if(aux.contains("Nome do Produto")){
                                            div = aux.split(":");
                                            nomeprod = div[1];
                                            aux = leitor.readLine();
                                            if(aux.contains("Descrição")){
                                                div = aux.split(":");
                                                desc = div[1];
                                                aux = leitor.readLine();
                                                if(aux.contains("Preço do Produto")){
                                                    div = aux.split(":");
                                                    preco = div[1];
                                                    dpreco = Double.parseDouble(preco);
                                                    p = new Produto(idprod,nomeprod, dpreco, desc);
                                                    aux = leitor.readLine();
                                                    if(aux.contains("Quantidade")){
                                                        div = aux.split(":");
                                                        qtt = Integer.parseInt(div[1]);
                                                        aux = leitor.readLine();
                                                        if(aux.contains("Valor por Item")){
                                                            div = aux.split(":");
                                                            valoritem = div[1];
                                                            dvaloritem = Double.parseDouble(valoritem);
                                                            item = new ItemVenda(p,qtt,dvaloritem);
                                                            
                                                            listItens.add(item);
                                                            lista.add(new Object[]{idvenda,
                                                                p.getId(),p.getNome(),p.getDescricao(), p.getPreco(),
                                                                item.getQuantidade(),item.getValorItem()
                                                            });
                                                            System.out.println("produtos em lista \n");
                                                            System.out.println("p.nome "+p.getNome());
                                                            aux = leitor.readLine();
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                aux = leitor.readLine();
            }
            aux = leitor.readLine();
        }
        
        
        
        return lista;
        
    }
    
    @Override
    public boolean ler() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean escrever(boolean append) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
