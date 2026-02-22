package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

/**
 * Esta clase abstracta define el comportamiento esperado para cualquier
 * calculadora de tarifas.
 */
public abstract class CalculadoraTarifas {

    /**
     * El porcentaje constante de impuesto que se aplica a la base del tiquete
     */
    public static final double IMPUESTO = 0.16;

    /**
     * Calcula la tarifa total, considerando el costo base, el descuento y los
     * impuestos.
     * 
     * @param vuelo   El vuelo para el que se va a calcular la tarifa
     * @param cliente El cliente al que se le va a vender la tarifa
     * @return El costo total con impuestos y descuentos.
     */
    public int calcularTarifa(Vuelo vuelo, Cliente cliente) {
        int costoBase = calcularCostoBase(vuelo, cliente);
        double descuento = calcularPorcentajeDescuento(cliente);
        int costoDescuento = (int) (costoBase * descuento);
        int baseConDescuento = costoBase - costoDescuento;
        int impuestos = calcularValorImpuestos(baseConDescuento);

        return baseConDescuento + impuestos;
    }

    /**
     * Método a implementar por los hijos para determinar la tarifa base.
     * 
     * @param vuelo
     * @param cliente
     * @return
     */
    protected abstract int calcularCostoBase(Vuelo vuelo, Cliente cliente);

    /**
     * Método a implementar por los hijos para determinar si aplica algún porcentaje
     * de descuento.
     * 
     * @param cliente
     * @return Retorna porcentaje entre 0 y 1
     */
    protected abstract double calcularPorcentajeDescuento(Cliente cliente);

    /**
     * Calcula la distancia de vuelo delegando a calcularDistancia de Aeropuerto
     * 
     * @param ruta
     * @return
     */
    protected int calcularDistanciaVuelo(Ruta ruta) {
        return Aeropuerto.calcularDistancia(ruta.getOrigen(), ruta.getDestino());
    }

    /**
     * Calcula los impuestos que se le cobran a una tarifa.
     * 
     * @param costoBase
     * @return
     */
    protected int calcularValorImpuestos(int costoBase) {
        return (int) (costoBase * IMPUESTO);
    }
}
