package bodega;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Renato Marques
 */
public interface IEstoque {
    public void adicionar(Produto produto) throws bodega.exception.PJCexception;
    public Produto buscar(String codigo) throws bodega.exception.PNEexception;
    public void apagar(String codigo) throws bodega.exception.PNEexception;
    public int quantidade();
    public Produto[] produtosEmEstoque();
    public Produto[] produtosEmFalta();
}
