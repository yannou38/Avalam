/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.Core.Globals;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author TheDoctor
 */
public class SoundEngine {

    private static Mixer mixer;
    private static Clip clip;
    private static long clipTime;
    private static boolean isMute;

    public static void init() {
        Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();
        SoundEngine.mixer = AudioSystem.getMixer(mixInfos[0]);
        SoundEngine.isMute = false;
        SoundEngine.clipTime = 0;
        DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);
        try {
            SoundEngine.clip = (Clip) SoundEngine.mixer.getLine(dataInfo);
        } catch (LineUnavailableException ex) {
            System.out.println("Error - " + SoundEngine.class.toString());
            Logger.getLogger(SoundEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        SoundEngine.play("./ressources/sounds/game_ambient2.wav");
        if (SetupManager.getElement("Son").equals("Non")) {
            SoundEngine.toggleMute();
        }
    }

    public static void play(String filePath) {
        if (!SoundEngine.clip.isActive()) {
            try {
                File soundFile = new File(filePath);
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
                SoundEngine.clip.open(audioStream);
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                System.out.println("Error - " + SoundEngine.class.toString());
                Logger.getLogger(SoundEngine.class.getName()).log(Level.SEVERE, null, ex);
            }
            SoundEngine.clip.loop(Clip.LOOP_CONTINUOUSLY);
            SoundEngine.clip.start();
        }
    }

    public static void stop() {
        SoundEngine.clip.stop();
    }

    public static void toggleMute() {
        SoundEngine.clipTime = SoundEngine.clip.getMicrosecondPosition();
        SoundEngine.clip.stop();
        Line[] lines = SoundEngine.mixer.getSourceLines();
        for (Line line : lines) {
            BooleanControl bc = (BooleanControl) line.getControl(BooleanControl.Type.MUTE);
            if (bc != null) {
                if (!SoundEngine.isMute) {
                    bc.setValue(true); // true to mute the line, false to unmute
                    SoundEngine.isMute = true;
                    SetupManager.setElement("Son", "Non");
                } else {
                    bc.setValue(false);
                    SoundEngine.isMute = false;
                    SetupManager.setElement("Son", "Oui");
                }
            }
        }
        SoundEngine.clip.setMicrosecondPosition(SoundEngine.clipTime);
        SoundEngine.clip.loop(Clip.LOOP_CONTINUOUSLY);
        SoundEngine.clip.start();

    }

    public static boolean isMuted() {
        return SoundEngine.isMute;
    }
}
