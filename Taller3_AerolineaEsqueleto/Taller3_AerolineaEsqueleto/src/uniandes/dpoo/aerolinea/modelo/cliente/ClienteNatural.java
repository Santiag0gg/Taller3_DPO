package uniandes.dpoo.aerolinea.modelo.cliente;

/**
 * Esta clase se usa para representar a los clientes de la aerolínea que son
 * personas naturales
 */
public class ClienteNatural extends Cliente {

    /**
     * Constante para referir al tipo de cliente
     */
    public static final String NATURAL = "Natural";

    /**
     * El nombre o identificador de la persona
     */
    private String nombre;

    /**
     * Construye el cliente usando el nombre provisto.
     * 
     * @param nombre
     */
    public ClienteNatural(String nombre) {
        super();
        this.nombre = nombre;
    }

    @Override
    public String getIdentificador() {
        return this.nombre;
    }

    @Override
    public String getTipoCliente() {
        return NATURAL;
    }

}
