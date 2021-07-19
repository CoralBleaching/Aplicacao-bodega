package bodega.exception;

public class PJCexception extends Exception {
    private String codigo;
    
    public PJCexception(String codigo) {
        super("Produto já cadastrado.");
        this.codigo = codigo;
    }
    
    public String getCodigo() {
        return codigo;
    }
}

