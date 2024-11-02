package dominio;

import java.util.Objects;

public class Sucursal implements Comparable<Sucursal> {
    private String codigo;
    private String nombre;
    private int latencia; // Nuevo atributo

    public Sucursal(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.latencia = 0; // Latencia inicializada a 0
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getLatencia() { // Nuevo getter para latencia
        return latencia;
    }

    public void setLatencia(int latencia) { // Nuevo setter para latencia
        this.latencia = latencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sucursal sucursal)) return false;
        return Objects.equals(codigo, sucursal.codigo);
    }

    @Override
    public int compareTo(Sucursal other) {
        return this.codigo.compareTo(other.getCodigo());
    }
}
