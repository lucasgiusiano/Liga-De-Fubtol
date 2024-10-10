package sistema;

import dominio.ABB.ABB;
import dominio.ABB.NodoABB;
import dominio.Equipo;
import dominio.Jugador;
import interfaz.*;

public class ImplementacionSistema implements Sistema {
    public ABB<Jugador> jugadores;
    public ABB<Equipo> equipos;

    @Override
    public Retorno inicializarSistema(int maxSucursales) {
        Retorno retorno = null;

        if (maxSucursales > 3) {
            jugadores = new ABB<Jugador>();
            equipos = new ABB<Equipo>();
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
        }
        if (jugadores.existe(j)) {
            return Retorno.error2("Jugador ya existe");
        }
        jugadores.insertar(j);

        return Retorno.ok();
    }

    @Override
    public Retorno buscarJugador(String alias) {

        if (alias == null || alias.isEmpty()) {
            return Retorno.error1("Alias es vacío o null");
        }

        Jugador jugadorEncontrado = this.jugadores.buscar(new Jugador(alias));

        if (jugadorEncontrado == null) {
            return Retorno.error2("No existe un jugador registrado con ese alias");
        }
        String resultado = jugadorEncontrado.getAlias() + ";" + jugadorEncontrado.getNombre() + ";" +
                jugadorEncontrado.getApellido() + ";" + jugadorEncontrado.getCategoria();
        return Retorno.ok(resultado);
    }


    @Override
    public Retorno listarJugadoresAscendente() {
        String resultado = this.jugadores.listarAscendentemente();

        if (resultado.isEmpty()) {
            return Retorno.ok("No hay jugadores registrados.");
        }
        return Retorno.ok(resultado);
    }

    @Override
    public Retorno listarJugadoresPorCategoria(Categoria unaCategoria) {
        String resultado = this.jugadores.listarPorCategoria(unaCategoria);

        if (resultado.isEmpty()) {
            return Retorno.ok("No hay jugadores registrados en esa categoría.");
        }
        return Retorno.ok(resultado);
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

        Jugador jugador = jugadores.buscar(new Jugador(aliasJugador));
        Equipo equipo = equipos.buscar(new Equipo(nombreEquipo));

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
        Equipo equipo = equipos.buscar(new Equipo(nombreEquipo));

        if (nombreEquipo.equals(null) || nombreEquipo.equals("")) {
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
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarConexion(String codigoSucursal1, String codigoSucursal2, int latencia) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno actualizarConexion(String codigoSucursal1, String codigoSucursal2, int latencia) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno analizarSucursal(String codigoSucursal) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno sucursalesParaTorneo(String codigoSucursalAnfitriona, int latenciaLimite) {
        return Retorno.noImplementada();
    }
}
