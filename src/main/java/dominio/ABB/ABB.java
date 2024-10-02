package dominio.ABB;

import java.util.ArrayList;
import java.util.List;

public class ABB<T> implements IAbb<T> {

    private NodoABB raiz;

    public ABB() {
    }

    public ABB(T dato) {
        this.raiz = new NodoABB(dato);
    }

    @Override
    public void insertar(T dato) {
        if (this.raiz == null) {
            this.raiz = new NodoABB(dato);
        } else {
            insertarRec(this.raiz, dato);
        }
    }

    private void insertarRec(NodoABB nodo, T dato) {
        if (dato > nodo.getDato()) {
            if (nodo.getDer() == null) {
                nodo.setDer(new NodoABB(dato));
            } else {
                insertarRec(nodo.getDer(), dato);
            }
        } else {
            if (nodo.getIzq() == null) {
                nodo.setIzq(new NodoABB(dato));
            } else {
                insertarRec(nodo.getIzq(), dato);
            }
        }
    }

    @Override
    public void listarAscendentemente() {
        if (this.raiz != null) {
            listarAscendentemente(this.raiz);
        } else {
            System.out.println("ERROR: El ABB está vacío");
        }
    }

    private void listarAscendentemente(NodoABB nodo) {
        if (nodo != null) {
            listarAscendentemente(nodo.getIzq());
            System.out.print(nodo.getDato() + " - ");
            listarAscendentemente(nodo.getDer());
        }
    }

    @Override
    public void listarDescendentemente() {
        if(this.raiz!=null){
            listarDescendentementeRec(this.raiz);
        }else{
            System.out.println("El Arbol esta vacio");
        }
    }
    private void listarDescendentementeRec(NodoABB nodo){
        if(nodo!=null){
            listarDescendentementeRec(nodo.getDer());
            System.out.println(nodo.getDato());
            listarDescendentementeRec(nodo.getIzq());
        }
    }

    @Override
    public boolean existe(T dato) {
        return existe(this.raiz, dato);
    }


    private boolean existe(NodoABB nodo, T dato) {
        if (nodo != null) {
            if (nodo.getDato() == dato) {
                return true;
            } else if (dato > nodo.getDato()) {
                return existe(nodo.getDer(), dato);
            } else {
                return existe(nodo.getIzq(), dato);
            }
        }
        return false;
    }

    @Override
    public int borrarMinimo() {
        if (this.raiz == null) {  //si es nula retorno devuelve Integer.MIN_VALUE como un valor de error que indica que no hay elementos en el árbol.
            return Integer.MIN_VALUE;
        } else if (this.raiz.getIzq() == null) { // si el subArbol izquierdo de la raiz es nulo
            int min_value = this.raiz.getDato();//significa que la raiz es el valor minimo
            this.raiz = this.raiz.getDer(); // aqui lo borra seteando el nodo a la derecha como la nueva raiz
            return min_value;               // por lo tanto borra el minimo
        }
        return borrarMinimo(this.raiz);
    }



    private int borrarMinimo(NodoABB nodo) {  // aca recivo la raiz por ejemplo
        if (nodo.getIzq().getIzq() == null) {//me fijo en el nodo a la izquierda de la raiz si tiene null el izquierdo
            int min_value = nodo.getIzq().getDato();// si es asi significa que el izquierdo de la raiz es el minimo porque
            nodo.setIzq(nodo.getIzq().getDer()); // no tiene izquierdo por lo tanto lo borro cambiandolo por el de su derecha
            return min_value;
        }
        return borrarMinimo(nodo.getIzq());
    }

    @Override
    public int cantidadMayores1(int valor) {
        return cantidadMayoresRec(this.raiz,valor);
    }
    public int cantidadMayoresRec(NodoABB nodo,int valor) {
        if(nodo!=null){
            if(nodo.getDato()>valor){

                return 1 + cantidadMayoresRec(nodo.getDer(),valor)+cantidadMayoresRec(nodo.getIzq(),valor);
            }
            return cantidadMayoresRec(nodo.getDer(),valor);
        }
        return 0;

    }

    @Override
    public List<T> listarAscendentementeDevuelveLista() {
        List<T> listaAscendentes= new ArrayList<>();
        if (this.raiz != null) {
            listarAscendentementeRec(this.raiz,listaAscendentes);
        } else {
            System.out.println("ERROR: El ABB está vacío");
        }
        return listaAscendentes;
    }

    private void listarAscendentementeRec(NodoABB nodo, List<Integer> listaAscendentes) {

        if (nodo != null) {
            listarAscendentementeRec(nodo.getIzq(),listaAscendentes);
            listaAscendentes.add(nodo.getDato());
            listarAscendentementeRec(nodo.getDer(),listaAscendentes);
        }

    }


}
