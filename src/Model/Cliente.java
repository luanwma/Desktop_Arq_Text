/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author Luan
 */
public class Cliente extends Pessoa implements java.io.Serializable{
   private static long serialVersionUID = 1L;
   public static final int FISICA = 0;
   public static final int JURIDICA = 1;
   
   PessoaFisica pf ;
   PessoaJuridica pj;
   
    private int idCliente;

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
   
    
    public void setIdCliente(int idcliente){
        this.idCliente = idcliente;
    }
    public int getIdCliente(){
        return idCliente;
    }
    
    
    /*
    public Cliente(int op){
       
       switch(op){
           case FISICA : 
               pf = new PessoaFisica();
           case JURIDICA: 
               pj = new PessoaJuridica();
       }
    }
    */
    public String tipo;
    
   
    public Cliente(int op , String[] dados){
        
        
        if(op == 0){
            setTipo("FÍSICA");
            pf = new PessoaFisica(dados);
            
        }else if(op == 1){
            setTipo("JURÍDICA");
            pj = new PessoaJuridica(dados);
        }
        selectId();
        
    }
    
    public Cliente(int op , int id, String nome, String email,
            String telefone, String doc1, String doc2, String estado,
            String cidade,String bairro,String rua,String numero,String complemento ){
        if(op == 0){
            
            setTipo("FÍSICA");
            pf = new PessoaFisica( id,  nome,  email,
             telefone,  doc1,  doc2,  estado,
             cidade, bairro, rua, numero, complemento);
        }else if(op == 1){
            setTipo("JURÍDICA");
            pj = new PessoaJuridica( id,  nome,  email, telefone,  doc1,  doc2,  estado,
             cidade, bairro, rua, numero, complemento);
        }
        
        
    }
    public void selectId(){
        if(pf != null){
            setIdCliente(pf.getId());
        }
        if(pj != null){
            setIdCliente(pj.getId());
        }
        
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
   
   
   public void setPessoaFisica(PessoaFisica pf){
       this.pf = pf;
   }
   public void setPessoaJuridica(PessoaJuridica pj){
       this.pj = pj;
   }
   public PessoaFisica getPessoaFisica(){
       return pf;
   }
   public PessoaJuridica getPessoaJuridica(){
       return pj;
   }

 
    
}
