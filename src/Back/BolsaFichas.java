package Back;

import java.util.ArrayList;
import java.util.Random;

public class BolsaFichas {
    public ArrayList<Ficha> listaFichas;
    final private ArrayList<Ficha> grupoLetras;
    public BolsaFichas() {

        Ficha A = new Ficha("A", 1);
        Ficha B = new Ficha("B", 3);
        Ficha C = new Ficha("C", 3);
        Ficha CH = new Ficha("CH", 5);
        Ficha D = new Ficha("D", 2);
        Ficha E = new Ficha("E", 1);
        Ficha F = new Ficha("F", 4);
        Ficha G = new Ficha("G", 2);
        Ficha H = new Ficha("H", 4);
        Ficha I = new Ficha("I", 1);
        Ficha J = new Ficha("J", 8);
        Ficha L = new Ficha("L", 1);
        Ficha LL = new Ficha("LL", 8);
        Ficha M = new Ficha("M", 3);
        Ficha N = new Ficha("N", 1);
        Ficha Ñ = new Ficha("Ñ", 8);
        Ficha O = new Ficha("O", 1);
        Ficha P = new Ficha("P", 3);
        Ficha Q = new Ficha("Q", 5);
        Ficha R = new Ficha("R", 1);
        Ficha RR = new Ficha("RR", 8);
        Ficha S = new Ficha("S", 1);
        Ficha T = new Ficha("T", 1);
        Ficha U = new Ficha("U", 1);
        Ficha V = new Ficha("V", 4);
        Ficha X = new Ficha("X", 8);
        Ficha Y = new Ficha("Y", 4);
        Ficha Z = new Ficha("Z", 10);
        Ficha comodin = new Ficha("?", 0);
        
        listaFichas = new ArrayList<>();
        listaFichas.add(A);
        listaFichas.add(B);
        listaFichas.add(C);
        listaFichas.add(CH);
        listaFichas.add(D);
        listaFichas.add(E);
        listaFichas.add(F);
        listaFichas.add(G);
        listaFichas.add(H);
        listaFichas.add(I);
        listaFichas.add(J);
        listaFichas.add(L);
        listaFichas.add(LL);
        listaFichas.add(M);
        listaFichas.add(N);
        listaFichas.add(Ñ);
        listaFichas.add(O);
        listaFichas.add(P);
        listaFichas.add(Q);
        listaFichas.add(R);
        listaFichas.add(RR);
        listaFichas.add(S);
        listaFichas.add(T);
        listaFichas.add(U);
        listaFichas.add(V);
        listaFichas.add(X);
        listaFichas.add(Y);
        listaFichas.add(Z);
        listaFichas.add(comodin);

        grupoLetras = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            grupoLetras.add(A);
            grupoLetras.add(E);
        }

        for (int i = 0; i < 9; i++) {
            grupoLetras.add(O);
        }

        for (int i = 0; i < 6; i++) {
            grupoLetras.add(I);
            grupoLetras.add(S);
        }

        for (int i = 0; i < 5; i++) {
            grupoLetras.add(D);
            grupoLetras.add(N);
            grupoLetras.add(R);
            grupoLetras.add(U);
        }
        
        for (int i = 0; i < 4; i++) {            
            grupoLetras.add(C);
            grupoLetras.add(L);
            grupoLetras.add(T);
        }

        for (int i = 0; i < 2; i++) {
            grupoLetras.add(B);
            grupoLetras.add(G);
            grupoLetras.add(H);
            grupoLetras.add(M);
            grupoLetras.add(P);
            grupoLetras.add(comodin);
        }
        
        grupoLetras.add(CH);
        grupoLetras.add(F);
        grupoLetras.add(J);
        grupoLetras.add(LL);
        grupoLetras.add(Ñ);
        grupoLetras.add(Q);
        grupoLetras.add(RR);
        grupoLetras.add(V);
        grupoLetras.add(X);
        grupoLetras.add(Y);
        grupoLetras.add(Z);
    }

    public int tamaño() {
        return grupoLetras.size();
    }

    public Ficha ponerCasilla() {
        int max = grupoLetras.size() - 1;
        Random random = new Random();
        int casillaRandom = random.nextInt(max + 1);

        return grupoLetras.remove(casillaRandom);
    }

    public int getLetraAnotada(String letra) {
        int puntos = 0;
        for (Ficha listaCasilla1 : listaFichas) {
            if (listaCasilla1.getLetra().equals(letra)) {
                puntos = listaCasilla1.getPuntos();
                break;
            }
        }
        return puntos;
    }
}
