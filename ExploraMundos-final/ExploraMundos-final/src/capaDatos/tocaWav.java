package capaDatos;

//Notese que a pesar del disclaimer en el inicio
//Este es el UNICO Clase que fue copiada de internet

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class tocaWav extends Thread {

	private String filename;
    private int mufondo = 0;
	private Position curPosition;

	private final int EXTERNAL_BUFFER_SIZE = 524288; // 128Kb

	enum Position {
		LEFT, RIGHT, NORMAL
	};

	public tocaWav(String wavfile) {
		filename = wavfile;
		curPosition = Position.NORMAL;
	}

    public tocaWav(String wavfile, int mufondo) {
        filename = wavfile;
		curPosition = Position.NORMAL;
        this.mufondo = mufondo;
	}

	public tocaWav(String wavfile, Position p) {
		filename = wavfile;
		curPosition = p;
	}

    @Override
	public void run() {

		File soundFile = new File(filename);
		if (!soundFile.exists()) {
			System.err.println("Wav no se encuentra: " + filename);
			return;
		}

		AudioInputStream audioInputStream = null;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(soundFile);
		} catch (UnsupportedAudioFileException e1) {
			e1.printStackTrace();
			return;
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}

		AudioFormat format = audioInputStream.getFormat();
		SourceDataLine auline = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

		try {
			auline = (SourceDataLine) AudioSystem.getLine(info);
			auline.open(format);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
			return;
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		if (auline.isControlSupported(FloatControl.Type.PAN)) {
			FloatControl pan = (FloatControl) auline
					.getControl(FloatControl.Type.PAN);
			if (curPosition == Position.RIGHT)
				pan.setValue(1.0f);
			else if (curPosition == Position.LEFT)
				pan.setValue(-1.0f);
		}
    
        if (mufondo == 1){
          // Adjust the volume on the output line.
           if(auline.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
             FloatControl volume = (FloatControl) auline.getControl(FloatControl.Type.MASTER_GAIN);
             volume.setValue(-15.0F);
           }

         }

		auline.start();
		int nBytesRead = 0;
		byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];

		try {
			while (nBytesRead != -1) {
				nBytesRead = audioInputStream.read(abData, 0, abData.length);
				if (nBytesRead >= 0)
					auline.write(abData, 0, nBytesRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} finally {
			auline.drain();
			auline.close();
		}

	}
}
