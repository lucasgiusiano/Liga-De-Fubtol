package dominio.ABB;

import java.util.List;

public interface IAbb<T> {

    public void insertar(T dato);

    public void listarAscendentemente();

    public void listarDescendentemente();

    public boolean existe(T dato);

    public T borrarMinimo();

    public List<T> listarAscendentementeDevuelveLista();

}
