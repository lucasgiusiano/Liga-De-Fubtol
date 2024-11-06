package dominio.ABB;

import dominio.Jugador;
import dominio.RetornoDTO;
import interfaz.Categoria;

import java.util.ArrayList;
import java.util.List;

public class ABB<T extends Comparable<T>> implements IAbb<T> {

    private NodoABB raiz;
    private int cantidadElementos;

    public ABB() {
    }

    public ABB(T dato) {
        this.raiz = new NodoABB(dato);
    }

    @Override
    public int getCantidadElementos() {
        return cantidadElementos;
    }

    public NodoABB<T> getRaiz() {
        return this.raiz;
    }

    @Override
    public void insertar(T dato) {
        if (this.raiz == null) {
            this.raiz = new NodoABB(dato);
            cantidadElementos++;
        } else {
            insertarRec(this.raiz, dato);
        }
        //balancear();
    }

    private void insertarRec(NodoABB nodo, T dato) {
        if (dato.compareTo((T) nodo.getDato()) < 0) {
            if (nodo.getIzq() == null) {
                nodo.setIzq(new NodoABB(dato));
                cantidadElementos++;
            } else {
                insertarRec(nodo.getIzq(), dato);
            }
        } else {
            if (nodo.getDer() == null) {
                nodo.setDer(new NodoABB(dato));
                cantidadElementos++;
            } else {
                insertarRec(nodo.getDer(), dato);
            }
        }
    }

    public void balancear() {
        // Obtener los datos del árbol en una lista ordenada
        List<T> elementos = new ArrayList<>();
        obtenerElementosEnOrden(raiz, elementos);

        // Reconstruir el árbol equilibrado
        this.raiz = construirArbolBalanceado(elementos, 0, elementos.size() - 1);
    }

    private void obtenerElementosEnOrden(NodoABB<T> nodo, List<T> elementos) {
        if (nodo != null) {
            obtenerElementosEnOrden(nodo.getIzq(), elementos); // Subárbol izquierdo
            elementos.add(nodo.getDato()); // Agregar el dato actual
            obtenerElementosEnOrden(nodo.getDer(), elementos); // Subárbol derecho
        }
    }

    private NodoABB<T> construirArbolBalanceado(List<T> elementos, int inicio, int fin) {
        if (inicio > fin) {
            return null;
        }

        int medio = (inicio + fin) / 2;
        NodoABB<T> nodo = new NodoABB<>(elementos.get(medio));

        nodo.setIzq(construirArbolBalanceado(elementos, inicio, medio - 1));
        nodo.setDer(construirArbolBalanceado(elementos, medio + 1, fin));

        return nodo;
    }

    @Override
    public String listarAscendentemente() {
        String resultado = listarAscendentementeRec(this.raiz, "");

        if (!resultado.isEmpty() && resultado.endsWith("|")) {
            resultado = resultado.substring(0, resultado.length() - 1);
        }

        return resultado;
    }

    private String listarAscendentementeRec(NodoABB nodo, String resultado) {
        if (nodo != null) {
            resultado = listarAscendentementeRec(nodo.getIzq(), resultado);
            resultado += nodo.getDato().toString() + "|";
            resultado = listarAscendentementeRec(nodo.getDer(), resultado);
        }
        return resultado;
    }

    @Override
    public String listarDescendentemente() {
        String resultado = listarDescendentementeRec(this.raiz, "");

        if (!resultado.isEmpty() && resultado.endsWith("|")) {
            resultado = resultado.substring(0, resultado.length() - 1);
        }

        return resultado;
    }

    private String listarDescendentementeRec(NodoABB nodo, String resultado) {
        if (nodo != null) {
            resultado = listarDescendentementeRec(nodo.getDer(), resultado);
            resultado += nodo.getDato().toString() + "|";
            resultado = listarDescendentementeRec(nodo.getIzq(), resultado);
        }
        return resultado;
    }


    @Override
    public boolean existe(T dato) {
        return existe(this.raiz, dato);
    }

    private boolean existe(NodoABB nodo, T dato) {
        if (nodo != null) {
            if (dato.equals(nodo.getDato())) {
                return true;
            } else if (dato.compareTo((T) nodo.getDato()) > 0) {
                return existe(nodo.getDer(), dato);
            } else {
                return existe(nodo.getIzq(), dato);
            }
        }
        return false;
    }

    @Override
    public RetornoDTO buscar(T dato) {
        return buscarRec(this.raiz, dato, 0);
    }

    private RetornoDTO buscarRec(NodoABB nodo, T dato, int recorridos) {
        if (nodo != null) {
            if (dato.equals(nodo.getDato())) {

                return new RetornoDTO(nodo.getDato(), recorridos + 1);  // No es necesario el casting
            } else if (dato.compareTo((T) nodo.getDato()) > 0) {
                return buscarRec(nodo.getDer(), dato, recorridos + 1);
            } else {
                return buscarRec(nodo.getIzq(), dato, recorridos + 1);
            }
        }
        return new RetornoDTO(null, 0);
    }

    @Override
    public T borrarMinimo() {
        if (this.raiz == null) {  //si es nula retorno devuelve Integer.MIN_VALUE como un valor de error que indica que no hay elementos en el árbol.
            return null;
        } else if (this.raiz.getIzq() == null) { // si el subArbol izquierdo de la raiz es nulo
            T min_value = (T) this.raiz.getDato();  //significa que la raiz es el valor minimo
            this.raiz = this.raiz.getDer(); // aqui lo borra seteando el nodo a la derecha como la nueva raiz
            return min_value;               // por lo tanto borra el minimo
        }
        return borrarMinimoRec(this.raiz);
    }

    private T borrarMinimoRec(NodoABB nodo) {  // aca recivo la raiz por ejemplo
        if (nodo.getIzq().getIzq() == null) {//me fijo en el nodo a la izquierda de la raiz si tiene null el izquierdo
            T min_value = (T) nodo.getIzq().getDato();// si es asi significa que el izquierdo de la raiz es el minimo porque
            nodo.setIzq(nodo.getIzq().getDer()); // no tiene izquierdo por lo tanto lo borro cambiandolo por el de su derecha
            return min_value;
        }
        return borrarMinimoRec(nodo.getIzq());
    }

    public String listarPorCategoria(Categoria categoria) {
        return listarPorCategoriaRec(this.raiz, categoria, "");
    }

    private String listarPorCategoriaRec(NodoABB nodo, Categoria categoria, String resultado) {
        if (nodo != null) {
            resultado = listarPorCategoriaRec(nodo.getIzq(), categoria, resultado);

            Jugador jugador = (Jugador) nodo.getDato();
            if (categoria.equals(jugador.getCategoria())) {
                if (!resultado.isEmpty()) {
                    resultado += "|";
                }
                resultado += jugador.toString();
            }
            resultado = listarPorCategoriaRec(nodo.getDer(), categoria, resultado);
        }
        return resultado;
    }


}
