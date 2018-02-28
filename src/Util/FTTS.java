
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;


import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

/**
 *
 * @author feriel
 */

public class FTTS {

    private static final String VOICENAME_kevin = "kevin";
    private String text; // string to speech

    public FTTS(String text) {
        this.text = text;
    }

    public void speak() {
        Voice voice;
        VoiceManager voiceManager = VoiceManager.getInstance();
        voice = voiceManager.getVoice(VOICENAME_kevin);
        voice.allocate();
        voice.speak(text);
    }

    public static void main(String[] args) {
        String text = "FreeTTS was written by the Sun Microsystems Laboratories "
                + "Speech Team and is based on CMU's Flite engine.";
        FTTS freeTTS = new FTTS(text);
        freeTTS.speak();
    }

}