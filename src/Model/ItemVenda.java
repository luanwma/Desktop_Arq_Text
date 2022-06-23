/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Luan
 */
public class ItemVenda {
    private Produto itemProduto ;
    private int quantidade;
    private double valorItem;

    public ItemVenda(Produto itemProduto, int quantidade, double valorItem) {
        this.itemProduto = itemProduto;
        this.quantidade = quantidade;
        this.valorItem = valorItem;
    }
    
    
    
    public Produto getItemProduto() {
        return itemProduto;
    }

    public void setItemProduto(Produto itemProduto) {
        this.itemProduto = itemProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorItem() {
        return valorItem;
    }

    public void setValorItem(double valorItem) {
        this.valorItem = valorItem;
    }

    

    

    
    
    
    
}
