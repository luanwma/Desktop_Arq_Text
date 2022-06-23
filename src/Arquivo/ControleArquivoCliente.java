/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Arquivo;

import Controller.ControleCliente;
import Model.Cliente;
import Model.PessoaFisica;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Luan
 */
public class ControleArquivoCliente extends ControleArquivo{
    private BufferedReader leitor;
    private BufferedWriter escritor;
    protected Cliente cliente;
   // ArrayList<String> listaClientes ;
    ArrayList<Cliente> listClient ;
    ArrayList<Cliente> listClientAux = new ArrayList<>(); ;
    ArrayList listaobjts;
    String textolinha ;
    private boolean append ;

    
    
  

    public String getTextolinha() {
        return textolinha;
    }

    public void setTextolinha(String textolinha) {
        this.textolinha = textolinha;
    }
    
    public void codGravacaoFisica( Cliente c,  boolean append) throws IOException{
            escritor = new BufferedWriter(new FileWriter(getArquivo(),append));
            System.out.println("id gravacao "+c.getPessoaFisica().getId());
            System.out.println("arquivo gravavao "+getArquivo());
            escritor.write("ID:"+ c.getPessoaFisica().getId()+"\n");
            escritor.write("Tipo: "+c.getTipo()+"\n");
            escritor.write("Nome: "+c.getPessoaFisica().getNome()+"\n");
            escritor.write("Email: "+c.getPessoaFisica().getEmail()+"\n");
            escritor.write("Telefone: "+c.getPessoaFisica().getTelefone()+"\n");
            escritor.write("RG: "+c.getPessoaFisica().getRg()+"\n");
            escritor.write("CPF: "+c.getPessoaFisica().getCpf()+"\n");
            escritor.write("Estado: "+c.getPessoaFisica().getEndereco().getEstado()+"\n");
            escritor.write("Cidade: "+c.getPessoaFisica().getEndereco().getCidade()+"\n");
            escritor.write("Bairro: "+c.getPessoaFisica().getEndereco().getBairro()+"\n");
            escritor.write("Rua: "+c.getPessoaFisica().getEndereco().getRua()+"\n");
            escritor.write("Numero: "+c.getPessoaFisica().getEndereco().getNumero()+"\n");
            escritor.write("Complemento: "+c.getPessoaFisica().getEndereco().getComplemento()+"\n");
            escritor.flush();
            escritor.close();
            
        
    }
    public void codGravacaoJuridica(Cliente c , boolean append ) throws IOException{
        escritor = new BufferedWriter(new FileWriter(getArquivo(),append)); 
      
            escritor.write("ID:"+c.getPessoaJuridica().getId()+"\n");
            escritor.write("Tipo: "+c.getTipo()+"\n");
            escritor.write("Nome: "+c.getPessoaJuridica().getNome()+"\n");
            escritor.write("Email: "+c.getPessoaJuridica().getEmail()+"\n");
            escritor.write("Telefone: "+c.getPessoaJuridica().getTelefone()+"\n");
            escritor.write("Nome Fantasia: "+c.getPessoaJuridica().getNomeFantasia()+"\n");
            escritor.write("CNPJ: "+c.getPessoaJuridica().getCNPJ()+"\n");
            escritor.write("Estado: "+c.getPessoaJuridica().getEndereco().getEstado()+"\n");
            escritor.write("Cidade: "+c.getPessoaJuridica().getEndereco().getCidade()+"\n");
            escritor.write("Bairro: "+c.getPessoaJuridica().getEndereco().getBairro()+"\n");
            escritor.write("Rua: "+c.getPessoaJuridica().getEndereco().getRua()+"\n");
            escritor.write("Numero: "+c.getPessoaJuridica().getEndereco().getNumero()+"\n");
            escritor.write("Complemento: "+c.getPessoaJuridica().getEndereco().getComplemento()+"\n");
            escritor.flush();
            escritor.close();
        
    }

    
    
