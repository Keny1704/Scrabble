package Back;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Tablero implements Serializable {

    public static final int TPMP = 4;
    public static final int TLMP = 3;
    public static final int DPMP = 2;
    public static final int DLMP = 1;

    private static final int[] TPVP = {0, 7, 14, 105, 119, 210, 217, 224};
    private static final int[] DLVP = {3, 11, 36, 38, 45, 52, 59, 92, 96, 98, 102, 108, 116, 122, 126, 128, 132, 165, 172, 179, 186, 188, 213, 221};
    private static final int[] DPVP = {16, 28, 32, 42, 48, 56, 64, 70, 154, 160, 168, 176, 182, 192, 196, 208};
    private static final int[] TLVP = {20, 24, 76, 80, 84, 88, 136, 140, 144, 148, 200, 204};
    private static final int NUMEROCASILLAS = 225;

    public ArrayList<Casilla> casillas;

    public Tablero() {
        casillas = new ArrayList<>();

        for (int i = 0; i < NUMEROCASILLAS; i++) {
            casillas.add(new Casilla(i));
        }
        
        pobModPuntuacion();

    }

    private void pobModPuntuacion() {
        for (int i = 0; i < NUMEROCASILLAS; i++) {
            for (int j = 0; j < TPVP.length; j++) {
                if (i == TPVP[j]) {
                    casillas.get(i).setModificadorPuntuacion(TPMP);
                    casillas.get(i).setLabel("3xP");
                }
            }
            for (int j = 0; j < TLVP.length; j++) {
                if (i == TLVP[j]) {
                    casillas.get(i).setModificadorPuntuacion(TLMP);
                    casillas.get(i).setLabel("3xL");
                }
            }
            for (int j = 0; j < DPVP.length; j++) {
                if (i == DPVP[j]) {
                    casillas.get(i).setModificadorPuntuacion(DPMP);
                    casillas.get(i).setLabel("2xP");
                }
            }
            for (int j = 0; j < DLVP.length; j++) {
                if (i == DLVP[j]) {
                    casillas.get(i).setModificadorPuntuacion(DLMP);
                    casillas.get(i).setLabel("2xL");
                }
            }
        }

    }
    
    public ArrayList<Casilla> getCasillasValidas(EstadoJuego estadoJuego) {

        ArrayList<Casilla> casillasValidas = new ArrayList<>();

        for (Casilla space : casillas) {
            space.setValidacion(false);
        }
        
        boolean tableroVacio = true;
        for (Casilla casilla : casillas) {
            if (casilla.getOcupado() && estadoJuego.getCasillasJugadas().contains(casilla)) {
                casilla.setValidacion(true);
            }
            
            if (casilla.getOcupado()) {
                tableroVacio = false;
            }
        }
        
        if (tableroVacio) {
            casillas.get(112).setValidacion(true);
        }
        
        if (estadoJuego.getCasillasJugadas().isEmpty()) {
            for (Casilla casilla : casillas) {
                if (casilla.getOcupado()) {
                    if (!casillas.get(casilla.getIndice() - 1).getOcupado()) {
                        casillas.get(casilla.getIndice() - 1).setValidacion(true);
                    }
                    
                    if (!casillas.get(casilla.getIndice() - 15).getOcupado()) {
                        casillas.get(casilla.getIndice() - 15).setValidacion(true);
                    }
                    
                    if (!casillas.get(casilla.getIndice() + 1).getOcupado()) {
                        casillas.get(casilla.getIndice() + 1).setValidacion(true);
                    }
                    
                    if (!casillas.get(casilla.getIndice() + 15).getOcupado()) {
                        casillas.get(casilla.getIndice() + 15).setValidacion(true);
                    }
                }
            }
        }

        if (estadoJuego.getCasillasJugadas().size() == 1) {
            Casilla primeraCasilla = estadoJuego.getCasillasJugadas().get(0);
            
            if (!casillas.get(primeraCasilla.getIndice() - 1).getOcupado()) {
                casillas.get(primeraCasilla.getIndice() - 1).setValidacion(true);
            } else {
                while (casillas.get(primeraCasilla.getIndice() - 1).getOcupado()) {
                    primeraCasilla = casillas.get(primeraCasilla.getIndice() - 1);
                }
                casillas.get(primeraCasilla.getIndice() - 1).setValidacion(true);
                primeraCasilla = estadoJuego.getCasillasJugadas().get(0);
            }
            
            if (!casillas.get(primeraCasilla.getIndice() - 15).getOcupado()) {
                casillas.get(primeraCasilla.getIndice() - 15).setValidacion(true);
                while (casillas.get(primeraCasilla.getIndice() - 15).getOcupado()) {
                    primeraCasilla = casillas.get(primeraCasilla.getIndice() - 15);
                }
                
                casillas.get(primeraCasilla.getIndice() - 15).setValidacion(true);
                primeraCasilla = estadoJuego.getCasillasJugadas().get(0);
            }

            if (!casillas.get(primeraCasilla.getIndice() + 1).getOcupado()) {
                casillas.get(primeraCasilla.getIndice() + 1).setValidacion(true);
            } else {
                while (casillas.get(primeraCasilla.getIndice() + 1).getOcupado()) {
                    primeraCasilla = casillas.get(primeraCasilla.getIndice() + 1);
                }
                casillas.get(primeraCasilla.getIndice() + 1).setValidacion(true);
                primeraCasilla = estadoJuego.getCasillasJugadas().get(0);
            }

            if (!casillas.get(primeraCasilla.getIndice() + 15).getOcupado()) {
                casillas.get(primeraCasilla.getIndice() + 15).setValidacion(true);
            } else {
                while (casillas.get(primeraCasilla.getIndice() + 15).getOcupado()) {
                    primeraCasilla = casillas.get(primeraCasilla.getIndice() + 15);
                }
                casillas.get(primeraCasilla.getIndice() + 15).setValidacion(true);
                primeraCasilla = estadoJuego.getCasillasJugadas().get(0);
            }

            if (!casillas.get(primeraCasilla.getIndice() - 1).getOcupado() && !casillas.get(primeraCasilla.getIndice() + 1).getOcupado()) {
                estadoJuego.setOrientacion(EstadoJuego.ABAJO);
            } else if (!casillas.get(primeraCasilla.getIndice() + 15).getOcupado() && !casillas.get(primeraCasilla.getIndice() - 15).getOcupado()) {
                estadoJuego.setOrientacion(EstadoJuego.OTRO_LADO);
            } else if (!casillas.get(primeraCasilla.getIndice() - 15).getOcupado() && !casillas.get(primeraCasilla.getIndice() - 1).getOcupado()) {
                estadoJuego.setOrientacion(EstadoJuego.OTRO_LADO);
            } else if (!casillas.get(primeraCasilla.getIndice() - 15).getOcupado() && !casillas.get(primeraCasilla.getIndice() + 1).getOcupado()) {
                estadoJuego.setOrientacion(EstadoJuego.ABAJO);
            } else if (!casillas.get(primeraCasilla.getIndice() + 15).getOcupado() && !casillas.get(primeraCasilla.getIndice() - 1).getOcupado()) {
                estadoJuego.setOrientacion(EstadoJuego.OTRO_LADO);
            } else if (!casillas.get(primeraCasilla.getIndice() + 15).getOcupado() && !casillas.get(primeraCasilla.getIndice() + 1).getOcupado()) {
                estadoJuego.setOrientacion(EstadoJuego.OTRO_LADO);
            }
        }

        if (estadoJuego.getCasillasJugadas().size() >= 2) {
            int ultimoIndice = estadoJuego.getCasillasJugadas().size() - 1;
            ArrayList<Casilla> casillasJugadas = estadoJuego.getCasillasJugadas();
            Collections.sort(casillasJugadas);
            int difference = Math.abs(casillasJugadas.get(0).getIndice() - casillasJugadas.get(1).getIndice());
            if (difference >= 15) {
                estadoJuego.setOrientacion(EstadoJuego.ABAJO);
                casillas.get(casillasJugadas.get(0).getIndice() - 15).setValidacion(true);
                casillas.get(casillasJugadas.get(ultimoIndice).getIndice() + 15).setValidacion(true);
            }
            if (difference < 15) {
                estadoJuego.setOrientacion(EstadoJuego.OTRO_LADO);
                casillas.get(casillasJugadas.get(0).getIndice() - 1).setValidacion(true);
                casillas.get(casillasJugadas.get(ultimoIndice).getIndice() + 1).setValidacion(true);
            }
        }

        for (Casilla casilla : casillas) {
            if (casilla.getValido()) {
                casillasValidas.add(casilla);
            }
        }      
        return casillasValidas;
    }
}