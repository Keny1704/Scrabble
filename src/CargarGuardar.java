import Back.Ficha;
import Back.Jugador;
import Back.Tablero;
import Main.ScrabbleMain;
import Front.VentanaTablero; // Asegúrate de que tus clases estén en este paquete
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

// Clase utilitaria para Guardar/Cargar partida
public class CargarGuardar {

    public static void guardarPartida(String nombreArchivo, Tablero tablero, List<Jugador> jugadores, List<Ficha> bolsa, int turnoActual) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            oos.writeObject(tablero);
            oos.writeObject(jugadores);
            oos.writeObject(bolsa);
            oos.writeInt(turnoActual);
            System.out.println("Partida guardada exitosamente en: " + nombreArchivo);
        } catch (IOException e) {
        }
    }

    public static Object[] cargarPartida(String nombreArchivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            Tablero tablero = (Tablero) ois.readObject();
            List<Jugador> jugadores = (List<Jugador>) ois.readObject();
            List<Ficha> bolsa = (List<Ficha>) ois.readObject();
            int turnoActual = ois.readInt();
            System.out.println("Partida cargada exitosamente desde: " + nombreArchivo);
            return new Object[]{tablero, jugadores, bolsa, turnoActual};
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
}

// Ejemplo de uso en ScrabbleMain.java:
// Guardar partida:
// GestorPartida.guardarPartida("partidaGuardada.dat", tablero, jugadores, bolsaFichas, turnoActual);

// Cargar partida:
// Object[] datos = GestorPartida.cargarPartida("partidaGuardada.dat");
// if (datos != null) {
//     tablero = (Tablero) datos[0];
//     jugadores = (List<Jugador>) datos[1];
//     bolsaFichas = (List<Ficha>) datos[2];
//     turnoActual = (int) datos[3];
//     ventanaTablero.actualizarVista(tablero, jugadores); // método hipotético
// }