    public boolean gravarClienteArquivo(Cliente cliente, boolean append) throws IOException{
      // escritor = new BufferedWriter(new FileWriter(getArquivo(),append));
        if(cliente.getTipo().equals("FÍSICA") ){
            
            try{
                
                codGravacaoFisica(cliente, true );
                
                return true;
            }catch(IOException erro){
                System.out.println("erro "+erro);
                return false;
            }
        }
        if(cliente.getTipo().equals("JURÍDICA") ){
            
            try{
                
                codGravacaoJuridica(cliente, true);
                
                return true;
            }catch(IOException erro){
                System.out.println("erro "+erro);
                return false;
            }   
        }  
        return false;
    }
    
    
    
    
    public void newListClient() throws FileNotFoundException, IOException{
        
        listClient = new ArrayList<Cliente>();
        Cliente c2 = null;
        
        String aux, tipo = null, id = null, nome= null, email= null, tel= null,
                doc1= null, doc2= null, estado= null, cidade= null, bairro= null, 
                rua= null, numero= null, complemento= null;
        int intipo = -1, cod=0;
        String [] div; 
        String [] dados = null;
        leitor = new BufferedReader(new FileReader(getArquivo()));
        aux = leitor.readLine();
        while(aux!= null){
            while(leitor.ready()){
                if(aux.contains("ID")){
                    div = aux.split(":");
                    id = div[1];
                    cod = Integer.parseInt(id);
                    aux = leitor.readLine();
                }
                if(aux.contains("Tipo")){
                    div = aux.split(":");
                    tipo = div[1];
                    if(aux.contains("FÍSICA")){
                            
                            intipo = 0;
                        }
                        if(aux.contains("JURÍDICA") ){
                            intipo = 1;
                        }
                    aux = leitor.readLine();
                }
                if(aux.contains("Nome")){
                    div = aux.split(":");
                            nome = div[1];
                            aux = leitor.readLine();
                }
                if(aux.contains("Email")){
                    div = aux.split(":");
                    email = div[1];
                    aux = leitor.readLine();
                }
                if(aux.contains("Telefone")){
                    div = aux.split(":");
                    tel = div[1];
                    aux = leitor.readLine();

                }
                if(aux.contains("RG") || aux.contains("Nome Fantasia")){
                    div = aux.split(":");
                    doc1 = div[1];
                    aux = leitor.readLine();
                }
                if(aux.contains("CPF") || aux.contains("CNPJ")){
                    div = aux.split(":");
                    doc2 = div[1];
                    aux = leitor.readLine();
                }
                if(aux.contains("Estado") ){
                    div = aux.split(":");
                    estado = div[1];
                    aux = leitor.readLine();
                }
                if(aux.contains("Cidade") ){
                    div = aux.split(":");
                    cidade = div[1];
                    aux = leitor.readLine();
                }
                if(aux.contains("Bairro") ){
                    div = aux.split(":");
                    bairro = div[1];
                    aux = leitor.readLine();
                }
                if(aux.contains("Rua") ){
                    div = aux.split(":");
                    rua = div[1];
                    aux = leitor.readLine();
                }
                if(aux.contains("Numero") ){
                    div = aux.split(":");
                    numero = div[1];
                    aux = leitor.readLine();
                }
                if(aux.contains("Complemento") ){
                    div = aux.split(":");
                    complemento = div[1];
                    aux = leitor.readLine();
                }    

                dados = new String []{id, nome, email , tel, doc1, doc2, estado, cidade, bairro, rua, numero, complemento};

               
               // Cliente c = new Cliente(intipo,cod,  nome,  email, tel,  doc1,  doc2,  
                 //       estado,cidade, bairro, rua, numero, complemento  );
                
                c2 = new Cliente(intipo , dados);
                listClient.add(c2);
               
                
                }
                 
            aux = leitor.readLine();
        }
    
    }
    public ArrayList<Cliente> listBuscaCliente(String nomebusca) throws IOException{
       Cliente aux = buscaClient(nomebusca);
       return listClientAux ;
        
       
    }
    
