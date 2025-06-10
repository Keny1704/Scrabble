package Back;

import Front.VentanaTablero;
import java.awt.Color;

public class CasillaBoton extends javax.swing.JButton {

    private Ficha fichaAsignada;
    private Casilla casillaAsignada;

    public CasillaBoton() {
        fichaAsignada = null;
        casillaAsignada = null;
    }

    public CasillaBoton(Ficha nuevaFicha) {
        setFichaAsignada(nuevaFicha);
        casillaAsignada = null;
    }

    public final void setFichaAsignada(Ficha nuevaFicha) {
        fichaAsignada = nuevaFicha;
        setText(fichaAsignada.toString());
        setBackground(Color.ORANGE);
    }

    public void eliminarFichaAsignada() {
        fichaAsignada = null;
        setText("");
        setBackground(VentanaTablero.BOTON_COLOR_ORIGINAL);
    }

    public Ficha getFichaAsignada() {
        return fichaAsignada;
    }

    public void setCasillaAsignada(Casilla nuevaCasilla) {
        casillaAsignada = nuevaCasilla;
    }

    public Casilla getCasillaAsignada() {
        return casillaAsignada;
    }

}
