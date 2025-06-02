package scrabble;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.FloatControl;

public class ReproductorAudio {
    // Instancia única para el patrón Singleton
    private static ReproductorAudio instancia;
    private Clip audioClip;
    // Guarda la posición actual para permitir la reanudación
    private int pausaFramePosition = 0;

    // Constructor privado para que solo se pueda instanciar internamente
    private ReproductorAudio(String rutaAudio) {
        iniciarAudio(rutaAudio);
    }

    // Método para obtener la instancia global del reproductor
    public static ReproductorAudio getInstancia(String rutaAudio) {
        if (instancia == null) {
            instancia = new ReproductorAudio(rutaAudio);
        }
        return instancia;
    }

    // Inicializa el audio y lo configura para reproducirse en bucle
    private void iniciarAudio(String rutaAudio) {
        try {
            File archivo = new File(rutaAudio);
            if (!archivo.exists()) {
                System.err.println("El archivo no existe: " + rutaAudio);
                return;
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(archivo);
            audioClip = AudioSystem.getClip();
            audioClip.open(audioStream);
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
            audioClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Pausa la reproducción del audio guardando la posición actual
    public void pausar() {
        if (audioClip != null && audioClip.isRunning()) {
            pausaFramePosition = audioClip.getFramePosition();
            audioClip.stop();
        }
    }

    // Reanuda el audio desde la posición en la que se pausó
    public void reanudar() {
        if (audioClip != null && !audioClip.isRunning()) {
            audioClip.setFramePosition(pausaFramePosition);
            audioClip.start();
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    // Ajusta el volumen del audio, donde 'volumen' es un valor entre 0.0 (mudo) y 1.0 (máximo)
    public void ajustarVolumen(double volumen) {
        if (audioClip != null) {
            // Obtenemos el control del volumen basado en la ganancia
            FloatControl controlVolumen = (FloatControl) audioClip.getControl(FloatControl.Type.MASTER_GAIN);
            if (volumen == 0) {
                controlVolumen.setValue(controlVolumen.getMinimum());
            } else {
                // Convertimos el valor (0.0 - 1.0) a decibelios
                float dB = (float) (20 * Math.log10(volumen));
                // Aseguramos que el valor esté dentro de los límites permitidos
                dB = Math.max(dB, controlVolumen.getMinimum());
                dB = Math.min(dB, controlVolumen.getMaximum());
                controlVolumen.setValue(dB);
            }
            audioClip.flush();
        }
    }

    // Detiene el audio y libera los recursos asociados
    public void detener() {
        if (audioClip != null) {
            audioClip.stop();
            audioClip.close();
        }
    }
}
