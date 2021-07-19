package bodega.exception;

public class PNEexception extends Exception {
    private String codigo;
    
    public PNEexception(String codigo) {
        super("Produto não encontrado.");
        this.codigo = codigo;
    }
    
    public String getCodigo() {
        return codigo;
    }
}

