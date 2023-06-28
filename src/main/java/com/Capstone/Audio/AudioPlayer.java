package com.Capstone.Audio;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;

public class AudioPlayer {
    public static void play(byte[] audioData) {
        try {
            // Create an AudioInputStream from the byte array
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new ByteArrayInputStream(audioData));

            // Get the audio format
            AudioFormat format = audioInputStream.getFormat();

            // Create a DataLine.Info object for the SourceDataLine
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

            // Open the SourceDataLine
            SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(format);

            // Start playing the audio
            line.start();

            // Create a buffer to read data from the AudioInputStream
            byte[] buffer = new byte[4096];
            int bytesRead;

            // Read data from the AudioInputStream and write it to the SourceDataLine
            while ((bytesRead = audioInputStream.read(buffer)) != -1) {
                line.write(buffer, 0, bytesRead);
            }

            // Close the SourceDataLine
            line.drain();
            line.close();

            // Close the AudioInputStream
            audioInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
