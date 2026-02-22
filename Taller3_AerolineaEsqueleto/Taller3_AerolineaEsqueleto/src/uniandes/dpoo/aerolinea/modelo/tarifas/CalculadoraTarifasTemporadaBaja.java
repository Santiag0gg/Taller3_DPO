package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteNatural;

public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas {

    protected final int COSTO_POR_KM_NATURAL = 600;
    protected final int COSTO_POR_KM_CORPORATIVO = 900;
    protected final double DESCUENTO_PEQ = 0.02;
    protected final double DESCUENTO_MEDIANAS = 0.1;
    protected final double DESCUENTO_GRANDES = 0.2;

    @Override
    protected int calcularCostoBase(Vuelo vuelo, Cliente cliente) {
        int distancia = calcularDistanciaVuelo(vuelo.getRuta());
        if (cliente.getTipoCliente().equals(ClienteCorporativo.CORPORATIVO)) {
            return distancia * COSTO_POR_KM_CORPORATIVO;
        } else {
            return distancia * COSTO_POR_KM_NATURAL;
        }
    }

    @Override
    protected double calcularPorcentajeDescuento(Cliente cliente) {
        if (cliente.getTipoCliente().equals(ClienteCorporativo.CORPORATIVO)) {
            ClienteCorporativo corp = (ClienteCorporativo) cliente;
            if (corp.getTamanoEmpresa() == ClienteCorporativo.PEQUENA) {
                return DESCUENTO_PEQ;
            } else if (corp.getTamanoEmpresa() == ClienteCorporativo.MEDIANA) {
                return DESCUENTO_MEDIANAS;
            } else if (corp.getTamanoEmpresa() == ClienteCorporativo.GRANDE) {
                return DESCUENTO_GRANDES;
            }
        }
        return 0.0;
    }

}
