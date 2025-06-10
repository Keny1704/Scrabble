package Back;

import java.util.ArrayList;
import java.util.Collections;

public class EstadoJuego {

    public final static int JUEGO_INICIADO = 0;
    public final static int FICHA_NO_SELECCIONADA = 1;
    public final static int RACK_FICHA_SELECCIONADA = 2;
    public final static int OTRO_LADO = 0;
    public final static int ABAJO = 1;

    private int estadoJuegoActual;
    private final boolean ejecutandose;
    private PalabraAnotada casillasJugadasEsteTurno;
    private ArrayList<PalabraAnotada> palabrasAnotadas;
    private ArrayList<String> palabrasAnotadasUltimoTurno;

    public EstadoJuego(int nuevoEstadoJuego) {
        estadoJuegoActual = nuevoEstadoJuego;
        ejecutandose = true;
        casillasJugadasEsteTurno = new PalabraAnotada(OTRO_LADO);
        palabrasAnotadas = new ArrayList<>();
        palabrasAnotadasUltimoTurno = new ArrayList<>();
    }

    public ArrayList<String> getWordsScoredLastTurn() {
        return palabrasAnotadasUltimoTurno;
    }

    public void setGameState(int newGameState) {
        estadoJuegoActual = newGameState;
    }

    public int getGameState() {
        return estadoJuegoActual;
    }

    public boolean isRunning() {
        return ejecutandose;
    }

    public boolean addPlayedSpace(Casilla space) {
        return casillasJugadasEsteTurno.add(space);
    }

    public boolean removePlayedSpace(Casilla space) {
        return casillasJugadasEsteTurno.remove(space);
    }

    public ArrayList<Casilla> getCasillasJugadas() {
        return casillasJugadasEsteTurno;
    }

    public void endTurn() {
        this.casillasJugadasEsteTurno.clear();
        this.palabrasAnotadas.clear();
    }

    public void setOrientacion(int nuevaOrientacion) {
        casillasJugadasEsteTurno.setOrientacion(nuevaOrientacion);
    }

    public int getOrientacion() {
        return casillasJugadasEsteTurno.getOrientacion();
    }

    public ArrayList<PalabraAnotada> getPalabrasAnotadas() {
        return palabrasAnotadas;
    }

    public void añadirPalabraAnotada(PalabraAnotada palabraAnotada, Tablero tablero) {
        Collections.sort(palabraAnotada);
        Casilla casillaActual = palabraAnotada.get(0);

        if (palabraAnotada.getOrientacion() == EstadoJuego.OTRO_LADO) {
            while ((casillaActual.getIndice() % 15) - 1 >= 0) {
                casillaActual = tablero.casillas.get(casillaActual.getIndice() - 1);

                if (casillaActual.getOcupado()) {
                    palabraAnotada.add(casillaActual);
                } else {
                    break;
                }
            }

            casillaActual = palabraAnotada.get(0);
            while (((casillaActual.getIndice() % 15) + 1) <= 14) {

                if (casillaActual.getOcupado() && !palabraAnotada.contains(casillaActual)) {
                    palabraAnotada.add(casillaActual);
                } else if (casillaActual.getOcupado() && casillasJugadasEsteTurno.contains(casillaActual)) {
                    Casilla casillaArriba = tablero.casillas.get(casillaActual.getIndice() - 15);
                    Casilla casillaAbajo = tablero.casillas.get(casillaActual.getIndice() + 15);

                    if (casillaArriba.getOcupado()) {
                        PalabraAnotada nuevaPalabra = new PalabraAnotada(EstadoJuego.ABAJO);
                        nuevaPalabra.add(casillaArriba);
                        añadirPalabraAnotada(nuevaPalabra, tablero);
                    } else if (casillaAbajo.getOcupado()) {
                        PalabraAnotada newWord = new PalabraAnotada(EstadoJuego.ABAJO);
                        newWord.add(casillaAbajo);
                        añadirPalabraAnotada(newWord, tablero);
                    }
                } else if (!casillaActual.getOcupado()) {
                    Collections.sort(palabraAnotada);
                    palabrasAnotadas.add(palabraAnotada);
                    break;
                }

                casillaActual = tablero.casillas.get(casillaActual.getIndice() + 1);
            }

        } else if (palabraAnotada.getOrientacion() == EstadoJuego.ABAJO) {
            while (casillaActual.getIndice() - 15 >= 0) {
                casillaActual = tablero.casillas.get(casillaActual.getIndice() - 15);
                
                if (casillaActual.getOcupado()) {
                    palabraAnotada.add(casillaActual);
                } else {
                    break;
                }
            }

            casillaActual = palabraAnotada.get(0);
            while ((casillaActual.getIndice() + 15) <= 224) {
                if (casillaActual.getOcupado() && !palabraAnotada.contains(casillaActual)) {
                    palabraAnotada.add(casillaActual);
                } else if (casillaActual.getOcupado() && casillasJugadasEsteTurno.contains(casillaActual)) {
                    Casilla casillaIzquierda = tablero.casillas.get(casillaActual.getIndice() - 1);
                    Casilla casillaDerecha = tablero.casillas.get(casillaActual.getIndice() + 1);

                    if (casillaIzquierda.getOcupado()) {
                        PalabraAnotada nuevaPalabra = new PalabraAnotada(EstadoJuego.OTRO_LADO);
                        nuevaPalabra.add(casillaIzquierda);
                        añadirPalabraAnotada(nuevaPalabra, tablero);
                    } else if (casillaDerecha.getOcupado()) {
                        PalabraAnotada nuevaPalabra = new PalabraAnotada(EstadoJuego.OTRO_LADO);
                        nuevaPalabra.add(casillaDerecha);
                        añadirPalabraAnotada(nuevaPalabra, tablero);
                    }

                } else if (!casillaActual.getOcupado()) {
                    Collections.sort(palabraAnotada);
                    palabrasAnotadas.add(palabraAnotada);
                    break;
                }

                casillaActual = tablero.casillas.get(casillaActual.getIndice() + 15);
            }
        }

        palabrasAnotadasUltimoTurno.clear();

        for (PalabraAnotada listaPalabras : palabrasAnotadas) {
            String palabra = new String();
            for (Casilla casilla : listaPalabras) {
                palabra = palabra.concat(casilla.getFichaAsignada().getLetra());
            }
            palabrasAnotadasUltimoTurno.add(palabra);
        }
    }
}
