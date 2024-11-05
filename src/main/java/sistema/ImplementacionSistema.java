package sistema;

import dominio.ABB.ABB;
import dominio.ABB.NodoABB;
import dominio.Equipo;
import dominio.Grafo.Arista;
import dominio.Grafo.Grafo;
import dominio.Grafo.IGrafo;
import dominio.Jugador;
import dominio.RetornoDTO;
import dominio.Sucursal;
import dominio.lista.Lista;
import dominio.lista.Nodo;
import interfaz.*;

import java.sql.SQLOutput;

public class ImplementacionSistema implements Sistema {
    public ABB<Jugador> jugadores;
    public ABB<Equipo> equipos;
    public Grafo sucursales;

    @Override
    public Retorno inicializarSistema(int maxSucursales) {
        Retorno retorno = null;

        if (maxSucursales > 3) {
            jugadores = new ABB<Jugador>();
            equipos = new ABB<Equipo>();
            sucursales = new Grafo(maxSucursales);
            retorno = Retorno.ok();
        } else {
            retorno = Retorno.error1("El sistema debe contar con al menos 3 sucursales");
        }


        return retorno;
    }

    @Override
    public Retorno registrarJugador(String alias, String nombre, String apellido, Categoria categoria) {
        Jugador j = new Jugador(alias, nombre, apellido, categoria);
        if (!j.esValido()) {
            return Retorno.error1("Algun dato ingresado es null o vacio");
        } else if (jugadores.existe(j)) {
            return Retorno.error2("Jugador ya existe");
        } else {
            jugadores.insertar(j);
        }


        return Retorno.ok();
    }

    @Override
    public Retorno buscarJugador(String alias) {

        if (alias == null || alias.isEmpty()) {
            return Retorno.error1("Alias es vacío o null");
        }

        RetornoDTO dto = this.jugadores.buscar(new Jugador(alias));
        Jugador jugadorEncontrado = (Jugador) dto.getDato();
        int recorridosHechos = dto.getRecorridos();

        if (jugadorEncontrado == null) {
            return Retorno.error2("No existe un jugador registrado con ese alias");
        }
        String resultado = jugadorEncontrado.getAlias() + ";" + jugadorEncontrado.getNombre() + ";" +
                jugadorEncontrado.getApellido() + ";" + jugadorEncontrado.getCategoria();

        return Retorno.ok(recorridosHechos, resultado);
    }


    @Override
    public Retorno listarJugadoresAscendente() {
        String resultado = this.jugadores.listarAscendentemente();

        return Retorno.ok(resultado);
    }

    @Override
    public Retorno listarJugadoresPorCategoria(Categoria unaCategoria) {
        return Retorno.ok(this.jugadores.listarPorCategoria(unaCategoria));
    }

    @Override
    public Retorno registrarEquipo(String nombre, String manager) {
        Retorno retorno = null;

        Equipo nuevo = new Equipo(nombre, manager);

        if (!nuevo.esValido()) {
            retorno = Retorno.error1("Alguno de los parámetros fue null o vacío");
        } else if (equipos.existe(nuevo)) {
            retorno = Retorno.error2("Ya existe un equipo con ese nombre");
        } else {
            equipos.insertar(nuevo);
            retorno = Retorno.ok("Equipo ingresado con éxito");
        }

        return retorno;
    }

    @Override
    public Retorno agregarJugadorAEquipo(String nombreEquipo, String aliasJugador) {
        Retorno retorno = null;

        RetornoDTO dtoJugador = jugadores.buscar(new Jugador(aliasJugador));
        Jugador jugador = (Jugador) dtoJugador.getDato();

        RetornoDTO dtoEquipo = equipos.buscar(new Equipo(nombreEquipo));
        Equipo equipo = (Equipo) dtoEquipo.getDato();

        if (nombreEquipo == null || nombreEquipo.equals("") || aliasJugador == null || aliasJugador.equals("")) {
            retorno = Retorno.error1("Los campos ingresados fueron nulos o vacíos");
        } else if (equipo == null) {
            retorno = Retorno.error2("El equipo no existe");
        } else if (jugador == null) {
            retorno = Retorno.error3("El jugador no existe");
        } else if (equipo.getCantidadJugadores() == 5) {
            retorno = Retorno.error4("El equipo ya tiene cinco integrantes");
        } else if (jugador.getCategoria() != Categoria.PROFESIONAL) {
            retorno = Retorno.error5("El jugador no es profesional");
        } else if (jugadorConEquipo(jugador)) {
            retorno = Retorno.error6("El jugador ya pertenece a otro equipo");
        } else {
            equipo.agregarJugador(jugador);
            retorno = Retorno.ok();
        }

        return retorno;
    }

