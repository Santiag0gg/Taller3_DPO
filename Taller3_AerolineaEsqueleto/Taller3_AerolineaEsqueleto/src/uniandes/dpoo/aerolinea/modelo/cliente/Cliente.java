package uniandes.dpoo.aerolinea.modelo.cliente;

import java.util.ArrayList;
import java.util.List;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

/**
 * Representa a un cliente general de la aerolínea.
 * Esta clase es abstracta y se especializa en ClienteCorporativo y
 * ClienteNatural.
 */
public abstract class Cliente {

    /**
     * La lista de tiquetes comprados por el cliente que todavía no se han usado.
     */
    private List<Tiquete> tiquetesSinUsar;

    /**
     * La lista de tiquetes comprados por el cliente que ya se han usado.
     */
    private List<Tiquete> tiquetesUsados;

    /**
     * Constructor del cliente que inicializa las listas de tiquetes como vacías.
     */
    public Cliente() {
        this.tiquetesSinUsar = new ArrayList<>();
        this.tiquetesUsados = new ArrayList<>();
    }

    /**
     * Retorna el tipo de cliente, como un String que lo identifique.
     * 
     * @return El tipo de cliente.
     */
    public abstract String getTipoCliente();

    /**
     * Retorna el identificador del cliente.
     * 
     * @return El string para distinguir al cliente.
     */
    public abstract String getIdentificador();

    /**
     * Agrega un nuevo tiquete a la lista de tiquetes sin usar comprados por el
     * cliente.
     * 
     * @param tiquete
     */
    public void agregarTiquete(Tiquete tiquete) {
        this.tiquetesSinUsar.add(tiquete);
    }

    /**
     * Calcula cuánto valen en total todos los tiquetes (calculando sin usar y
     * usados en suma).
     * 
     * @return El total del costo en tiquetes que el cliente ha adquirido en la
     *         historia.
     */
    public int calcularValorTotalTiquetes() {
        int valorTotal = 0;

        for (Tiquete t : tiquetesSinUsar) {
            valorTotal += t.getTarifa();
        }

        for (Tiquete t : tiquetesUsados) {
            valorTotal += t.getTarifa();
        }

        return valorTotal;
    }

    /**
     * Marca como usados todos los tiquetes del cliente comprador que estén
     * asociados al vuelo entregado.
     * Estos se pasan de la lista de sin usar a usados.
     * 
     * @param vuelo
     */
    public void usarTiquetes(Vuelo vuelo) {
        List<Tiquete> nuevosSinUsar = new ArrayList<>();

        for (Tiquete tiquete : tiquetesSinUsar) {
            if (tiquete.getVuelo().equals(vuelo)) {
                tiquete.marcarComoUsado();
                this.tiquetesUsados.add(tiquete);
            } else {
                nuevosSinUsar.add(tiquete);
            }
        }

        this.tiquetesSinUsar = nuevosSinUsar;
    }

}