    public Cliente buscaClient(String  nomeBusca) throws FileNotFoundException, IOException{
        Cliente c = null;
        String aux, tipo = null, id = null, nome= null, email= null, tel= null,
                doc1= null, doc2= null, estado= null, cidade= null, bairro= null, 
                rua= null, numero= null, complemento= null;
        int intipo = -1, cod=0;
        String [] div; 
        String [] dados = null;
        leitor = new BufferedReader(new FileReader(getArquivo()));
        aux = leitor.readLine();
        while(aux != null){
            while(leitor.ready()){
                if(aux.contains("ID")){
                    div = aux.split(":");
                    id = div[1];
                    cod = Integer.parseInt(id);
                    
                    aux = leitor.readLine();
                    
                }
                if(aux.contains("Tipo")){
                    div = aux.split(":");
                    tipo = div[1];
                    if(aux.contains("FÍSICA")){
                            
                            intipo = 0;
                        }
                        if(aux.contains("JURÍDICA") ){
                            intipo = 1;
                        }
                    aux = leitor.readLine();
                    
                }
                if(aux.contains("Nome")){
                    div = aux.split(":");
                    nome = div[1];
                    aux = leitor.readLine();
                    if( nome.contains(nomeBusca)){
                       
                        
                        if(aux.contains("Email")){
                            div = aux.split(":");
                            email = div[1];
                           
                            aux = leitor.readLine();
                        
                        if(aux.contains("Telefone")){
                            div = aux.split(":");
                            tel = div[1];
                            
                            aux = leitor.readLine();

                        
                        if(aux.contains("RG") || aux.contains("Nome Fantasia")){
                            div = aux.split(":");
                            doc1 = div[1];
                            
                            aux = leitor.readLine();
                        
                        if(aux.contains("CPF") || aux.contains("CNPJ")){
                            div = aux.split(":");
                            doc2 = div[1];
                            
                            aux = leitor.readLine();
                        
                        if(aux.contains("Estado") ){
                            div = aux.split(":");
                            estado = div[1];
                           
                            aux = leitor.readLine();
                        
                        if(aux.contains("Cidade") ){
                            div = aux.split(":");
                            cidade = div[1];
                            
                            aux = leitor.readLine();
                        
                        if(aux.contains("Bairro") ){
                            div = aux.split(":");
                            bairro = div[1];
                           
                            aux = leitor.readLine();
                        
                        if(aux.contains("Rua") ){
                            div = aux.split(":");
                            rua = div[1];
                            
                            aux = leitor.readLine();
                        
                        if(aux.contains("Numero") ){
                            div = aux.split(":");
                            numero = div[1];
                            
                            aux = leitor.readLine();
                        
                        if(aux.contains("Complemento") ){
                            div = aux.split(":");
                            complemento = div[1];
                            
                            aux = leitor.readLine();
                        c = new Cliente(intipo, cod, nome, email, tel, doc1, doc2, estado, 
                            cidade,bairro,rua, numero, complemento);
                        
                        listClientAux.add(c);
                        }
                        
                        }}}}}}}}}
                       // aux = leitor.readLine();
                        
                    }
                    while(!aux.contains("Complemento")){
                        aux = leitor.readLine();
                    }
                   // 
                }
                aux = leitor.readLine();
            }
            aux = leitor.readLine();
        }
        return c;
    }
    
    public ArrayList clienteToObject(Cliente c){
        ArrayList printlist = new ArrayList();
        if(c.getPessoaFisica() != null){
            printlist.add(new Object[]{c.getPessoaFisica().getId(), c.getTipo(),c.getPessoaFisica().getNome(),
                c.getPessoaFisica().getRg(), c.getPessoaFisica().getCpf(), c.getTelefone(), c.getEmail()});
        }if(c.getPessoaJuridica() != null){
            printlist.add(new Object[]{c.getPessoaJuridica().getId(), c.getTipo(),c.getPessoaJuridica().getNome(),
                c.getPessoaJuridica().getNomeFantasia(), c.getPessoaJuridica().getCNPJ(), c.getTelefone(), c.getEmail()});
        }
        return printlist;
        
        
    }
        
        
    
    public boolean removeCliente( int id){
        
        for(int i = 0 ; i <listClient.size() ; i++){
            if(listClient.get(i).getPessoaFisica() != null ){
                if(listClient.get(i).getPessoaFisica().getId() == id){
                    listClient.remove(listClient.get(i));
                }
                   
                
            }else {
                if(listClient.get(i).getPessoaJuridica()!= null ){
                    if(listClient.get(i).getPessoaJuridica().getId() == id){
                        listClient.remove(listClient.get(i));
                    }
                }
            }
            
        }
        return true;
    }
    public boolean novoarquivo() throws IOException{
        escritor = new BufferedWriter(new FileWriter(getArquivo(),false));
        
        for(Cliente c:listClient){
           
                if(c.getTipo().equals("FÍSICA")){
                    codGravacaoFisica(c, true);
                }
                if(c.getTipo().equals("JURÍDICA")){
                    codGravacaoJuridica(c, true);
                }
                
                
        }
         return true;   

        
        
    }
     
