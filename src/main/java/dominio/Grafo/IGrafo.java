package dominio.Grafo;

import dominio.RetornoDTO;
import dominio.Sucursal;
import dominio.lista.Lista;

public interface IGrafo {


    public void agregarVertice(Sucursal v);

    public void agregarArista(Sucursal vOrigen,Sucursal vDestino,int peso);

    public void borrarVertice(Sucursal v);

    public void borrarArista(Sucursal vOrigen,Sucursal vDestino);

    void actualizarArista(Sucursal vOrigen,Sucursal vDestino,int peso);

    public boolean esVacio();

    public Lista verticesAdyacentes(Sucursal v);

    public boolean sonAdyacentes(Sucursal v1, Sucursal v2);

    public boolean existeVertice (Sucursal v);

    Sucursal buscarVertice(Sucursal s);

    String esPuntoDeArticulacion(Sucursal s);

    boolean dfs(Sucursal v);

    public RetornoDTO dijkstra(String codigoSucursalAnfitriona, int latenciaLimite);
}
