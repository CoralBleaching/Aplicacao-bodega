/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bodega;
import java.util.Objects;
import java.util.Vector;
import java.util.Collections;
/**
 *
 * @author Renato Marques
 */
public class EstoqueVector implements IEstoque{
    
    private Vector<Produto> estoque;
    
    public EstoqueVector() {
        estoque = new Vector();
    }
    
    @Override
    public Produto buscar(String codigo) throws bodega.exception.PNEexception {
        for (int i = 0; i < estoque.size(); i++) {
            if (Objects.equals(estoque.get(i).getCodigo(), codigo)) {
                return estoque.get(i);
            }
        }
        throw new bodega.exception.PNEexception(codigo);
    }
    @Override
    public void adicionar(Produto produto) throws bodega.exception.PJCexception{
        try {
            buscar(produto.getCodigo());
            throw new bodega.exception.PJCexception(produto.getCodigo());
        } catch (bodega.exception.PNEexception e) {
            estoque.add(produto);
        }
    }
    @Override
    public void apagar(String codigo) throws bodega.exception.PNEexception {
        try {
            Produto i = buscar(codigo);
            estoque.remove(i);
        } catch (bodega.exception.PNEexception e) {
            throw e;
        }
    }
    @Override
    public int quantidade() {
        return estoque.size();
    }
    private static boolean comparar(boolean estoque, int quantidade) {
        if (estoque) {
            return quantidade > 0;
        } else {
            return quantidade == 0;
        }
    }
    private Produto[] listarProdutos(boolean consultarEstocados) {
        Vector<Produto> lista = new Vector();
        int contador = 0;
        for (int i = 0; i < estoque.size(); i++) {
            if (comparar(consultarEstocados, estoque.get(i).getQuantidade())) {
                lista.add(estoque.get(i));
                contador = contador + 1;
            }
        }
        Collections.sort(lista);
        return lista.toArray(new Produto[contador]);
    }
    @Override
    public Produto[] produtosEmEstoque() {
        return listarProdutos(true);
    }
    @Override
    public Produto[] produtosEmFalta() {
        return listarProdutos(false);
    } 
}