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
public class Bodega {
    private IEstoque estoque;
    public Bodega(IEstoque estoque) {
        this.estoque = estoque;
    }
    public void adicionarProduto(Produto produto) throws bodega.exception.PJCexception {
        try {
            estoque.adicionar(produto);
        } catch (bodega.exception.PJCexception e) {
            throw e;
        }
    }
    public void removerProduto(String codigo) throws bodega.exception.PNEexception {
        try {
            estoque.apagar(codigo);
        } catch (bodega.exception.PNEexception e) {
            throw e;
        }
    }
    public void venderProduto(String codigo, int quantidade) throws bodega.exception.PNEexception, bodega.exception.QIexception{
        try {
            int q_estoque = estoque.buscar(codigo).getQuantidade();
            if (quantidade > q_estoque) {
                throw new bodega.exception.QIexception(codigo);
            }
            estoque.buscar(codigo).setQuantidade(q_estoque - quantidade);
        } catch (bodega.exception.PNEexception e) {
            throw e;
        }
    }
    public void estocarProduto(String codigo, int quantidade) throws bodega.exception.PNEexception {
        try {
            int q_estoque = estoque.buscar(codigo).getQuantidade();
            estoque.buscar(codigo).setQuantidade(q_estoque + quantidade);
        } catch (bodega.exception.PNEexception e) {
            throw e;
        }
    }
   public Produto consultarProduto(String codigo) throws bodega.exception.PNEexception {
       try {
           Produto produto = estoque.buscar(codigo);
           return produto;
       } catch (bodega.exception.PNEexception e) {
           throw e;
       }
   }
   public Produto[] listarProdutos() {
       return estoque.produtosEmEstoque();
   }
   public Produto[] listarProdutosEmFalta() {
       return estoque.produtosEmFalta();
   }
}
