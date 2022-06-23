/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Arquivo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Luan
 */
public abstract class ControleArquivo {
    protected File arquivo = null;
    public abstract boolean ler();
    public abstract boolean escrever(boolean append);
    
    public void setArquivo(String nomearquivo) {
        //String initDir = System.getProperty("user.dir");
        try{
            FileWriter arq = new FileWriter (nomearquivo, true);
        }catch(IOException erro){
            JOptionPane.showMessageDialog(null, erro);
        }
        
        arquivo = new File(nomearquivo);
        
        
    }
    public File getArquivo(){
        return arquivo;
    }
}
