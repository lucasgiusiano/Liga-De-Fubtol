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
    void noDeberiaIngresarJugadorParametrosVaciosONull() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);

        retorno = sistema.registrarJugador("", "Nombre", "Apellido", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.registrarJugador(null, "Nombre", "Apellido", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.registrarJugador("Alias", "", "Apellido", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.registrarJugador("Alias", null, "Apellido", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void noDeberiaIngresarJugadorYaExistente() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);

        sistema.registrarJugador("Alias", "Nombre", "Apellido", Categoria.PROFESIONAL);
        retorno = sistema.registrarJugador("Alias", "Nombre", "Apellido", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void deberiaRegistrarJugadorCorrectamente() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);

        retorno = sistema.registrarJugador("Alias", "Nombre", "Apellido", Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    void noDeberiaBuscarJugadorParametrosVaciosONull() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);

        retorno = sistema.buscarJugador("");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.buscarJugador(null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void noDeberiaBuscarJugadorInexistente() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);

        retorno = sistema.buscarJugador("AliasInexistente");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void deberiaBuscarJugadorCorrectamente() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);

        sistema.registrarJugador("Alias", "Nombre", "Apellido", Categoria.PROFESIONAL);
        retorno = sistema.buscarJugador("Alias");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("Alias;Nombre;Apellido;PROFESIONAL", retorno.getValorString());
    }

    @Test
    void deberiaListarJugadoresCorrectamente() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);

        sistema.registrarJugador("Alias1", "Nombre1", "Apellido1", Categoria.PROFESIONAL);
        sistema.registrarJugador("Alias2", "Nombre2", "Apellido2", Categoria.ESTANDARD);

        retorno = sistema.listarJugadoresAscendente();
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("Alias1;Nombre1;Apellido1;PROFESIONAL|Alias2;Nombre2;Apellido2;ESTANDARD|", retorno.getValorString());
    }

    @Test
    void deberiaListarJugadoresPorCategoriaCorrectamente() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);

        sistema.registrarJugador("Alias1", "Nombre1", "Apellido1", Categoria.PROFESIONAL);
        sistema.registrarJugador("Alias2", "Nombre2", "Apellido2", Categoria.PROFESIONAL);
        sistema.registrarJugador("Alias3", "Nombre3", "Apellido3", Categoria.ESTANDARD);

        retorno = sistema.listarJugadoresPorCategoria(Categoria.PROFESIONAL);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("Alias1;Nombre1;Apellido1;PROFESIONAL|Alias2;Nombre2;Apellido2;PROFESIONAL", retorno.getValorString());
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

        retorno = sistema.listarEquiposDescendente();

        assertEquals(retorno.getValorString(), "Wanderers;Alejandro Cappuccio;0|River Plate;Ignacio Ithurralde;0|Racing;Eduardo Espinel;0|Progreso;Carlos Canobbio;0|Peñarol;Diego Aguirre;0|Nacional;Diego Testas;0|Miramar Misiones;Leonardo Medina;0|Liverpool;Emiliano Alfaro;0|Fénix;Leonel Rocco;0|Deportivo Maldonado;Joaquín Boghossian;0|Defensor Sporting;Martín Varini;0|Danubio;Alejandro Apud;0|Boston River;Jadson Viera;0|");
    }
    //Test de registrar Sucursales
    @Test
    void registrarSucursalError1() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(2);

        sistema.registrarSucursal("S1", "Sucursal 1");
        sistema.registrarSucursal("S2", "Sucursal 2");

        Retorno retorno = sistema.registrarSucursal("S3", "Sucursal 3");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }
    @Test
    void RegistrarSucursalConCodigoYNombresVacios() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);

        retorno = sistema.registrarSucursal("", "");

        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void RegistrarSucursalConCodigoExistente() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);

        sistema.registrarSucursal("S1", "Sucursal 1");

        retorno = sistema.registrarSucursal("S1", "Sucursal Duplicada");

        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

    @Test
    void RegistrarSucursalCorrecto() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);

        retorno = sistema.registrarSucursal("S1", "Sucursal 1");

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }
    //Test Registrar Conexiones
    @Test
    void RegistrarConexionConLatenciaNegativa() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);

        sistema.registrarSucursal("S1", "Sucursal 1");
        sistema.registrarSucursal("S2", "Sucursal 2");

        retorno = sistema.registrarConexion("S1", "S2", -5);

        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void RegistrarConexionConSucursalInexistente() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);

        sistema.registrarSucursal("S1", "Sucursal 1");


        retorno = sistema.registrarConexion("S1", "S3", 10);

        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

    @Test
    void RegistrarConexionCorrecto() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);

        sistema.registrarSucursal("S1", "Sucursal 1");
        sistema.registrarSucursal("S2", "Sucursal 2");

        retorno = sistema.registrarConexion("S1", "S2", 15);

        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }
    //Test Actualizar Conexiones
    @Test
    void actualizarConexionExitosamente() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);
        sistema.registrarSucursal("S1", "Sucursal 1");
        sistema.registrarSucursal("S2", "Sucursal 2");
        sistema.registrarConexion("S1", "S2", 10);

        Retorno retorno = sistema.actualizarConexion("S1", "S2", 15);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
    }

    @Test
    void actualizarConexionConLatenciaNegativa() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);
        sistema.registrarSucursal("S1", "Sucursal 1");
        sistema.registrarSucursal("S2", "Sucursal 2");
        sistema.registrarConexion("S1", "S2", 10);

        Retorno retorno = sistema.actualizarConexion("S1", "S2", -5);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void actualizarConexionConParametrosVaciosONull() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);

        Retorno retorno = sistema.actualizarConexion("", "S2", 10);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());

        retorno = sistema.actualizarConexion(null, "S2", 10);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void actualizarConexionConSucursalNoExistente() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);
        sistema.registrarSucursal("S1", "Sucursal 1");

        Retorno retorno = sistema.actualizarConexion("S1", "S3", 10);
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }

    @Test
    void actualizarConexionInexistente() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);
        sistema.registrarSucursal("S1", "Sucursal 1");
        sistema.registrarSucursal("S2", "Sucursal 2");

        Retorno retorno = sistema.actualizarConexion("S1", "S2", 10);
        assertEquals(Retorno.Resultado.ERROR_4, retorno.getResultado());
    }
    //Test Analizar Sucursales
    @Test
    void analizarSucursalCritica() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);

        sistema.registrarSucursal("S1", "Sucursal Crítica");
        sistema.registrarSucursal("S2", "Sucursal Conectada");

        sistema.actualizarConexion("S1", "S2", 10);

        Retorno retorno = sistema.analizarSucursal("S1");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("SI", retorno.getValorString());
    }

    @Test
    void analizarSucursalNoCritica() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);

        sistema.registrarSucursal("S1", "Sucursal Crítica");
        sistema.registrarSucursal("S2", "Sucursal Conectada");
        sistema.registrarSucursal("S3", "Sucursal No Crítica");

        sistema.actualizarConexion("S1", "S2", 10);

        Retorno retorno = sistema.analizarSucursal("S3");
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("NO", retorno.getValorString());
    }

    @Test
    void analizarSucursalConCodigoVacioONull() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);

        Retorno retorno = sistema.analizarSucursal("");
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.analizarSucursal(null);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void analizarSucursalNoExistente() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);

        Retorno retorno = sistema.analizarSucursal("S3");
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }
    @Test
    void seleccionarSucursalesParaTorneoExitoso() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);
        sistema.registrarSucursal("S1", "Sucursal Anfitriona");
        sistema.registrarSucursal("S2", "Sucursal Cercana");
        sistema.registrarConexion("S1", "S2", 5);

        Retorno retorno = sistema.sucursalesParaTorneo("S1", 10);
        assertEquals(Retorno.Resultado.OK, retorno.getResultado());
        assertEquals("S2;Sucursal Cercana", retorno.getValorString());
    }

    @Test
    void seleccionarSucursalesConCodigoAnfitrionVacioONull() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);

        Retorno retorno = sistema.sucursalesParaTorneo("", 10);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());

        retorno = sistema.sucursalesParaTorneo(null, 10);
        assertEquals(Retorno.Resultado.ERROR_1, retorno.getResultado());
    }

    @Test
    void seleccionarSucursalesConAnfitrionInexistente() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);

        Retorno retorno = sistema.sucursalesParaTorneo("S3", 10);
        assertEquals(Retorno.Resultado.ERROR_2, retorno.getResultado());
    }

    @Test
    void seleccionarSucursalesConLatenciaInvalida() {
        Sistema sistema = new ImplementacionSistema();
        sistema.inicializarSistema(4);
        sistema.registrarSucursal("S1", "Sucursal Anfitriona");

        Retorno retorno = sistema.sucursalesParaTorneo("S1", -5);
        assertEquals(Retorno.Resultado.ERROR_3, retorno.getResultado());
    }
}
