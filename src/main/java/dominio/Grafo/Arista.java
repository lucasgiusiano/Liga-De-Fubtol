package dominio.Grafo;

public class Arista {
    private int latencia;
    private boolean existe;


    public Arista() {
        this.latencia = 0;
        existe = false;
    }

    public Arista(int latencia) {
        this.latencia = latencia;
        this.existe = true;
    }

    public int getLatencia() {
        return latencia;
    }

    public void setLatencia(int latencia) {
        this.latencia = latencia;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
}
