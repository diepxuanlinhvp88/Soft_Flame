package data.api;
//
//import javax.sound.sampled.AudioSystem;
//import javax.sound.sampled.AudioInputStream;
//import javax.sound.sampled.Clip;
//import javax.sound.sampled.LineEvent;
//import java.io.*;
//import java.net.URI;
//import java.net.URLEncoder;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//
//
//public class textToSpeech {
//    private final String ApiKey="6d8410dc85e148719230206b80501689";
//
//    private int rate=3;
//    private static float volume;
//
//    public static float getVolume() {
//        return volume;
//    }
//
//    public static void setVolume(float volume) {
//        textToSpeech.volume = volume;
//    }
//
//    public void Speak(String text ,String voice){
//        try {
//            String baseUrl ="https://api.voicerss.org/?"
//                    +"key=" +ApiKey
//                    + "&hl="+ URLEncoder.encode(voice, "UTF-8")
//                    +"&src="+ URLEncoder.encode(text, "UTF-8");
//            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(baseUrl)).build();
//
//            HttpClient client =HttpClient.newHttpClient();
//            HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
//            if (response.statusCode() == 200) {
//                byte[] audioFile = response.body();
//                ByteArrayInputStream inAudio = new ByteArrayInputStream(audioFile);
//                AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(inAudio);
//                Clip clip = AudioSystem.getClip();
//                clip.open(audioInputStream);
//                clip.start();
//                Thread.sleep(clip.getMicrosecondLength()/1000);
//
//            } else {
//                System.out.println("khong thanh cong");
//
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            System.out.println("erro: " + e.getMessage());
//        }
//    }
//    public static void  main(String args[]){
//        textToSpeech tmp = new textToSpeech();
//        tmp.Speak("hello", "en-us");
//
//    }
//
//}

import domain.api.ITextToSpeech;

import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.EngineStateError;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Voice;
import java.beans.PropertyVetoException;
import java.util.Locale;

public class TextToSpeech implements ITextToSpeech {


    SynthesizerModeDesc desc;
    Synthesizer synthesizer;
    Voice voice;


    public void init(String voiceName)
            throws EngineException, AudioException, EngineStateError,
            PropertyVetoException {
        if (desc == null) {

            System.setProperty("freetts.voices",
                    "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

            desc = new SynthesizerModeDesc(Locale.US);
            Central.registerEngineCentral
                    ("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
            synthesizer = Central.createSynthesizer(desc);
            synthesizer.allocate();
            synthesizer.resume();
            SynthesizerModeDesc smd =
                    (SynthesizerModeDesc) synthesizer.getEngineModeDesc();
            Voice[] voices = smd.getVoices();
            Voice voice = null;
            for (int i = 0; i < voices.length; i++) {
                if (voices[i].getName().equals(voiceName)) {
                    voice = voices[i];
                    break;
                }
                System.out.println(voices[i].getName());
            }
            synthesizer.getSynthesizerProperties().setVoice(voice);
        }

    }   

    public void terminate() throws EngineException, EngineStateError {
        synthesizer.deallocate();
    }

    public void doSpeak(String speakText)
            throws EngineException, AudioException, IllegalArgumentException,
            InterruptedException {
        synthesizer.speakPlainText(speakText, null);
        synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);

    }
}
