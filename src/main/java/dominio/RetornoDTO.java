package dominio;

public class RetornoDTO {
    Object dato;
    int recorridos;

    public RetornoDTO(Object dato, int recorridos) {
        this.dato = dato;
        this.recorridos = recorridos;
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public int getRecorridos() {
        return recorridos;
    }

    public void setRecorridos(int recorridos) {
        this.recorridos = recorridos;
    }
}