    public boolean jugadorConEquipo(Jugador jugador) {
        return jugadorConEquipoRec(equipos.getRaiz(), jugador);
    }

    private boolean jugadorConEquipoRec(NodoABB nodo, Jugador jugador) {
        if (nodo != null) {
            Equipo actual = (Equipo) nodo.getDato();
            if (actual.getJugadores().existe(jugador)) {
                return true;
            }

            return jugadorConEquipoRec(nodo.getIzq(), jugador) || jugadorConEquipoRec(nodo.getDer(), jugador);
        }
        return false;
    }

    @Override
    public Retorno listarJugadoresDeEquipo(String nombreEquipo) {
        Retorno retorno = null;

        RetornoDTO dto = equipos.buscar(new Equipo(nombreEquipo));
        Equipo equipo = (Equipo) dto.getDato();

        if (nombreEquipo == null || nombreEquipo.isEmpty()) {
            retorno = Retorno.error1("El nombre del equipo fue nulo o vacío");
        } else if (equipo == null) {
            retorno = Retorno.error2("El equipo no existe");
        } else {
            String listaJugadores = equipo.getJugadores().listarAscendentemente();
            retorno = Retorno.ok(listaJugadores);
        }

        return retorno;
    }

    @Override
    public Retorno listarEquiposDescendente() {
        return Retorno.ok(equipos.listarDescendentemente());
    }

    @Override
    public Retorno registrarSucursal(String codigo, String nombre) {
        Retorno retorno = Retorno.ok();
        Sucursal nueva = new Sucursal(codigo, nombre);

        if (sucursales.isFull()) {
            retorno = Retorno.error1("Se alcanzo la maxima cantidad de sucursales en el sistema");
        } else if (codigo == null || codigo.isEmpty() || nombre == null || nombre.isEmpty()) {
            retorno = Retorno.error2("El nombre o el codigo fue vacío o nulo");
        } else if (sucursales.existeVertice(nueva)) {
            retorno = Retorno.error3("Ya existe una sucursal con ese codigo");
        } else {
            sucursales.agregarVertice(nueva);
        }

        return retorno;
    }

    @Override
    public Retorno registrarConexion(String codigoSucursal1, String codigoSucursal2, int latencia) {
        Arista nueva = new Arista();
        Retorno retorno = Retorno.ok();

        Sucursal sucursal1 = new Sucursal(codigoSucursal1, "");
        Sucursal sucursal2 = new Sucursal(codigoSucursal2, "");

        if (latencia < 0) {
            return Retorno.error1("La latencia debe ser mayor o igual que cero");
        } else if (codigoSucursal1 == null || codigoSucursal1.isEmpty() || codigoSucursal2 == null || codigoSucursal2.isEmpty()) {
            return Retorno.error2("Alguno de los parámetros fue vacío o nulo");
        } else if (!sucursales.existeVertice(sucursal1) || !sucursales.existeVertice(sucursal2)) {
            return Retorno.error3("Alguna de las sucursales indicadas, no existe");
        } else if (sucursales.sonAdyacentes(sucursal1, sucursal2)) {
            return Retorno.error4("Ya existe una conexion entre las dos sucursales");
        }

        Sucursal s1 = sucursales.buscarVertice(sucursal1);
        Sucursal s2 = sucursales.buscarVertice(sucursal2);

        sucursales.agregarArista(s1, s2, latencia);

        return retorno;
    }

