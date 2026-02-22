package uniandes.dpoo.aerolinea.tiquetes;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

/**
 * Esta clase agrupa la información de un tiquete que se le vendió a un cliente
 * para un vuelo.
 */
public class Tiquete {

    /**
     * El código que identifica a este tiquete
     */
    private String codigo;

    /**
     * La tarifa pagada por este tiquete
     */
    private int tarifa;

    /**
     * Indica si el tiquete ya se usó o no
     */
    private boolean usado;

    /**
     * El vuelo para el que es válido el tiquete
     */
    private Vuelo vuelo;

    /**
     * El comprador del tiquete
     */
    private Cliente cliente;

    /**
     * Construye un nuevo tiquete e inicializa todos los parámetros con base en los
     * argumentos que se le pasan.
     * Igualmente, el tiquete se marca como "no usado".
     * 
     * @param codigo  El código que identificará al tiquete
     * @param vuelo   El vuelo para el que es el tiquete
     * @param cliente El cliente al que se le vendió el tiquete
     * @param tarifa  El valor que se cobró
     */
    public Tiquete(String codigo, Vuelo vuelo, Cliente clienteComprador, int tarifa) {
        this.codigo = codigo;
        this.vuelo = vuelo;
        this.cliente = clienteComprador;
        this.tarifa = tarifa;
        this.usado = false;
    }

    /**
     * Retorna el cliente comprador
     * 
     * @return
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Retorna el vuelo al que está asociado el tiquete
     * 
     * @return
     */
    public Vuelo getVuelo() {
        return vuelo;
    }

    /**
     * Retorna el código asignado a este tiquete al crearlo
     * 
     * @return
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Retorna la tarifa pagada por este tiquete
     * 
     * @return
     */
    public int getTarifa() {
        return tarifa;
    }

    /**
     * Cambia el estado del tiquete para marcarlo como usado
     */
    public void marcarComoUsado() {
        this.usado = true;
    }

    /**
     * Indica si ya se usó o no este tiquete
     * 
     * @return
     */
    public boolean esUsado() {
        return usado;
    }

}
