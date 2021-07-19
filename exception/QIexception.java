package bodega.exception;

public class QIexception extends Exception {
    private String codigo;
    
    public QIexception(String codigo) {
        super("Quantidade de produto insuficiente.");
        this.codigo = codigo;
    }
    
    public String getCodigo() {
        return codigo;
    }
}

