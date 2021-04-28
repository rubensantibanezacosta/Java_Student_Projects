/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 *
 * @author ruben
 */
public class Reproductor {

    Player player;

    public Reproductor() throws FileNotFoundException, JavaLayerException {
        while (true) {
            this.player = new Player(new FileInputStream(
                    "musica/Mountkid-Dino-_NCS-Release_-_152kbit_Opus_.mp3"));

                    player.play();
        }
    }

}
