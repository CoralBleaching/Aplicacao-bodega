/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bodega;
import java.util.Arrays;
/**
 *
 * @author Renato Marques
 */
public class EstoqueArray implements IEstoque{
    
    private Produto[] estoque;
    private int size;
    
    public EstoqueArray() {
        estoque = new Produto[1];
        size = 0;
    }
    
    private static<T> T[] redimensionar(T[] t, int size) {
        t = Arrays.copyOf(t, size);
        return t;
    }
    
    private static<T> void remover(T[] t, int k, int size) {
        for (int i = k; i < size - 1; i++) {
            t[i] = t[i + 1];
        }
    }
    
    @Override
    public Produto buscar(String codigo) throws bodega.exception.PNEexception {
        for (int i = 0; i < size; i++) {
            if (estoque[i].getCodigo().equals(codigo)) {
                return estoque[i];
            }
        }
        throw new bodega.exception.PNEexception(codigo);
    }
    
    @Override
    public void adicionar(Produto produto) throws bodega.exception.PJCexception{
        if (size == estoque.length) {
            estoque = redimensionar(estoque, size*2);
        }
        try {
            buscar(produto.getCodigo());
            throw new bodega.exception.PJCexception(produto.getCodigo());
        } catch (bodega.exception.PNEexception e) {
            estoque[size] = produto;
            size = size + 1;
        }
    }
    
    @Override
    public void apagar(String codigo) throws bodega.exception.PNEexception {
        for (int i = 0; i < size; i++) {
            if (estoque[i].getCodigo().equals(codigo)) {
                remover(estoque, i, size);
                size = size - 1;
                break;
            }
        }
        if (size <= estoque.length/4) {
            estoque = redimensionar(estoque, estoque.length/2);
        }
    }
    
    @Override
    public int quantidade() {
        return size;
    }
    
    private interface Comparador {
        public boolean comparar(int quantidade);
    }
    
    private class ComparadorEstoque implements Comparador {
        @Override
        public boolean comparar(int quantidade) {
            return quantidade > 0;
        }
    }
    
    private class ComparadorFalta implements Comparador {
        @Override
        public boolean comparar(int quantidade) {
            return quantidade == 0;
        }
    }
    
    private Produto[] listarProdutos(int size, Comparador c) {
        Produto[] lista = new Produto[size];
        int contador = 0;
        for (int i = 0; i < size; i++) {
            if (c.comparar(estoque[i].getQuantidade())) {
                lista[contador] = estoque[i];
                contador = contador + 1;
            }
        }
        lista = redimensionar(lista, contador);
        Arrays.sort(lista);
        return lista;
    }
    
    @Override
    public Produto[] produtosEmEstoque() {
        return listarProdutos(size,new ComparadorEstoque());
    }
    
    @Override
    public Produto[] produtosEmFalta() {
        return listarProdutos(size,new ComparadorFalta());
    }
}
    