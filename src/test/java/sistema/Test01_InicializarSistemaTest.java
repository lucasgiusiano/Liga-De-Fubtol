package sistema;

import interfaz.Categoria;
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

        retorno = sistema.registrarEquipo("", "");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void IngresarEquipoEquipoYaExisteEnSistema() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);

        sistema.registrarEquipo("Peñarol", "Diego Aguirre");
        sistema.registrarEquipo("Nacional", "Diego Testas");
        sistema.registrarEquipo("Danubio", "Alejandro Apud");
        sistema.registrarEquipo("Racing", "Eduardo Espinel");
        sistema.registrarEquipo("Defensor", "Álvaro Navarro");

        retorno = sistema.registrarEquipo("Peñarol", "Diego Aguirre");

        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void IngresarEquipoCorrectamente() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);

        sistema.registrarEquipo("Peñarol", "Diego Aguirre");
        sistema.registrarEquipo("Nacional", "Diego Testas");

        retorno = sistema.registrarEquipo("Defensor", "Álvaro Navarro");

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    //Agregar jugador a equipo

    @Test
    void AgregarJugadorAEquipoConParametrosVacios() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);

        retorno = sistema.agregarJugadorAEquipo("", "");

        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void AgregarJugadorAEquipoInexistente() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);

        sistema.registrarEquipo("Peñarol", "Diego Aguirre");
        sistema.registrarEquipo("Nacional", "Diego Testas");
        sistema.registrarEquipo("Danubio", "Alejandro Apud");

        sistema.registrarJugador("Forlan", "Diego", "Forlan", Categoria.PROFESIONAL);
        sistema.registrarJugador("Suarez", "Luis", "Suarez", Categoria.PROFESIONAL);
        sistema.registrarJugador("El Morro", "Santiago", "Garcia", Categoria.ESTANDARD);
        sistema.registrarJugador("La Pantera", "Darwin", "Núñez", Categoria.PROFESIONAL);
        sistema.registrarJugador("Facu", "Facundo", "Torrez", Categoria.ESTANDARD);
        sistema.registrarJugador("Pellistri", "Facundo", "Pellistri", Categoria.PROFESIONAL);
        sistema.registrarJugador("Nahitan", "Nahitan", "Nández", Categoria.PROFESIONAL);
        sistema.registrarJugador("Federico", "Valverde", "Valverde", Categoria.PROFESIONAL);

        retorno = sistema.agregarJugadorAEquipo("Defensor", "Forlan");

        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void AgregarJugadorAEquipoConJugadorInexistente() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);

        sistema.registrarEquipo("Peñarol", "Diego Aguirre");
        sistema.registrarEquipo("Nacional", "Diego Testas");
        sistema.registrarEquipo("Danubio", "Alejandro Apud");

        sistema.registrarJugador("Forlan", "Diego", "Forlan", Categoria.PROFESIONAL);
        sistema.registrarJugador("Suarez", "Luis", "Suarez", Categoria.PROFESIONAL);
        sistema.registrarJugador("El Morro", "Santiago", "Garcia", Categoria.ESTANDARD);

        retorno = sistema.agregarJugadorAEquipo("Peñarol", "Lucas");

        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

    @Test
    void AgregarJugadorAEquipoConEquipoLleno() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);

        sistema.registrarEquipo("Peñarol", "Diego Aguirre");
        sistema.registrarEquipo("Nacional", "Diego Testas");
        sistema.registrarEquipo("Danubio", "Alejandro Apud");

        sistema.registrarJugador("Forlan", "Diego", "Forlan", Categoria.PROFESIONAL);
        sistema.registrarJugador("Suarez", "Luis", "Suarez", Categoria.PROFESIONAL);
        sistema.registrarJugador("El Morro", "Santiago", "Garcia", Categoria.ESTANDARD);
        sistema.registrarJugador("La Pantera", "Darwin", "Núñez", Categoria.PROFESIONAL);
        sistema.registrarJugador("Facu", "Facundo", "Torrez", Categoria.ESTANDARD);
        sistema.registrarJugador("Pellistri", "Facundo", "Pellistri", Categoria.PROFESIONAL);
        sistema.registrarJugador("Nahitan", "Nahitan", "Nández", Categoria.PROFESIONAL);
        sistema.registrarJugador("Federico", "Federico", "Valverde", Categoria.PROFESIONAL);

        sistema.agregarJugadorAEquipo("Peñarol", "Forlan");
        sistema.agregarJugadorAEquipo("Peñarol", "Suarez");
        sistema.agregarJugadorAEquipo("Peñarol", "La Pantera");
        sistema.agregarJugadorAEquipo("Peñarol", "Pellistri");
        sistema.agregarJugadorAEquipo("Peñarol", "Federico");

        retorno = sistema.agregarJugadorAEquipo("Peñarol", "Nahitan");

        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());
    }

    @Test
    void AgregarJugadorAEquipoConJugadorNoProfesional() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);

        sistema.registrarEquipo("Peñarol", "Diego Aguirre");
        sistema.registrarEquipo("Nacional", "Diego Testas");
        sistema.registrarEquipo("Danubio", "Alejandro Apud");

        sistema.registrarJugador("Forlan", "Diego", "Forlan", Categoria.PROFESIONAL);
        sistema.registrarJugador("Suarez", "Luis", "Suarez", Categoria.PROFESIONAL);
        sistema.registrarJugador("El Morro", "Santiago", "Garcia", Categoria.ESTANDARD);
        sistema.registrarJugador("La Pantera", "Darwin", "Núñez", Categoria.PROFESIONAL);
        sistema.registrarJugador("Facu", "Facundo", "Torrez", Categoria.ESTANDARD);

        retorno = sistema.agregarJugadorAEquipo("Peñarol", "El Morro");

        assertEquals(Retorno.Resultado.ERROR_5, retorno.getResultado());
    }

    @Test
    void AgregarJugadorAEquipoConJugadorConEquipo() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);

        sistema.registrarEquipo("Peñarol", "Diego Aguirre");
        sistema.registrarEquipo("Nacional", "Diego Testas");
        sistema.registrarEquipo("Danubio", "Alejandro Apud");

        sistema.registrarJugador("Forlan", "Diego", "Forlan", Categoria.PROFESIONAL);
        sistema.registrarJugador("Suarez", "Luis", "Suarez", Categoria.PROFESIONAL);
        sistema.registrarJugador("El Morro", "Santiago", "Garcia", Categoria.ESTANDARD);
        sistema.registrarJugador("La Pantera", "Darwin", "Núñez", Categoria.PROFESIONAL);
        sistema.registrarJugador("Facu", "Facundo", "Torrez", Categoria.ESTANDARD);
        sistema.registrarJugador("Pellistri", "Facundo", "Pellistri", Categoria.PROFESIONAL);
        sistema.registrarJugador("Nahitan", "Nahitan", "Nández", Categoria.PROFESIONAL);
        sistema.registrarJugador("Federico", "Federico", "Valverde", Categoria.PROFESIONAL);

        sistema.agregarJugadorAEquipo("Peñarol", "Forlan");
        sistema.agregarJugadorAEquipo("Peñarol", "Suarez");
        sistema.agregarJugadorAEquipo("Peñarol", "La Pantera");
        sistema.agregarJugadorAEquipo("Nacional", "Pellistri");
        sistema.agregarJugadorAEquipo("Nacional", "Federico");

        retorno = sistema.agregarJugadorAEquipo("Peñarol", "Pellistri");

        assertEquals(Retorno.Resultado.ERROR_6, retorno.getResultado());
    }

    @Test
    void AgregarJugadorAEquipo() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);

        sistema.registrarEquipo("Peñarol", "Diego Aguirre");
        sistema.registrarEquipo("Nacional", "Diego Testas");
        sistema.registrarEquipo("Danubio", "Alejandro Apud");

        sistema.registrarJugador("Forlan", "Diego", "Forlan", Categoria.PROFESIONAL);
        sistema.registrarJugador("Suarez", "Luis", "Suarez", Categoria.PROFESIONAL);
        sistema.registrarJugador("El Morro", "Santiago", "Garcia", Categoria.ESTANDARD);

        retorno = sistema.agregarJugadorAEquipo("Nacional", "Forlan");

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    //Listar los jugadores de un equipo

    @Test
    void ListarJugadoresDeEquipoConNombreDeEquipoVacio() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);

        sistema.registrarEquipo("Peñarol", "Diego Aguirre");

        retorno = sistema.listarJugadoresDeEquipo("");

        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void ListarJugadoresDeEquipoConEquipoInexistente() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);

        sistema.registrarEquipo("Peñarol", "Diego Aguirre");

        retorno = sistema.listarJugadoresDeEquipo("Nacional");

        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void ListarJugadoresDeEquipo() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);

        sistema.registrarEquipo("Peñarol", "Diego Aguirre");

        sistema.registrarJugador("Forlan", "Diego", "Forlan", Categoria.PROFESIONAL);
        sistema.registrarJugador("Suarez", "Luis", "Suarez", Categoria.PROFESIONAL);
        sistema.registrarJugador("La Pantera", "Darwin", "Núñez", Categoria.PROFESIONAL);
        sistema.registrarJugador("Pellistri", "Facundo", "Pellistri", Categoria.PROFESIONAL);
        sistema.registrarJugador("Federico", "Federico", "Valverde", Categoria.PROFESIONAL);

        sistema.agregarJugadorAEquipo("Peñarol", "Forlan");
        sistema.agregarJugadorAEquipo("Peñarol", "Suarez");
        sistema.agregarJugadorAEquipo("Peñarol", "La Pantera");
        sistema.agregarJugadorAEquipo("Peñarol", "Pellistri");
        sistema.agregarJugadorAEquipo("Peñarol", "Federico");

        retorno = sistema.listarJugadoresDeEquipo("Peñarol");

        assertEquals(retorno.getValorString(), "Federico;Federico;Valverde;PROFESIONAL|Forlan;Diego;Forlan;PROFESIONAL|La Pantera;Darwin;Núñez;PROFESIONAL|Pellistri;Facundo;Pellistri;PROFESIONAL|Suarez;Luis;Suarez;PROFESIONAL|");
    }

    //Listar equipos por nombre descendente

    @Test
    void ListarEquipos() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);

        sistema.registrarEquipo("Peñarol", "Diego Aguirre");
        sistema.registrarEquipo("Nacional", "Diego Testas");
        sistema.registrarEquipo("Danubio", "Alejandro Apud");
        sistema.registrarEquipo("Defensor Sporting", "Martín Varini");
        sistema.registrarEquipo("Boston River", "Jadson Viera");
        sistema.registrarEquipo("Deportivo Maldonado", "Joaquín Boghossian");
        sistema.registrarEquipo("Fénix", "Leonel Rocco");
        sistema.registrarEquipo("Liverpool", "Emiliano Alfaro");
        sistema.registrarEquipo("Miramar Misiones", "Leonardo Medina");
        sistema.registrarEquipo("Wanderers", "Alejandro Cappuccio");
        sistema.registrarEquipo("Progreso", "Carlos Canobbio");
        sistema.registrarEquipo("Racing", "Eduardo Espinel");
        sistema.registrarEquipo("River Plate", "Ignacio Ithurralde");

        System.out.println(sistema.listarEquiposDescendente());
    }

}
