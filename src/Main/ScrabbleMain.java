package Main;

import Back.BolsaFichas;
import Back.Tablero;
import Back.EstadoJuego;
import Back.Ficha;
import Back.Jugador;
import Back.PalabraAnotada;
import Back.Casilla;
import Back.CasillaBoton;
import Back.Diccionario;
import Front.VentanaTablero;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ScrabbleMain {

    private Diccionario diccionario;
    private final BolsaFichas bolsaFichas;
    private final Tablero tablero;
    private Jugador jugadoresActuales;
    private Jugador jug1;
    private Jugador jug2;
    private int turnoJugador = 0;
    private final EstadoJuego estadoJuego;

    private final VentanaTablero ventanaTablero;

    public ScrabbleMain() {
        try {
            this.diccionario = new Diccionario();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Scrabble.class.getName()).log(Level.SEVERE, null, ex);
        }

        bolsaFichas = new BolsaFichas();

        tablero = new Tablero();

        estadoJuego = new EstadoJuego(EstadoJuego.JUEGO_INICIADO);

        ventanaTablero = new VentanaTablero();
        ventanaTablero.initBasicComponents(tablero);

        ventanaTablero.setVerificarBotonEstablecido(false);

        ventanaTablero.setVisible(true);

    }

    public void startNewGameButtonPressed() {
        ventanaTablero.añadirBotonJuegoNuevoActionListener((java.awt.event.ActionEvent evt) -> {

            estadoJuego.setGameState(EstadoJuego.FICHA_NO_SELECCIONADA);

            JFrame frame = new JFrame();
            frame.setAlwaysOnTop(true);
            String nombreJugador = "";

                frame.toFront();
                frame.repaint();
                nombreJugador = JOptionPane.showInputDialog(frame, "Escribe el nombre del jugador 1");
                jug1 = new Jugador(bolsaFichas, nombreJugador);
                nombreJugador = JOptionPane.showInputDialog(frame, "Escribe el nombre del jugador 2");
                jug2 = new Jugador(bolsaFichas, nombreJugador);
            

            turnoJugador = 1;
            jugadoresActuales = jug1;

            ventanaTablero.actualizarRack(jugadoresActuales);
            ventanaTablero.setBotonJuegoNuevo1Establecido(false);
        }
        );
    }

    public void challengeButtonPressed() {
        ventanaTablero.añadirBotonVerificarActionListener((java.awt.event.ActionEvent evt) -> {

            String[] words = new String[estadoJuego.getWordsScoredLastTurn().size()];
            words = estadoJuego.getWordsScoredLastTurn().toArray(words);

            int response = JOptionPane.showOptionDialog(ventanaTablero, "Select The word you want to challenge.", "Challenge Word",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, words, words[0]);

            if (diccionario.verificar(words[response])) {
                JOptionPane.showMessageDialog(ventanaTablero,
                        words[response] + " is in the wordlist! Challenge Invalid!");
                ventanaTablero.setVerificarBotonEstablecido(false);
            } else if (!diccionario.verificar(words[response])) {
                JOptionPane.showMessageDialog(ventanaTablero,
                        words[response] + " is not in the wordlist! Challenge Valid!");
                undoLastTurn();
            }

        }
        );
    }

    public void endTurnButtonPressed() {
        ventanaTablero.añadirBotonTerminarTurnoActionListener((java.awt.event.ActionEvent evt) -> {

            int turnScore = 0;

            PalabraAnotada playedSpaces = new PalabraAnotada(estadoJuego.getCasillasJugadas(), estadoJuego.getOrientacion());
            estadoJuego.añadirPalabraAnotada(playedSpaces, tablero);

            boolean wordModifierDetected = false;
            ArrayList<Integer> scoreModifiers = new ArrayList<>();

            for (PalabraAnotada currentWord : estadoJuego.getPalabrasAnotadas()) {

                for (Casilla currentSpace : currentWord) {
                    if (currentSpace.getModificadorPuntos() != 0) {
                        switch (currentSpace.getModificadorPuntos()) {
                            case Tablero.DLMP: {
                                turnScore += currentSpace.getFichaAsignada().getPuntos() * 2;
                                break;
                            }
                            case Tablero.TLMP: {
                                turnScore += currentSpace.getFichaAsignada().getPuntos() * 3;
                                break;
                            }
                            case Tablero.DPMP: {
                                turnScore += currentSpace.getFichaAsignada().getPuntos();
                                wordModifierDetected = true;
                                scoreModifiers.add(2);
                                break;
                            }
                            case Tablero.TPMP: {
                                turnScore += currentSpace.getFichaAsignada().getPuntos();
                                wordModifierDetected = true;
                                scoreModifiers.add(3);
                                break;
                            }
                        }
                        tablero.casillas.get(currentSpace.getIndice()).setModificadorPuntuacion(0);
                    } else if (currentSpace.getModificadorPuntos() == 0) {
                        turnScore += currentSpace.getFichaAsignada().getPuntos();
                    }
                }
                if (wordModifierDetected) {
                    for (Integer modifier : scoreModifiers) {
                        turnScore = turnScore * modifier;
                    }
                }
                jugadoresActuales.añadirPuntos(turnScore);
            }

            for (PalabraAnotada wordList : estadoJuego.getPalabrasAnotadas()) {
                for (Casilla space : wordList) {
                    System.out.print(space.getFichaAsignada().getLetra());
                }
            }

            estadoJuego.endTurn();

            while (jugadoresActuales.getRack().size() < 7 && bolsaFichas.tamaño() > 0) {
                jugadoresActuales.añadirFicha(bolsaFichas);
            }

            if (turnoJugador == 1) {
                ventanaTablero.actualizarRack(jug2);
                turnoJugador = 2;
                jugadoresActuales = jug2;
            } else if (turnoJugador == 2) {
                ventanaTablero.actualizarRack(jug1);
                turnoJugador = 1;
                jugadoresActuales = jug1;
            }

            for (Casilla space : tablero.casillas) {
                if (tablero.getCasillasValidas(estadoJuego).contains(space)) {
                    space.setValidacion(true);
                } else {
                    space.setValidacion(false);
                }
            }
            
            ventanaTablero.setPuntosJugador1(jug1.getPuntos());
            ventanaTablero.setPuntosJugador2(jug2.getPuntos());
            ventanaTablero.actualizarRack(jugadoresActuales);
            ventanaTablero.setBotonJuegoNuevo1Establecido(false);
            ventanaTablero.setVerificarBotonEstablecido(true);
            ventanaTablero.updateBoard(tablero);
        }
        );
    }

    public void rackButton1Pressed() {
        ventanaTablero.añadirBotonRack1ActionListener((java.awt.event.ActionEvent evt) -> {
            if (estadoJuego.getGameState() == EstadoJuego.RACK_FICHA_SELECCIONADA) {
                estadoJuego.setGameState(EstadoJuego.FICHA_NO_SELECCIONADA);
                jugadoresActuales.setBotonRackSeleccionado(null);
                ventanaTablero.setBotonRack1bg(java.awt.Color.orange);
                ventanaTablero.setBotonRack2Enabled(true);
                ventanaTablero.setBotonRack3Enabled(true);
                ventanaTablero.setBotonRack4Enabled(true);
                ventanaTablero.setBotonRack5Enabled(true);
                ventanaTablero.setBotonRack6Enabled(true);
                ventanaTablero.setBotonRack7Enabled(true);
                ventanaTablero.setCursor(Cursor.getDefaultCursor());
            } else {
                estadoJuego.setGameState(EstadoJuego.RACK_FICHA_SELECCIONADA);
                jugadoresActuales.setBotonRackSeleccionado((CasillaBoton) evt.getSource());

                for (Casilla currentSpace : tablero.casillas) {
                    if (currentSpace.getOcupado()) {
                        currentSpace.setValidacion(false);
                    }
                }
                
                ventanaTablero.setBotonRack1bg(java.awt.Color.yellow);
                ventanaTablero.setBotonRack2Enabled(false);
                ventanaTablero.setBotonRack3Enabled(false);
                ventanaTablero.setBotonRack4Enabled(false);
                ventanaTablero.setBotonRack5Enabled(false);
                ventanaTablero.setBotonRack6Enabled(false);
                ventanaTablero.setBotonRack7Enabled(false);
                ventanaTablero.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                        new ImageIcon("src/Front/Scrabble_icon.png").getImage(),
                        new Point(0, 0), "custom cursor"));
                ventanaTablero.updateBoard(tablero);
            }
        }
        );
    }
    public void rackButton2Pressed() {
        ventanaTablero.añadirBotonRack2ActionListener((java.awt.event.ActionEvent evt) -> {
            if (estadoJuego.getGameState() == EstadoJuego.RACK_FICHA_SELECCIONADA) {
                estadoJuego.setGameState(EstadoJuego.FICHA_NO_SELECCIONADA);
                jugadoresActuales.setBotonRackSeleccionado(null);
                ventanaTablero.setBotonRack1Enabled(true);
                ventanaTablero.setBotonRack2bg(java.awt.Color.orange);
                ventanaTablero.setBotonRack3Enabled(true);
                ventanaTablero.setBotonRack4Enabled(true);
                ventanaTablero.setBotonRack5Enabled(true);
                ventanaTablero.setBotonRack6Enabled(true);
                ventanaTablero.setBotonRack7Enabled(true);
                ventanaTablero.setCursor(Cursor.getDefaultCursor());
            } else {
                estadoJuego.setGameState(EstadoJuego.RACK_FICHA_SELECCIONADA);
                jugadoresActuales.setBotonRackSeleccionado((CasillaBoton) evt.getSource());

                for (Casilla currentSpace : tablero.casillas) {
                    if (currentSpace.getOcupado()) {
                        currentSpace.setValidacion(false);
                    }
                }
                
                ventanaTablero.setBotonRack1Enabled(false);
                ventanaTablero.setBotonRack2bg(java.awt.Color.yellow);
                ventanaTablero.setBotonRack3Enabled(false);
                ventanaTablero.setBotonRack4Enabled(false);
                ventanaTablero.setBotonRack5Enabled(false);
                ventanaTablero.setBotonRack6Enabled(false);
                ventanaTablero.setBotonRack7Enabled(false);
                ventanaTablero.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                        new ImageIcon("src/Front/Scrabble_icon.png").getImage(),
                        new Point(0, 0), "custom cursor"));
                ventanaTablero.updateBoard(tablero);
            }
        }
        );
    }
    public void rackButton3Pressed() {
        ventanaTablero.añadirBotonRack3ActionListener((java.awt.event.ActionEvent evt) -> {
            if (estadoJuego.getGameState() == EstadoJuego.RACK_FICHA_SELECCIONADA) {
                estadoJuego.setGameState(EstadoJuego.FICHA_NO_SELECCIONADA);
                jugadoresActuales.setBotonRackSeleccionado(null);
                ventanaTablero.setBotonRack1Enabled(true);
                ventanaTablero.setBotonRack2Enabled(true);
                ventanaTablero.setBotonRack3bg(java.awt.Color.orange);
                ventanaTablero.setBotonRack4Enabled(true);
                ventanaTablero.setBotonRack5Enabled(true);
                ventanaTablero.setBotonRack6Enabled(true);
                ventanaTablero.setBotonRack7Enabled(true);
                ventanaTablero.setCursor(Cursor.getDefaultCursor());
            } else {
                estadoJuego.setGameState(EstadoJuego.RACK_FICHA_SELECCIONADA);
                jugadoresActuales.setBotonRackSeleccionado((CasillaBoton) evt.getSource());

                for (Casilla currentSpace : tablero.casillas) {
                    if (currentSpace.getOcupado()) {
                        currentSpace.setValidacion(false);
                    }
                }
                ventanaTablero.setBotonRack1Enabled(false);
                ventanaTablero.setBotonRack2Enabled(false);
                ventanaTablero.setBotonRack3bg(java.awt.Color.yellow);
                ventanaTablero.setBotonRack4Enabled(false);
                ventanaTablero.setBotonRack5Enabled(false);
                ventanaTablero.setBotonRack6Enabled(false);
                ventanaTablero.setBotonRack7Enabled(false);
                ventanaTablero.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                        new ImageIcon("src/Front/Scrabble_icon.png").getImage(),
                        new Point(0, 0), "custom cursor"));
                ventanaTablero.updateBoard(tablero);
            }
        }
        );
    }
    public void rackButton4Pressed() {
        ventanaTablero.añadirBotonRack4ActionListener((java.awt.event.ActionEvent evt) -> {
            if (estadoJuego.getGameState() == EstadoJuego.RACK_FICHA_SELECCIONADA) {
                estadoJuego.setGameState(EstadoJuego.FICHA_NO_SELECCIONADA);
                jugadoresActuales.setBotonRackSeleccionado(null);
                ventanaTablero.setBotonRack1Enabled(true);
                ventanaTablero.setBotonRack2Enabled(true);
                ventanaTablero.setBotonRack3Enabled(true);
                ventanaTablero.setBotonRack4bg(java.awt.Color.orange);
                ventanaTablero.setBotonRack5Enabled(true);
                ventanaTablero.setBotonRack6Enabled(true);
                ventanaTablero.setBotonRack7Enabled(true);
                ventanaTablero.setCursor(Cursor.getDefaultCursor());
            } else {
                estadoJuego.setGameState(EstadoJuego.RACK_FICHA_SELECCIONADA);
                jugadoresActuales.setBotonRackSeleccionado((CasillaBoton) evt.getSource());

                for (Casilla currentSpace : tablero.casillas) {
                    if (currentSpace.getOcupado()) {
                        currentSpace.setValidacion(false);
                    }
                }
                ventanaTablero.setBotonRack1Enabled(false);
                ventanaTablero.setBotonRack2Enabled(false);
                ventanaTablero.setBotonRack3Enabled(false);
                ventanaTablero.setBotonRack4bg(java.awt.Color.yellow);
                ventanaTablero.setBotonRack5Enabled(false);
                ventanaTablero.setBotonRack6Enabled(false);
                ventanaTablero.setBotonRack7Enabled(false);
                ventanaTablero.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                        new ImageIcon("src/Front/Scrabble_icon.png").getImage(),
                        new Point(0, 0), "custom cursor"));
                ventanaTablero.updateBoard(tablero);
            }
        }
        );
    }
    public void rackButton5Pressed() {
        ventanaTablero.añadirBotonRack5ActionListener((java.awt.event.ActionEvent evt) -> {
            if (estadoJuego.getGameState() == EstadoJuego.RACK_FICHA_SELECCIONADA) {
                estadoJuego.setGameState(EstadoJuego.FICHA_NO_SELECCIONADA);
                jugadoresActuales.setBotonRackSeleccionado(null);
                ventanaTablero.setBotonRack1Enabled(true);
                ventanaTablero.setBotonRack2Enabled(true);
                ventanaTablero.setBotonRack3Enabled(true);
                ventanaTablero.setBotonRack4Enabled(true);
                ventanaTablero.setBotonRack5bg(java.awt.Color.orange);
                ventanaTablero.setBotonRack6Enabled(true);
                ventanaTablero.setBotonRack7Enabled(true);
                ventanaTablero.setCursor(Cursor.getDefaultCursor());
            } else {
                estadoJuego.setGameState(EstadoJuego.RACK_FICHA_SELECCIONADA);
                jugadoresActuales.setBotonRackSeleccionado((CasillaBoton) evt.getSource());

                for (Casilla currentSpace : tablero.casillas) {
                    if (currentSpace.getOcupado()) {
                        currentSpace.setValidacion(false);
                    }
                }
                ventanaTablero.setBotonRack1Enabled(false);
                ventanaTablero.setBotonRack2Enabled(false);
                ventanaTablero.setBotonRack3Enabled(false);
                ventanaTablero.setBotonRack4Enabled(false);
                ventanaTablero.setBotonRack5bg(java.awt.Color.yellow);
                ventanaTablero.setBotonRack6Enabled(false);
                ventanaTablero.setBotonRack7Enabled(false);


                ventanaTablero.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                        new ImageIcon("src/Front/Scrabble_icon.png").getImage(),
                        new Point(0, 0), "custom cursor"));

                ventanaTablero.updateBoard(tablero);
            }
        }
        );
    }
    public void rackButton6Pressed() {
        ventanaTablero.añadirBotonRack6ActionListener((java.awt.event.ActionEvent evt) -> {
            if (estadoJuego.getGameState() == EstadoJuego.RACK_FICHA_SELECCIONADA) {
                estadoJuego.setGameState(EstadoJuego.FICHA_NO_SELECCIONADA);
                jugadoresActuales.setBotonRackSeleccionado(null);
                ventanaTablero.setBotonRack1Enabled(true);
                ventanaTablero.setBotonRack2Enabled(true);
                ventanaTablero.setBotonRack3Enabled(true);
                ventanaTablero.setBotonRack4Enabled(true);
                ventanaTablero.setBotonRack5Enabled(true);
                ventanaTablero.setBotonRack6bg(java.awt.Color.orange);
                ventanaTablero.setBotonRack7Enabled(true);
                ventanaTablero.setCursor(Cursor.getDefaultCursor());
            } else {
                estadoJuego.setGameState(EstadoJuego.RACK_FICHA_SELECCIONADA);
                jugadoresActuales.setBotonRackSeleccionado((CasillaBoton) evt.getSource());

                for (Casilla currentSpace : tablero.casillas) {
                    if (currentSpace.getOcupado()) {
                        currentSpace.setValidacion(false);
                    }
                }
                ventanaTablero.setBotonRack1Enabled(false);
                ventanaTablero.setBotonRack2Enabled(false);
                ventanaTablero.setBotonRack3Enabled(false);
                ventanaTablero.setBotonRack4Enabled(false);
                ventanaTablero.setBotonRack5Enabled(false);
                ventanaTablero.setBotonRack6bg(java.awt.Color.yellow);
                ventanaTablero.setBotonRack7Enabled(false);


                ventanaTablero.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                        new ImageIcon("src/Front/Scrabble_icon.png").getImage(),
                        new Point(0, 0), "custom cursor"));

                ventanaTablero.updateBoard(tablero);
            }
        }
        ); 
    }
    public void rackButton7Pressed() {
        ventanaTablero.añadirBotonRack7ActionListener((java.awt.event.ActionEvent evt) -> {

            if (estadoJuego.getGameState() == EstadoJuego.RACK_FICHA_SELECCIONADA) {
                estadoJuego.setGameState(EstadoJuego.FICHA_NO_SELECCIONADA);
                jugadoresActuales.setBotonRackSeleccionado(null);
                ventanaTablero.setBotonRack1Enabled(true);
                ventanaTablero.setBotonRack2Enabled(true);
                ventanaTablero.setBotonRack3Enabled(true);
                ventanaTablero.setBotonRack4Enabled(true);
                ventanaTablero.setBotonRack5Enabled(true);
                ventanaTablero.setBotonRack6Enabled(true);
                ventanaTablero.setBotonRack7bg(java.awt.Color.orange);
                ventanaTablero.setCursor(Cursor.getDefaultCursor());
            } else {
                estadoJuego.setGameState(EstadoJuego.RACK_FICHA_SELECCIONADA);
                jugadoresActuales.setBotonRackSeleccionado((CasillaBoton) evt.getSource());

                for (Casilla currentSpace : tablero.casillas) {
                    if (currentSpace.getOcupado()) {
                        currentSpace.setValidacion(false);
                    }
                }
                ventanaTablero.setBotonRack1Enabled(false);
                ventanaTablero.setBotonRack2Enabled(false);
                ventanaTablero.setBotonRack3Enabled(false);
                ventanaTablero.setBotonRack4Enabled(false);
                ventanaTablero.setBotonRack5Enabled(false);
                ventanaTablero.setBotonRack6Enabled(false);
                ventanaTablero.setBotonRack7bg(java.awt.Color.yellow);
                ventanaTablero.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                        new ImageIcon("src/Front/Scrabble_icon.png").getImage(),
                        new Point(0, 0), "custom cursor"));

                ventanaTablero.updateBoard(tablero);
            }
        }
        );
    }
    public void spaceButtonPressed() {
        ventanaTablero.añadirListaCasillaBotonActionListener((java.awt.event.ActionEvent evt) -> {

            CasillaBoton currentButton = (CasillaBoton) evt.getSource();
            Casilla currentSpace = currentButton.getCasillaAsignada();

            if (!currentSpace.getOcupado() && estadoJuego.getGameState() == EstadoJuego.RACK_FICHA_SELECCIONADA) {

                Ficha currentTile = jugadoresActuales.getBotonRackSeleccionado().getFichaAsignada();

                if (currentTile.getPuntos() == 0) {
                    String newLetter = "empty";
                    newLetter = JOptionPane.showInputDialog(ventanaTablero, "Enter the letter for the tile");
                    String letra = newLetter;
                    currentTile.setLetra(letra);
                }

                currentSpace.setAssignedTile(currentTile);

                currentTile.setCasillaAsignada(currentSpace);

                estadoJuego.setGameState(EstadoJuego.FICHA_NO_SELECCIONADA);
                estadoJuego.addPlayedSpace(currentSpace);
                jugadoresActuales.eliminarFicha(currentTile);

                for (Casilla space : tablero.casillas) {
                    if (tablero.getCasillasValidas(estadoJuego).contains(space)) {
                        space.setValidacion(true);
                    } else {
                        space.setValidacion(false);
                    }
                }

                currentButton.setFichaAsignada(currentTile);

                ventanaTablero.setCursor(Cursor.getDefaultCursor());
                ventanaTablero.updateBoard(tablero);
                ventanaTablero.actualizarRack(jugadoresActuales);

            } else if (currentSpace.getOcupado() && estadoJuego.getGameState() == EstadoJuego.FICHA_NO_SELECCIONADA) {
                Ficha currentTile = currentSpace.getFichaAsignada();
                estadoJuego.removePlayedSpace(currentSpace);
                jugadoresActuales.añadirFichaEspecifica(currentTile);
                jugadoresActuales.setBotonRackSeleccionado(null);

                if (currentTile.getPuntos() == 0) {
                    currentTile.setLetra(" ");
                }
                currentTile.eliminarCasillaAsignada();
                currentSpace.eliminarFichaAsignada();

                for (Casilla space : tablero.casillas) {
                    if (tablero.getCasillasValidas(estadoJuego).contains(space)) {
                        space.setValidacion(true);
                    } else {
                        space.setValidacion(false);
                    }
                }

                currentButton.eliminarFichaAsignada();

                ventanaTablero.setBotonRack1Enabled(true);
                ventanaTablero.setBotonRack2Enabled(true);
                ventanaTablero.setBotonRack3Enabled(true);
                ventanaTablero.setBotonRack4Enabled(true);
                ventanaTablero.setBotonRack5Enabled(true);
                ventanaTablero.setBotonRack6Enabled(true);
                ventanaTablero.setBotonRack7Enabled(true);
                ventanaTablero.actualizarRack(jugadoresActuales);
                ventanaTablero.updateBoard(tablero);
            }
        });
    }

    private void undoLastTurn() {

    }

}
