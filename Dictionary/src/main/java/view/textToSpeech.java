
/**
 * Viết lỗi bỏ, dùng cái trên controller .
 */
//package view;
//
//import javax.speech.AudioException;
//import javax.speech.Central;
//import javax.speech.EngineException;
//import javax.speech.EngineStateError;
//import javax.speech.synthesis.Synthesizer;
//import javax.speech.synthesis.SynthesizerModeDesc;
//import javax.speech.synthesis.Voice;
//import java.beans.PropertyVetoException;
//import java.util.Locale;
//
//public class textToSpeech {
//
//    SynthesizerModeDesc desc;
//    Synthesizer synthesizer;
//    Voice voice;
//
//
//    public void init(String voiceName)
//            throws EngineException, AudioException, EngineStateError,
//            PropertyVetoException {
//        if (desc == null) {
//
//            System.setProperty("freetts.voices",
//                    "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
//
//            desc = new SynthesizerModeDesc(Locale.US);
//            Central.registerEngineCentral
//                    ("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
//            synthesizer = Central.createSynthesizer(desc);
//            synthesizer.allocate();
//            synthesizer.resume();
//            SynthesizerModeDesc smd =
//                    (SynthesizerModeDesc) synthesizer.getEngineModeDesc();
//            Voice[] voices = smd.getVoices();
//            Voice voice = null;
//            for (int i = 0; i < voices.length; i++) {
//                if (voices[i].getName().equals(voiceName)) {
//                    voice = voices[i];
//                    break;
//                }
//            }
//            synthesizer.getSynthesizerProperties().setVoice(voice);
//        }
//
//    }
//
//    public void terminate() throws EngineException, EngineStateError {
//        synthesizer.deallocate();
//    }
//
//    public void doSpeak(String speakText)
//            throws EngineException, AudioException, IllegalArgumentException,
//            InterruptedException {
//        synthesizer.speakPlainText(speakText, null);
//        synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
//
//    }
//    public static void main(String[] args) {
//        textToSpeech tts = new textToSpeech();
//
//        try {
//            // Khởi tạo textToSpeech với giọng nói mong muốn (ví dụ: "kevin16")
//            tts.init("kevin16");
//
//            // Chuỗi bạn muốn phát âm
//            String textToSpeak = "headache";
//
//            // Gọi hàm doSpeak để phát âm chuỗi
//            tts.doSpeak(textToSpeak);
//
//            // Kết thúc và giải phóng nguồn tài nguyên
//            tts.terminate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//}
