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
import Front.Puntuacion;
import Front.VentanaTablero;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ScrabbleMain {
    private Puntuacion menu;
    private Diccionario diccionario;
    private final BolsaFichas bolsaFichas;
    private Tablero tablero;
    private Jugador jugadoresActuales;
    private Jugador jug1;
    private Jugador jug2;
    private int turnoJugador = 0;
    private final EstadoJuego estadoJuego;
    private int puntuacionUltimoTurno = 0;
    private int ultimoTurnoJugado = 0;
    private final VentanaTablero ventanaTablero;
    private int restarPuntosJug1 = 0;
    private int restarPuntosJug2 = 0;
    private int fichasRestantes = 100;

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
        menu = new Puntuacion();
    }

    public void botonJuegoNuevoPressed() {
        ventanaTablero.añadirBotonJuegoNuevoActionListener((java.awt.event.ActionEvent evt) -> {
            estadoJuego.setEstadoJuego(EstadoJuego.FICHA_NO_SELECCIONADA);
            JFrame frame = new JFrame();
            frame.setAlwaysOnTop(true);
            menu.setVisible(true);
            String nombreJugador = null;
                frame.toFront();
                frame.repaint();
                while(nombreJugador == null) {
                    nombreJugador = JOptionPane.showInputDialog(frame, "Escribe el nombre del jugador 1", "Ej: Keny");
                    menu.setNombre1(nombreJugador);
                    jug1 = new Jugador(bolsaFichas, nombreJugador);
                    ventanaTablero.setNombreJugador1(nombreJugador);
                }
                
                nombreJugador = null;
                
                while(nombreJugador == null) {
                    nombreJugador = JOptionPane.showInputDialog(frame, "Escribe el nombre del jugador 2",  "Ej: Thiago");
                    menu.setNombre2(nombreJugador);
                    jug2 = new Jugador(bolsaFichas, nombreJugador);
                    ventanaTablero.setNombreJugador2(nombreJugador);
                }
                
            turnoJugador = 1;
            jugadoresActuales = jug1;

            ventanaTablero.actualizarRack(jugadoresActuales);
            ventanaTablero.setBotonJuegoNuevo1Establecido(false);
        }
        );
    }

    public void botonVerificarPressed() {
        ventanaTablero.añadirBotonVerificarActionListener((java.awt.event.ActionEvent evt) -> {
           boolean unaLetra = false;
            if (estadoJuego.isPrimerTurno()) {
                for (String w : estadoJuego.getPalabraAnotadaUltimoTurno()) {
                    if (w.length() < 2) {
                        JOptionPane.showMessageDialog(
                            ventanaTablero,
                            "La primera palabra debe tener al menos 2 letras.",
                            "Error de longitud",
                            JOptionPane.ERROR_MESSAGE
                        );
                         unaLetra = true;
                    }
                }
            }

            String[] palabras = new String[estadoJuego.getPalabraAnotadaUltimoTurno().size()];
            palabras = estadoJuego.getPalabraAnotadaUltimoTurno().toArray(palabras);
            int response = JOptionPane.showOptionDialog(ventanaTablero, "Selecciona la palabra para verificarla", "Verificar palabra",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, palabras, palabras[0]);
            
            boolean existe = diccionario.verificar(palabras[response]);
            if (existe) {
                JOptionPane.showMessageDialog(
                    ventanaTablero,
                    palabras[response] + " sí está en el diccionario. Verificación inválida."
                );
            } else {
                JOptionPane.showMessageDialog(
                    ventanaTablero,
                    palabras[response] + " no está en el diccionario. Deshaciendo turno."
                );
                turnoAtras();

            }

           
                    
        });
}
    
    

    public void botonTerminarTurnoPressed() {
        ventanaTablero.añadirBotonTerminarTurnoActionListener((java.awt.event.ActionEvent evt) -> {
            int puntosTurno = 0;

            PalabraAnotada casillasJugadas = new PalabraAnotada(estadoJuego.getCasillasJugadas(), estadoJuego.getOrientacion());
            estadoJuego.añadirPalabraAnotada(casillasJugadas, tablero);

            boolean modPalabraDetectado = false;
            ArrayList<Integer> modificadoresPuntos = new ArrayList<>();

            for (PalabraAnotada palabraActual : estadoJuego.getPalabrasAnotadas()) {

                for (Casilla casillaActual : palabraActual) {
                    if (casillaActual.getModificadorPuntos() != 0) {
                        switch (casillaActual.getModificadorPuntos()) {
                            case Tablero.DLMP: {
                                puntosTurno += casillaActual.getFichaAsignada().getPuntos() * 2;
                                break;
                            }
                            case Tablero.TLMP: {
                                puntosTurno += casillaActual.getFichaAsignada().getPuntos() * 3;
                                break;
                            }
                            case Tablero.DPMP: {
                                puntosTurno += casillaActual.getFichaAsignada().getPuntos();
                                modPalabraDetectado = true;
                                modificadoresPuntos.add(2);
                                break;
                            }
                            case Tablero.TPMP: {
                                puntosTurno += casillaActual.getFichaAsignada().getPuntos();
                                modPalabraDetectado = true;
                                modificadoresPuntos.add(3);
                                break;
                            }
                        }
                        tablero.casillas.get(casillaActual.getIndice()).setModificadorPuntuacion(0);
                    } else if (casillaActual.getModificadorPuntos() == 0) {
                        puntosTurno += casillaActual.getFichaAsignada().getPuntos();
                    }
                }
                if (modPalabraDetectado) {
                    for (Integer modificador : modificadoresPuntos) {
                        puntosTurno = puntosTurno * modificador;
                    }
                }
                jugadoresActuales.añadirPuntos(puntosTurno);
            }
            
            

            
            
            puntuacionUltimoTurno = puntosTurno;
            ultimoTurnoJugado = turnoJugador;

            estadoJuego.terminarTurno();
            
            while (jugadoresActuales.getRack().size() < 7 && bolsaFichas.tamaño() > 0) {
                jugadoresActuales.añadirFicha(bolsaFichas);
            }

            if (turnoJugador == 1) {
                ventanaTablero.actualizarRack(jug2);
                ventanaTablero.setPuntosJugador1(jug1.getPuntos());
                menu.setPuntos1(jug1.getPuntos());
                turnoJugador = 2;
                jugadoresActuales = jug2;
            } else if (turnoJugador == 2) {
                ventanaTablero.actualizarRack(jug1);
                ventanaTablero.setPuntosJugador2(jug2.getPuntos());
                menu.setPuntos2(jug2.getPuntos());
                turnoJugador = 1;
                jugadoresActuales = jug1;
            }
            
            for (Casilla casilla : tablero.casillas) {
                if (tablero.getCasillasValidas(estadoJuego).contains(casilla)) {
                    casilla.setValidacion(true);
                } else {
                    casilla.setValidacion(false);
                }
            }
            
            menu.setfichasRestantes(fichasRestantes);
            ventanaTablero.actualizarRack(jugadoresActuales);
            ventanaTablero.setBotonJuegoNuevo1Establecido(false);
            ventanaTablero.setVerificarBotonEstablecido(true);
            ventanaTablero.actualizarTablero(tablero); 
        }
        );
    }

    public void rackButton1Pressed() {
        ventanaTablero.añadirBotonRack1ActionListener((java.awt.event.ActionEvent evt) -> {
            if (estadoJuego.getEstadoJuego() == EstadoJuego.RACK_FICHA_SELECCIONADA) {
                estadoJuego.setEstadoJuego(EstadoJuego.FICHA_NO_SELECCIONADA);
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
                estadoJuego.setEstadoJuego(EstadoJuego.RACK_FICHA_SELECCIONADA);
                jugadoresActuales.setBotonRackSeleccionado((CasillaBoton) evt.getSource());

                for (Casilla casillaActual : tablero.casillas) {
                    if (casillaActual.getOcupado()) {
                        casillaActual.setValidacion(false);
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
                ventanaTablero.actualizarTablero(tablero);
            }
        }
        );
    }
    public void rackButton2Pressed() {
        ventanaTablero.añadirBotonRack2ActionListener((java.awt.event.ActionEvent evt) -> {
            if (estadoJuego.getEstadoJuego() == EstadoJuego.RACK_FICHA_SELECCIONADA) {
                estadoJuego.setEstadoJuego(EstadoJuego.FICHA_NO_SELECCIONADA);
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
                estadoJuego.setEstadoJuego(EstadoJuego.RACK_FICHA_SELECCIONADA);
                jugadoresActuales.setBotonRackSeleccionado((CasillaBoton) evt.getSource());

                for (Casilla casillaActual : tablero.casillas) {
                    if (casillaActual.getOcupado()) {
                        casillaActual.setValidacion(false);
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
                ventanaTablero.actualizarTablero(tablero);
            }
        }
        );
    }
    public void rackButton3Pressed() {
        ventanaTablero.añadirBotonRack3ActionListener((java.awt.event.ActionEvent evt) -> {
            if (estadoJuego.getEstadoJuego() == EstadoJuego.RACK_FICHA_SELECCIONADA) {
                estadoJuego.setEstadoJuego(EstadoJuego.FICHA_NO_SELECCIONADA);
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
                estadoJuego.setEstadoJuego(EstadoJuego.RACK_FICHA_SELECCIONADA);
                jugadoresActuales.setBotonRackSeleccionado((CasillaBoton) evt.getSource());

                for (Casilla casillaActual : tablero.casillas) {
                    if (casillaActual.getOcupado()) {
                        casillaActual.setValidacion(false);
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
                ventanaTablero.actualizarTablero(tablero);
            }
        }
        );
    }
    public void rackButton4Pressed() {
        ventanaTablero.añadirBotonRack4ActionListener((java.awt.event.ActionEvent evt) -> {
            if (estadoJuego.getEstadoJuego() == EstadoJuego.RACK_FICHA_SELECCIONADA) {
                estadoJuego.setEstadoJuego(EstadoJuego.FICHA_NO_SELECCIONADA);
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
                estadoJuego.setEstadoJuego(EstadoJuego.RACK_FICHA_SELECCIONADA);
                jugadoresActuales.setBotonRackSeleccionado((CasillaBoton) evt.getSource());

                for (Casilla casillaActual : tablero.casillas) {
                    if (casillaActual.getOcupado()) {
                        casillaActual.setValidacion(false);
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
                ventanaTablero.actualizarTablero(tablero);
            }
        }
        );
    }
    public void rackButton5Pressed() {
        ventanaTablero.añadirBotonRack5ActionListener((java.awt.event.ActionEvent evt) -> {
            if (estadoJuego.getEstadoJuego() == EstadoJuego.RACK_FICHA_SELECCIONADA) {
                estadoJuego.setEstadoJuego(EstadoJuego.FICHA_NO_SELECCIONADA);
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
                estadoJuego.setEstadoJuego(EstadoJuego.RACK_FICHA_SELECCIONADA);
                jugadoresActuales.setBotonRackSeleccionado((CasillaBoton) evt.getSource());

                for (Casilla casillaActual : tablero.casillas) {
                    if (casillaActual.getOcupado()) {
                        casillaActual.setValidacion(false);
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

                ventanaTablero.actualizarTablero(tablero);
            }
        }
        );
    }
    public void rackButton6Pressed() {
        ventanaTablero.añadirBotonRack6ActionListener((java.awt.event.ActionEvent evt) -> {
            if (estadoJuego.getEstadoJuego() == EstadoJuego.RACK_FICHA_SELECCIONADA) {
                estadoJuego.setEstadoJuego(EstadoJuego.FICHA_NO_SELECCIONADA);
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
                estadoJuego.setEstadoJuego(EstadoJuego.RACK_FICHA_SELECCIONADA);
                jugadoresActuales.setBotonRackSeleccionado((CasillaBoton) evt.getSource());

                for (Casilla casillaActual : tablero.casillas) {
                    if (casillaActual.getOcupado()) {
                        casillaActual.setValidacion(false);
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

                ventanaTablero.actualizarTablero(tablero);
            }
        }
        ); 
    }
    public void rackButton7Pressed() {
        ventanaTablero.añadirBotonRack7ActionListener((java.awt.event.ActionEvent evt) -> {

            if (estadoJuego.getEstadoJuego() == EstadoJuego.RACK_FICHA_SELECCIONADA) {
                estadoJuego.setEstadoJuego(EstadoJuego.FICHA_NO_SELECCIONADA);
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
                estadoJuego.setEstadoJuego(EstadoJuego.RACK_FICHA_SELECCIONADA);
                jugadoresActuales.setBotonRackSeleccionado((CasillaBoton) evt.getSource());

                for (Casilla casillaActual : tablero.casillas) {
                    if (casillaActual.getOcupado()) {
                        casillaActual.setValidacion(false);
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

                ventanaTablero.actualizarTablero(tablero);
            }
        }
        );
    }
    public void spaceButtonPressed() {
        ventanaTablero.añadirListaCasillaBotonActionListener((java.awt.event.ActionEvent evt) -> {

            CasillaBoton botonActual = (CasillaBoton) evt.getSource();
            Casilla casillaActual = botonActual.getCasillaAsignada();

            if (!casillaActual.getOcupado() && estadoJuego.getEstadoJuego() == EstadoJuego.RACK_FICHA_SELECCIONADA) {

                Ficha fichaActual = jugadoresActuales.getBotonRackSeleccionado().getFichaAsignada();

                if (fichaActual.getPuntos() == 0) {
                    String nuevaLetra = "empty";
                    nuevaLetra = JOptionPane.showInputDialog(ventanaTablero, "Comodín: Escribe la letra quieres que sea esta ficha");
                    String letra = nuevaLetra;
                    fichaActual.setLetra(letra);
                }

                casillaActual.setFichaAsignada(fichaActual);

                fichaActual.setCasillaAsignada(casillaActual);

                estadoJuego.setEstadoJuego(EstadoJuego.FICHA_NO_SELECCIONADA);
                estadoJuego.añadirCasillaJugada(casillaActual);
                jugadoresActuales.eliminarFicha(fichaActual);
                fichasRestantes--;
                for (Casilla casilla : tablero.casillas) {
                    if (tablero.getCasillasValidas(estadoJuego).contains(casilla)) {
                        casilla.setValidacion(true);
                    } else {
                        casilla.setValidacion(false);
                    }
                }

                botonActual.setFichaAsignada(fichaActual);

                ventanaTablero.setCursor(Cursor.getDefaultCursor());
                ventanaTablero.actualizarTablero(tablero);
                ventanaTablero.actualizarRack(jugadoresActuales);

            } else if (casillaActual.getOcupado() && estadoJuego.getEstadoJuego() == EstadoJuego.FICHA_NO_SELECCIONADA) {
                Ficha fichaActual = casillaActual.getFichaAsignada();
                estadoJuego.eliminarCasillaJugada(casillaActual);
                jugadoresActuales.añadirFichaEspecifica(fichaActual);
                jugadoresActuales.setBotonRackSeleccionado(null);
                  if (fichasRestantes < 100) {
                     fichasRestantes++;
                }
                if (fichaActual.getPuntos() == 0) {
                    fichaActual.setLetra(" ");
                }
                fichaActual.eliminarCasillaAsignada();
                casillaActual.eliminarFichaAsignada();

                for (Casilla casilla : tablero.casillas) {
                    if (tablero.getCasillasValidas(estadoJuego).contains(casilla)) {
                        casilla.setValidacion(true);
                    } else {
                        casilla.setValidacion(false);
                    }
                }

                botonActual.eliminarFichaAsignada();

                ventanaTablero.setBotonRack1Enabled(true);
                ventanaTablero.setBotonRack2Enabled(true);
                ventanaTablero.setBotonRack3Enabled(true);
                ventanaTablero.setBotonRack4Enabled(true);
                ventanaTablero.setBotonRack5Enabled(true);
                ventanaTablero.setBotonRack6Enabled(true);
                ventanaTablero.setBotonRack7Enabled(true);
                ventanaTablero.actualizarRack(jugadoresActuales);
                ventanaTablero.actualizarTablero(tablero);
            }
        });
    }
    
    private void turnoAtras() {
        restarPuntosJug1 = 0;
        restarPuntosJug2 = 0;
    if (turnoJugador == 1) {
                restarPuntosJug2 = puntuacionUltimoTurno;    
                ventanaTablero.setPuntosJugador2(jug2.getPuntos() - restarPuntosJug2);
                menu.setPuntos2(jug2.getPuntos() - restarPuntosJug2);
            } else {
                restarPuntosJug1 = puntuacionUltimoTurno;
                ventanaTablero.setPuntosJugador1(jug1.getPuntos() - restarPuntosJug1);
                menu.setPuntos1(jug1.getPuntos() - restarPuntosJug1);
        }
    
    for (Casilla casilla : estadoJuego.getCasillasJugadas()) {
            Ficha tile = casilla.getFichaAsignada();
            if (tile != null) {
                casilla.eliminarFichaAsignada();
                if (ultimoTurnoJugado == 1) {
                    jug1.añadirFichaEspecifica(tile);                    
                } else {
                    jug2.añadirFichaEspecifica(tile);
                }
            }
        }
    


    estadoJuego.terminarTurno();
        while (jugadoresActuales.getRack().size() < 7 && bolsaFichas.tamaño() > 0) {
            jugadoresActuales.añadirFicha(bolsaFichas);
        }

    ventanaTablero.actualizarRack(jugadoresActuales);
    ventanaTablero.actualizarTablero(tablero);
    ventanaTablero.setVerificarBotonEstablecido(false);

}

    
    
}
