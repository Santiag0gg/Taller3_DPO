package uniandes.dpoo.aerolinea.persistencia;

import java.io.IOException;

import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;

public interface IPersistenciaAerolinea {

    /**
     * Carga la información de la aerolínea a partir de un archivo
     * 
     * @param archivo   La ruta al archivo que contiene la información
     * @param aerolinea La aerolínea en la que se debe cargar la información
     * @throws IOException                       Se lanza si hay algún error leyendo
     *                                           el archivo
     * @throws InformacionInconsistenteException Se lanza si el archivo tiene
     *                                           errores de consistencia en la
     *                                           información
     */
    void cargarAerolinea(String archivo, Aerolinea aerolinea) throws IOException, InformacionInconsistenteException;

    /**
     * Salva la información de la aerolínea en un archivo
     * 
     * @param archivo   La ruta al archivo donde debe quedar guardada la información
     * @param aerolinea La aerolínea donde está la información que se quiere guardar
     * @throws IOException Se lanza si hay problemas escribiendo en el archivo
     */
    void salvarAerolinea(String archivo, Aerolinea aerolinea) throws IOException;

}
