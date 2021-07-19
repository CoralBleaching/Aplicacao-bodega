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
public class ProdutoPerecivel extends Produto {
    private String validade;
    public ProdutoPerecivel(String codigo) {
        super(codigo);
    }
    public void setValidade(String validade) {
        this.validade = validade;
    }
    public String getValidade() {
        return validade;
    }
}
