/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bodega.GUI;

import bodega.Bodega;
import bodega.Produto;
import javax.swing.DefaultListModel;

/**
 *
 * @author Renato Marques
 */
public class JListaBodegaFalta<E> extends javax.swing.JList implements JListaBodega {
    
    @Override
    public void setListaProdutos(Bodega bodega) {
        DefaultListModel<Produto> mdl = new DefaultListModel();
        for (Produto produto : bodega.listarProdutosEmFalta()) {
            mdl.addElement(produto);
        }
        setModel(mdl);
    }
}
