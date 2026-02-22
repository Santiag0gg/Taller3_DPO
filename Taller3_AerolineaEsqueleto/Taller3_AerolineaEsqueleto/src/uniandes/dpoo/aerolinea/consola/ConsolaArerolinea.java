package uniandes.dpoo.aerolinea.consola;

import java.io.IOException;

import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import uniandes.dpoo.aerolinea.persistencia.CentralPersistencia;
import uniandes.dpoo.aerolinea.persistencia.TipoInvalidoException;

public class ConsolaArerolinea extends ConsolaBasica {
    private Aerolinea unaAerolinea;

    /**
     * Es un método que corre la aplicación y realmente no hace nada interesante:
     * sólo muestra cómo se podría utilizar la clase Aerolínea para hacer pruebas.
     */
    public void correrAplicacion() {
        try {
            System.out.println("Iniciando pruebas de validacion de Aerolinea...");
            unaAerolinea = new Aerolinea();

            System.out.println("Cargando rutas y vuelos desde aerolinea.json...");

            // 1. Cargar vuelos y rutas de prueba
            String archivoAero = "aerolinea.json";
            unaAerolinea.cargarAerolinea("./datos/" + archivoAero, CentralPersistencia.JSON);

            // 2. Cargar tiquetes (ahora no fallará)
            // String archivo = this.pedirCadenaAlUsuario( "Digite el nombre del archivo
            // json con la información de una aerolinea" );
            String archivo = "tiquetes.json";
            System.out.println("Cargando tiquetes desde " + archivo + "...");
            unaAerolinea.cargarTiquetes("./datos/" + archivo, CentralPersistencia.JSON);
            System.out.println("\n=======================================================");
            System.out.println("La aplicacion leyo el JSON de tiquetes");
            System.out.println("y pudo cruzarlos exitosamente contra  la ruta 4558 y");
            System.out.println("el avion dentro de la memoria del programa.");
            System.out.println("=======================================================");

        } catch (TipoInvalidoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InformacionInconsistenteException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsolaArerolinea ca = new ConsolaArerolinea();
        ca.correrAplicacion();
    }
}
