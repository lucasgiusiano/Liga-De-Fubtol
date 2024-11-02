package dominio.lista;

public class Lista<T extends Comparable> implements ILista<T> {

    private Nodo<T> inicio;
    private Nodo<T> fin;
    private int cantElementos;
    private int cantMaxima;

    public Lista() {
        inicio = null;
        fin = null;
        cantElementos = 0;
        cantMaxima = Integer.MAX_VALUE;
    }

    public Lista(int cantMax) {
        inicio = null;
        fin = null;
        cantElementos = 0;
        cantMaxima = cantMax;
    }

    @Override
    public boolean esVacia() {
        return getInicio() == null;
    }

    @Override
    public void agregarInicio(T n) {

        if (cantElementos < cantMaxima) {
            Nodo<T> nuevo = new Nodo(n);

            nuevo.setSiguiente(getInicio());
            inicio = nuevo;
            cantElementos++;

            if (cantElementos == 1) {
                fin = inicio;
            }
        }
    }

    @Override
    public void agregarFinal(T n) {

        if (esVacia()) {
            agregarInicio(n);
        } else {
            if (cantElementos < cantMaxima) {
                Nodo<T> nuevo = new Nodo<T>(n);
                fin.setSiguiente(nuevo);
                fin = nuevo;
                cantElementos++;
            }
        }
    }

    @Override
    public void borrarInicio() {

        if (!esVacia()) {
            if (cantElementos == 1) {
                fin = null;
                inicio = null;
            } else {
                Nodo<T> aBorrar = getInicio();
                inicio = getInicio().getSiguiente();
                aBorrar.setSiguiente(null);
            }
            cantElementos--;
        }
    }

    @Override
    public void borrarFin() {
        if (!esVacia()) {
            if (getInicio().getSiguiente() == null) { //tiene un solo elemento
                borrarInicio();
            } else {
                Nodo<T> aux = getInicio();

                while (aux.getSiguiente().getSiguiente() != null) {
                    aux = aux.getSiguiente();
                }

                aux.setSiguiente(null);
                fin = aux;
                cantElementos--;
            }
        }
    }

    @Override
    public void vaciar() {
        inicio = null;
        fin = null;
        cantElementos = 0;
    }

    @Override
    public String mostrar() {
        Nodo<T> aux = getInicio();
        String lista = "";
        while (aux != null) {

            lista+=aux.getDato().toString();
            aux = aux.getSiguiente();
        }
        return lista.substring(0,lista.length()-1);
    }

    @Override
    public boolean estaElemento(T n) {

        Nodo<T> aux = getInicio();
        boolean existe = false;

        while (aux != null && !existe) {
            if (aux.getDato().equals(n)) {
                existe = true;
            }
            aux = aux.getSiguiente();
        }
        return existe;

    }

    @Override
    public Nodo<T> obtenerElemento(T n) {
        Nodo<T> aux = getInicio();
        Nodo<T> ret = new Nodo(null);

        while (aux != null && ret.getDato() == null) {
            if (aux.getDato().equals(n)) {
                ret = aux;
            }
            aux = aux.getSiguiente();
        }

        return ret;
    }

    @Override
    public void borrarElemento(T n) {

        if (!esVacia()) {

            if (getInicio().getDato().equals(n)) { //es el primero             
                borrarInicio();
            } else {

                Nodo<T> aux = getInicio();

                while (aux.getSiguiente() != null && aux.getSiguiente().getDato() != n) {
                    aux = aux.getSiguiente();
                }

                if (aux.getSiguiente() != null) {
                    Nodo<T> aBorrar = aux.getSiguiente();
                    aux.setSiguiente(aBorrar.getSiguiente());
                    aBorrar.setSiguiente(null);
                    cantElementos--;
                } else {
                    borrarFin();
                }
            }
        }
    }

    @Override
    public void agregarOrd(T n) {

        Nodo<T> aux = getInicio();

        if (aux == null || aux.getDato().compareTo(n) >= 0) { //Es el primero
            agregarInicio(n);
        } else {

            while (aux.getSiguiente() != null && aux.getSiguiente().getDato().compareTo(n) < 0) {
                aux = aux.getSiguiente();
            }

            if (aux.getSiguiente() == null) {  //Es el último
                agregarFinal(n);
            } else {
                Nodo<T> nuevo = new Nodo(n);
                nuevo.setSiguiente(aux.getSiguiente());
                aux.setSiguiente(nuevo);
                cantElementos++;
            }
        }

    }

    @Override
    public int cantElementos() {
        return cantElementos;
    }

    public Nodo<T> getInicio() {
        return inicio;
    }

    public Nodo<T> getFin() {
        return fin;
    }

}
