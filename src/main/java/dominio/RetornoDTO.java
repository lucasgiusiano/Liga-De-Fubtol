package dominio;

public class RetornoDTO {
    Object dato;
    int valorEntero;

    public RetornoDTO(Object dato, int recorridos) {
        this.dato = dato;
        this.valorEntero = recorridos;
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public int getValorEntero() {
        return valorEntero;
    }

    public void setValorEntero(int valorEntero) {
        this.valorEntero = valorEntero;
    }
}
