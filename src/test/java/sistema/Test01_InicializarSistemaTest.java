package sistema;

import interfaz.Retorno;
import interfaz.Sistema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test01_InicializarSistemaTest {
    Retorno retorno;


    @Test
    void noDeberiaInicializarSistemaConMaxSucursalesMenorOIgualA3() {
        Sistema sistema = new ImplementacionSistema();

        retorno = sistema.inicializarSistema(3);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.inicializarSistema(2);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.inicializarSistema(-1);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.inicializarSistema(0);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void deberiaInicializarSistema() {
        Sistema sistema = new ImplementacionSistema();

        retorno = sistema.inicializarSistema(4);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());

        retorno = sistema.inicializarSistema(10);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    //Tests Manuel (Hacer un test por cada caso de establecido en el recuadro "ERROR" y "OK" en cada funcionalidad de la letra)

    @Test
    void noDeberiaIngresarJugador() {

    }

    @Test
    void DeberiaIngresarJugador() {

    }

    @Test
    void noDeberiaBuscarJugador() {

    }

    @Test
    void DeberiaBuscarJugador() {

    }

    @Test
    void ListaDeJugadores() {

    }

    @Test
    void ListaDeJugadoresPorCategoria() {

    }


    //Tests Lucas

    //Registrar equipo

    @Test
    void IngresarEquipoConParametrosVacios() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);

        retorno = sistema.registrarEquipo("","");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void IngresarEquipoEquipoYaExisteEnSistema() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);

        sistema.registrarEquipo("Peñarol","Diego Aguirre");
        sistema.registrarEquipo("Nacional","Diego Testas");
        sistema.registrarEquipo("Danubio","Alejandro Apud");
        sistema.registrarEquipo("Racing","Eduardo Espinel");
        sistema.registrarEquipo("Defensor","Álvaro Navarro");

        retorno = sistema.registrarEquipo("Peñarol","Diego Aguirre");

        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void IngresarEquipoCorrectamente() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);

        sistema.registrarEquipo("Peñarol","Diego Aguirre");
        sistema.registrarEquipo("Nacional","Diego Testas");

        retorno = sistema.registrarEquipo("Defensor","Álvaro Navarro");

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    //Agregar jugador a equipo

    @Test
    void AgregarJugadorAEquipoConParametrosVacios() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);

        sistema.registrarEquipo("Peñarol","Diego Aguirre");
        sistema.registrarEquipo("Nacional","Diego Testas");
        sistema.registrarEquipo("Danubio","Alejandro Apud");


    }

    @Test
    void AgregarJugadorAEquipoConEquipoInexistente() {

    }

    @Test
    void AgregarJugadorAEquipoConJugadorInexistente() {

    }

    @Test
    void AgregarJugadorAEquipoConEquipoLleno() {

    }

    @Test
    void AgregarJugadorAEquipoConJugadorNoProfesional() {

    }

    @Test
    void AgregarJugadorAEquipoConJugadorConEquipo() {

    }

    @Test
    void AgregarJugadorAEquipo() {

    }

    //Listar los jugadores de un equipo

    @Test
    void ListarJugadoresDeEquipoConEquipoVacio() {

    }

    @Test
    void ListarJugadoresDeEquipoConEquipoInexistente() {

    }

    @Test
    void ListarJugadoresDeEquipo() {

    }

    //Listar equipos por nombre descendente

    @Test
    void ListarEquipos() {

    }

}
