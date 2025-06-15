package Back;

import Main.ScrabbleMain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Tablero implements Serializable {

    public static final int TPMP = 4;
    public static final int TLMP = 3;
    public static final int DPMP = 2;
    public static final int DLMP = 1;

    private ScrabbleMain scrabbleMain;
    private static final int[] TPVP = {0, 7, 14, 105, 119, 210, 217, 224};
    private static final int[] DLVP = {3, 11, 36, 38, 45, 52, 59, 92, 96, 98, 102, 108, 116, 122, 126, 128, 132, 165, 172, 179, 186, 188, 213, 221};
    private static final int[] DPVP = {16, 28, 32, 42, 48, 56, 64, 70, 154, 160, 168, 176, 182, 192, 196, 208};
    private static final int[] TLVP = {20, 24, 76, 80, 84, 88, 136, 140, 144, 148, 200, 204};
    private static final int NUMEROCASILLAS = 225;
    private ArrayList<Integer> fichasJugadasEsteTurno;
    
    public ArrayList<Casilla> casillas;

    public Tablero() {
        casillas = new ArrayList<>();
        fichasJugadasEsteTurno = new ArrayList<>();

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
        
        ArrayList<Integer> bordesIzquierdos = new ArrayList<>();
        Collections.addAll(bordesIzquierdos, 0, 15, 30, 45, 60, 75, 90, 105, 120, 135, 150, 165, 180, 195, 210, 225);
        
        ArrayList<Integer> bordesDerechos = new ArrayList<>();
        Collections.addAll(bordesDerechos, 14, 29, 44, 59, 74, 89, 104, 119, 134, 149, 164, 179, 194, 209, 224);
        
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
            if(1 == fichasJugadasEsteTurno.size()){ 
                fichasJugadasEsteTurno.remove(fichasJugadasEsteTurno.size()-1);
            }
            for (Casilla casilla : casillas) {
                if (casilla.getOcupado()) {
                    if(casilla.getIndice() > 0){
                        if (!casillas.get(casilla.getIndice() - 1).getOcupado() && !bordesDerechos.contains((casilla.getIndice() - 1))) {
                            casillas.get(casilla.getIndice() - 1).setValidacion(true);
                        }
                    }
                    
                    if(casilla.getIndice() - 15 >= 0){
                        if (!casillas.get(casilla.getIndice() - 15).getOcupado()) {
                            casillas.get(casilla.getIndice() - 15).setValidacion(true);
                        }
                    }
                    
                    if(casilla.getIndice() < 224){
                        if (!casillas.get(casilla.getIndice() + 1).getOcupado() && !bordesIzquierdos.contains((casilla.getIndice() + 1))) {
                            casillas.get(casilla.getIndice() + 1).setValidacion(true);
                        }
                    }
                    
                    
                    if(casilla.getIndice() + 15 <= 225){
                        if (!casillas.get(casilla.getIndice() + 15).getOcupado()) {
                            casillas.get(casilla.getIndice() + 15).setValidacion(true);
                        }
                    }
                }
            }
        }

        if (estadoJuego.getCasillasJugadas().size() == 1) {
            Casilla primeraCasilla = estadoJuego.getCasillasJugadas().get(0);
            
            if(2 == fichasJugadasEsteTurno.size()){ 
                fichasJugadasEsteTurno.remove(fichasJugadasEsteTurno.size()-1); 
            }
            
            if(!fichasJugadasEsteTurno.contains(primeraCasilla.getIndice())){
                fichasJugadasEsteTurno.add(primeraCasilla.getIndice());   
            }
            
            if((primeraCasilla.getIndice()) != 0){
                    if (!casillas.get(primeraCasilla.getIndice() - 1).getOcupado()&& 
                                !bordesDerechos.contains((primeraCasilla.getIndice() - 1))) 
                    {
                        if(!bordesDerechos.contains((primeraCasilla.getIndice() - 1))){
                            casillas.get(primeraCasilla.getIndice() - 1).setValidacion(true);
                        }
                    } else {
                        while (casillas.get(primeraCasilla.getIndice() - 1).getOcupado() && 
                                !bordesDerechos.contains(primeraCasilla.getIndice() - 1 )) {
                            primeraCasilla = casillas.get((primeraCasilla.getIndice() - 1));
                        }
                        if(!bordesDerechos.contains((primeraCasilla.getIndice() - 1))){
                            casillas.get(primeraCasilla.getIndice() - 1).setValidacion(true);
                        }
                        primeraCasilla = estadoJuego.getCasillasJugadas().get(0);
                    }
            }
            
            if(primeraCasilla.getIndice() != 224){
                if (!casillas.get(primeraCasilla.getIndice() + 1).getOcupado()&& 
                            !bordesIzquierdos.contains((primeraCasilla.getIndice()+ 1))) {

                    if(!bordesIzquierdos.contains((primeraCasilla.getIndice()+ 1))){
                        casillas.get(primeraCasilla.getIndice() + 1).setValidacion(true);
                    }
                } else {
                    while (casillas.get(primeraCasilla.getIndice() + 1).getOcupado() && 
                            !bordesIzquierdos.contains(primeraCasilla.getIndice() + 1 )) {
                        primeraCasilla = casillas.get((primeraCasilla.getIndice()+ 1));
                    }
                    if(!bordesIzquierdos.contains((primeraCasilla.getIndice()+ 1))){
                        casillas.get(primeraCasilla.getIndice() + 1).setValidacion(true);
                    }
                    primeraCasilla = estadoJuego.getCasillasJugadas().get(0);
                }
            }
            
            int contadorArriba = 1;
            int limiteArriba = primeraCasilla.getIndice() / 15;
            for(int i = 1; i < limiteArriba; i++){
                if(casillas.get(primeraCasilla.getIndice() - 15*contadorArriba).getOcupado()){
                    contadorArriba++;
                }
            }
            if(limiteArriba > 0){
                    casillas.get(primeraCasilla.getIndice() - 15*contadorArriba).setValidacion(true);
            }
            
            
            int contadorAbajo = 1;
            int limiteAbajo = (225 - primeraCasilla.getIndice()) / 15;
            for(int i = 1; i < limiteAbajo; i++){
                if(casillas.get(primeraCasilla.getIndice() + 15*contadorAbajo).getOcupado()){
                    contadorAbajo ++;
                }
            }
            if(limiteAbajo > 0){
                    casillas.get(primeraCasilla.getIndice() + 15*contadorAbajo).setValidacion(true);
            }

            if(primeraCasilla.getIndice() + 15 <= 225 && primeraCasilla.getIndice() - 15 >= 0 && primeraCasilla.getIndice() + 1 <= 225 && primeraCasilla.getIndice() - 1 >= 0){
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
            
            
        }

        if (estadoJuego.getCasillasJugadas().size() >= 2) {
            int ultimoIndice = estadoJuego.getCasillasJugadas().size() - 1;
            ArrayList<Casilla> casillasJugadas = estadoJuego.getCasillasJugadas();
            
            if(ultimoIndice+1 < fichasJugadasEsteTurno.size()){ 
                fichasJugadasEsteTurno.remove(fichasJugadasEsteTurno.size()-1);
            }
            
            if(!fichasJugadasEsteTurno.contains(casillasJugadas.get(ultimoIndice).getIndice())){
                fichasJugadasEsteTurno.add(casillasJugadas.get(ultimoIndice).getIndice());  
            }
            
            for (Casilla casilla : casillas) {
                casilla.setValidacion(false);
                if(casilla.getIndice() == fichasJugadasEsteTurno.get(fichasJugadasEsteTurno.size() - 1)){
                    casilla.setValidacion(true);
                }
            }
            
            Collections.sort(casillasJugadas);
            int difference = Math.abs(casillasJugadas.get(0).getIndice() - casillasJugadas.get(1).getIndice());
         
            
            if (difference >= 15) {
                estadoJuego.setOrientacion(EstadoJuego.ABAJO);
                
                int contadorArriba = 1;
                int limiteArriba = casillasJugadas.get(0).getIndice() / 15;
                for(int i = 1; i < limiteArriba; i++){
                    if(casillas.get(casillasJugadas.get(0).getIndice() - (15*contadorArriba)).getOcupado()){
                        contadorArriba++;
                    }
                }
                if(limiteArriba > 0){
                    casillas.get(casillasJugadas.get(0).getIndice() - (15*(contadorArriba))).setValidacion(true);
                }
                
                int contadorAbajo = 1;
                int limiteAbajo = (225 - casillasJugadas.get(0).getIndice()) / 15;
                for(int i = 1; i < limiteAbajo; i++){
                    if(casillas.get(casillasJugadas.get(0).getIndice() + (15*contadorAbajo)).getOcupado()){
                        contadorAbajo++;
                    }
                }
                if(limiteAbajo > 0){
                    casillas.get(casillasJugadas.get(0).getIndice() + (15*(contadorAbajo))).setValidacion(true);
                }
            }
            
            if (difference < 15) {
                estadoJuego.setOrientacion(EstadoJuego.OTRO_LADO);
                
                int contadorIzquierda = 1;
                if(casillasJugadas.get(0).getIndice() - (1*contadorIzquierda) >= 0){
                    while (
                            casillas.get(casillasJugadas.get(0).getIndice() - (1*contadorIzquierda)).getOcupado() && 
                            !bordesDerechos.contains(casillasJugadas.get(0).getIndice() - (1*contadorIzquierda))){
                        contadorIzquierda++;
                    }
                    if(!bordesDerechos.contains(casillasJugadas.get(0).getIndice() - (1*contadorIzquierda))){
                        casillas.get(casillasJugadas.get(0).getIndice() - 1*contadorIzquierda).setValidacion(true);
                    }
                }
              
                int contadorDerecha = 1;
                while (
                        casillas.get(casillasJugadas.get(0).getIndice() + (1*contadorDerecha)).getOcupado() && 
                        !bordesIzquierdos.contains(casillasJugadas.get(0).getIndice() + (1*contadorDerecha))){
                    if((casillasJugadas.get(0).getIndice() + (1*contadorDerecha))== 224){
                        break;
                    }else{
                        contadorDerecha++;
                    }
                }
                if(!bordesIzquierdos.contains(casillasJugadas.get(0).getIndice() + (1*contadorDerecha))){
                    casillas.get(casillasJugadas.get(0).getIndice() + 1*contadorDerecha).setValidacion(true);
                }
            }
        }

        for (Casilla casilla : casillas) {
            if (casilla.getValido()) {
                casillasValidas.add(casilla);
            }
        }      
        return casillasValidas;
    }
    
    public void restablecerFichasJugadasEsteTurno() {
        fichasJugadasEsteTurno.clear();
    }
    
    public Integer getFichasJugadasEsteTurno() {
        if(!fichasJugadasEsteTurno.isEmpty()){
            return fichasJugadasEsteTurno.get(fichasJugadasEsteTurno.size()-1);
        } else{
           return 0; 
        }
    }
}