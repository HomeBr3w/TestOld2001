package notefinder.pc;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Mp3Player extends Thread {

    private File file;
    private Player player;
    private int seek = 0;

    public void loadFile(File f) {
        this.Stop();

        FileInputStream fis = null;
        try {
            saveUrl("bestand.mp3", "http://jasperwestra.nl/post.php");
            this.file = new File("bestand.mp3");
            fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            try {
                Player player = new Player(bis);
            } catch (JavaLayerException ex) {

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Programma.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(Programma.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(Programma.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void Play() {
        try {
            if (seek != 0)
            {
                player.play(seek);
            }
            else
            {
                player.play();
            }
        } catch (JavaLayerException ex) {
            Logger.getLogger(Mp3Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Pause() {
        seek = player.getPosition();
        player.close();
    }

    public void Stop() {
        seek = 0;
        player.close();
    }

    public void saveUrl(final String filename, final String urlString)
            throws MalformedURLException, IOException {
        BufferedInputStream in = null;
        FileOutputStream fout = null;
        try {
            in = new BufferedInputStream(new URL(urlString).openStream());
            fout = new FileOutputStream(filename);

            final byte data[] = new byte[1024];
            int count;
            while ((count = in.read(data, 0, 1024)) != -1) {
                fout.write(data, 0, count);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (fout != null) {
                fout.close();
            }
        }
    }
}
