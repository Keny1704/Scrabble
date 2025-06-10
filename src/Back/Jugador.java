package Back;

import java.util.ArrayList;

public class Jugador {
    private ArrayList<Ficha> rack;
    private CasillaBoton botonRackSeleccionado;
    private int puntos;
    private final String nombre;

    public Jugador(BolsaFichas bolsa, String nuevoNombre) {
        nombre = nuevoNombre;

        rack = new ArrayList<>();
        botonRackSeleccionado = null;

        for (int i = 0; i < 7; i++) {
            añadirFicha(bolsa);
        }
    }

    public final boolean añadirFicha(BolsaFichas bolsa) {
        boolean añadir = rack.add(bolsa.ponerCasilla());
        return añadir;
    }

    public boolean añadirFichaEspecifica(Ficha ficha) {
            return rack.add(ficha);
    }

    public boolean eliminarFicha(Ficha ficha) {
        return rack.remove(ficha);
    }

    public ArrayList<Ficha> getRack() {
        return rack;
    }

    public void setRack(ArrayList<Ficha> nuevaLista) {
        rack = nuevaLista;
    }

    public CasillaBoton getBotonRackSeleccionado() {
        return botonRackSeleccionado;
    }

    public void setBotonRackSeleccionado(CasillaBoton nuevoBotonRackSeleccionado) {
        botonRackSeleccionado = nuevoBotonRackSeleccionado;
    }

    public void añadirPuntos(int palabraAnotada) {
        this.puntos += palabraAnotada;
    }

    public int getPuntos() {
        return this.puntos;
    }

    public String getNombre() {
        return this.nombre;
    }
}
