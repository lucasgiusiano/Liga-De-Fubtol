package dominio.ABB;

import dominio.RetornoDTO;

import java.util.List;

public interface IAbb<T extends Comparable<T>> {

    public void insertar(T dato);

    public String listarAscendentemente();

    public String listarDescendentemente();

    public boolean existe(T dato);

    public RetornoDTO buscar(T dato);

    public T borrarMinimo();

    public int getCantidadElementos();
}
