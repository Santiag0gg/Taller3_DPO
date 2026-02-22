package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

/**
 * Calculadora de tarifas de temporada alta
 */
public class CalculadoraTarifasTemporadaAlta extends CalculadoraTarifas {

    /**
     * El costo base por kilómetro cuando el vuelo es en temporada alta.
     */
    protected final int COSTO_POR_KM = 1000;

    @Override
    protected int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
        int distancia = calcularDistanciaVuelo(vuelo.getRuta());
        return distancia * COSTO_POR_KM;
    }

    @Override
    protected double calcularPorcentajeDescuento(Cliente cliente) {
        return 0; // En temporada alta no hay descuentos
    }

}
