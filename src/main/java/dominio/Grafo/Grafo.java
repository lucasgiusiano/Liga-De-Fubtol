package dominio.Grafo;

import dominio.RetornoDTO;
import dominio.Sucursal;
import dominio.lista.Lista;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class Grafo implements IGrafo {
    private Sucursal[] vertices;
    private Arista[][] matAdy;
    private int cantMaxVertices;
    private int cantActualVertices;
    private boolean esDirigido;

    public boolean isFull() {
        return cantMaxVertices == cantActualVertices;
    }

    public Grafo(int cantMaxDeVertices) {
        this.cantMaxVertices = cantMaxDeVertices;
        this.cantActualVertices = 0;
        this.esDirigido = true;
        this.vertices = new Sucursal[this.cantMaxVertices];
        this.matAdy = new Arista[this.cantMaxVertices][this.cantMaxVertices];

        for (int i = 0; i < this.cantMaxVertices; i++) {
            for (int j = 0; j < this.cantMaxVertices; j++) {
                this.matAdy[i][j] = new Arista();
            }
        }
    }

    public Grafo(int cantMaxDeVertices, boolean isDirigido) {
        this.cantMaxVertices = cantMaxDeVertices;
        this.cantActualVertices = 0;
        this.esDirigido = isDirigido;
        this.vertices = new Sucursal[this.cantMaxVertices];
        this.matAdy = new Arista[this.cantMaxVertices][this.cantMaxVertices];
        if (isDirigido) {
            for (int i = 0; i < this.cantMaxVertices; i++) {
                for (int j = 0; j < this.cantMaxVertices; j++) {
                    this.matAdy[i][j] = new Arista();
                }
            }
        } else {
            for (int i = 0; i < this.cantMaxVertices; i++) {
                for (int j = i; j < this.cantMaxVertices; j++) {
                    this.matAdy[i][j] = new Arista();
                    this.matAdy[j][i] = new Arista();
                }
            }
        }

    }

    private int obtenerPosVacia() {
        for (int i = 0; i < this.cantMaxVertices; i++) {
            if (this.vertices[i] == null) {
                return i;
            }
        }
        return -1;
    }

    private int obtenerPosVertice(Sucursal v) {
        for (int i = 0; i < this.cantMaxVertices; i++) {
            if (this.vertices[i] != null && this.vertices[i].equals(v)) {
                return i;
            }
        }
        return -1;
    }


    @Override
    public void agregarVertice(Sucursal s) {
        if (this.cantActualVertices < this.cantMaxVertices) {
            int posVacia = this.obtenerPosVacia();
            this.vertices[posVacia] = s;
            this.cantActualVertices++;
        }
    }


    @Override
    public void agregarArista(Sucursal vOrigen, Sucursal vDestino, int peso) {
        int posOrigen = this.obtenerPosVertice(vOrigen);
        int posDestino = this.obtenerPosVertice(vDestino);
        if (posOrigen >= 0 && posDestino >= 0) {
            this.matAdy[posOrigen][posDestino] = new Arista(peso);
            this.matAdy[posDestino][posOrigen] = new Arista(peso);
            if (!this.esDirigido) {
                this.matAdy[posDestino][posOrigen].setExiste(true);
                this.matAdy[posDestino][posOrigen].setLatencia(peso);
            }

        }
    }

    @Override
    public void actualizarArista(Sucursal vOrigen, Sucursal vDestino, int latencia) {
        int posOrigen = obtenerPosVertice(vOrigen);
        int posDestino = obtenerPosVertice(vDestino);

        // Verificar que ambos vértices existen
        if (posOrigen == -1 || posDestino == -1) {
            System.out.println("Error: uno o ambos vértices no existen.");
            return;
        }

        // Verificar que la arista existe
        if (!matAdy[posOrigen][posDestino].isExiste()) {
            System.out.println("Error: no existe conexión entre los vértices especificados.");
            return;
        }

        // Actualizar el peso de la arista
        matAdy[posOrigen][posDestino].setLatencia(latencia);

        // Si es no dirigido, actualizar el peso en ambas direcciones
        if (!esDirigido) {
            matAdy[posDestino][posOrigen].setLatencia(latencia);
        }
    }


    @Override
    public void borrarVertice(Sucursal v) {
        int posVertice = this.obtenerPosVertice(v);
        if (posVertice >= 0) {
            this.vertices[posVertice] = null;
            this.cantActualVertices--;
            for (int i = 0; i < this.cantMaxVertices; i++) {
                this.matAdy[posVertice][i] = new Arista();
                this.matAdy[i][posVertice] = new Arista();
//                this.matAdy[posVertice][i].setExiste(false);
//                this.matAdy[i][posVertice].setPeso(0);
            }
        }
    }

    @Override
    public void borrarArista(Sucursal vOrigen, Sucursal vDestino) {
        int posOrigen = this.obtenerPosVertice(vOrigen);
        int posDestino = this.obtenerPosVertice(vDestino);
        this.matAdy[posOrigen][posDestino] = new Arista();
        if (!this.esDirigido) {
            this.matAdy[posDestino][posOrigen] = new Arista();
        }
    }

    @Override
    public boolean esVacio() {
        return this.cantActualVertices == 0;
    }

    @Override
    public Lista verticesAdyacentes(Sucursal v) {
        Lista<Sucursal> adyacentes = new Lista<>();
        int posVert = this.obtenerPosVertice(v);
        for (int i = 0; i < this.cantMaxVertices; i++) {
            if (this.matAdy[posVert][i].isExiste()) {
                adyacentes.agregarFinal(this.vertices[i]);
            }
        }
        return adyacentes;
    }

    @Override
    public boolean sonAdyacentes(Sucursal v1, Sucursal v2) {
        int posOrigen = this.obtenerPosVertice(v1);
        int posDestino = this.obtenerPosVertice(v2);

        return this.matAdy[posOrigen][posDestino].isExiste();
    }

    @Override
    public boolean existeVertice(Sucursal v) {
        return this.obtenerPosVertice(v) >= 0;
    }

    @Override
    public Sucursal buscarVertice(Sucursal v) {
        return vertices[this.obtenerPosVertice(v)];
    }

    @Override
    public String esPuntoDeArticulacion(Sucursal s) {
        String retorno = "";

        if (dfs(s)) {
            retorno = "SI";
        } else {
            retorno = "NO";
        }

        return retorno;
    }

    @Override
    public boolean dfs(Sucursal s) {
        int pos = this.obtenerPosVertice(s);
        boolean[] visitados = new boolean[this.cantMaxVertices];
        int[] descubrimiento = new int[this.cantMaxVertices];
        int[] low = new int[this.cantMaxVertices];
        int[] padre = new int[this.cantMaxVertices];
        Arrays.fill(padre, -1);  // Inicializar todos los padres como -1
        boolean[] esArticulacion = new boolean[this.cantMaxVertices];
        AtomicInteger tiempo = new AtomicInteger(0);  // Usar AtomicInteger para que el tiempo sea mutable

        dfsRec(pos, visitados, descubrimiento, low, padre, esArticulacion, tiempo);

        return esArticulacion[pos];
    }

    private void dfsRec(int pos, boolean[] visitados, int[] descubrimiento, int[] low, int[] padre, boolean[] esArticulacion, AtomicInteger tiempo) {
        visitados[pos] = true;
        descubrimiento[pos] = low[pos] = tiempo.incrementAndGet();  // Incrementa y asigna el tiempo
        int hijos = 0;

        for (int i = 0; i < this.cantMaxVertices; i++) {
            if (this.matAdy[pos][i].isExiste()) {
                if (!visitados[i]) { // Si no ha sido visitado
                    padre[i] = pos;
                    hijos++;
                    dfsRec(i, visitados, descubrimiento, low, padre, esArticulacion, tiempo);

                    // Verifica la condición para ser punto de articulación
                    low[pos] = Math.min(low[pos], low[i]);

                    if (padre[pos] == -1 && hijos > 1) {
                        esArticulacion[pos] = true;
                    }
                    if (padre[pos] != -1 && low[i] >= descubrimiento[pos]) {
                        esArticulacion[pos] = true;
                    }
                } else if (i != padre[pos]) { // Actualiza low si el nodo ha sido visitado y no es el padre
                    low[pos] = Math.min(low[pos], descubrimiento[i]);
                }
            }
        }
    }


    public RetornoDTO dijkstra(String codigoSucursalAnfitriona, int latenciaLimite) {
        int posInicial = this.obtenerPosVertice(new Sucursal(codigoSucursalAnfitriona, ""));
        Lista<Sucursal> sucursalesDentroDelLimite = new Lista<Sucursal>();
        int maximaLatenciaAlcanzada = 0;

        int[] costos = new int[this.cantMaxVertices];
        boolean[] visitados = new boolean[this.cantMaxVertices];

        for (int i = 0; i < this.cantMaxVertices; i++) {
            visitados[i] = false;
            costos[i] = Integer.MAX_VALUE;
        }

        costos[posInicial] = 0;
        int posVerticeActualVisitado = posInicial;

        while (posVerticeActualVisitado > -1) {
            actualizarAdyacentesNoVisitados(posVerticeActualVisitado, costos, visitados, latenciaLimite);
            posVerticeActualVisitado = obtenerPosVerticeDeCostoMinimoNoVisitado(costos, visitados, latenciaLimite);
        }

        sucursalesDentroDelLimite.agregarOrd(this.vertices[posInicial]);

        for (int i = 0; i < costos.length; i++) {
            if (costos[i] <= latenciaLimite && i != posInicial) {
                if (costos[i] > maximaLatenciaAlcanzada) {
                    maximaLatenciaAlcanzada = costos[i];
                }
                sucursalesDentroDelLimite.agregarOrd(this.vertices[i]);
            }
        }

        return new RetornoDTO(sucursalesDentroDelLimite, maximaLatenciaAlcanzada);
    }

    private void actualizarAdyacentesNoVisitados(int posVertice, int[] costos, boolean[] visitados, int latenciaLimite) {
        for (int i = 0; i < this.cantMaxVertices; i++) {
            if (!visitados[i] && this.matAdy[posVertice][i].isExiste()) {
                int nuevoCosto = costos[posVertice] + this.matAdy[posVertice][i].getLatencia();
                if (nuevoCosto < costos[i] && nuevoCosto <= latenciaLimite) {
                    costos[i] = nuevoCosto;
                }
            }
        }
        visitados[posVertice] = true;
    }

    private int obtenerPosVerticeDeCostoMinimoNoVisitado(int[] costos, boolean[] visitados, int latenciaLimite) {
        int minCosto = Integer.MAX_VALUE;
        int posMin = -1;

        for (int i = 0; i < costos.length; i++) {
            if (!visitados[i] && costos[i] < minCosto && costos[i] <= latenciaLimite) {
                minCosto = costos[i];
                posMin = i;
            }
        }
        return posMin;
    }
}
