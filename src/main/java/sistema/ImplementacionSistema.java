package sistema;

import dominio.ABB.ABB;
import dominio.Equipo;
import dominio.Jugador;
import interfaz.*;

public class ImplementacionSistema implements Sistema {
    public ABB<Jugador> jugadores = new ABB<Jugador>();
    public ABB<Equipo> equipos = new ABB<Equipo>();

    @Override
    public Retorno inicializarSistema(int maxSucursales) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarJugador(String alias, String nombre, String apellido, Categoria categoria) {
        Jugador j = new Jugador(alias, nombre, apellido, categoria);
        if(!j.esValido()){
            return Retorno.error1("Algun dato ingresado es null o vacio");
        }
        if(jugadores.existe(j)){
            return Retorno.error2("Jugador ya existe");
        }
        jugadores.insertar(j);

        return Retorno.ok();
    }

    @Override
    public Retorno buscarJugador(String alias) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarJugadoresAscendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarJugadoresPorCategoria(Categoria unaCategoria) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarEquipo(String nombre, String manager) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno agregarJugadorAEquipo(String nombreEquipo, String aliasJugador) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarJugadoresDeEquipo(String nombreEquipo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarEquiposDescendente() {
        return Retorno.noImplementada();
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
