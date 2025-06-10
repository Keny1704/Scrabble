package Back;

public class Casilla implements Comparable<Casilla> {

    private Ficha fichaAsignada;
    private final int indice;
    private int modificadorPuntos;
    private String label;
    private boolean valido;
    private boolean ocupado;

    public Casilla(int nuevoIndice) {
        fichaAsignada = null;
        indice = nuevoIndice;
        modificadorPuntos = 0;
        ocupado = false;
        label = "";
        valido = false;
    }

    @Override
    public int compareTo(Casilla otraCasilla) {
        final int ANTES = -1;
        final int DESPUES = 1;

        if (indice > otraCasilla.indice) {
            return DESPUES;
        } else {
            return ANTES;
        }
    }

    public int getIndice() {
        return indice;
    }

    public void setOcupado(boolean isOcupado) {
        ocupado = isOcupado;
    }

    public boolean getOcupado() {
        return ocupado;
    }

    public void setModificadorPuntuacion(int nuevoModificador) {
        modificadorPuntos = nuevoModificador;
    }
    public int getModificadorPuntos() {
        return modificadorPuntos;
    }

    public void eliminarFichaAsignada() {
        ocupado = false;
        fichaAsignada = null;
    }

    public boolean getValido() {
        return valido;
    }

    public void setValidacion(boolean nuevaValidacion) {
        valido = nuevaValidacion;
    }

    public void setAssignedTile(Ficha nuevaFicha) {
        fichaAsignada = nuevaFicha;
        ocupado = true;
    }

    public Ficha getFichaAsignada() {
        return fichaAsignada;
    }

    public void setLabel(String nuevoLabel) {
        label = nuevoLabel;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        String returnString;
        returnString = "Esta casilla tiene esta ficha asignada: ";

        if (fichaAsignada != null) {
            returnString = returnString + fichaAsignada.toString() + "";
        } else {
            returnString = returnString + "ninguna";
        }
        return returnString;
    }
}