    /*
    public boolean novoArquivo(ArrayList<Cliente> lista) throws IOException{
        escritor = new BufferedWriter(new FileWriter(getArquivo(),false));
        for(int i = 0 ; i < lista.size(); i++){
            escritor.write("ID:"+Integer.toString(lista.get(i).getId()));
            escritor.write("\n");
            
            escritor.write("Tipo: "+lista.get(i).getTipo());
            escritor.write("\n");
            escritor.write("Nome: "+lista.get(i).getNome());
            escritor.write("\n");
            escritor.write("Email"+lista.get(i).getEmail());
            escritor.write("\n");
            escritor.write("Telefone:"+lista.get(i).getTelefone());
            escritor.write("\n");
            if(listClient.get(i).getPessoaFisica() != null){
                escritor.write("RG: "+lista.get(i).getPessoaFisica().getRg());
                escritor.write("\n");
                escritor.write("CPF: "+lista.get(i).getPessoaFisica().getCpf());
                escritor.write("\n");
            }else if(listClient.get(i).getPessoaJuridica() != null){
                escritor.write("Nome Fantasia: "+lista.get(i).getPessoaJuridica().getNomeFantasia());
                escritor.write("\n");
                escritor.write("CNPJ: "+lista.get(i).getPessoaJuridica().getCNPJ());
                escritor.write("\n");
            }
            
            escritor.write("Estado: "+lista.get(i).getEndereco().getEstado());
            escritor.write("\n");
            escritor.write("Cidade: "+lista.get(i).getEndereco().getCidade());
            escritor.write("\n");
            escritor.write("Bairro:"+lista.get(i).getEndereco().getBairro());
            escritor.write("\n");
            escritor.write("Rua: "+lista.get(i).getEndereco().getRua());
            escritor.write("\n");
            escritor.write("Numero: "+lista.get(i).getEndereco().getNumero());
            escritor.write("\n");
            escritor.write("Complemento: "+lista.get(i).getEndereco().getComplemento());
            escritor.write("\n");
            
        }
        escritor.flush();
        escritor.close();
        this.listClient = lista;
        return true;
    }
    */
    // retorno de array para montar tabela
    public ArrayList listaClientes() throws FileNotFoundException, IOException{
        ArrayList impressaoClientes = new ArrayList();
        String aux;
        String id,tipo , nome,  doc1 , doc2 , tel , email ;
        String div[];
        System.out.println("dentro o lista clientes "+getArquivo());
        leitor = new BufferedReader(new FileReader(getArquivo()));
        aux = leitor.readLine();
        while(leitor.ready()){
            if(aux.contains("ID")){
                div = aux.split(":");
                id = div[1];
                aux = leitor.readLine(); 
            
                if(aux.contains("Tipo")){
                    div = aux.split(":");
                    tipo = div[1];
                    System.out.println("tipo "+tipo);
                    aux = leitor.readLine();
                    if(aux.contains("Nome")){
                        div = aux.split(":");
                        nome = div[1];
                        System.out.println("nome "+nome); 
                        aux = leitor.readLine();
                        if(aux.contains("Email")){
                            div = aux.split(":");
                            email = div[1];
                            System.out.println("email "+email); 
                            aux = leitor.readLine();

                            if(aux.contains("Telefone")){
                                div = aux.split(":");
                                tel = div[1];
                                System.out.println("telefone "+tel); 
                                aux = leitor.readLine();

                                if(aux.contains("RG") || aux.contains("Nome Fantasia")){
                                    div = aux.split(":");
                                    doc1 = div[1];
                                    System.out.println("doc1 rg ou nome fantasia "+doc1); 
                                    aux = leitor.readLine();
                                    if(aux.contains("CPF") || aux.contains("CNPJ")){
                                        div = aux.split(":");
                                        doc2 = div[1];
                                        System.out.println("doc2 cnpj ou cpf "+doc2);
                                        //aux = leitor.readLine();

                                        impressaoClientes.add(new Object[]{id,tipo , nome,  doc1 , doc2 , tel , email});
                                    }
                                }
                            }
                        }
                    }
                }
            
            }
            aux = leitor.readLine();
        }
        return  impressaoClientes;
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
               
                //valor = Integer.valueOf(numero);
                valor = Integer.parseInt(numero);
               
                conta = valor +1;
            }
            aux = leitor.readLine();
        }
        return conta;
    }
    
    public boolean gravarClienteOrdenado(int id, Cliente cliente) throws IOException{
        int menor, maior;
        
        
        escritor = new BufferedWriter(new FileWriter(getArquivo(),false));
        escritor.flush();;
        escritor.close();
        int i = 0;
       
        for(Cliente c : listClient){
            if(c.getIdCliente() > id || c.getIdCliente() < id){
                if(c.getPessoaFisica() != null){
                    codGravacaoFisica(c, true);
                }
                 if(c.getPessoaJuridica() != null){
                    codGravacaoJuridica(c, true);
                }
            }
            if(c.getIdCliente() == id-1){
                
                
                if(cliente.getPessoaFisica() != null){
                    codGravacaoFisica(cliente, true);
                }
                 if(cliente.getPessoaJuridica() != null){
                    codGravacaoJuridica(cliente, true);
                }
            }
        }
        return true;
        
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
                escritor.close();
                return true;
            }catch(IOException erro){
                System.err.println(erro.getMessage()+"Erro ao ler arquivo");
                return false;
            }
        }else return false;
        
    }
}
