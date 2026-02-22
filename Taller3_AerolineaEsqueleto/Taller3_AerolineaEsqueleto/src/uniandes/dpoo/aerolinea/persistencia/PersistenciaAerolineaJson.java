package uniandes.dpoo.aerolinea.persistencia;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import org.json.JSONArray;
import org.json.JSONObject;

import uniandes.dpoo.aerolinea.exceptions.InformacionInconsistenteException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Avion;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;

public class PersistenciaAerolineaJson implements IPersistenciaAerolinea {

    private static final String NOMBRE = "nombre";
    private static final String CAPACIDAD = "capacidad";
    private static final String HORA_SALIDA = "horaSalida";
    private static final String HORA_LLEGADA = "horaLlegada";
    private static final String CODIGO_RUTA = "codigoRuta";
    private static final String ORIGEN = "origen";
    private static final String DESTINO = "destino";
    private static final String FECHA = "fecha";
    private static final String RUTAS = "rutas";
    private static final String AVIONES = "aviones";
    private static final String VUELOS = "vuelos";

    @Override
    public void cargarAerolinea(String archivo, Aerolinea aerolinea)
            throws IOException, InformacionInconsistenteException {
        String jsonCompleto = new String(Files.readAllBytes(new File(archivo).toPath()));
        JSONObject jobject = new JSONObject(jsonCompleto);

        cargarAviones(aerolinea, jobject.getJSONArray(AVIONES));
        cargarRutas(aerolinea, jobject.getJSONArray(RUTAS));
        cargarVuelos(aerolinea, jobject.getJSONArray(VUELOS));
    }

    @Override
    public void salvarAerolinea(String archivo, Aerolinea aerolinea) throws IOException {
        JSONObject jAerolinea = new JSONObject();

        JSONArray jAviones = new JSONArray();
        for (Avion avion : aerolinea.getAviones()) {
            JSONObject jAvion = new JSONObject();
            jAvion.put(NOMBRE, avion.getNombre());
            jAvion.put(CAPACIDAD, avion.getCapacidad());
            jAviones.put(jAvion);
        }
        jAerolinea.put(AVIONES, jAviones);

        JSONArray jRutas = new JSONArray();
        for (Ruta ruta : aerolinea.getRutas()) {
            JSONObject jRuta = new JSONObject();
            jRuta.put(HORA_SALIDA, ruta.getHoraSalida());
            jRuta.put(HORA_LLEGADA, ruta.getHoraLlegada());
            jRuta.put(CODIGO_RUTA, ruta.getCodigoRuta());
            jRuta.put(ORIGEN, ruta.getOrigen().getCodigo());
            jRuta.put(DESTINO, ruta.getDestino().getCodigo());
            jRutas.put(jRuta);
        }
        jAerolinea.put(RUTAS, jRutas);

        JSONArray jVuelos = new JSONArray();
        for (Vuelo vuelo : aerolinea.getVuelos()) {
            JSONObject jVuelo = new JSONObject();
            jVuelo.put(FECHA, vuelo.getFecha());
            jVuelo.put(CODIGO_RUTA, vuelo.getRuta().getCodigoRuta());
            jVuelo.put("avion", vuelo.getAvion().getNombre());
            jVuelos.put(jVuelo);
        }
        jAerolinea.put(VUELOS, jVuelos);

        PrintWriter pw = new PrintWriter(archivo);
        jAerolinea.write(pw, 2, 0);
        pw.close();
    }

    private void cargarAviones(Aerolinea aerolinea, JSONArray jAviones) {
        for (int i = 0; i < jAviones.length(); i++) {
            JSONObject jAvion = jAviones.getJSONObject(i);
            String nombre = jAvion.getString(NOMBRE);
            int capacidad = jAvion.getInt(CAPACIDAD);
            Avion avion = new Avion(nombre, capacidad);
            aerolinea.agregarAvion(avion);
        }
    }

    private void cargarRutas(Aerolinea aerolinea, JSONArray jRutas) throws InformacionInconsistenteException {
        // Para crear rutas se necesitan aeropuertos, que en esta estructura JSON solo
        // vienen los codigos,
        // asi que se crean constructores basicos de Aeropuerto para no romper. En la
        // realidad podrian unirse a un mapa global de aeropuertos.
        for (int i = 0; i < jRutas.length(); i++) {
            JSONObject jRuta = jRutas.getJSONObject(i);
            String horaSalida = jRuta.getString(HORA_SALIDA);
            String horaLlegada = jRuta.getString(HORA_LLEGADA);
            String codigoRuta = jRuta.getString(CODIGO_RUTA);
            String codigoOrigen = jRuta.getString(ORIGEN);
            String codigoDestino = jRuta.getString(DESTINO);

            Aeropuerto origen = new Aeropuerto("Aeropuerto " + codigoOrigen, codigoOrigen, "Ciudad " + codigoOrigen,
                    0.0, 0.0);
            Aeropuerto destino = new Aeropuerto("Aeropuerto " + codigoDestino, codigoDestino, "Ciudad " + codigoDestino,
                    0.0, 0.0);

            Ruta ruta = new Ruta(origen, destino, horaSalida, horaLlegada, codigoRuta);
            aerolinea.agregarRuta(ruta);
        }
    }

    private void cargarVuelos(Aerolinea aerolinea, JSONArray jVuelos) throws InformacionInconsistenteException {
        for (int i = 0; i < jVuelos.length(); i++) {
            JSONObject jVuelo = jVuelos.getJSONObject(i);
            String fecha = jVuelo.getString(FECHA);
            String codigoRuta = jVuelo.getString(CODIGO_RUTA);
            String nombreAvion = jVuelo.getString("avion");

            try {
                aerolinea.programarVuelo(fecha, codigoRuta, nombreAvion);
            } catch (Exception e) {
                throw new InformacionInconsistenteException(
                        "Error programando el vuelo desde el persistente: " + e.getMessage());
            }
        }
    }

}