    @Override
    public Retorno actualizarConexion(String codigoSucursal1, String codigoSucursal2, int latencia) {
        Retorno retorno = Retorno.ok();

        // Verifica si alguno de los parámetros es null o vacío
        if (codigoSucursal1 == null || codigoSucursal2 == null || codigoSucursal1.isEmpty() || codigoSucursal2.isEmpty()) {
            retorno = Retorno.error2("Alguno de los parámetros fue vacío o nulo");
        } else if (latencia < 0) {
            retorno = Retorno.error1("La latencia debe ser mayor o igual que cero");
        } else {
            Sucursal sucursal1 = new Sucursal(codigoSucursal1, "");
            Sucursal sucursal2 = new Sucursal(codigoSucursal2, "");

            if (!sucursales.existeVertice(sucursal1) || !sucursales.existeVertice(sucursal2)) {
                retorno = Retorno.error3("Alguna de las sucursales indicadas, no existe");
            } else if (!sucursales.sonAdyacentes(sucursal1, sucursal2)) {
                retorno = Retorno.error4("Ya existe una conexion entre las dos sucursales");
            } else {
                Sucursal s1 = sucursales.buscarVertice(sucursal1);
                Sucursal s2 = sucursales.buscarVertice(sucursal2);

                sucursales.actualizarArista(sucursal1, sucursal2, latencia);
            }
        }

        return retorno;
    }

    @Override
    public Retorno analizarSucursal(String codigoSucursal) {
        Retorno retorno = Retorno.ok();

        if (codigoSucursal == null || codigoSucursal.isEmpty()) {
            retorno = Retorno.error1("El código de la sucursal fue nulo o vacío");
            return retorno;
        }

        Sucursal sAux = new Sucursal(codigoSucursal, "");

        if (!sucursales.existeVertice(sAux)) {
            retorno = Retorno.error2("No existe la sucursal");
            return retorno;
        }


        String esCritica = sucursales.esPuntoDeArticulacion(sAux);
        retorno = Retorno.ok(esCritica);

        return retorno;
    }

    @Override
    public Retorno sucursalesParaTorneo(String codigoSucursalAnfitriona, int latenciaLimite) {
        // Validación de los parámetros de entrada
        if (codigoSucursalAnfitriona == null || codigoSucursalAnfitriona.isEmpty()) {
            return Retorno.error1("El código de la sucursal anfitriona es vacío o nulo");
        }
        if (!sucursales.existeVertice(new Sucursal(codigoSucursalAnfitriona, ""))) {
            return Retorno.error2("La sucursal anfitriona no existe");
        }
        if (latenciaLimite <= 0) {
            return Retorno.error3("La latencia debe ser mayor a cero");
        }

        // Utilización del algoritmo de Dijkstra para obtener las sucursales dentro del límite de latencia
        Lista<Sucursal> sucursalesDentroDelLimite = sucursales.dijkstra(codigoSucursalAnfitriona, latenciaLimite);

        // Si no se encontraron sucursales dentro del límite de latencia
        if (sucursalesDentroDelLimite.esVacia()) {
            return Retorno.error4("No existen sucursales dentro del límite de latencia");
        }

        return Retorno.ok(formarStringSucursalesEnLatencia(sucursalesDentroDelLimite));
    }

    private String formarStringSucursalesEnLatencia(Lista<Sucursal> sucursalesDentroDelLimite) {
        StringBuilder retornoString = new StringBuilder();

        Nodo<Sucursal> nodoSucursal = sucursalesDentroDelLimite.getInicio();
        for (int i = 0; i < sucursalesDentroDelLimite.cantElementos(); i++) {
            if (retornoString.length() > 0) {
                retornoString.append("|");
            }

            Sucursal sucursalActual = nodoSucursal.getDato();

            retornoString.append(sucursalActual.getCodigo()).append(";").append(sucursalActual.getNombre());

            if (nodoSucursal.getSiguiente() != null) {
                nodoSucursal = nodoSucursal.getSiguiente();
            }
        }

        return retornoString.toString();
    }
}
