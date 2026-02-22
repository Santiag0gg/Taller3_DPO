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
        unaAerolinea = new Aerolinea();
        boolean continuar = true;

        while (continuar) {
            String[] opciones = {
                    "Cargar/Salvar Aerolínea (JSON)",
                    "Programar Vuelo",
                    "Vender Tiquetes",
                    "Registrar Vuelo Realizado",
                    "Consultar Saldo Pendiente",
                    "Salir"
            };

            int opcion_seleccionada = mostrarMenu("Menú Principal - Pruebas de Aerolínea", opciones);

            switch (opcion_seleccionada) {
                case 1:
                    ejecutarCargarYSalvar();
                    break;
                case 2:
                    ejecutarProgramarVuelo();
                    break;
                case 3:
                    ejecutarVenderTiquetes();
                    break;
                case 4:
                    ejecutarRegistrarVuelo();
                    break;
                case 5:
                    ejecutarConsultarSaldo();
                    break;
                case 6:
                    continuar = false;
                    System.out.println("Saliendo de la aplicación...");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }

    private void ejecutarCargarYSalvar() {
        try {
            System.out.println("\n*** Cargar Aerolínea (aerolinea.json) ***");
            unaAerolinea.cargarAerolinea("./datos/aerolinea.json", CentralPersistencia.JSON);
            System.out.println("Aerolínea cargada exitosamente.");

            System.out.println("\n*** Cargar Tiquetes (tiquetes.json) ***");
            unaAerolinea.cargarTiquetes("./datos/tiquetes.json", CentralPersistencia.JSON);
            System.out.println("Tiquetes cargados exitosamente.");

        } catch (TipoInvalidoException | IOException | InformacionInconsistenteException e) {
            System.out.println("Error al cargar datos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void ejecutarProgramarVuelo() {
        String fecha = pedirCadenaAlUsuario("Ingrese la fecha (YYYY-MM-DD)");
        String codigoRuta = pedirCadenaAlUsuario("Ingrese el código de la ruta");
        String nombreAvion = pedirCadenaAlUsuario("Ingrese el nombre del avión");

        try {
            unaAerolinea.programarVuelo(fecha, codigoRuta, nombreAvion);
            System.out.println("Vuelo programado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al programar el vuelo: " + e.getMessage());
        }
    }

    private void ejecutarVenderTiquetes() {
        String identificadorCliente = pedirCadenaAlUsuario("Ingrese el identificador del cliente");
        String fecha = pedirCadenaAlUsuario("Ingrese la fecha del vuelo (YYYY-MM-DD)");
        String codigoRuta = pedirCadenaAlUsuario("Ingrese el código de la ruta");
        int cantidad = pedirEnteroAlUsuario("Ingrese la cantidad de tiquetes");

        try {
            int valor = unaAerolinea.venderTiquetes(identificadorCliente, fecha, codigoRuta, cantidad);
            System.out.println("Tiquetes vendidos exitosamente. Valor total: " + valor);
        } catch (Exception e) {
            System.out.println("Error al vender tiquetes: " + e.getMessage());
        }
    }

    private void ejecutarRegistrarVuelo() {
        String fecha = pedirCadenaAlUsuario("Ingrese la fecha del vuelo a registrar (YYYY-MM-DD)");
        String codigoRuta = pedirCadenaAlUsuario("Ingrese el código de la ruta");

        try {
            unaAerolinea.registrarVueloRealizado(fecha, codigoRuta);
            System.out.println("Vuelo registrado como realizado.");
        } catch (Exception e) {
            System.out.println("Error al registrar vuelo: " + e.getMessage());
        }
    }

    private void ejecutarConsultarSaldo() {
        String identificadorCliente = pedirCadenaAlUsuario("Ingrese el identificador del cliente");
        String saldo = unaAerolinea.consultarSaldoPendienteCliente(identificadorCliente);
        System.out.println("El saldo pendiente (valor de tiquetes no usados) del cliente es: " + saldo);
    }

    public static void main(String[] args) {
        ConsolaArerolinea ca = new ConsolaArerolinea();
        ca.correrAplicacion();
    }
}
