package Back;

import java.util.ArrayList;

public class PalabraAnotada extends ArrayList<Casilla> {

    private int direccionPalabra;

    public PalabraAnotada(int orientacion) {
        direccionPalabra = orientacion;
    }

    public PalabraAnotada(ArrayList<Casilla> palabra, int orientacion) {
        super(palabra);
        direccionPalabra = orientacion;
    }

    public int getOrientacion() {
        return direccionPalabra;
    }

    public void setOrientacion(int orientacion) {
        direccionPalabra = orientacion;
    }

    @Override
    public String toString() {
        String cadenaPalabraActual = new String();
        String devolverCadena = new String();

        for (Casilla casilla : this) {
            devolverCadena = cadenaPalabraActual.concat(casilla.getFichaAsignada().getLetra());
        }
        return devolverCadena;
    }
}
