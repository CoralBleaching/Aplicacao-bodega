/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bodega;

/**
 *
 * @author Renato Marques
 */
public class Produto implements Comparable {
    private String codigo;
    private String nome;
    private double preco;
    private int quantidade;
    public Produto(String codigo) {
        this.codigo = codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    public void setPreco(String preco) throws bodega.exception.FIexception {
        try {
            this.preco = Double.parseDouble(preco);
        } catch (java.lang.NumberFormatException e) {
            throw new bodega.exception.FIexception(codigo);
        }
    }
    public double getPreco() {
        return preco;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public void setQuantidade(String quantidade) throws bodega.exception.FIexception {
        try {
            this.quantidade = Integer.parseInt(quantidade);
        } catch (java.lang.NumberFormatException e) {
            throw new bodega.exception.FIexception(codigo);
        }
    }
    public int getQuantidade() {
        return quantidade;
    }
    @Override
    public String toString() {
        return nome;
    }
    public int compareTo(Object produto) {
        return nome.compareToIgnoreCase(((Produto) produto).getNome());
    }
}
