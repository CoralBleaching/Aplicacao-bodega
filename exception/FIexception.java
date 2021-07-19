package bodega.exception;

public class FIexception extends Exception {
    private String codigo;
    
    public FIexception(String codigo) {
        super("Formato inválido de dados. \n(Exemplos corretos: Preço 3.50 Quantidade 2)");
        this.codigo = codigo;
    }
    
    public String getCodigo() {
        return codigo;
    }
}

