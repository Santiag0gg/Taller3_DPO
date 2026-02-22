package uniandes.dpoo.aerolinea.modelo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import uniandes.dpoo.aerolinea.exceptions.VueloSobrevendidoException;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifas;
import uniandes.dpoo.aerolinea.tiquetes.GeneradorTiquetes;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

/**
 * Esta clase tiene la información de un vuelo particular que cubre una ruta y se lleva a cabo en una cierta fecha.
 * 
 * Los vuelos también guardan la información de los tiquetes que se han vendido, usando el código de cada tiquete como la llave.
 */
public class Vuelo {

    /**
     * La fecha del vuelo, en formato "YYYY-MM-DD"
     */
    private String fecha;
    
    /**
     * La ruta que cubrirá el vuelo
     */
    private Ruta ruta;
    
    /**
     * El avión que realizará el vuelo
     */
    private Avion avion;
    
    /**
     * Un mapa con los tiquetes del vuelo, organizados por código
     */
    private Map<String, Tiquete> tiquetes;

    /**
     * Crea un nuevo vuelo con los recursos provistos, y deja los tiquetes inicializados
     * @param ruta
     * @param fecha
     * @param avion
     */
    public Vuelo(Ruta ruta, String fecha, Avion avion) {
        this.ruta = ruta;
        this.fecha = fecha;
        this.avion = avion;
        this.tiquetes = new HashMap<>();
    }

    /**
     * Retorna la ruta del vuelo
     * @return
     */
    public Ruta getRuta() {
        return ruta;
    }

    /**
     * Retorna la fecha del vuelo
     * @return
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Retorna el avión del vuelo
     * @return
     */
    public Avion getAvion() {
        return avion;
    }

    /**
     * Retorna la colección de tiquetes del vuelo
     * @return
     */
    public Collection<Tiquete> getTiquetes() {
        return tiquetes.values();
    }

    /**
     * Vende una cierta cantidad de tiquetes para el vuelo, registrando en un solo objeto cada uno de los tiquetes 
     * como vendidos para el cliente y registrados para el vuelo.
     * 
     * @param cliente El cliente al que se le vende el tiquete
     * @param calculadora La calculadora de tarifas que se debe utilizar para saber el precio del tiquete
     * @param cantidad La cantidad de tiquetes que se van a vender
     * @return Un entero con el costo total de los tiquetes
     * @throws VueloSobrevendidoException Se lanza en caso de que la cantidad de tiquetes a vender supere la capacidad del avión (o la capacidad restante del avión)
     */
    public int venderTiquetes(Cliente cliente, CalculadoraTarifas calculadora, int cantidad) throws VueloSobrevendidoException {
        int tiquetesVendidos = this.tiquetes.size();
        int capacidadDisponible = this.avion.getCapacidad() - tiquetesVendidos;
        
        if (cantidad > capacidadDisponible) {
            throw new VueloSobrevendidoException(this);
        }
        
        int costoTotal = 0;
        
        for (int i = 0; i < cantidad; i++) {
            int precio = calculadora.calcularTarifa(this, cliente);
            Tiquete nuevoTiquete = GeneradorTiquetes.generarTiquete(this, cliente, precio);
            GeneradorTiquetes.registrarTiquete(nuevoTiquete);
            this.tiquetes.put(nuevoTiquete.getCodigo(), nuevoTiquete);
            cliente.agregarTiquete(nuevoTiquete);
            costoTotal += precio;
        }
        
        return costoTotal;
    }

    /*
     * Modifica el metodo equals para poder comparar diferentes instancias de vuelo según su fecha y código de ruta.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vuelo vuelo = (Vuelo) obj;
        return fecha.equals(vuelo.fecha) && ruta.getCodigoRuta().equals(vuelo.ruta.getCodigoRuta());
    }

}
