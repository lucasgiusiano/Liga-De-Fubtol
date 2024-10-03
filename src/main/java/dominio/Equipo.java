package dominio;

import dominio.ABB.ABB;

import java.util.Objects;

public class Equipo implements Comparable<Equipo> {
    private String nombre;
    private String manager;
    private ABB<Jugador> jugadores;

    public Equipo(String nombre, String manager) {
        this.nombre = nombre;
        this.manager = manager;
        this.jugadores = new ABB<Jugador>();
    }

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.manager = "";
        this.jugadores = new ABB<Jugador>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipo equipo = (Equipo) o;
        return Objects.equals(nombre, equipo.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    @Override
    public int compareTo(Equipo o) {
        return this.nombre.compareTo(o.getNombre());
    }

    public boolean esValido() {
        return !nombre.equals("") && !nombre.equals(null) && !manager.equals("") && !manager.equals(null);
    }

    public int getCantidadJugadores() {
        return jugadores.getCantidadElementos();
    }

    public ABB<Jugador> getJugadores() {
        return jugadores;
    }

    public void agregarJugador(Jugador jugador) {
        if (getCantidadJugadores() < 5) {
            jugadores.insertar(jugador);
        }
    }

    @Override
    public String toString() {
        return nombre + ";" + manager + ";" + getCantidadJugadores();
    }
}
