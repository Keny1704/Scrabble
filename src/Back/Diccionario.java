package Back;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Diccionario {

    final private ArrayList<String> diccionario;

    public Diccionario() throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(getClass().getResource("diccionario.txt").getFile()))) {
            diccionario = new ArrayList<>();
            while (scanner.hasNext()) {
                diccionario.add(scanner.next());
            }
        }
    }

    public boolean verificar(String palabra) {
        return diccionario.stream().anyMatch((palabraActual) -> (palabra.equalsIgnoreCase(palabraActual)));
    }
    
    public int size() {
        return diccionario.size();
    }
    
    public String get(int indice) {
        return diccionario.get(indice);
    }
}
