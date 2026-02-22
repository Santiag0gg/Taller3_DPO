package uniandes.dpoo.aerolinea.modelo;

/**
 * En esta clase se organizan los aspectos que definen a un  Avión.
 */
public class Avion {
    
    /**
     * El nombre asignado al avión
     */
    private String nombre;
    
    /**
     * La capacidad del avión en número de pasajeros
     */
    private int capacidad;

    /**
     * Construye un nuevo avión e inicializa sus atributos con los parámetros dados.
     * @param nombre El nombre del avión
     * @param capacidad La capacidad de pasajeros del avión
     */
    public Avion(String nombre, int capacidad) {
        this.nombre = nombre;
        this.capacidad = capacidad;
    }

    /**
     * Retorna el nombre del avión.
     * @return 
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Retorna la capacidad total de pasajeros del avión.
     * @return 
     */
    public int getCapacidad() {
        return capacidad;
    }

}
